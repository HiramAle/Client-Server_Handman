import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Handman {
    public static void main(String[] args) {
        String[] easy = {"uva", "res", "dos", "dodo", "col"};
        String[] medium = {"tacos", "merequetengue", "escuela", "botella", "celular"};
        String[] hard = {"tacos de pastor", "pablo lorenzo", "atun dolores", "enchiladas de yakult", "memes chisyosos"};

        Scanner scan = new Scanner(System.in);
        Random random = new Random();

//        Difficulty
        System.out.println("Choose difficulty");
        String difficulty = scan.nextLine().toLowerCase();
//        Get word based on difficulty
        String word = "";
        switch (difficulty) {
            case "easy" -> word = easy[random.nextInt(0, easy.length)];
            case "medium" -> word = medium[random.nextInt(0, medium.length)];
            case "hard" -> word = hard[random.nextInt(0, hard.length)];
        }
//        Create char array for the words
        char[] hidden_word = word.toCharArray();
        char[] answer = new char[hidden_word.length];
        Arrays.fill(answer, '_');

        for (int i = 0; i < hidden_word.length; i++) {
            if (hidden_word[i] == ' ') {
                answer[i] = hidden_word[i];
            }
        }

        boolean running = true;
        int tries = 6;

        while (running) {
            System.out.print("Your word: ");
            for (char c : answer) {
                System.out.print(" " + c);
            }

            System.out.print(" \nWrite a letter: ");
            String character = scan.nextLine();
            if (character.length() > 1) {
                System.out.println("Invalid input, try again");
            } else {
                if (word.contains(character)) {
                    for (int i = 0; i < hidden_word.length; i++) {
                        if (hidden_word[i] == character.charAt(0)) {
                            answer[i] = hidden_word[i];
                        }
                    }
                } else {
                    System.out.println("Wrong letter");
                    tries--;
                }

                System.out.println("Tries left: " + tries);
                draw(tries);

                if (!Arrays.toString(answer).contains("_")) {
                    System.out.println(Arrays.toString(answer));
                    System.out.println("You won");
                    running = false;
                } else if (tries <= 0) {
                    System.out.println(Arrays.toString(answer));
                    System.out.println("You lost");
                    running = false;
                }
            }
        }
    }

    public static void draw(int tries) {
        switch (tries) {
            case 6 -> {
                System.out.println("|----------");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
            case 5 -> {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
            case 4 -> {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|    |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
            case 3 -> {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|   -|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
            case 2 -> {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|   -|-");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
            case 1 -> {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|   -|-");
                System.out.println("|   /");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
            case 0 -> {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|   -|-");
                System.out.println("|   / \\");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
        }
    }
}

