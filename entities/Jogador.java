package entities;

import java.util.Random;

public class Jogador {
    private final int TAMANHO_TABULEIRO = 16;
    private int pontuacao;
    private Mapa mapa;
    private Mapa mapaInimigo;
    private Random random;
    private String nome;

    public int portaAviaoPartesAcertadas;
    public int destroyerPartesAcertadas;
    public int submarinoPartesAcertadas;
    public int fragataPartesAcertadas;
    public int botePartesAcertadas;

    public PortaAviao portaAviao;
    public Destroyer destroyer;
    public Submarino submarino;
    public Fragata fragata;
    public Bote bote;
    public Embarcacao[] embarcacoes;

    public Jogador() {
        mapa = new Mapa();
        mapaInimigo = new Mapa();
        random = new Random();
        portaAviaoPartesAcertadas = 0;
        destroyerPartesAcertadas = 0;
        submarinoPartesAcertadas = 0;
        fragataPartesAcertadas = 0;
        botePartesAcertadas = 0;
        portaAviao = new PortaAviao("A", 8);
        destroyer = new Destroyer("D", 5);
        submarino = new Submarino("S", 4);
        fragata = new Fragata("F", 3);
        bote = new Bote("B", 2);
        mapa.initTabuleiro("V");
        mapaInimigo.initTabuleiro("O");
        embarcacoes = new Embarcacao[] {
            portaAviao, portaAviao,
            destroyer, destroyer, destroyer,
            submarino, submarino, submarino, submarino,
            fragata, fragata, fragata, fragata, fragata,
            bote, bote, bote, bote, bote, bote
        };
    }

    public Jogador(int pontuacao, String nome) {
        this();
        this.pontuacao = pontuacao;
        this.nome = nome;
    }

    public void arruma() {
        posicionarEmbarcacoes(this.embarcacoes);
    }

    public void printMapaJogador() {
        mapa.printTabuleiro();
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
            if (horizontal && mapa.getTabuleiro()[linha][coluna + i] != "V") {
                return false;
            }
            if (!horizontal && mapa.getTabuleiro()[linha + i][coluna] != "V") {
                return false;
            }
        }

        return true;

    }

    private void posicionarEmbarcacoes(Embarcacao[] embarcacoes) {
        for (Embarcacao embarcacao : embarcacoes) {
            boolean posicionado = false;

            while (!posicionado) {
                int linha = this.random.nextInt(this.TAMANHO_TABULEIRO);
                int coluna = this.random.nextInt(this.TAMANHO_TABULEIRO);
                boolean horizontal = random.nextBoolean();

                if (podePosicionar(embarcacao, linha, coluna, horizontal)) {
                    posicionarEmbarcacao(embarcacao, linha, coluna, horizontal);
                    posicionado = true;
                }

            }
        }
    }

    public void printMapa() {
        this.mapa.printTabuleiro();
    }

    public void printMapaRival() {
        this.mapaInimigo.printTabuleiro();
    }

    public String getNome() {
        return this.nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public String[][] getMapa() {
        return mapa.getTabuleiro();
    }

    public String[][] getMapaRival() {
        return mapaInimigo.getTabuleiro();
    }
}