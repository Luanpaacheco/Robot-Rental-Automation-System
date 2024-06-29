package ui.locacao;

import aplicacao.ACMERobots;
import dados.cliente.*;
import dados.Locacao;
import dados.Status;
import ui.Aplicacao;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class TelaCriarLocacao extends JDialog {
    ACMERobots acmeRobots;
    Aplicacao aplicacao;
    private JPanel contentPane;
    private JPanel painel;
    private JTextField numeroField;
    private JTextArea textAreaClientes;
    private JTextField situacaoField;
    private JTextField dataInicioField;
    private JTextField dataFimField;
    private JTextField clienteField;
    private JButton fecharButton;
    private JButton continuarButton;
    private JButton limparButton;


    public TelaCriarLocacao(Aplicacao aplicacao, ACMERobots acmeRobots) {
        this.acmeRobots = acmeRobots;
        this.aplicacao = aplicacao;


        textAreaClientes.setText(acmeRobots.getListaClientes().toString());

        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numeroField.setText("");
                situacaoField.setText("");
                dataInicioField.setText("");
                dataFimField.setText("");
                clienteField.setText("");
            }
        });

        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.valueOf(numeroField.getText());
                    String situacaoString = situacaoField.getText();
                    String dataInicioString = dataInicioField.getText();
                    int dataFim = Integer.valueOf(dataFimField.getText());
                    int codigoCliente = Integer.valueOf(clienteField.getText());

                    Date dataInicio = acmeRobots.dataConvertida(dataInicioString);
                    Status situacao = Status.valueOf(situacaoString);
                    Cliente cliente = acmeRobots.consultaCodigo(codigoCliente);

                    if(cliente == null) {
                        JOptionPane.showMessageDialog(aplicacao, "Essa cliente nao esta cadastrado.");
                    } else {
                    Locacao novaLocacao = new Locacao(numero, situacao, dataInicio, dataFim, cliente);

                    if(acmeRobots.adicionarLocacao(novaLocacao)) {
                        numeroField.setText("");
                        situacaoField.setText("");
                        dataInicioField.setText("");
                        dataFimField.setText("");
                        clienteField.setText("");
                        aplicacao.mudaPainel(2);
                    } else {
                        JOptionPane.showMessageDialog(aplicacao, "Essa locacao já foi cadastrada.");
                        numeroField.setText("");
                        situacaoField.setText("");
                        dataInicioField.setText("");
                        dataFimField.setText("");
                        clienteField.setText("");
                        }
                    }

                }catch (Exception c){
                    JOptionPane.showMessageDialog(aplicacao, "Campos prenchidos de forma incorreta.");
                }
            }
        });
    }





    public JPanel getPainel() {
        return painel;
    }

}
