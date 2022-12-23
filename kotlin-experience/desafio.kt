/*
    Abstrair o seguinte domínio de aplicação:
    A DIO possui Formacoes incríveis que têm como objetivo oferecer um conjunto de ConteudosEducacionais voltados para uma stack tecnológica específica, preparando profissionais de TI para o mercado de trabalho. Formacoes possuem algumas características importantes, como nome, nivel e seus respectivos conteudosEducacionais. Além disso, tais experiências educacionais têm um comportamento relevante ao nosso domínio, definido pela capacidade de matricular um ou mais Alunos.
*/

// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

// data class Usuario(var nome: String, var cursos: List<ConteudoEducacional>)
data class Usuario(var nome: String)

data class ConteudoEducacional(var nome: String,
                               val duracao: Int = 60,
                               var isEnrolled: Boolean = false,
                               var isConcluded: Boolean = false)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {
    
    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        // TODO("Utilize o parâmetro $usuario para simular uma matrícula (usar a lista de $inscritos).")
        println(conteudos)
        /*

        if(!usuario.isEnrolled && !usuario.isConcluded){
            usuario.isEnrolled = true
            inscritos.add(usuario)
            // println(inscritos)
        }
        */
    }
}

fun main() {
    // TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
    // TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
    
    val user1 = Usuario("John")
    val user2 = Usuario("Paul")
    val user3 = Usuario("George")
    val user4 = Usuario("Ringo")
    
    val curso1 = ConteudoEducacional("Programador", 90)
    val curso2 = ConteudoEducacional("QA", 60)
    val curso3 = ConteudoEducacional("Test", 30)
    
    var userList = mutableListOf<Usuario>()
    userList.add(user1)
    userList.add(user2)
    userList.add(user3)
    userList.add(user4)
    
    var cursosList = mutableListOf<ConteudoEducacional>()
    cursosList.add(curso1)
    cursosList.add(curso2)
    cursosList.add(curso3)
    
    // Formacao(cursosList[0].nome, cursosList).matricular(user1)
    // Formacao(cursosList[0].nome, cursosList).matricular(user2)
    
    println(user1)
    
    // check lists
    
}