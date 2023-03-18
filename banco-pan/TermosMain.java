/*

	Desafio
	Neste desafio, faça um programa que calcule o valor de H com N termos.
	Sendo, H = 1 + 1/2 + 1/3 + 1/4 + ... + 1/N. 
	
	Entrada
	A entrada consiste em um número inteiro positivo.
	
	Saída
	Na saída será impresso o valor que representa a soma dos termos.

*/

import java.util.Scanner;

public class TermosMain {

	public static void main(String[] Args) {

		double h = 0;
		Scanner sc = new Scanner(System.in);
		double n = sc.nextDouble();
		double md = 1;
		double num = 0;
		sc.close();

		for (int i = 1; i <= n; i++) {
			md *= i;
		}

		for (int i = 1; i <= n; i++) {
			num += md / i;
		}

		h = Math.round(num / md);

		System.out.println(String.format("%.0f", h));

	}

}