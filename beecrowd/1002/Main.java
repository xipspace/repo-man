/*
	beecrowd 1002 : Area of a Circle
*/

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {

		final double n = 3.14159;
		double A = 0;

		Scanner scan1 = new Scanner(System.in);
		System.out.print("enter data : ");
		double R = scan1.nextDouble();
		scan1.close();

		A = Math.pow(R, 2) * n;
		BigDecimal formatArea = new BigDecimal(A).setScale(4, RoundingMode.HALF_UP);

		System.out.println("A=" + formatArea);

	}

}
