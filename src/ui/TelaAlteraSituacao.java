package ui;

import aplicacao.ACMERobots;
import dados.Status;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAlteraSituacao {
    private ACMERobots acmeRobots = ACMERobots.getInstance();
    private JTextField textFieldNumero;
    private JTextField textFieldSituacao;
    private JButton MostrarLocacao;
    private JButton TrocarSituacao;
    private JTextArea textArea1;
    private JPanel painel1;
    private JButton Voltar;

    public TelaAlteraSituacao(Aplicacao aplicacao) {

        //textAreaLocacao.setText(acmeRobots.getListaLocacoes().toString()+"\n"+acmeRobots.getListaReserva().toString());

        MostrarLocacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numero = Integer.valueOf(textFieldNumero.getText());
                textArea1.setText(acmeRobots.locacaoCerta(numero).toString());
            }
        });
        TrocarSituacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String situacao = textFieldSituacao.getText().toUpperCase();
                int numero1 = Integer.valueOf(textFieldNumero.getText());
                if(acmeRobots.alterarSituacao(numero1, situacao)) {
                    textArea1.setText(acmeRobots.locacaoCerta(numero1).toString());
                } else {
                    textArea1.setText("Erro.");
                }


            }
        });
        Voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicacao.mudaPainel(0);
            }
        });


    }
    public JPanel getPainel() {
        return painel1;
    }



}

