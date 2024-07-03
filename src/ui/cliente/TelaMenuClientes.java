package ui.cliente;

import aplicacao.ACMERobots;
import dados.cliente.Cliente;
import ui.Aplicacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class TelaMenuClientes {
    private ACMERobots acmeRobots = ACMERobots.getInstance();
    private Aplicacao aplicacao;
    private JButton INDIVIDUAL;
    private JButton EMPRESARIAL;
    private JButton MOSTRAR;
    private JPanel principal;
    private JTextArea textArea1;
    private JLabel Legenda;
    private JButton limpar;
    private JButton voltarButton;

    public TelaMenuClientes(Aplicacao aplicacao) {
        this.aplicacao=aplicacao;

        INDIVIDUAL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                aplicacao.mudaPainel(6);

            }
        });


        EMPRESARIAL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicacao.mudaPainel(7);
            }
        });
        MOSTRAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(acmeRobots.getListaClientes().isEmpty()){
                    JOptionPane.showMessageDialog(aplicacao,"cadastre pelo menos um cliente!");
                }else {
                    textArea1.setText("");
                    acmeRobots.getListaClientes().stream().sorted(Comparator.comparingInt(Cliente::getCodigo)).forEach(a-> textArea1.append(a.toString()+"\n"));
                }

            }
        });
        limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicacao.mudaPainel(0);
            }
        });
    }
    public JPanel getPainel() {
        return principal;
    }

}
