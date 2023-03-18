/*

	Desafio
	A seguinte sequência de números 0 1 1 2 3 5 8 13 21... é conhecida como série de Fibonacci.
	Nessa sequência, cada número, depois dos 2 primeiros, é igual à soma dos 2 anteriores.
	Escreva um algoritmo que leia um inteiro N (N < 46) e mostre os N primeiros números dessa série.

	Entrada
	O arquivo de entrada contém um valor inteiro N (0 < N < 46).

	Saída
	Os valores devem ser mostrados na mesma linha, separados por um espaço em branco. Não deve haver espaço após o último valor.

*/

import java.io.IOException;
import java.util.Scanner;

public class FibMain {

	public static void main(String[] args) throws IOException {

		Scanner leitor = new Scanner(System.in);
		int N = leitor.nextInt();
		leitor.close();

		int A = 0;
		int B = 1;
		int temp = 0;

		if (N > 0 || N < 46) {

			if (N == 1) {
				System.out.print(temp);
			} else {
				for (int i = 0; i < N - 1; ++i) {

					if (i == 0) {
						System.out.print(i);
					}
					if (i == 1) {
						System.out.print(" " + i);
					} else {
						temp = A + B;
						A = B;
						B = temp;

						System.out.print(" " + B);
					}
				}
			}
			System.out.println();
		}
	}
}
