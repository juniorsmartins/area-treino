package routes

import (
	"api-rest-golang/controllers"
	"log"
	"net/http"

	"github.com/gorilla/mux"
)

func HandleRequest() {
    gorilaMux := mux.NewRouter()
	gorilaMux.HandleFunc("/", controllers.Home)
	gorilaMux.HandleFunc("/api/personalidades", controllers.Personalidades).Methods("Get")
	gorilaMux.HandleFunc("/api/personalidades/{id}", controllers.RetornaUmaPersonalidade).Methods("Get")
	log.Fatal(http.ListenAndServe(":8000", gorilaMux))
}

