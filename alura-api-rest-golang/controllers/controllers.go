package controllers

import (
	"api-rest-golang/models"
	"encoding/json"
	"fmt"
	"net/http"
)

func Home(w http.ResponseWriter, r *http.Request) {

	fmt.Fprint(w, "Home Page")
}

func Personalidades(w http.ResponseWriter, r *http.Request) {

	json.NewEncoder(w).Encode(models.Personalidades)
}
