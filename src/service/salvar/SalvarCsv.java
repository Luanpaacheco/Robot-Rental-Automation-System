package service.salvar;

import dados.Locacao;
import dados.robo.Robo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Queue;

public class SalvarCsv {

    public void salvarLocacaoDados(String nomeArquivo, Queue<Locacao> locacoes) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo + ".csv"))) {

            bw.write("numero;situacao;datainicio;datafim;codigo;idsrobos");
            bw.newLine();

            for (Locacao locacao : locacoes) {
                StringBuilder linha = new StringBuilder();

                linha.append(locacao.getNumero()).append(";");
                linha.append(locacao.getSituacao()).append(";");
                linha.append(dateFormat.format(locacao.getDataInicio())).append(";");
                linha.append(dateFormat.format(locacao.getDataFim())).append(";");
                linha.append(locacao.getCliente().getCodigo()).append(";");

                List<Robo> robos = locacao.getListaDeRobos();
                for (int i = 0; i < robos.size(); i++) {
                    linha.append(robos.get(i).getId());
                    if (i < robos.size() - 1) {
                        linha.append(";");
                    }
                }

                bw.write(linha.toString());
                bw.newLine();
            }

            System.out.println("Dados salvos com sucesso no arquivo '" + nomeArquivo + ".csv'");

        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados no arquivo '" + nomeArquivo + ".csv': " + e.getMessage());
            e.printStackTrace();
        }
    }
}
