package service.carregarDados.csv;

import aplicacao.ACMERobots;
import java.util.*;
import dados.Locacao;
import dados.Status;
import dados.cliente.Cliente;
import dados.cliente.Empresarial;
import dados.cliente.Individual;
import dados.robo.Agricola;
import dados.robo.Domestico;
import dados.robo.Industrial;
import dados.robo.Robo;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CarregarDadosCsv {




    public List<Robo> carregarRobosDados(String nomeArquivo) {
        List<Robo> robos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo + ".csv"))) {
            String linha;
            boolean primeiraLinha = true; // Para ignorar o cabeçalho, se houver

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue; // Pula a primeira linha (cabeçalho)
                }

                String[] dados = linha.split(";");

                try {
                    // Extrair os dados do CSV
                    int id = Integer.parseInt(dados[0].trim());
                    String modelo = dados[1].trim();
                    int tipo = Integer.parseInt(dados[2].trim());

                    // Inicializar as variáveis opcionais
                    String nivelSetorArea = dados.length > 3 ? dados[3].trim() : "";
                    String uso = dados.length > 4 ? dados[4].trim() : "";

                    Robo robo = null;

                    switch (tipo) {
                        case 1:
                            if (!nivelSetorArea.isEmpty()) {
                                robo = new Domestico(id, modelo, Integer.parseInt(nivelSetorArea));
                            }
                            break;
                        case 2:
                            robo = new Industrial(id, modelo, nivelSetorArea);
                            break;
                        case 3:
                            if (!nivelSetorArea.isEmpty() && !uso.isEmpty()) {
                                robo = new Agricola(id, modelo, Double.parseDouble(nivelSetorArea), uso);
                            }
                            break;
                        default:
                            System.err.println("Erro: tipo de robô inválido na linha " + linha);
                            continue; // Pular linhas com tipos de robô inválidos
                    }

                    if (robo != null) {
                        robos.add(robo);
                    } else {
                        System.err.println("Erro: dados insuficientes para criar o robô na linha " + linha);
                    }

                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter número na linha " + linha + ": " + e.getMessage());
                }
            }
            System.out.println("Dados carregados com sucesso do arquivo '" + nomeArquivo + ".csv'");
        } catch (FileNotFoundException e) {
            System.err.println("Erro: arquivo '" + nomeArquivo + ".csv' não encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo '" + nomeArquivo + ".csv': " + e.getMessage());
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

    public void carregarLocacoesDados(String nomeArquivo,ACMERobots acmeRobots) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo + ".csv"))) {
            String linha;
            boolean primeiraLinha = true; // Para ignorar o cabeçalho, se houver

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue; // Pula a primeira linha (cabeçalho)
                }

                String[] dados = linha.split(";");

                try {
                    // Extrair os dados da locação do CSV
                    int numero = Integer.parseInt(dados[0].trim());
                    Status situacao = Status.valueOf(dados[1].trim()); // Converte a string para enum Status
                    Date dataInicio = dateFormat.parse(dados[2].trim());
                    Date dataFim = dateFormat.parse(dados[3].trim());
                    int codigoCliente = Integer.parseInt(dados[4].trim());

                    // Lista para armazenar os robôs associados à locação
                    List<Robo> robos = new ArrayList<>();

                    // Itera sobre os códigos de robôs na linha atual
                    for (int i = 5; i < dados.length; i++) {
                        int codigoRobo = Integer.parseInt(dados[i].trim());
                        Robo robo = acmeRobots.consultaIdRobo(codigoRobo);
                        if (robo != null) {
                            robos.add(robo);
                        }
                    }

                    // Consulta o cliente pelo código
                    Cliente cliente = acmeRobots.consultaCodigoCliente(codigoCliente);

                    if (cliente != null) {
                        // Cria uma nova locação e a adiciona às reservas do ACMERobots
                        Locacao locacao = new Locacao(numero, situacao, dataInicio, dataFim, cliente);
                        acmeRobots.adicionarReserva(locacao);

                        // Adiciona os robôs à locação
                        for (Robo robo : robos) {
                            acmeRobots.adicionarRoboNaReserva(robo);
                        }
                    }

                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter número na linha " + linha + ": " + e.getMessage());
                } catch (ParseException e) {
                    System.err.println("Erro ao converter data na linha " + linha + ": " + e.getMessage());
                }
            }
            System.out.println("Dados carregados com sucesso do arquivo '" + nomeArquivo + ".csv'");
        } catch (FileNotFoundException e) {
            System.err.println("Erro: arquivo '" + nomeArquivo + ".csv' não encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo '" + nomeArquivo + ".csv': " + e.getMessage());
            e.printStackTrace();
        }
    }

//    public Collection<? extends Locacao> carregarLocacoesDados(String arquivoLocacao) {
//
//    }
}
