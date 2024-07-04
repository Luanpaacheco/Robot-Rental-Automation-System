package ui.menu;

import aplicacao.ACMERobots;
import service.carregarDados.csv.CarregarDadosCsv;
import ui.Aplicacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenu {
    private CarregarDadosCsv carregarDados;
    private ACMERobots acmeRobots = ACMERobots.getInstance();
    private Aplicacao app;

    private JPanel panel1;
    private JButton voltarButton;
    private JButton cadastrarRoboButton;
    private JButton cadastrarClienteButton;
    private JButton domesticoButton;
    private JTextArea informacoesArea;
    private JButton limparButton;
    private JButton relatorioGeralButton;
    private JButton processarLocacoes;
    private JButton consultarTodsLocacoes;
    private JButton mudarSituacaoLocacao;
    private JButton iniciarDadosButton;
    private JTextField roboArquivoField1;
    private JTextField clienteArquivoField2;
    private JTextField locacaoArquiviField3;


    public TelaMenu(Aplicacao app){



        cadastrarRoboButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mudaPainel(1);
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cadastrarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mudaPainel(5);
            }
        });

        domesticoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mudaPainel(8);
            }
        });
        processarLocacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(acmeRobots.getListaReserva().isEmpty()) {
                    JOptionPane.showMessageDialog(app, "Nenhuma locação necessita ser processada");;
                }
                acmeRobots.processarLocacoes();
                informacoesArea.setText("Locações processadas, visualize em 'relatório geral' ");
//                for(Locacao l : acmeRobots.getListaReserva()){
//                    if(l.getSituacao()== Status.CADASTRADA){
//                        System.out.println(l.toString());
//                    }
//                }


            }
        });
        consultarTodsLocacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mudaPainel(12);
            }
        });
        mudarSituacaoLocacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mudaPainel(13);
            }
        });
        relatorioGeralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mudaPainel(11);
            }
        });
        iniciarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String arquivoRobo = roboArquivoField1.getText();
                String arquivoCliente = clienteArquivoField2.getText();
                String arquivoLocacao = locacaoArquiviField3.getText();
                try{
                    acmeRobots.carregaDados(arquivoRobo, arquivoCliente,arquivoLocacao);
                    System.out.println(acmeRobots.getListaRobos().toString());
                    System.out.println(acmeRobots.getListaClientes().toString());
                }catch (Exception a){
                    System.out.println(a.getMessage());
                    informacoesArea.setText("Opa teve algum problema!");
                }

            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public JPanel getPainel() {
        return panel1;
    }
}
