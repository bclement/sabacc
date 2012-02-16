package sabacc

type Move struct {
	TypeName string `json:"type"`
	Table string `json:"table"`
	Move string `json:"move"`
	Credits int `json:"credits"`
	Card string `json:"card"`
	LockingCard string `json:"lockingCard"`
	UnlockingCard string `json:"unlockingCard"`
}

