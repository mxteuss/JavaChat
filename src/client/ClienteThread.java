package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClienteThread extends Thread{
    private final Socket socket;

    public ClienteThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(inputStreamReader);
            String x;
            while ((x = br.readLine()) != null) {
                System.out.println(x);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
