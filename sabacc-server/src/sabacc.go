package sabacc

import (
	"fmt"
	"http"
	"json"
	"appengine"
	"appengine/user"
)

type Error struct {
	TypeName string `json:"type"`
	Name string `json:"name"`
	Reason string `json:"reason"`
}

func (e *Error) String() string {
	return e.Reason
}

func init() {
	http.HandleFunc("/tables", listTables)
	http.HandleFunc("/join", join)
	http.HandleFunc("/", handler)
}

func join( w http.ResponseWriter, r *http.Request ){
	c := appengine.NewContext(r)
	u := user.Current(c)
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
	r.ParseForm()
	// TODO check table arg
	state, err := joinTable(c, r.Form["table"][0], u.String())
	if err != nil {
		http.Error(w, err.String(), http.StatusInternalServerError)
		return
	}
	var b []byte
	b, err = json.Marshal(state)
	if err != nil {
		http.Error(w, err.String(), http.StatusInternalServerError)
		return
	}
	fmt.Fprintf(w,"%s", b)
}

func listTables( w http.ResponseWriter, r *http.Request ){
	tables, err := getTables(appengine.NewContext(r))
	if err != nil {
		http.Error(w, err.String(), http.StatusInternalServerError)
		return
	}
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
	fmt.Fprintf(w, "Sup, %v ", u)
}
