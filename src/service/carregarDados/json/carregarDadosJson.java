package service.carregarDados.json;

import aplicacao.ACMERobots;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import dados.Locacao;
import dados.Status;
import dados.cliente.Cliente;
import dados.cliente.Empresarial;
import dados.cliente.Individual;
import dados.robo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class carregarDadosJson {
    ACMERobots acmeRobots = ACMERobots.getInstance();

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
        return adicionarRobosUnicos(robos);
    }

    public List<Cliente> carregarDadosClientesJson(String nomeArquivo) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Opção para formatar o JSON de saída para facilitar a leitura (opcional)

        // Configuração para lidar com polimorfismo de tipos usando anotações Jackson
        SimpleModule module = new SimpleModule();
        module.addAbstractTypeMapping(Cliente.class, Empresarial.class);
        module.addAbstractTypeMapping(Cliente.class, Individual.class);
        objectMapper.registerModule(module);

        List<Cliente> clientes = null;

        try {
            // Ler o arquivo JSON e converter para uma lista de objetos Robo
            File arquivo = new File(nomeArquivo+".json");
            CollectionType tipoColecao = objectMapper.getTypeFactory().constructCollectionType(List.class, Robo.class);
            clientes = objectMapper.readValue(arquivo, tipoColecao);
            System.out.println("Dados carregados com sucesso do arquivo '" + nomeArquivo + ".json'");
        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados do arquivo '" + nomeArquivo + "': " + e.getMessage());
            e.printStackTrace();
        }
        return adicionarClientesUnicos(clientes);
    }
    private List<Robo> adicionarRobosUnicos(List<Robo> novosRobos) {
        List<Robo> robosParaAdicionar = new ArrayList<>();
        Set<Integer> idsExistentes = new HashSet<>();
        for (Robo robo : acmeRobots.getListaRobos()) {
            idsExistentes.add(robo.getId());
        }

        for (Robo novoRobo : novosRobos) {
            if (!idsExistentes.contains(novoRobo.getId())) {
                robosParaAdicionar.add(novoRobo);
                System.out.println("Unicos" + novoRobo.toString());
            }
        }
        return robosParaAdicionar;
    }
    private List<Cliente> adicionarClientesUnicos(List<Cliente> novosClientes) {
        List<Cliente> clientesParaAdicionar = new ArrayList<>();
        Set<Integer> codigosExistentes = new HashSet<>();
        for (Cliente cliente : acmeRobots.getListaClientes()) {
            codigosExistentes.add(cliente.getCodigo());
        }

        for (Cliente novoCliente : novosClientes) {
            if (!codigosExistentes.contains(novoCliente.getCodigo())) {
                clientesParaAdicionar.add(novoCliente);
                System.out.println("Unicos" + novoCliente.toString());
            }
        }
        return clientesParaAdicionar;
    }
    public List<Locacao> carregarDadosLocacoesJson(String nomeArquivo) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        SimpleModule module = new SimpleModule();
        module.addAbstractTypeMapping(Locacao.class, Locacao.class);
        module.addAbstractTypeMapping(Status.class, Status.class);
        objectMapper.registerModule(module);

        List<Locacao> locacoes = null;

        try {
            File arquivo = new File(nomeArquivo + ".json");
            CollectionType tipoColecao = objectMapper.getTypeFactory().constructCollectionType(List.class, Locacao.class);
            locacoes = objectMapper.readValue(arquivo, tipoColecao);
            System.out.println("Dados carregados com sucesso do arquivo '" + nomeArquivo + ".json'");
        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados do arquivo '" + nomeArquivo + "': " + e.getMessage());
            e.printStackTrace();
        }

        return adicionarLocacoesUnicas(locacoes);
    }
    private List<Locacao> adicionarLocacoesUnicas(List<Locacao> novasLocacoes) {
        List<Locacao> locacoesParaAdicionar = new ArrayList<>();
        Set<Integer> numerosExistentes = new HashSet<>();
        for (Locacao locacao : acmeRobots.getListaLocacoes()) {
            numerosExistentes.add(locacao.getNumero());
        }

        for (Locacao novaLocacao : novasLocacoes) {
            if (!numerosExistentes.contains(novaLocacao.getNumero())) {
                locacoesParaAdicionar.add(novaLocacao);
                System.out.println("Unicos" + novaLocacao.toString());

                // Adicionar robôs à locação
//                for (Robo robo : novaLocacao.getRobos()) {
//                    acmeRobots.adicionarRoboNaReserva(robo);
//                }
            }
        }
        return locacoesParaAdicionar;
    }
}
