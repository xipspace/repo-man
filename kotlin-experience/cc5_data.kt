data class Pais(var habitantes: Double, val taxaCrescimento: Double) {
	fun crescerPopulacaoAnual() { 
		// TODO("Criar a lógica de crescimento populacional, usando as propriedades do [Pais]")
		// println("hello")
		habitantes *= (taxaCrescimento/100) + 1
		// println(habitantes)
	}
}
fun main() {
	// val habitantesPaisA = readLine()!!.toDouble();
	val habitantesPaisA = 80000.00
	// val habitantesPaisB = readLine()!!.toDouble();
	val habitantesPaisB = 100000.00
	val paisA = Pais(habitantesPaisA, taxaCrescimento = 3.0)
	val paisB = Pais(habitantesPaisB, taxaCrescimento = 1.5)

	var quantidadeAnos = 0
	while (paisA.habitantes < paisB.habitantes) {

		//   TODO("Invocar a função que consolida o crescimento anual de cada [Pais]")
		paisA.crescerPopulacaoAnual()
		paisB.crescerPopulacaoAnual()
		//  TODO("Garantir de a variável mutável $quantidadeAnos seja atualizada")	
		quantidadeAnos++

	}

	println("$quantidadeAnos anos")
}