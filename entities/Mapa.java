package entities;

public class Mapa {
    private final int TAM = 16;
    private String[][] tabuleiro = new String[TAM][TAM];

    public void initTabuleiro() {
        for (int i=0; i<tabuleiro.length; i++) {
            for (int j=0; j<tabuleiro[i].length; j++) {
                tabuleiro[i][j] = "O";
            }
        }
    }

    public void printTabuleiro() {
        System.out.println("Tabuleiro");
        for (int i=0; i<tabuleiro.length; i++) {
            for (int j=0; j<tabuleiro[i].length; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }
}