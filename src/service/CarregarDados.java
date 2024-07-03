package service;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import dados.cliente.Cliente;
import dados.cliente.Empresarial;
import dados.cliente.Individual;
import dados.robo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

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

}
