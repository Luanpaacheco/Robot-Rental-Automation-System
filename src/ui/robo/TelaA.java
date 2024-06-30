package ui.robo;

import aplicacao.ACMERobots;
import dados.robo.Agricola;
import ui.Aplicacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaA {
    private ACMERobots acmeRobots = ACMERobots.getInstance();

    private Aplicacao aplicacao ;
    private JButton cadastrarButton;
    private JButton fecharButton;
    private JTextArea estadoArea;
    private JTextField idField;
    private JTextField modeloField;
    private JButton menuRobosButton;
    private JTextField usoField;
    private JTextField areaField;
    private JPanel painel1;
    private JLabel imgRoboField;
    private JButton limparButton;


    public TelaA(Aplicacao app) {
        aplicacao = app;

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
                    String uso = usoField.getText();
                    double area = Double.parseDouble(areaField.getText());
                    Agricola robo = new Agricola(id, modelo,area,uso);
                    if (acmeRobots.adicionarRobo(robo)){
                        JOptionPane.showMessageDialog(aplicacao, "Robo agricola cadastrado com sucesso!");
                        estadoArea.setText(robo.toString());
                    } else {
                        JOptionPane.showMessageDialog(aplicacao, "Id já existente!");
                    }
                } catch(NumberFormatException npe){
                    JOptionPane.showMessageDialog(aplicacao, "Insira os valores corretamente");
                    estadoArea.setText("Id deve receber inteiros\nModelo deve ser caracteres\nArea deve ser números\nUso deve ser caracteres");
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
                usoField.setText("");
                areaField.setText("");

            }
        });
    }

    public JPanel getPainel() {
        estadoArea.setText("");
        return painel1;
    }
}
