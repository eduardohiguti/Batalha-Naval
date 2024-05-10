package application;

import java.util.Scanner;
import entities.Jogo;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Bem vindo ao batalha Naval!!! \n1-Para 1 v 1\n2-Para 2 v 2\n Opção:");
        int opcao = sc.nextInt();
        if (opcao == 1) {
            Jogo jogo = new Jogo(2);
            jogo.Tabuleiro();
        } else if (opcao == 2) {
            Jogo jogo = new Jogo(4);
            jogo.Tabuleiro();
        } else {
            System.out.println("Opção inválida!!!");
        }
        sc.close();
    }
}
