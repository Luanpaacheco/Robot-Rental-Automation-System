package ui.robo;

import aplicacao.ACMERobots;
import dados.robo.Domestico;
import ui.Aplicacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaD {
    ACMERobots acmeRobots;
    private Aplicacao aplicacao ;
    private JPanel panel1;
    private JTextField nivelField;
    private JButton cadastrarButton;
    private JLabel imgRoboField;
    private JButton fecharButton;
    private JTextArea estadoArea;
    private JTextField idField;
    private JTextField modeloField;
    private JButton menuRobosButton;
    private JButton limparButton;


    public TelaD(Aplicacao app, ACMERobots robots) {
        aplicacao = app;
        acmeRobots = robots;

        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int id = Integer.parseInt(idField.getText());
                    String modelo = modeloField.getText();
                    int nivel = Integer.parseInt(nivelField.getText());
                    if (nivel != 1 && nivel != 2 && nivel != 3 ){
                        JOptionPane.showMessageDialog(aplicacao, "Insira um nível válido 1 | 2 | 3");
                    } else {
                        Domestico robo = new Domestico (id, modelo, nivel);
                        if (acmeRobots.adicionarRobo(robo)){
                            JOptionPane.showMessageDialog(aplicacao, "Robo doméstico cadastrado com sucesso!");
                            estadoArea.setText(robo.toString());
                        } else {
                            JOptionPane.showMessageDialog(aplicacao, "Id já existente!");
                        }
                    }
                } catch(NumberFormatException npe){
                    JOptionPane.showMessageDialog(aplicacao, "Insira os valores corretamente");
                    estadoArea.setText("Id deve receber inteiros\nModelo deve ser caracteres\nNivel deve inteiros de 1 a 3");
                }
            }
        });
        menuRobosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicacao.mudaPainel(1);
            }
        });
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estadoArea.setText("");
                idField.setText("");
                modeloField.setText("");
                nivelField.setText("");
            }
        });
    }

    public JPanel getPainel() {
        estadoArea.setText("");
        return panel1;
    }
}
