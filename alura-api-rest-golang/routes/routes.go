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
	gorilaMux.HandleFunc("/personalidades", controllers.Personalidades)
	log.Fatal(http.ListenAndServe(":8000", gorilaMux))
}

