package co.ga.nyc;

public class Bot extends Player{
    private String name = "Bot";

    public Bot(int currentMove, int score) {
        super(currentMove, score);
        this.name = "Bot";
    }

    @Override
    public  int throwSymbol() {
        double randomNumberGenerator = Math.floor(Math.random() * 3+1);
//        return Integer.parseInt(randomNumberGenerator);
        return (int) randomNumberGenerator;
    }
}
