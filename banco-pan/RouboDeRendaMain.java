/*

	Desafio
	Há um país denominado Lolipad, todos os habitantes ficam felizes em pagar seus impostos, pois sabem que nele não existem políticos corruptos e os recursos arrecadados são utilizados em benefício da população, sem qualquer desvio.
	A moeda deste país é o Loli, cujo símbolo é o R$.
	Você terá o desafio de ler um valor com duas casas decimais, equivalente ao salário de uma pessoa de Loli.
	Em seguida, calcule e mostre o valor que esta pessoa deve pagar de Imposto de Renda, segundo a tabela abaixo.
	Lembre que, se o salário for R$ 3002.00, a taxa que incide é de 8% apenas sobre R$ 1000.00, pois a faixa de salário que fica de R$ 0.00 até R$ 2000.00 é isenta de Imposto de Renda.
	No exemplo fornecido, a taxa é de 8% sobre R$ 1000.00 + 18% sobre R$ 2.00, o que resulta em R$ 80.36 no total.
	O valor deve ser impresso com duas casas decimais.

	Entrada
	A entrada contém apenas um valor de ponto flutuante, com duas casas decimais.

	Saída
	Imprima o texto "R$" seguido de um espaço e do valor total devido de Imposto de Renda, com duas casas após o ponto. Se o valor de entrada for menor ou igual a 2000, deverá ser impressa a mensagem "Isento".

*/

import java.io.IOException;
import java.util.Scanner;

public class RouboDeRendaMain {

	public static void main(String[] args) throws IOException {

		final double aliquota1 = 0.08;
		final double aliquota2 = 0.18;
		final double aliquotaMax = 0.28;

		Scanner leitor = new Scanner(System.in);
		double renda = leitor.nextDouble();
		double imposto = 0;
		leitor.close();

		if (renda <= 2000) {

			System.out.println("Isento");

		}

		if (renda > 2000 && renda <= 3000) {

			imposto = (renda - 2000) * aliquota1;
			RouboDeRendaMain.mostraValor(imposto);

		}

		if (renda > 3000 && renda <= 4500) {

			imposto = 1000 * aliquota1;
			imposto += (renda - 3000) * aliquota2;
			RouboDeRendaMain.mostraValor(imposto);

		}

		if (renda > 4500) {

			imposto = 1000 * aliquota1;
			imposto += 1499.99 * aliquota2;
			imposto += (renda - 4500) * aliquotaMax;
			RouboDeRendaMain.mostraValor(imposto);

		}

	}

	static void mostraValor(double valor) {
		System.out.println(String.format("R$ %.2f", valor));
	}

}
