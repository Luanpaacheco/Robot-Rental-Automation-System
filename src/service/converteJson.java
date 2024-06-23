package service;

import aplicacao.ACMERobots;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dados.robo.Robo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class converteJson {
    private ACMERobots acmeRobots;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public converteJson(ACMERobots acmeRobots) {
        this.acmeRobots = acmeRobots;
    }

    public void converte(Object a) {
        String json = gson.toJson(acmeRobots.getListaRobos());
        System.out.println(json);
    }
    public  void salvarDados(Object o, String nomeArquivo) {
        try (FileWriter writer = new FileWriter(nomeArquivo + ".json")) {
            gson.toJson(o, writer);
            System.out.println("Dados salvos com sucesso no arquivo '" + nomeArquivo + ".json'");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
