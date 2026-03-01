package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class SalasService {

    static Map<String, List<PrintWriter>> salas = new HashMap<>();

    public SalasService() {
        salas.put("geral", new ArrayList<>());
        salas.put("off-topic", new ArrayList<>());
    }

    public void entrarSala(String nomeSala, PrintWriter cliente) {
        salas.computeIfAbsent(nomeSala, k -> new ArrayList<>()).add(cliente);
        cliente.println("Você entrou na sala: " + nomeSala);

        enviarParaSala(nomeSala, "Um novo membro entrou na sala.");

    }

    public void enviarParaSala(String nomeSala, String mensagem) {
        List<PrintWriter> clientes = salas.get(nomeSala);
        if (clientes != null) {
            for (PrintWriter cliente : clientes) {
                cliente.println(mensagem);
            }
        }
    }

    public void listarSalas(Socket clienteSocket) throws IOException {
        PrintWriter cliente = new PrintWriter(clienteSocket.getOutputStream(), true);
        salas.forEach((key,value) -> cliente.println(key));
        System.out.println("Usuário usou /listar");
    }
}
