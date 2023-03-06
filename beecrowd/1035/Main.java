/*
	beecrowd 1035 : Selection Test 1
*/

import java.io.IOException;
import java.util.Scanner;
/*
import java.math.BigDecimal;
import java.math.RoundingMode;
*/

class Main {

	public static void main(String[] args) throws IOException {

		int A, B, C, D;

		Scanner scan1 = new Scanner(System.in);
		System.out.print("enter data : ");
		A = scan1.nextInt();
		System.out.print("enter data : ");
		B = scan1.nextInt();
		System.out.print("enter data : ");
		C = scan1.nextInt();
		System.out.print("enter data : ");
		D = scan1.nextInt();
		scan1.close();

		if(B > C && D > A && (C + D) > (A + B) && C >= 0 && D >= 0 && A % 2 == 0){
			System.out.println("Valores aceitos");
		} else {
			System.out.println("Valores nao aceitos");
		}

		

	}

}
