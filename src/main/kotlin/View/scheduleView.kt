package View

import Controller.PersonController
import kotlin.system.exitProcess

class PersonView(private val controller: PersonController){
    val options = arrayListOf<String>("Criar", "Listar", "Buscar", "Deletar", "Sair")

    fun showOptions(){
        options.forEachIndexed { index, string ->
            println("${index+1}. $string")
        }
    }

    fun showMenu(){
        while(true){
            showOptions()
            println("Digite um numero de 1 a ${options.size}: ")
            val choice = readln()

            when {
                choice.toIntOrNull() == 1 -> showToCreate()
                choice.toIntOrNull() == 2 -> showToList()
                choice.toIntOrNull() == 3 -> showToFilter()
                choice.toIntOrNull() == 4 -> showToDelete()
                choice.toIntOrNull() == 5 -> toLeave()
                else -> println("Opção invalida")
            }
        }
    }

    private fun showToCreate(){
        toClean()
        println("Digite o nome: "); val name = readln()
        println("Digite o numero de telefone: "); val phoneNumber = readln()
        println(controller.toCreate(name, phoneNumber))
    }

    private fun showToList(){
        toClean()
        controller.toList().item?.forEach { println(it) }
    }

    private fun showToDelete(){
        toClean()
        println("Digite o nome: "); val name = readln()
        println(controller.toDelete(name))
    }

    private fun showToFilter(){
        toClean()
        println("Digite o nome: "); val name = readln()
        val results = controller.toFind(name)
        println("${results.message}: \n")
        println(results.item)
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