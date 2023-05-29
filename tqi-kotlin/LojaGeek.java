import java.util.Scanner;

public class LojaGeek {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    double media = scanner.nextDouble();
    scanner.close();
    String[] texto = { "REP", "MED", "APR" };

    if (media < 5) {
      banner(texto[0]);
    } else if (media >= 5 && media < 7) {
      banner(texto[1]);
    } else {
      banner(texto[2]);
    }

  }

  static void banner(String msg) {
    System.out.println(msg);
  }

}