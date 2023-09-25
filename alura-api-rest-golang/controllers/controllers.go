package controllers

import (
	"api-rest-golang/models"
	"encoding/json"
	"fmt"
	"strconv"
	"net/http"
)

func Home(w http.ResponseWriter, r *http.Request) {

	fmt.Fprint(w, "Home Page")
}

func Personalidades(w http.ResponseWriter, r *http.Request) {

	json.NewEncoder(w).Encode(models.Personalidades)
}

func RetornaUmaPersonalidade(w http.ResponseWriter, r *http.Request) {

    vars := mux.Vars(r)
    id := vars["id"]

    for _, personalidade := range models.Personalidades {
        if strconv.Itoa(personalidade.Id) == id {
            json.NewEncoder(w).Encode(personalidade)
        }
    }
}

