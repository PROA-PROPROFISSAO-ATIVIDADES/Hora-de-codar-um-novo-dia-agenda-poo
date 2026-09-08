import Controller.PersonController
import RepositoryImplementation.PersonRepositortImp
import View.PersonView

fun main(){
    val repository = PersonRepositortImp()
    val controller = PersonController(repository)
    val view = PersonView(controller)
    view.showMenu()
}