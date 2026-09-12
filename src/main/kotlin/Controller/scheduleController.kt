package Controller

import Model.Person
import Reply
import Repository.PersonRepository

class PersonController(private val repository: PersonRepository) {
    fun toCreate(name: String, phoneNumber: String): Reply<Person> {
        require(name.isNotBlank())

        try {
            val person = Person(name, phoneNumber)
            repository.save(person)

            return Reply<Person>(
                201,
                "Contato adicionado!",
                person
            )
        } catch (_: Exception){
            return Reply<Person>( 500,"Erro inesperado")
        }
    }

    fun toList(): Reply<List<Person>> {
        try {
            val list = repository.listAll()

            if(list.isEmpty()){
                return Reply(
                    status = 404,
                    "Nenhum contato cadastrado."
                )
            }

            return Reply<List<Person>>(
                200,
                "Lista de usuarios encontrada com sucesso",
                list
            )
        } catch (_: Exception){
            return Reply<List<Person>>( 500,"Erro inesperado")
        }
    }

    fun toFind(name: String): Reply<List<Person>> {
        try {
            val person = repository.find(name)

            if(person.isNotEmpty()){
                return Reply<List<Person>>(
                    200,
                    "Resultados encontrado para essa pesquisa",
                    person
                )
            }

            return Reply(404, "Contato não encontrado")
        } catch (_: Exception){
            return Reply(500, "Erro inesperado")
        }
    }

    fun toDelete(name: String): Reply<Person> {
        try {
            val person = repository.delete(name)

            if(person != null){
                return Reply<Person>(
                    200,
                    "Contato removido!",
                    person
                )
            }

            return Reply<Person>(404, "Contato não encontrado")
        } catch (_: Exception){
            return Reply<Person>(500, "Erro inesperado")
        }
    }

    fun getSize(): Int{
        return repository.listAll().size
    }
}