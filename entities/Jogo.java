package entities;

import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private final int TAMANHO_TABULEIRO = 16;
    private Mapa mapa;
    private Random random;

    public Jogo() {
        this.mapa = new Mapa();
        this.random = new Random();
    }

    public void Jogar() {
        Scanner sc = new Scanner(System.in);
        PortaAviao portaAviao = new PortaAviao("A", 8);
        Destroyer destroyer = new Destroyer("D", 5);
        Submarino submarino = new Submarino("S", 4);
        Fragata fragata = new Fragata("F", 3);
        Bote bote = new Bote("B", 2);

        Embarcacao[] embarcacoes = {
            portaAviao, portaAviao,
            destroyer, destroyer, destroyer,
            submarino, submarino, submarino, submarino,
            fragata, fragata, fragata, fragata, fragata,
            bote, bote, bote, bote, bote, bote
        };

        Jogador jogador1 = new Jogador(0);
        Jogador jogador2 = new Jogador(0);

        mapa.initTabuleiro();
        posicionarEmbarcacoes(embarcacoes);
        mapa.printTabuleiro();

       int linha, coluna;

        do {
            System.out.println("Insira as coordenadas do ataque:");
            linha = sc.nextInt();
            coluna = sc.nextInt();
       
            if (linha < 0 || linha >= TAMANHO_TABULEIRO || coluna < 0 || coluna >= TAMANHO_TABULEIRO) {
                System.out.println("Coordenadas inválidas. Por favor, insira novamente.");
            }
        } while (linha < 0 || linha >= TAMANHO_TABULEIRO || coluna < 0 || coluna >= TAMANHO_TABULEIRO);
       
        if (mapa.getTabuleiro()[linha][coluna] != ",") {
            mapa.getTabuleiro()[linha][coluna] = "X";
        } else {
            mapa.getTabuleiro()[linha][coluna] = "V";
        }
           

        mapa.printTabuleiro();

        sc.close();
    }
    
    private void posicionarEmbarcacao(Embarcacao embarcacao, int linha, int coluna, boolean horizontal) {
        int tamanho = embarcacao.getTamanho();

        if (horizontal) {
            for (int i = 0; i < tamanho; i++) {
                mapa.getTabuleiro()[linha][coluna + i] = embarcacao.getName();
            }
        } else {
            for (int i = 0; i < tamanho; i++) {
            mapa.getTabuleiro()[linha + i][coluna] = embarcacao.getName();
            }
        }
    }

    private boolean podePosicionar(Embarcacao embarcacao, int linha, int coluna, boolean horizontal) {
        int tamanho = embarcacao.getTamanho();

        if (horizontal && coluna + tamanho > TAMANHO_TABULEIRO) {
            return false;
        }
        if (!horizontal && linha + tamanho > TAMANHO_TABULEIRO) {
            return false;
        }

        for (int i = 0; i < tamanho; i++) {
            if (horizontal && mapa.getTabuleiro()[linha][coluna + i] != ",") {
                return false;
            }
            if (!horizontal && mapa.getTabuleiro()[linha + i][coluna] != ",") {
                return false;
            }
        }

        return true;
    } 

    private void posicionarEmbarcacoes(Embarcacao[] embarcacoes) {
        for (Embarcacao embarcacao : embarcacoes) {
            boolean posicionado = false;

            while (!posicionado) {
                int linha = random.nextInt(TAMANHO_TABULEIRO);
                int coluna = random.nextInt(TAMANHO_TABULEIRO);
                boolean horizontal = random.nextBoolean();

                if (podePosicionar(embarcacao, linha, coluna, horizontal)) {
                    posicionarEmbarcacao(embarcacao, linha, coluna, horizontal);
                    posicionado = true;
                }
                
            }
        }
    }

}