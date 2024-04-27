package entities;

public class Embarcacao {
    private String name;
    private int tamanho;

    public Embarcacao(String name, int tamanho) {
        this.name = name;
        this.tamanho = tamanho;
    }

    public String getName() {
        return name;
    }

    public int getTamanho() {
        return tamanho;
    }

    public String toString() {
        return name;
    }
}

class PortaAviao extends Embarcacao { 
    public PortaAviao(String name, int tamanho) { // 2 unidades
        super(name, tamanho);
    }
}

class Destroyer extends Embarcacao { 
    public Destroyer(String name, int tamanho) { // 3 unidades
        super(name, tamanho);
    }
}

class Submarino extends Embarcacao { 
    public Submarino(String name, int tamanho) { // 4 unidades
        super(name, tamanho);
    }
}

class Fragata extends Embarcacao {
    public Fragata(String name, int tamanho) { // 5 unidades
        super(name, tamanho);
    }
}

class Bote extends Embarcacao {
    public Bote(String name, int tamanho) { // 6 unidades
        super(name, tamanho);
    }
}
