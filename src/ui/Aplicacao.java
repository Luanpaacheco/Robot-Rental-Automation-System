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
    private TelaMenuClientes telaMenuClientes = new TelaMenuClientes(this);
    private TelaEmpresarial telaEmpresarial= new TelaEmpresarial(this);
    private TelaMenu telaMenu = new TelaMenu(this);
    private TelaR tela = new TelaR(this);
    private TelaD telaD = new TelaD(this);
    private TelaA telaA = new TelaA(this);
    private TelaI telaI = new TelaI(this);
    private TelaAddRobos telaAddRobos = new TelaAddRobos(this);
    private TelaCriarLocacao telaCriarLocacao = new TelaCriarLocacao(this);
    private TelaRelatorioGeral telaRelatorioGeral = new TelaRelatorioGeral(this);
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
                TelaIndividual telaIndividual = new TelaIndividual(this);
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
                TelaAddRobos telaAddRobos = new TelaAddRobos(this);
                this.setContentPane(telaAddRobos.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
            case 10:
                TelaResultados telaResultados = new TelaResultados(this);
                this.setContentPane(telaResultados.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
            case 11:
                TelaRelatorioGeral telaRelatorioGeral = new TelaRelatorioGeral(this);
                this.setContentPane(telaRelatorioGeral.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
            case 12:
                TelaConsultarLocacoes telaConsultarLocacoes = new TelaConsultarLocacoes(this);
                this.setContentPane(telaConsultarLocacoes.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
            case 13:
                TelaAlteraSituacao telaAlteraSituacao = new TelaAlteraSituacao(this);
                this.setContentPane(telaAlteraSituacao.getPainel());
                this.pack();
                this.setSize(1000,800);
                break;
        }
    }
}
