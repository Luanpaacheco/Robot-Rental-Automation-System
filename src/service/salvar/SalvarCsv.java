package service.salvar;

import dados.Locacao;
import dados.robo.Robo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class SalvarCsv {

//    public void salvarLocacaoDados(String nomeArquivo, List<Locacao> locacoes) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo + ".csv"))) {
//
//            // Escreve o cabeçalho
//            bw.write("Numero;Situacao;DataInicio;DataFim;CodigoCliente;CodigosRobos");
//            bw.newLine();
//
//            // Escreve cada locação na lista
//            for (Locacao locacao : locacoes) {
//                StringBuilder linha = new StringBuilder();
//
//                // Número da locação
//                linha.append(locacao.getNumero()).append(";");
//
//                // Situação da locação
//                linha.append(locacao.getSituacao()).append(";");
//
//                // Data de início
//                linha.append(dateFormat.format(locacao.getDataInicio())).append(";");
//
//                // Data de fim
//                linha.append(locacao.getDataFim()).append(";");
//
//                // Código do cliente
//                linha.append(locacao.getCliente().getCodigo()).append(";");
//
//                // Códigos dos robôs
//                List<Robo> robos = locacao.getRobos();
//                for (int i = 0; i < robos.size(); i++) {
//                    linha.append(robos.get(i).getId());
//                    if (i < robos.size() - 1) {
//                        linha.append(",");
//                    }
//                }
//
//                // Escreve a linha no arquivo
//                bw.write(linha.toString());
//                bw.newLine();
//            }
//
//            System.out.println("Dados salvos com sucesso no arquivo '" + nomeArquivo + ".csv'");
//
//        } catch (IOException e) {
//            System.err.println("Erro ao salvar os dados no arquivo '" + nomeArquivo + ".csv': " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
}
