import java.io.IOException;
import java.io.PrintStream;

import java.net.Socket;
import java.util.Scanner;

public class Cliente1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4000);
        System.out.println("Digite algo: ");
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            ClienteThread clienteThread = new ClienteThread(socket);
            clienteThread.start();
            PrintStream saida = new PrintStream(socket.getOutputStream());
            String teclado = sc.nextLine();
            saida.println(teclado);
        }
    }
}
