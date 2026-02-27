import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        try (ServerSocket serverSocket1 = new ServerSocket(4000);
             ServerSocket serverSocket2 = new ServerSocket(5000)){


            Socket socket1 = serverSocket1.accept();
            System.out.println("Cliente 1 entrou!");
            Socket socket2 = serverSocket2.accept();
            System.out.println("Cliente 2 entrou!");


            InputStreamReader inputStreamReader1 = new InputStreamReader(socket1.getInputStream());
            PrintStream saida1 = new PrintStream(socket1.getOutputStream());
            BufferedReader br1 = new BufferedReader(inputStreamReader1);
            String msgCliente1;

            InputStreamReader inputStreamReader2 = new InputStreamReader(socket2.getInputStream());
            PrintStream saida2 = new PrintStream(socket2.getOutputStream());
            BufferedReader br2 = new BufferedReader(inputStreamReader2);
            String msgCliente2;

            while ((msgCliente1 = br1.readLine()) != null && (msgCliente2 = br2.readLine()) != null) {
                saida1.println("Cliente 2:" + msgCliente2);
                saida2.println("Cliente 1: " + msgCliente1);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}