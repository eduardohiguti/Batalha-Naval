package entities;

public class Jogo {
    public void Jogar() {
        Mapa mapa = new Mapa();

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
        
        mapa.initTabuleiro();
        mapa.printTabuleiro();

        for (Embarcacao embarcacao : embarcacoes) {
          System.out.println(embarcacao);
        }
    }
}
