package ui.locacao;

import aplicacao.ACMERobots;
import ui.Aplicacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAlteraSituacao {
    private ACMERobots acmeRobots = ACMERobots.getInstance();
    private Aplicacao app;
    private JTextField textFieldNumero;
    private JTextField textFieldSituacao;
    private JButton MostrarLocacao;
    private JButton TrocarSituacao;
    private JTextArea textArea1;
    private JPanel painel1;
    private JButton Voltar;

    public TelaAlteraSituacao(Aplicacao aplicacao) {
        app = aplicacao;

        //textAreaLocacao.setText(acmeRobots.getListaLocacoes().toString()+"\n"+acmeRobots.getListaReserva().toString());

        MostrarLocacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int numero = Integer.valueOf(textFieldNumero.getText());
                    if(acmeRobots.locacaoCerta(numero)!=null){
                        textArea1.setText(acmeRobots.locacaoCerta(numero).toString());
                    }else
                        textArea1.setText("Essa locação não existe");
                }catch (Exception o){
                    JOptionPane.showMessageDialog(app, "Dados digitados incorretos");
                    textFieldNumero.setText("");
                }

            }
        });
        TrocarSituacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String situacao = textFieldSituacao.getText().toUpperCase();
                    int numero1 = Integer.valueOf(textFieldNumero.getText());
                    if(acmeRobots.alterarSituacao(numero1, situacao)) {
                        textArea1.setText(acmeRobots.locacaoCerta(numero1).toString());
                    } else {
                        textArea1.setText("Não é possível realizar essa operação");
                    }
                }
                catch (Exception o ){
                    JOptionPane.showMessageDialog(app, "Dados digitados incorretos");
                    textFieldNumero.setText("");
                    textFieldSituacao.setText("");

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

