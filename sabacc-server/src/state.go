package sabacc

import (
//	"os"
//	"sort"
//	"appengine"
//	"appengine/datastore"
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

type Player struct {
	Name string `json:"name"`
	Credits int `json:"credits"`
	Bet int `json:"bet"`
	LastMove string `json:"lastMove"`
	HandCards []string `json:"handCards"`
	LockCards []string `json:"lockCards"`
	Winner string `json:"winner"`
	OnAction string `json:"onAction"`
	Folded string `json:"folded"`
	table string
}

