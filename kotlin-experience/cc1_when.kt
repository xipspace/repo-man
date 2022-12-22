fun main() {
   val media = readLine()!!.toDouble()
   
   when {
       media < 5 -> println("reprovado")
       media >= 5 && media < 7 -> println("recuperacao")
       else -> println("aprovado")
   }
}