package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente1 {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4000);
        System.out.println("Digite algo: ");
        Scanner sc = new Scanner(System.in);
        PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
        new ClienteThread(socket).start();

        String linha;
        while ((linha = sc.nextLine()) != null) {
            if (linha.startsWith("/")){
                saida.println(linha);
            }
            else {
        saida.println("Cliente 1: " + linha);}
        }
    }
}
