package View

import Controller.PersonController
import kotlin.system.exitProcess

class PersonView(private val controller: PersonController){
    val options = arrayListOf<String>("Adicionar", "Listar", "Buscar", "Remover", "Sair")

    fun showOptions(){
        options.forEachIndexed { index, string ->
            println("${index+1}. $string")
        }
    }

    fun showMenu(){
        while(true){
            showOptions()
            println("Digite um comando (ADICIONAR, LISTAR, BUSCAR, REMOVER, SAIR): ")
            val choice = readln()

            when {
                choice.uppercase() == "ADICIONAR" -> showToCreate()
                choice.uppercase() == "LISTAR" -> showToList()
                choice.uppercase() == "BUSCAR" -> showToFilter()
                choice.uppercase() == "REMOVER" -> showToDelete()
                choice.uppercase() == "SAIR" -> toLeave()
                else -> {
                    println("Opção invalida! Comandos possiveis:")
                    println(options)
                }
            }
        }
    }

    private fun showToCreate(){
        toClean()
        println("Digite o nome: "); val name = readln()
        println("Digite o numero de telefone: "); val phoneNumber = readln()
        println(controller.toCreate(name, phoneNumber).message)
    }

    private fun showToList(){
        toClean()
        val results = controller.toList()
        if (results.item.isNullOrEmpty()) {
            println(results.message)
        } else {
            results.item.forEach { println(it) }
        }
    }

    private fun showToDelete(){
        toClean()
        println("Digite o nome: "); val name = readln()
        println(controller.toDelete(name).message)
    }

    private fun showToFilter(){
        toClean()
        println("Digite o nome: "); val name = readln()
        val results = controller.toFind(name)
        if (results.item.isNullOrEmpty()) {
            println(results.message)
        } else {
            results.item.forEach { println("Telefone: ${it.phoneNumber}") }
        }
    }

    private fun toLeave(){
        println("Agenda encerrada. Total de contatos: ${controller.getSize()}")
        exitProcess(0)
    }

    private fun toClean(){
        repeat(50) {
            println("")
        }
    }
}