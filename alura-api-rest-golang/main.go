package main

import (
	"api-rest-golang/models"
	"api-rest-golang/routes"
	"fmt"
)

func main() {

	models.Personalidades = []models.Personalidade{
		{Nome: "Sam Newman", Historia: "Autor do livro Microservices"},
		{Nome: "Martin Fowler", Historia: "Autor do livro Domain Drive Design - DDD"},
	}

	fmt.Println("Iniciando o servidor Rest com Go")
	routes.HandleResquest()
}
