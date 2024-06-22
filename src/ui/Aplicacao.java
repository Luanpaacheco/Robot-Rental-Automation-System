package ui;

import aplicacao.ACMERobots;

import javax.swing.*;

public class Aplicacao extends JFrame {
    ACMERobots robots = new ACMERobots();
    private TelaMenu telaMenu = new TelaMenu(this,robots);
    private TelaR tela = new TelaR(this,robots);
    private TelaD telaD = new TelaD(this,robots);
    private TelaA telaA = new TelaA(this,robots);
    private TelaI telaI = new TelaI(this,robots);

    public Aplicacao(){
        super();
        JPanel painel = telaMenu.getPainel();
        add(painel);
        setSize(1000,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void mudaPainel(int painel) {
        switch(painel) {
            case 0:
                this.setContentPane(telaMenu.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
            case 1:
                this.setContentPane(tela.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
            case 2:
                this.setContentPane(telaD.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
            case 3:
                this.setContentPane(telaA.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
            case 4:
                this.setContentPane(telaI.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
        }
    }
}
