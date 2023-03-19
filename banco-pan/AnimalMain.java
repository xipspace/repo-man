/*

	Desafio
	Neste problema, você deverá ler 3 palavras que definem o tipo de animal possível segundo o esquema abaixo, da esquerda para a direita.
	Em seguida conclua qual dos animais seguintes foi escolhido, através das três palavras fornecidas.

	Entrada
	A entrada contém 3 palavras, uma em cada linha, necessárias para identificar o animal segundo a figura acima, com todas as letras minúsculas.

	Saída
	Imprima o nome do animal correspondente à entrada fornecida.

*/

import java.io.IOException;
import java.util.Scanner;

public class AnimalMain {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		String AN1, AN2, AN3;

		AN1 = sc.nextLine();
		AN2 = sc.nextLine();
		AN3 = sc.nextLine();
		sc.close();

		if (AN1.equals("vertebrado") && AN2.equals("ave") && AN3.equals("carnivoro")) {
			System.out.println("aguia");
		} else if (AN1.equals("vertebrado") && AN2.equals("ave") && AN3.equals("onivoro")) {
			System.out.println("pomba");
		} else if (AN1.equals("vertebrado") && AN2.equals("mamifero") && AN3.equals("onivoro")) {
			System.out.println("homem");
		} else if (AN1.equals("vertebrado") && AN2.equals("mamifero") && AN3.equals("herbivoro")) {
			System.out.println("vaca");
		} else if (AN1.equals("invertebrado") && AN2.equals("inseto") && AN3.equals("hematofago")) {
			System.out.println("pulga");
		} else if (AN1.equals("invertebrado") && AN2.equals("inseto") && AN3.equals("herbivoro")) {
			System.out.println("lagarta");
		} else if (AN1.equals("invertebrado") && AN2.equals("anelideo") && AN3.equals("hematofago")) {
			System.out.println("sanguessuga");
		} else if (AN1.equals("invertebrado") && AN2.equals("anelideo") && AN3.equals("onivoro")) {
			System.out.println("minhoca");
		} else {
			System.out.println("entrada incorreta");
		}

	}

}