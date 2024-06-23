package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import dados.robo.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CarregarJson {

    public List<Robo> carregarDados(String nomeArquivo) {
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
}
