package features.game;
import java.lang.Math;
import java.util.Scanner;

public class GuessGame {
    Scanner scanner = new Scanner(System.in);

    public GuessGame() {}


    public boolean GuessGameCore(int answer) {
        System.out.print("Enter number: ");
        int guess = scanner.nextInt();

        if (answer > guess) {
            System.out.println("UP");
        } else if (answer < guess) {
            System.out.println("DOWN");
        } else {
            return true;
        }
        return false;
    }


    public String gameFor() {
        int answer = (int)(Math.random() * 100) + 1;

        for (int tries = 1; tries <= 10; tries++) {
            boolean isClear = GuessGameCore(answer);
            if (isClear) {
                return "You got it in " + tries + " tries.\nThe answer was " + answer;
            }
        }
        return "You lost. The answer was " + answer;
    }


    public String gameWhile() {
        int answer = (int)(Math.random() * 100) + 1;
        int tries = 1;

        while (tries <= 10) {
            boolean isClear = GuessGameCore(answer);
            if (isClear) {
                return "You got it in " + tries + " tries.\nThe answer was " + answer;
            }
            tries++;
        }

        return "You lost. The answer was " + answer;
    }


    public String gameDoWhile() {
        int answer = (int)(Math.random() * 100) + 1;
        int tries = 1;

        do {
            boolean isClear = GuessGameCore(answer);
            if (isClear) {
                return "You got it in " + tries + " tries.\nThe answer was " + answer;
            }            
            tries++;
        } while (tries <= 10);

        return "You lost. The answer was " + answer;
    }
}
