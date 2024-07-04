package ui.locacao;

import aplicacao.ACMERobots;
import dados.cliente.*;
import dados.Locacao;
import dados.Status;
import ui.Aplicacao;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class TelaCriarLocacao extends JDialog {
    private ACMERobots acmeRobots = ACMERobots.getInstance();
    Aplicacao aplicacao;
    private JPanel contentPane;
    private JPanel painel;
    private JTextField numeroField;
    private JTextArea textAreaClientes;

    private JTextField dataInicioField;
    private JTextField dataFimField;
    private JTextField clienteField;
    private JButton fecharButton;
    private JButton continuarButton;
    private JButton limparButton;
    private JButton voltar;


    public TelaCriarLocacao(Aplicacao aplicacao) {
        this.aplicacao = aplicacao;

        acmeRobots.criarRobosEClientes();

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
                    //String situacaoString = situacaoField.getText();
                    String dataInicioString = dataInicioField.getText();
                    String dataFimString = dataFimField.getText();
                    int codigoCliente = Integer.valueOf(clienteField.getText());

                    Date dataInicio = acmeRobots.dataConvertida(dataInicioString);
                    Date dataFim = acmeRobots.dataConvertida(dataFimString);
                   // Status situacao = Status.valueOf(situacaoString);
                    Cliente cliente = acmeRobots.consultaCodigoCliente(codigoCliente);

                    if(cliente == null) {
                        JOptionPane.showMessageDialog(aplicacao, "Essa cliente nao esta cadastrado.");
                    } else {
                    Locacao novaLocacao = new Locacao(numero, Status.CADASTRADA, dataInicio, dataFim, cliente);

                    if( acmeRobots.adicionarReserva(novaLocacao)) {
                        numeroField.setText("");

                        dataInicioField.setText("");
                        dataFimField.setText("");
                        clienteField.setText("");
                        aplicacao.mudaPainel(9);
                    } else {
                        JOptionPane.showMessageDialog(aplicacao, "Essa locacao já foi cadastrada.");
                        numeroField.setText("");

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
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicacao.mudaPainel(0);
            }
        });
    }





    public JPanel getPainel() {
        return painel;
    }

}
