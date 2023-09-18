package routes

import (
	"api-rest-golang/controllers"
	"log"
	"net/http"
)

func HandleResquest() {
	http.HandleFunc("/", controllers.Home)
	http.HandleFunc("/personalidades", controllers.Personalidades)
	log.Fatal(http.ListenAndServe(":8000", nil))
}
