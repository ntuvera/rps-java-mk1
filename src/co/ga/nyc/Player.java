package co.ga.nyc;

public abstract class Player {
    private String name;
    private int currentMove;
    private int score;

    public Player(int currentMove, int score) {
        this.currentMove = currentMove;
        this.score = score;
    }

    public abstract int throwSymbol();
}

