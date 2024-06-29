package ui.locacao;

import aplicacao.ACMERobots;
import dados.*;
import dados.cliente.Individual;
import dados.robo.*;
import ui.Aplicacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAddRobos extends JDialog {
    ACMERobots acmeRobots;
    Aplicacao aplicacao;
    private JPanel contentPane;
    private JTextArea textAreaResult;
    private JTextField robosField;
    private JTextArea textAreaRobos;
    private JButton concluirButton;
    private JButton adicionarButton;
    private JButton fecharButton;
    private JButton limparButton;

    public TelaAddRobos(Aplicacao aplicacao, ACMERobots acmeRobots) {
        this.acmeRobots = acmeRobots;
        this.aplicacao = aplicacao;


        textAreaRobos.setText(acmeRobots.getListaRobos().toString());

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idRobo = Integer.valueOf(robosField.getText());

                    Robo robo = acmeRobots.consultaId(idRobo);
                    int numero = acmeRobots.getListaLocacoes().size() -1;
                    String cliente = acmeRobots.getListaLocacoes().get(numero).getCliente().getNome();

                    if(robo == null) {
                        JOptionPane.showMessageDialog(aplicacao, "Robo nao cadastrado.");
                        robosField.setText("");
                    } else {

                    if(acmeRobots.adicionarRoboNaLocacao(numero, robo)) {
                        JOptionPane.showMessageDialog(aplicacao, "Robo adicionado na locacao de " + cliente);
                        textAreaResult.setText(acmeRobots.getListaLocacoes().get(numero).getListaRobos().toString());
                        robosField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(aplicacao, "Esse robo já foi alugado.");
                        robosField.setText("");
                    }
                    }

                }catch (Exception c){
                    JOptionPane.showMessageDialog(aplicacao, "Campos prenchidos de forma incorreta.");
                }
            }
        });

        concluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicacao.mudaPainel(3);
            }
        });

        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                robosField.setText("");
            }
        });

      }

    public JPanel getPainel() {
        return contentPane;
    }
}
