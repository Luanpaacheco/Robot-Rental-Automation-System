package ui.cliente;

import aplicacao.ACMERobots;
import dados.cliente.Empresarial;
import dados.cliente.Individual;
import ui.Aplicacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TelaEmpresarial {
    private Aplicacao aplicacao;
    private ACMERobots client;
    private JPanel principal;
    private JTextField labelCodigo;
    private JTextField labelAno;
    
    private JButton voltar;
    private JButton cadastra;

    private JLabel ano;
    private JTextField labelNome;

    public TelaEmpresarial(Aplicacao aplicacao, ACMERobots client){
        this.aplicacao=aplicacao;
        this.client=client;


        cadastra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = labelNome.getText();
                    int codigo = Integer.valueOf(labelCodigo.getText());
                    int ano =Integer.valueOf(labelAno.getText());
                    Empresarial novoCliente = new Empresarial(codigo, nome, ano);

                    if (client.cadastraCliente(novoCliente)) {
                        JOptionPane.showMessageDialog(aplicacao, "Cadastro confirmado!");
                    } else
                        JOptionPane.showMessageDialog(aplicacao, "Esse cliente já foi cadastrado");

                    labelNome.setText("");
                    labelAno.setText("");
                    labelCodigo.setText("");
                }catch (Exception c){
                    JOptionPane.showMessageDialog(aplicacao, "Campos prenchidos de forma incorreta!!");
                }
            }
        });
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelNome.setText("");
                labelAno.setText("");
                labelCodigo.setText("");
                aplicacao.mudaPainel(1);
            }
        });
    }

    public JPanel getPainel(){
        return principal;
    }
}


