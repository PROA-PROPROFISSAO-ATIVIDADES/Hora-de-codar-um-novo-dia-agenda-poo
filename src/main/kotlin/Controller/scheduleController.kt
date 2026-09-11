package Controller

import Model.Person
import Reply
import Repository.PersonRepository

class PersonController(private val repository: PersonRepository) {
    fun toCreate(name: String, phoneNumber: String): Reply<Person> {
        require(name.isNotBlank())

        try {
            val phoneNumberInt = phoneNumber.toLong()
            val person = Person(name, phoneNumberInt)
            repository.save(person)

            return Reply<Person>(
                201,
                "Usuario cadastrado com sucesso",
                person
            )
        } catch (e: NumberFormatException){
            return Reply<Person>( 404,"Erro ao cadastrar numero | Digite um numero de telefone valido e sem pontos")
        } catch (e: Exception){
            return Reply<Person>( 500,"Erro inesperado")
        }
    }

    fun toList(): Reply<List<Person>> {
        try {
            val list = repository.listAll()

            return Reply<List<Person>>(
                200,
                "Lista de usuarios encontrada com sucesso",
                list
            )
        } catch (e: Exception){
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

            return Reply(404, "Usuario não encontrado")
        } catch (e: Exception){
            return Reply(500, "Erro inesperado")
        }
    }

    fun toDelete(name: String): Reply<Person> {
        try {
            val person = repository.delete(name)

            if(person != null){
                return Reply<Person>(
                    200,
                    "Usuarios deletado com sucesso",
                    person
                )
            }

            return Reply<Person>(404, "Usuario não encontrado")
        } catch (e: Exception){
            return Reply<Person>(500, "Erro inesperado")
        }
    }

    fun getSize(): Int{
        return repository.listAll().size
    }
}