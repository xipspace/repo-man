/*
	beecrowd 1003 : Simple Sum
*/

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {

		int A, B;

		Scanner scan1 = new Scanner(System.in);
		System.out.print("enter data : ");
		A = scan1.nextInt();
		System.out.print("enter data : ");
		B = scan1.nextInt();
		scan1.close();

		System.out.println("SOMA = " + (A + B));

	}

}
