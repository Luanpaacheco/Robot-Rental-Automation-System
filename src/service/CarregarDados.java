package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import dados.robo.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarregarDados {

    public List<Robo> carregarDadosRobosJson(String nomeArquivo) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Opção para formatar o JSON de saída para facilitar a leitura (opcional)

        // Configuração para lidar com polimorfismo de tipos usando anotações Jackson
        SimpleModule module = new SimpleModule();
        module.addAbstractTypeMapping(Robo.class, Industrial.class);
        module.addAbstractTypeMapping(Robo.class, Domestico.class);
        module.addAbstractTypeMapping(Robo.class, Agricola.class);
        objectMapper.registerModule(module);

        List<Robo> robos = null;

        try {
            // Ler o arquivo JSON e converter para uma lista de objetos Robo
            File arquivo = new File(nomeArquivo+".json");
            CollectionType tipoColecao = objectMapper.getTypeFactory().constructCollectionType(List.class, Robo.class);
            robos = objectMapper.readValue(arquivo, tipoColecao);
            System.out.println("Dados carregados com sucesso do arquivo '" + nomeArquivo + ".json'");
        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados do arquivo '" + nomeArquivo + "': " + e.getMessage());
            e.printStackTrace();
        }
        return robos;
    }
    public List<Robo> carregarRobosDados(String nomeArquivo) {
        Robo robo;

        List<Robo> robos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo+".csv"))) {
            String linha;
            boolean primeiraLinha = true; // Para ignorar o cabeçalho, se houver

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue; // Pula a primeira linha (cabeçalho)
                }

                String[] dados = linha.split(";");

                // Extrai os dados do CSV
                int id = Integer.parseInt(dados[0].trim());
                String modelo = dados[1].trim();
                int tipo = Integer.parseInt(dados[2].trim());
                String nivelSetorArea = dados[3].trim();
                String uso = dados[4].trim();
                if(tipo == 1){
                    robo = new Domestico(id, modelo, Integer.parseInt(nivelSetorArea));
                } else if(tipo == 2){
                    robo = new Industrial(id, modelo, nivelSetorArea);
                } else {
                    robo = new Agricola(id, modelo, Double.parseDouble(nivelSetorArea),uso);
                }
                if (robo != null) {
                    robos.add(robo);
                }
            }
            System.out.println("Dados carregados com sucesso do arquivo '" + nomeArquivo + "'");
        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados do arquivo '" + nomeArquivo + "': " + e.getMessage());
            e.printStackTrace();
        }

        return robos;
    }
}
