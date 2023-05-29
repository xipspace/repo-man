import java.util.Scanner;

public class MudancaIrlanda {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    double salarioBruto = input.nextDouble();
    double beneficios = input.nextDouble();
    input.close();

    double imposto = 0;
    
    if (salarioBruto <= 1100){
      imposto = salarioBruto * 0.05;
    } else if (salarioBruto > 1100 && salarioBruto <= 2500){
      imposto = salarioBruto * 0.1;
    } else {
      imposto = salarioBruto * 0.15;
    }

    double salarioLiquido = (salarioBruto - imposto) + beneficios;

    banner(salarioLiquido);

  }
  static void banner(double valor){
    String format = String.format("%.2f", valor);
    System.out.println(format);
  }

}