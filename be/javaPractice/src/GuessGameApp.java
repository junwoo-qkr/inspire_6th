import features.game.GuessGame;

public class GuessGameApp {
    public static void main(String[] args) {
        GuessGame game = new GuessGame();

        String result1 = game.gameFor();
        System.out.println(result1);

        String result2 = game.gameWhile();
        System.out.println(result2);

        String result3 = game.gameDoWhile();
        System.out.println(result3);
    }
}
