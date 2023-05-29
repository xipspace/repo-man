import java.util.Scanner;

public class ComprasLivraria {
  
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Livro livro1 = new Livro(scanner.nextLine(), scanner.nextDouble(), scanner.nextInt());
    scanner.nextLine();
    Livro livro2 = new Livro(scanner.nextLine(), scanner.nextDouble(), scanner.nextInt());

    int livroTotal = livro1.quantidade + livro2.quantidade;
    
    double compraTotal;
    
    if (livro1.quantidade > 0 && livro2.quantidade > 0){
      compraTotal = (livro1.preco * livro1.quantidade) + (livro2.preco * livro2.quantidade);
    } else {
      compraTotal = 0;
    }
  
    System.out.println("Valor total da compra: " + String.format("%.2f", compraTotal));

    System.out.println("Numero de livros comprados: " + livroTotal);

    System.out.println("Obrigado por comprar na nossa livraria!");

  }
  
  static class Livro {
    String nome;
    double preco;
    int quantidade;
    
    Livro(String nome, double preco, int quantidade) {
      this.nome = nome;
      this.preco = preco;
      this.quantidade = quantidade;
    }
  }
}