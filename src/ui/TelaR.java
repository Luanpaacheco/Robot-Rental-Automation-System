package ui;

import aplicacao.ACMERobots;
import dados.Locacao;
import dados.robo.Robo;
import service.CarregarDados;
import service.converteJson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

public class TelaR {
    CarregarDados carregarDados;
    private converteJson a;
    private Aplicacao aplicacao;
    Locacao locacao = new Locacao();
    private ACMERobots acmeRobots;
    private List<Robo> listaRobos;
    private JPanel panel1;
    private JButton limparButton;
    private JButton fecharButton;
    private JTextArea estadoArea;
    private JButton exibirButton;
    private JLabel imgRoboField;
    private JButton domesticoButton;
    private JButton agricolaButton;
    private JButton industrialButton;
    private JButton salvarRobosButton;
    private JTextField arquivoNomeField;
    private JButton carregarDadosButton;
    private JTextField carregarField;

    public TelaR(Aplicacao app, ACMERobots robots) {
        acmeRobots = robots;
        aplicacao = app;
        listaRobos = acmeRobots.getListaRobos();
        a =  new converteJson(acmeRobots);
        carregarDados = new CarregarDados(listaRobos);


        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estadoArea.setText("");
            }
        });

        exibirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listaRobos.isEmpty()){
                    estadoArea.setText("Adicione pelo menos 1 Robo!");
                }
                estadoArea.setText("");
                listaRobos.stream()
                        .sorted(Comparator.comparingInt(Robo::getId))
                        .forEach(a -> estadoArea.append(a.toString() + "\n"));

                a.converte(listaRobos);

            }
        });
        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                app.mudaPainel(0);
            }
        });
        domesticoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mudaPainel(2);
            }
        });

        industrialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mudaPainel(4);
            }
        });
        agricolaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mudaPainel(3);
            }
        });
        salvarRobosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeArquivo = arquivoNomeField.getText();
                if(nomeArquivo.isEmpty()){
                    JOptionPane.showMessageDialog(aplicacao, "Para salvar digie o nome do arquivo!\nSem extenção");
                }else{
                    a.salvarDados(listaRobos, nomeArquivo);
                }
            }
        });
        carregarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String arquivo = carregarField.getText();
                carregarDados.carregarDadosDoArquivoRobo(arquivo);
            }
        });
    }

    public JPanel getPainel() {
        estadoArea.setText("");
        return panel1;
    }
}
