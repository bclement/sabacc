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
}

type TableRequest struct {
	TypeName string
	TableName string
	ResultChan chan Tables
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
	if ( err != nil ){
		return nil, err
	}
	if (len(tables.Tables) < 1){
		t := new(Table)
		populateTable(t, "table0")
		key := datastore.NewKey(c, "Table", "table0", 0, nil)
		_, err =datastore.Put(c, key, t)
		tables.Tables = append(tables.Tables, *t)
	}
	return tables, err
}

func joinTable(c appengine.Context, tableName string, name string) os.Error{
	key := datastore.NewKey(c, "Table", tableName, 0, nil)
	var table Table
	err := datastore.Get(c, key, &table)
	if err != nil{
		return err
	}
	var ss sort.StringSlice = table.Players
	ss.Sort()
	i := ss.Search(name)
	if i < 0 {
		table.Players = append(table.Players, name)
	}
	_,err = datastore.Put(c, key, &table)
	if err != nil{
		return err
	}
	return nil
}
