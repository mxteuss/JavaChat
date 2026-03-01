package server;

import service.SalasService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteHandler extends Thread{

    private final Socket socket;
    private final SalasService salasService;
    private String salaAtual = null;

    public ClienteHandler(Socket socket, SalasService salasService) {
        this.socket = socket;
        this.salasService = salasService;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            String x;

            while ((x = br.readLine()) != null){
                if (x.startsWith("/entrar")){
                salaAtual = x.split(" ")[1];
                salasService.entrarSala(salaAtual, saida);
                } else if (x.startsWith("/listar")) {
                    salasService.listarSalas(socket);
                } else if (salaAtual == null) {
                    saida.println("Entre em uma sala primeiro. Use /entrar <sala>.");
                    saida.println("Use '/listar' para ver todas as salas.");
                }
                else {
                    salasService.enviarParaSala(salaAtual, x);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
