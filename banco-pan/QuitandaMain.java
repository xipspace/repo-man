/*

	Desafio
	Seu Zé está vendendo frutas com a seguinte tabela de preços:
	
	Peso			-5		+5
	Morango			2,5		2,2
	Maça			1,8		1,5

	Se o cliente comprar mais de 8 Kg em frutas ou o valor total da compra ultrapassar R$ 25,00, receberá ainda um desconto de 10% sobre este total.
	Escreva um algoritmo para ler a quantidade (em Kg) de morangos e a quantidade (em Kg) de maças adquiridas e escreva o valor a ser pago pelo cliente.

	Entrada
	Como entrada, você recebera a quantidade em kg de morangos e a quantidade em kg de maçãs.

	Saída
	Será o valor a ser pago pelo cliente, conforme a tabela de preços da quintanda do seu Zé.

*/

import java.util.*;

public class QuitandaMain {

	public static void main(String[] args) {

		final double morangoMax = 2.5;
		final double morangoMin = 2.2;
		final double macaMax = 1.8;
		final double macaMin = 1.5;
		final double desconto = 0.9;
		double valor = 0;
		
		Scanner input = new Scanner(System.in);
		int morangos = input.nextInt();
		int macas = input.nextInt();
		input.close();
		
		if(morangos > 0 && morangos > 5){
			valor = morangoMin * morangos;
		} else if(morangos > 0 && morangos <= 5){
			valor = morangoMax * morangos;
		}
		
		if(macas > 0 && macas > 5){
			valor += macaMin * macas;
		} else if(macas > 0 && macas <= 5){
			valor += macaMax * macas;
		}
		
		if(valor > 25 || (morangos + macas) > 8){
			valor *= desconto;
		}
		System.out.println(valor);

	}

}