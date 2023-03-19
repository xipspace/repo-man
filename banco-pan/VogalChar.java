/*

	Desafio
	Jorginho é professor do primário de uma determinada escola. Ele tem 100000 alunos e precisa criar um programa que verifica quantos espaços em branco e quantas vogais existem em uma determinada string de entrada que os alunos entregaram na avaliação final.
	Ajude-o a realizar essa tarefa antes que o tempo para entrega-la no fim do semestre acabe!

	Entrada
	A entrada será uma frase no formato de string. 

	Saída
	A saída deverá retornar quantos espaços em branco e quantas vogais existem na determinada string.

*/

import java.util.Scanner;

public class VogalChar {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();

		str = str.toLowerCase();
		
		String vowels = "aeiou";

		int spaceCount = 0, vowelCount = 0;

		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) == ' ') {
				spaceCount++;
			} else {
				for (int j = 0; j < vowels.length(); j++){
					if (str.charAt(i) == vowels.charAt(j)) {
						vowelCount++;
					}
				}
			}

		}

		System.out.println("Espacos em branco: " + spaceCount + " Vogais: " + vowelCount);

	}

}