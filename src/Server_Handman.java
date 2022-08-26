import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.StandardSocketOptions;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Server_Handman {
    public static void main(String[] args) {
        try {
//            Words arrays
            String[] easy = {"uva", "res", "dos", "dodo", "col"};
            String[] medium = {"tacos", "merequetengue", "escuela", "botella", "celular"};
            String[] hard = {"tacos de pastor", "pablo lorenzo", "atun dolores", "enchiladas de yakult", "memes chisyosos"};
            Random random = new Random();
//            Create socket with port 1234
            ServerSocket s_socket = new ServerSocket(1234);

            for (; ; ) {
                System.out.println("Server running...");
                System.out.println("Waiting clients...");
                Socket socket = s_socket.accept();
                System.out.println("Client connected from: " + socket.getInetAddress());
//                Create Reader and Writer
                PrintWriter p_writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.ISO_8859_1));
                BufferedReader b_reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.ISO_8859_1));
//                Get difficulty from client input
                String difficulty = b_reader.readLine().toLowerCase();
//                Select the word based on difficulty
                String word = "";
                switch (difficulty) {
                    case "easy" -> word = easy[random.nextInt(0, easy.length)];
                    case "medium" -> word = medium[random.nextInt(0, medium.length)];
                    case "hard" -> word = hard[random.nextInt(0, hard.length)];
                }
                System.out.println("Difficulty selected: " + difficulty);
                System.out.println("Random word selected: " + word);
//                Send the word
                p_writer.println(word);
                p_writer.flush();
                b_reader.close();
                p_writer.close();
                socket.close();
                System.out.println("Closed connection with client");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
