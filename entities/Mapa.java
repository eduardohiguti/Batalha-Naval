package entities;

public class Mapa {
    private final int TAMANHO_TABULEIRO = 16;
    private String[][] tabuleiro = new String[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];

    public void initTabuleiro() {
        for (int i=0; i<tabuleiro.length; i++) {
            for (int j=0; j<tabuleiro[i].length; j++) {
                tabuleiro[i][j] = ",";
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

    public String[][] getTabuleiro() {
        return tabuleiro;
    }
}