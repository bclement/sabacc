package sabacc

import (
	"os"
	"sort"
	"appengine"
	"appengine/datastore"
)

type Tables struct {
	TypeName string `json:"type"`
	Tables []Table  `json:"tables"`
}

type Table struct {
	Name string `json:"name"`
	Players []string `json:"players"`
	Status string `json:"status"`
	round int
	phase string
	handpot int
	sabaccpot int
}

type Player struct {
	Name string `json:"name"`
	Credits int `json:"credits"`
	Bet int `json:"bet"`
	LastMove string `json:"lastMove"`
	HandCards []string `json:"handCards"`
	LockCards []string `json:"lockCards"`
	Winner bool `json:"winner"`
	OnAction bool `json:"onAction"`
	Folded bool `json:"folded"`
	discards []string
}

func populateTable(t *Table, name string){
	t.Name = name
	t.Status = "waiting"
}

func getTables(c appengine.Context) (*Tables, os.Error){
	q := datastore.NewQuery("Table")
	tables := new(Tables)
	tables.TypeName = "tables"
	tables.Tables = make([]Table, 0)
	_, err := q.GetAll(c, &tables.Tables)
	if err != nil {
		return nil, err
	}
	if len(tables.Tables) < 1{
		t := new(Table)
		populateTable(t, "table0")
		key := datastore.NewKey(c, "Table", "table0", 0, nil)
		_, err =datastore.Put(c, key, t)
		tables.Tables = append(tables.Tables, *t)
	}
	return tables, err
}

func joinTable(c appengine.Context, tableName string, name string) (*State, os.Error) {
	var player Player
	pkey := datastore.NewKey(c, "Player", name + tableName, 0, nil)
	// check if player is at table
	err := datastore.Get(c, pkey, &player)
	if err != nil && err == datastore.ErrNoSuchEntity {
		// create player
		player.Name = name
		_, err = datastore.Put(c, pkey, &player)
		if err != nil {
			return nil, err
		}
	} else if err != nil && err != datastore.ErrNoSuchEntity {
		return nil, err
	}
	var table Table
	// TODO remove player if table update fails
	// add player func needs to handle dups
	err = addPlayerToTable(c, tableName, &table, name)
	if err != nil {
		return nil, err
	}
	return GetState(c, &table, name, false)
}

func addPlayerToTable(c appengine.Context, tableName string, table *Table, name string) os.Error {
	key := datastore.NewKey(c, "Table", tableName, 0, nil)
	return datastore.RunInTransaction(c, func( c appengine.Context) os.Error{
		err := datastore.Get(c, key, table)
		if err != nil{
			return err
		}

		var ss sort.StringSlice = table.Players
		ss.Sort()
		i := ss.Search(name)
		if i >= len(ss) {
			table.Players = append(table.Players, name)
		}
		_, err = datastore.Put(c, key, table)
		return err
	}, nil )
}
