/*
	beecrowd 1151 : Easy Fibonacci
*/

import java.io.IOException;
import java.util.Scanner;

class Main {

	public static void main(String[] args) throws IOException {

		int N = 0;

		Scanner scan1 = new Scanner(System.in);
		System.out.print("enter data : ");
		N = scan1.nextInt();
		scan1.close();

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