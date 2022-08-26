import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Client_Handman {

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
//            Create Socket
            String ip_dir = "127.0.0.1";
            int port = 1234;
            Socket socket = new Socket(ip_dir, port);
            System.out.println("Successful connection to server " + ip_dir + " at port " + port);
//            Create Writer and Reader
            PrintWriter p_writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.ISO_8859_1));
            BufferedReader b_reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.ISO_8859_1));
//            Get difficulty from input
            boolean selecting = true;
            String difficulty = "";
            while(selecting){
                System.out.println("Choose difficulty:");
                System.out.println("- Easy");
                System.out.println("- Medium");
                System.out.println("- Hard");
                difficulty = scan.nextLine();
                if (difficulty.equalsIgnoreCase("easy") || difficulty.equalsIgnoreCase("medium") || difficulty.equalsIgnoreCase("hard")){
                    selecting = false;
                }else{
                    System.out.println("Select a valid difficulty");
                }
            }

//            Send difficulty
            p_writer.println(difficulty);
            p_writer.flush();
//        Create char array for the words
            char[] hidden_word = b_reader.readLine().toCharArray();
//            Close server connection
            b_reader.close();
            p_writer.close();
            socket.close();

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
                    if (String.valueOf(hidden_word).contains(character)) {
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
                        System.out.println("Your word: "+ String.valueOf(answer));
                        System.out.println("You won");
                        running = false;
                    } else if (tries <= 0) {
                        System.out.println("Your word: "+ String.valueOf(answer));
                        System.out.println("You lost");
                        running = false;
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
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
