package ui.menu;

import aplicacao.ACMERobots;
import ui.Aplicacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaRelatorioGeral {
    private ACMERobots acmeRobots = ACMERobots.getInstance();
    private JPanel painel;
    private JTextArea textAreaCliente;
    private JTextArea textAreaRobo;
    private JTextArea textAreaLocacao;
    private JButton voltar;
    private JButton fechar;
    private JPanel panel1;

    public TelaRelatorioGeral(Aplicacao aplicacao) {
        textAreaCliente.setText(acmeRobots.getListaClientes().toString());
        textAreaRobo.setText(acmeRobots.getListaRobos().toString());
        textAreaLocacao.setText(acmeRobots.getListaLocacoes().toString()+"\n"+acmeRobots.getListaReserva().toString());

        fechar.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
        });
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicacao.mudaPainel(0);
            }
        });
    }


    public JPanel getPainel() {
        return panel1;
    }
}

