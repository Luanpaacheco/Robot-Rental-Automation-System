package ui.locacao;

import aplicacao.ACMERobots;
import dados.Locacao;
import ui.Aplicacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaConsultarLocacoes {
    private ACMERobots acmeRobots = ACMERobots.getInstance();
    private JPanel panel1;
    private JTextArea textAreaLocacao;
    private JButton fechar;
    private JButton Voltar;

    public TelaConsultarLocacoes(Aplicacao aplicacao) {

//        for (Locacao l : acmeRobots.getListaLocacoes()){
//            String texto +=l.toString2();
//        }
        textAreaLocacao.setText("");

        for (Locacao l : acmeRobots.getListaLocacoes()) {
            double valor=acmeRobots.calculoValorFinal(l.getNumero());
            textAreaLocacao.append(l.toString2()+"\n" + acmeRobots.calculoValorFinal(l.getNumero()) + "\n\n");
        }
        for (Locacao l : acmeRobots.getListaReserva()) {
            textAreaLocacao.append(l.toString2()+"\n" + acmeRobots.calculoValorFinal(l.getNumero()) +  "\n\n");
        }

        //textAreaLocacao.append(acmeRobots.getListaLocacoes().stream().map(a->a.toString2()).toString());

        Voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicacao.mudaPainel(0);

            }
        });
        fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public JPanel getPainel() {
        return panel1;
    }
}
