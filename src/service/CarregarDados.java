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
    public List<Cliente> carregarClientesDados(String nomeArquivo) {
        Cliente cliente = null;

        List<Cliente> clientes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo + ".csv"))) {
            String linha;
            boolean primeiraLinha = true; // Para ignorar o cabeçalho, se houver

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue; // Pula a primeira linha (cabeçalho)
                }
                String[] dados = linha.split(";");
                if (dados.length < 4) {
                    System.err.println("Erro: linha incompleta no arquivo '" + nomeArquivo + "'");
                    continue; // Pular linhas que não têm dados suficientes
                }

                // Extrai os dados do CSV
                int codigo = Integer.parseInt(dados[0].trim());
                String nome = dados[1].trim();
                int tipo = Integer.parseInt(dados[2].trim());
                String ano_cpf = dados[3].trim();

                if (tipo == 1) {
                    cliente = new Individual(codigo, nome, ano_cpf);
                } else if (tipo == 2) {
                    cliente = new Empresarial(codigo, nome, Integer.parseInt(ano_cpf));
                } else {
                    System.err.println("Erro: tipo de cliente inválido na linha " + linha);
                    continue; // Pular linhas com tipos de cliente inválidos
                }

                clientes.add(cliente);
            }
            System.out.println("Dados carregados com sucesso do arquivo '" + nomeArquivo + "'");
        } catch (FileNotFoundException e) {
            System.err.println("Erro: arquivo '" + nomeArquivo + "' não encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo '" + nomeArquivo + "': " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter número no arquivo '" + nomeArquivo + "': " + e.getMessage());
            e.printStackTrace();
        }

        return clientes; // Retorna a lista de clientes, mesmo que vazia em caso de erro
    }
}
