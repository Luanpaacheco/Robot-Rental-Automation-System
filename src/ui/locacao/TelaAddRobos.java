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
    private ACMERobots acmeRobots = ACMERobots.getInstance();
    Aplicacao aplicacao;
    private JPanel contentPane;
    private JTextArea textAreaResult;
    private JTextField robosField;
    private JTextArea textAreaRobos;
    private JButton concluirButton;
    private JButton adicionarButton;
    private JButton fecharButton;
    private JButton limparButton;

    public TelaAddRobos(Aplicacao aplicacao) {

        this.aplicacao = aplicacao;


        textAreaRobos.setText(acmeRobots.getListaRobos().toString());

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idRobo = Integer.valueOf(robosField.getText());

                    Robo robo = acmeRobots.consultaIdRobo(idRobo);
                    int numero = acmeRobots.getListaReserva().peek().getNumero();
                    System.out.println(numero);
                    String cliente = acmeRobots.getListaLocacoes().get(numero).getCliente().getNome();
                    System.out.println(cliente.toString());

                    if(robo == null) {
                        JOptionPane.showMessageDialog(aplicacao, "Robo nao cadastrado.");
                        robosField.setText("");
                    } else {

                    if(acmeRobots.adicionarRoboNaReserva(numero, robo)) {
                        JOptionPane.showMessageDialog(aplicacao, "Robo adicionado na locacao de " + cliente);
                        textAreaResult.setText(acmeRobots.consultaLocacaoPorNuumeroReserva(numero).getListaRobos().toString());
                        robosField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(aplicacao, "Esse robo já foi alugado.");
                        robosField.setText("");
                    }
                    }

                }catch (Exception c){
                    JOptionPane.showMessageDialog(aplicacao, c.getLocalizedMessage());
                    System.out.println(c.getLocalizedMessage());
                    System.out.println(c.toString());
                }
            }
        });

        concluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicacao.mudaPainel(10);
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
