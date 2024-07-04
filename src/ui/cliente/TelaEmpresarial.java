package ui.cliente;

import aplicacao.ACMERobots;
import dados.cliente.Empresarial;
import ui.Aplicacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TelaEmpresarial {
    private Aplicacao aplicacao;
    private ACMERobots acmeRobots = ACMERobots.getInstance();

    private JPanel principal;
    private JTextField labelCodigo;
    private JTextField labelAno;
    
    private JButton voltar;
    private JButton cadastra;
    private JLabel ano;
    private JTextField labelNome;
    private JButton fecharButton;

    public TelaEmpresarial(Aplicacao aplicacao){
        this.aplicacao=aplicacao;



        cadastra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = labelNome.getText();
                    int codigo = Integer.valueOf(labelCodigo.getText());
                    int ano =Integer.valueOf(labelAno.getText());
                    Empresarial novoCliente = new Empresarial(codigo, nome, ano);

                    if (acmeRobots.cadastraCliente(novoCliente)) {
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
                aplicacao.mudaPainel(0);
            }
        });
        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JPanel getPainel(){
        return principal;
    }
}


