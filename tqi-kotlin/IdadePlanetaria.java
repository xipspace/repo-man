import java.util.Scanner;

public class IdadePlanetaria {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    double idadeTerrestre = scanner.nextDouble();
    String planeta = scanner.next();
    scanner.close();

    double marte = 1.88;
    double venus = 0.62;
    double jupiter = 11.86;

    double resultado = 0;

    if (planeta.equals("Marte")) {
      resultado = idadeTerrestre / marte;
      System.out.println("Idade equivalente em " + planeta + ": " + String.format("%.2f", resultado) + " anos");
    } else if (planeta.equals("Venus")) {
      resultado = idadeTerrestre / venus;
      System.out.println("Idade equivalente em " + planeta + ": " + String.format("%.2f", resultado) + " anos");
    } else if (planeta.equals("Jupiter")) {
      resultado = idadeTerrestre / jupiter;
      System.out.println("Idade equivalente em " + planeta + ": " + String.format("%.2f", resultado) + " anos");
    } else {
      System.out.println("Planeta invalido.");
    }

  }
}