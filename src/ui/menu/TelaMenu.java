package ui.menu;

import aplicacao.ACMERobots;
import service.CarregarDados;
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
    private JButton salvarButton;
    private JButton carregarDadosButton;
    private JTextField textField1;
    private JButton processarLocacoes;
    private JButton consultarTodsLocacoes;
    private JButton mudarSituacaoLocacao;
    private JButton iniciarDadosButton;


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
                acmeRobots.carregaDados("EXEMPLO-ROBOS", "EXEMPLO-CLIENTES","EXEMPLO-LOCACOES");
                System.out.println(acmeRobots.getListaRobos().toString());
                System.out.println(acmeRobots.getListaClientes().toString());
            }
        });
    }
    public JPanel getPainel() {
        return panel1;
    }
}
