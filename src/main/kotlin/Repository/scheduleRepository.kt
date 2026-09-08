package Repository

import Model.Person

interface PersonRepository{
    fun save(person: Person): Person
    fun find(name: String): Person?
    fun delete(name: String): Person?
    fun listAll(): List<Person>
}