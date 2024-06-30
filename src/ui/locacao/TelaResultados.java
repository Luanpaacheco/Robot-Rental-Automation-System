package ui.locacao;

import aplicacao.ACMERobots;
import ui.Aplicacao;

import javax.swing.*;
import java.awt.event.*;

public class TelaResultados extends JDialog {
    private ACMERobots acmeRobots = ACMERobots.getInstance();
    Aplicacao aplicacao;
    private JPanel contentPane;
    private JTextArea textAreaResultado;
    private JButton novoAlguelButton;
    private JButton fecharButton;
    private JPanel painel;
    private JButton exibirTodasButton;


    public TelaResultados(Aplicacao aplicacao) {
        this.aplicacao = aplicacao;
        int numero = acmeRobots.getListaLocacoes().size() -1;

        atualizarResultado();
        textAreaResultado.setText(acmeRobots.getListaLocacoes().get(numero).toString());
        JOptionPane.showMessageDialog(aplicacao, acmeRobots.getListaLocacoes().get(numero).toString());

        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        exibirTodasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaResultado.setText(acmeRobots.getListaLocacoes().toString() + "\n");
            }
        });

        novoAlguelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicacao.mudaPainel(8);
            }
        });
        }

    public void atualizarResultado() {
        int numeroL = acmeRobots.getListaLocacoes().size() - 1;
      int numeroR = acmeRobots.getListaLocacoes().get(numeroL).getListaRobos().size();
        if (numeroL >= 0 && numeroR > 0) {
            textAreaResultado.setText(acmeRobots.getListaLocacoes().get(numeroL).toString());
        } else {
            textAreaResultado.setText("Nenhuma locação disponível.");
        }
    }

    public JPanel getPainel() {
        return painel;
    }
}
