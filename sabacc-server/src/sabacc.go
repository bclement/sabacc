package sabacc

import (
	"fmt"
	"http"
	"json"
	"appengine"
	"appengine/user"
)

var tchan = make( chan *TableRequest )

func init() {
	go tableServe(tchan)
	http.HandleFunc("/tables", listTables)
	http.HandleFunc("/", handler)
}

func listTables( w http.ResponseWriter, r *http.Request ){
	treq := &TableRequest{"foo", "", make( chan Tables )}
	tchan <- treq
	tables := <-treq.ResultChan
	b, err := json.Marshal(tables)
	if err != nil {
		http.Error(w, err.String(), http.StatusInternalServerError)
		return
	}
	fmt.Fprintf(w,"%s", b) 
}

func handler( w http.ResponseWriter, r *http.Request) {
	c := appengine.NewContext(r)
	u := user.Current(c)
	t := test{1}
	if u == nil {
		url, err := user.LoginURL(c, r.URL.String())
		if err != nil {
			http.Error(w, err.String(), http.StatusInternalServerError)
			return
		}
		w.Header().Set("Location", url)
		w.WriteHeader(http.StatusFound)
		return
	}
	fmt.Fprintf(w, "Sup, %v %d", u, t.num)
}
