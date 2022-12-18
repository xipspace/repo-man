/*
	beecrowd 1151 : Easy Fibonacci
*/

import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

class Run {

	public static void main(String[] args) throws IOException {

		int N = 0, i = 0;

		Scanner scan1 = new Scanner(System.in);
		System.out.print("enter data : ");
		N = scan1.nextInt();
		
		if(N > 0 && N < 46){
			int[] fibArr = new int[N];
			
			while(i < N){
				if(i == 0){
					fibArr[i] = 0;
					//System.out.print(fibArr[i]);
				} else if(i == 1 || i == 2){
					fibArr[i] = 1;
					//System.out.print(" " + fibArr[i]);
				} else {
					fibArr[i] = fibArr[i-1] + fibArr[i-2];
					//System.out.print(" " + fibArr[i]);
				}
				//System.out.print(" ");
				System.out.print(fibArr[i] + " ");
				i++;				
			}
			
			System.out.println("");
			System.out.println(Arrays.toString(fibArr));
			System.out.println(fibArr.length);

		} else {
			System.out.println("valor incorreto");
		}

	}

}
