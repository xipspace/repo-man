/*
    Abstrair o seguinte domínio de aplicação:
    A DIO possui Formacoes incríveis que têm como objetivo oferecer um conjunto de ConteudosEducacionais voltados para uma stack tecnológica específica, preparando profissionais de TI para o mercado de trabalho. Formacoes possuem algumas características importantes, como nome, nivel e seus respectivos conteudosEducacionais. Além disso, tais experiências educacionais têm um comportamento relevante ao nosso domínio, definido pela capacidade de matricular um ou mais Alunos.
*/

// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val nome: String)

data class ConteudoEducacional(var nome: String,
                               val duracao: Int = 60)

data class Formacao(val nome: String,
                    var conteudos: List<ConteudoEducacional> = emptyList()) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        // TODO("Utilize o parâmetro $usuario para simular uma matrícula (usar a lista de $inscritos).")
        if(!inscritos.contains(usuario)){

            inscritos.add(usuario)
            println("Cadastro de ${usuario.nome} em ${this.nome} foi um sucesso.")
            // println(inscritos)

        }

    }
}

fun main() {
    // TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
    // TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")

    val formacao1 = Formacao("Android Developer")

    val user1 = Usuario("John")
    val user2 = Usuario("Paul")
    val user3 = Usuario("George")
    val user4 = Usuario("Ringo")

    var userList = mutableListOf<Usuario>()
    userList.addAll(listOf(user1, user2, user3, user4))

    /*
    Nivel.values().forEach { println(it) }
    userList.forEach { println(it) }
    cursoList.forEach { println(it) }

    for (user in userList){
        println(user)
    }

    for (i in 0 until userList.size){
        println(userList[i])
    }
    */

    userList.forEach { formacao1.matricular(it) }
    println(formacao1.inscritos)

}