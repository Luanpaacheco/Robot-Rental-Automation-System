package ui;

import aplicacao.ACMERobots;
import ui.cliente.TelaIndividual;
import ui.cliente.TelaEmpresarial;
import ui.cliente.TelaMenuClientes;
import ui.locacao.TelaAddRobos;
import ui.locacao.TelaCriarLocacao;
import ui.locacao.TelaResultados;
import ui.robo.TelaA;
import ui.robo.TelaD;
import ui.robo.TelaI;
import ui.robo.TelaR;

import javax.swing.*;

public class Aplicacao extends JFrame {
    ACMERobots acmeRobots = new ACMERobots();
    private TelaMenuClientes telaMenuClientes = new TelaMenuClientes(this,acmeRobots);
    private TelaIndividual telaIndividual = new TelaIndividual(this,acmeRobots);
    private TelaEmpresarial telaEmpresarial= new TelaEmpresarial(this, acmeRobots);
    private TelaMenu telaMenu = new TelaMenu(this, acmeRobots);
    private TelaR tela = new TelaR(this, acmeRobots);
    private TelaD telaD = new TelaD(this, acmeRobots);
    private TelaA telaA = new TelaA(this, acmeRobots);
    private TelaI telaI = new TelaI(this, acmeRobots);
    private TelaAddRobos telaAddRobos = new TelaAddRobos(this, acmeRobots);
    private TelaCriarLocacao telaCriarLocacao = new TelaCriarLocacao(this, acmeRobots);
    private TelaResultados telaResultados = new TelaResultados(this, acmeRobots);

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
            case 5:
                this.setContentPane(telaMenuClientes.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
            case 6:
                this.setContentPane(telaIndividual.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
            case 7:
                this.setContentPane(telaEmpresarial.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
            case 8:
                this.setContentPane(telaCriarLocacao.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
            case 9:
                this.setContentPane(telaAddRobos.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
            case 10:
                this.setContentPane(telaResultados.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
        }
    }
}
