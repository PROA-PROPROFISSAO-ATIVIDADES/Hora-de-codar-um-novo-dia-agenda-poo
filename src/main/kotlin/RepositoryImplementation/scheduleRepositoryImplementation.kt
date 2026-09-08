package RepositoryImplementation

import Model.Person
import Repository.PersonRepository

class PersonRepositortImp : PersonRepository {
    private val people = mutableListOf<Person>()

    override fun save(person: Person): Person {
        people.add(person)
        return person
    }

    override fun find(name: String): Person? {
        return people.find { it.name.equals(name, ignoreCase = true) }
    }

    override fun delete(name: String): Person? {
        val person = find(name)
        people.remove(person)
        return person
    }

    override fun listAll(): List<Person> {
        return people
    }
}