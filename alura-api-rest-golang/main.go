package main

import (
	"api-rest-golang/models"
	"api-rest-golang/routes"
	"fmt"
)

func main() {

	models.Personalidades = []models.Personalidade{
		{Id: 1, Nome: "Sam Newman", Historia: "Autor do livro Microservices"},
		{Id: 2, Nome: "Martin Fowler", Historia: "Autor do livro Domain Drive Design - DDD"},
	}

	fmt.Println("Iniciando o servidor Rest com Go")
	routes.HandleRequest()
}

