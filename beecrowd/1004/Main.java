/*
	beecrowd 1004 : Simple Product
*/

import java.io.IOException;
import java.util.Scanner;

import java.math.BigDecimal;
import java.math.RoundingMode;


class Main {

	public static void main(String[] args) throws IOException {

		int A, B, PROD;

		Scanner scan1 = new Scanner(System.in);
		System.out.print("enter data : ");
		A = scan1.nextInt();
		System.out.print("enter data : ");
		B = scan1.nextInt();
		scan1.close();

		PROD = A * B;

		System.out.println("PROD = " + PROD);

	}

}
