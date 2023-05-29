fun main() {
    
    // val entrada: String? = readLine()
    val entrada: String? = "30/01/2023"
    val (dia, mes, ano) = entrada!!.split("/")
    
    val mesPorExtenso = when (mes.toInt()) {
        1 -> "Janeiro"
        2 -> "Fevereiro"
        3 -> "Marco"
        4 -> "Abril"
        5 -> "Maio"
        6 -> "Junho"
        7 -> "Julho"
        8 -> "Agosto"
        9 -> "Setembro"
        10 -> "Outubro"
        11 -> "Novembro"
        12 -> "Dezembro"
        else -> "Mes Invalido"
    }
    print("$dia de $mesPorExtenso de $ano")

}