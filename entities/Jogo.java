package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    private final int TAMANHO_TABULEIRO = 16;
    private int quantidadeJogadores;
    private int jogando;
    private ArrayList<Jogador> jogadores;
    private Scanner sc;

    public Jogo() {
        sc = new Scanner(System.in);
        jogadores = new ArrayList<>();
        jogando = 0;
    }

    public Jogo(int quantidade) {
        this();
        this.quantidadeJogadores = quantidade;
    }

    public boolean Jogar(Jogador jogador, Jogador jogadorInimigo) {
        int linha, coluna;
        while (true) {
            System.out.println("1. " + jogadorInimigo.getNome() + ", deseja ver o seu tabuleiro?\n2. Ataque!!!\nOpção:");
            int opcao = sc.nextInt();
            if (opcao == 1) {
                jogadorInimigo.printMapa();
                System.out.println("Pressione 1 e Enter para continuar....");
                int continuar = 0;
                while (continuar != 1)
                continuar = sc.nextInt();
                clearScreen();
            } else if (opcao == 2) {
                System.out.println(jogador.getNome() + " atacando " + jogadorInimigo.getNome());
                jogador.printMapaRival();
                break;
            } else {
                System.out.println("Opção invalida.\n");
            }

        }
        System.out.println("É a vez de " + jogador.getNome() + "!!!!");
        do {
            System.out.println("Insira as coordenadas do ataque (Linha e Coluna):");
            linha = sc.nextInt();
            coluna = sc.nextInt();

            if (linha < 0 || linha >= this.TAMANHO_TABULEIRO || coluna < 0 || coluna >= this.TAMANHO_TABULEIRO
                    || jogador.getMapaRival()[linha][coluna] == "Y" || jogador.getMapaRival()[linha][coluna] == "X") {
                System.out.println("Coordenadas inválidas. Por favor, insira novamente.");
            }
        } while (linha < 0 || linha >= this.TAMANHO_TABULEIRO || coluna < 0 || coluna >= this.TAMANHO_TABULEIRO
                || jogador.getMapaRival()[linha][coluna] == "Y" || jogador.getMapaRival()[linha][coluna] == "X");

        if (jogadorInimigo.getMapa()[linha][coluna] != "V") {
            switch (jogadorInimigo.getMapa()[linha][coluna]) {
                case "A":
                    System.out.println("Acertou um Porta-Aviões!!!");
                    jogador.portaAviaoPartesAcertadas++;
                    break;
                case "B":
                    System.out.println("Acertou um Bote!!!");
                    jogador.botePartesAcertadas++;
                    break;
                case "D":
                    System.out.println("Acertou um Destroyer!!!");
                    jogador.destroyerPartesAcertadas++;
                    break;
                case "S":
                    System.out.println("Acertou um Submarino!!!");
                    jogador.submarinoPartesAcertadas++;
                    break;
                case "F":
                    System.out.println("Acertou um Fragata!!!");
                    jogador.fragataPartesAcertadas++;
                    break;
                default:
                    break;
            }
            jogador.getMapaRival()[linha][coluna] = "Y";
            jogadorInimigo.getMapa()[linha][coluna] = "Y";
            System.out.println(jogador.getNome() + " acertou!!!");
        } else {
            jogadorInimigo.getMapa()[linha][coluna] = "X";
            jogador.getMapaRival()[linha][coluna] = "X";
        }

        jogador.printMapaRival();
        if (verificaVencedor(jogadorInimigo) && this.quantidadeJogadores == 2) {
            return false;
        } else if (this.quantidadeJogadores == 4 && verificaVencedorDupla()) {
            return false;
        } else {
            return true;
        }

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void Tabuleiro() {
        CriaPlayers();
        boolean rodar = true;
        for (Jogador jogador : this.jogadores) {
            jogador.arruma();
        }
        while (rodar) {
            int i = 0;
            boolean z = false;
            Jogador vaiJogar = null;
            for (Jogador jogador : this.jogadores) {
                if (i == jogando % this.quantidadeJogadores) {
                    vaiJogar = jogador;
                    if (i == quantidadeJogadores - 1) {
                        rodar = Jogar(jogador, this.jogadores.get(0));
                    } else
                        z = true;
                } else if (z) {
                    rodar = Jogar(vaiJogar, jogador);
                    break;
                }
                i++;
            }
            this.jogando++;
        }
    }

    public boolean verificaVencedor(Jogador a) {
        for (int i = 0; i < this.TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < this.TAMANHO_TABULEIRO; j++) {
                if (a.getMapa()[i][j] != "V" && a.getMapa()[i][j] != "Y" && a.getMapa()[i][j] != "X") {
                    return false;
                }
            }
        }
        if (this.quantidadeJogadores == 2) {
            System.out.println("O time " + (jogando % 2 + 1) + " a partida!!!.");
            mostrarLog();
        }
        return true;
    }

    public void mostrarLog() {
        for (Jogador jogador : this.jogadores) {
            System.out.println("Relatório de navios afundados por " + jogador.getNome() + ":");
            System.out.println("Porta aviões: " + jogador.portaAviaoPartesAcertadas / 8);
            System.out.println("Destroyers: " + jogador.destroyerPartesAcertadas / 5);
            System.out.println("Submarino: " + jogador.submarinoPartesAcertadas / 4);
            System.out.println("Fragata: " + jogador.fragataPartesAcertadas / 3);
            System.out.println("Bote: " + jogador.botePartesAcertadas / 2);

        }
    }

    public boolean verificaVencedorDupla() {
        boolean verifica = true;
        int i = 0;
        for (Jogador jogador : this.jogadores) {
            if (i == (jogando % this.quantidadeJogadores) + 1 && verifica == true) {
                System.out.println("Oi ele ta retornando o seguinte valor " + verifica);
                verifica = verificaVencedor(jogador);
            } else if (verifica == false) {
                System.out.println("Ele entrou no return e ta retornando " + verifica);
                return verifica;
            }
            i++;
        }
        System.out.println("A equipe " + (jogando + 1) + " ganhou a Partida!!!");
        mostrarLog();
        return verifica;
    }

    private void CriaPlayers() {
        for (int i = 0; i < this.quantidadeJogadores; i++) {
            System.out.print("Nome do " + (i + 1) + "º jogador: ");
            String nome = sc.nextLine();
            Jogador jogador = new Jogador(0, nome);
            this.jogadores.add(jogador);
        }
    }
}