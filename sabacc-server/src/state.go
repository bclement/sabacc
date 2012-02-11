package sabacc

import (
	"os"
	"appengine"
	"appengine/datastore"
)

type State struct {
	TypeName string `json:"type"`
	Table string `json:"table"`
	Round int `json:"round"`
	Phase string `json:"phase"`
	HandPot int `json:"handPot"`
	SabaccPot int `json:"sabaccPot"`
	Players []Player `json:"players"`
}

func GetState(c appengine.Context, table *Table, name string, show bool) (*State, os.Error) {
	rval := new(State)
	rval.TypeName = "state"
	rval.Table = table.Name
	rval.Round = table.round
	rval.Phase = table.phase
	rval.HandPot = table.handpot
	rval.SabaccPot = table.sabaccpot
	rval.Players = make([]Player, len(table.Players))
	for i := range table.Players {
		pname := &table.Players[i]
		pkey := datastore.NewKey(c, "Player", *pname + table.Name, 0, nil)
		player := &rval.Players[i]
		err := datastore.Get(c, pkey, player)
		if err != nil {
			// TODO recover?
			return nil, err
		}
		if *pname != name && ( !show || player.Folded) {
			// remove private info from player object
			player.HandCards = nil
		}
	}
	return rval, nil
}
