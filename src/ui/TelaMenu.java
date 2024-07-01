package ui;

import aplicacao.ACMERobots;
import dados.Locacao;
import dados.Status;
import service.CarregarDados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenu {
    private CarregarDados carregarDados;
    private ACMERobots acmeRobots = ACMERobots.getInstance();

    private JPanel panel1;
    private JButton voltarButton;
    private JButton cadastrarRoboButton;
    private JButton cadastrarClienteButton;
    private JButton domesticoButton;
    private JTextArea informacoesArea;
    private JButton limparButton;
    private JButton relatorioGeralButton;
    private JButton salvarButton;
    private JButton carregarDadosButton;
    private JTextField textField1;
    private JButton processarLocacoes;
    private JButton consultarTodsLocacoes;
    private JButton mudarSituacaoLocacao;


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
        carregarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dados = textField1.getText();
                carregarDados.carregarRobosDados(dados);

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
                    System.out.println("Erro: a lista esta vazia.");
                }
                acmeRobots.processarLocacoes();
                for(Locacao l : acmeRobots.getListaReserva()){
                    if(l.getSituacao()== Status.CADASTRADA){
                        System.out.println(l.toString());
                    }
                }


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
    }
    public JPanel getPainel() {
        return panel1;
    }
}
