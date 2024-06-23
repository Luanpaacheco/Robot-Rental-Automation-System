package ui;

import aplicacao.ACMERobots;
import dados.cliente.Individual;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaIndividual {
    private JPanel principal;
    private JButton cadastrar;
    private JButton voltar;
    private JTextField labelCPF;
    private JTextField labelCodigo;
    private JTextField labelNome;
    Aplicacao aplicacao;
    ACMERobots client;
   public TelaIndividual(Aplicacao aplicacao, ACMERobots client){
       this.aplicacao=aplicacao;
       this.client=client;


       cadastrar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   String nome = labelNome.getText();
                   int codigo = Integer.valueOf(labelCodigo.getText());
                   String cpf =labelCPF.getText();
                   Individual novoCliente = new Individual(codigo, nome, cpf);

                   if (client.cadastraCliente(novoCliente)) {
                       JOptionPane.showMessageDialog(aplicacao, "Cadastro confirmado!");
                   } else
                       JOptionPane.showMessageDialog(aplicacao, "Esse cliente já foi cadastrado");
               }catch (Exception c){
                   JOptionPane.showMessageDialog(aplicacao, "Campos prenchidos de forma incorreta!!");
               }
           }
       });
       voltar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               aplicacao.mudaPainel(1);
           }
       });
   }
    public JPanel getPainel(){

       return principal;
    }
}


