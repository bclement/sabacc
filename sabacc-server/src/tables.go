package sabacc

type Tables struct {
	TypeName string `json:"type"`
	Tables []Table  `json:"tables"`
}

type Table struct {
	Name string `json:"name"`
	Players int `json:"players"`
	Status string `json:"status"`
}

type TableRequest struct {
	TypeName string
	TableName string
	ResultChan chan Tables
}

var tables Tables

func init(){
	tables.TypeName = "tables"
	tables.Tables = make([]Table, 2)
	populateTable( &tables.Tables[0], "table0")
	populateTable( &tables.Tables[1], "table1")
}

func populateTable(t *Table, name string){
	t.Name = name
	t.Status = "waiting"
}

func tableServe( req chan *TableRequest ){
	for {
		tr := <-req
		tr.ResultChan <- tables
	}
}
