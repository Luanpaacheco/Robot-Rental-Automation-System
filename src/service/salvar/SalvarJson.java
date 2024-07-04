package service.salvar;

import aplicacao.ACMERobots;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;

public class SalvarJson {
    private ACMERobots acmeRobots = ACMERobots.getInstance();
    private ObjectMapper objectMapper = new ObjectMapper();

    public SalvarJson() {

        //this.acmeRobots = acmeRobots;
    }

    public void converte(Object a) {
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(acmeRobots.getListaRobos());
            System.out.println(json);
        } catch (IOException e) {
            System.err.println("Erro ao converter para JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void salvarDados(Object o, String nomeArquivo) {
        try (FileWriter writer = new FileWriter(nomeArquivo + ".json")) {
            objectMapper.writeValue(writer, o);
            System.out.println("Dados salvos com sucesso no arquivo '" + nomeArquivo + ".json'");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
