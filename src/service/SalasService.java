package service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void enviarParaSala(String nomeSala, String mensagem){
        List<PrintWriter> clientes = salas.get(nomeSala);
        if (clientes != null){
            for (PrintWriter cliente: clientes){
                cliente.println(mensagem);
            }
        }
    }

    public void listarSalas(){
        System.out.println(salas.values());
    }
}
