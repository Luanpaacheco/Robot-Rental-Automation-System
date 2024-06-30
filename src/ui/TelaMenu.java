package ui;

import aplicacao.ACMERobots;
import service.CarregarDados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenu {
    private CarregarDados carregarDados;

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


    public TelaMenu(Aplicacao app, ACMERobots robots){


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
    }
    public JPanel getPainel() {
        return panel1;
    }
}
