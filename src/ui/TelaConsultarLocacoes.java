package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaConsultarLocacoes {
    private JPanel panel1;
    private JTextArea textAreaLocacao;
    private JButton fechar;
    private JButton Voltar;

    public TelaConsultarLocacoes() {

        Voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
