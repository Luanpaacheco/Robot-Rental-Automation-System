package aplicacao;

import dados.Status;
import dados.cliente.Cliente;
import dados.robo.Robo;
import dados.Locacao;
import org.springframework.scheduling.config.TaskNamespaceHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ACMERobots {
    private List<Robo> listaRobos = new ArrayList<>();
    private List<Cliente>clientes;
    private ArrayList<Locacao> listaLocacoes = new ArrayList<>();


    public ACMERobots() {
        this.listaRobos = listaRobos;
        this.clientes = new ArrayList<>();
        this.listaLocacoes = listaLocacoes;
    }

    public boolean adicionarRobo(Robo novoRobo) {
        if (consultaIdRobo(novoRobo.getId()) != null) {
            return false;
        }
        return listaRobos.add(novoRobo);
    }

    public boolean adicionarLocacao(Locacao novaLocacao) {
        for(Locacao l : listaLocacoes) {
            if(novaLocacao.getNumero() == l.getNumero()) {
                return false;
            }
        }
        return listaLocacoes.add(novaLocacao);
    }

    public boolean adicionarRoboNaLocacao(int numero, Robo novoRobo) {
        Locacao l = listaLocacoes.get(numero);
        for(Robo robo : l.getListaRobos()) {
            if(robo == novoRobo) {
                return false;
            }
        }
        l.adicionaRobos(novoRobo);
        return true;
        //return false;
    }

    public Date dataConvertida(String dataString) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = formato.parse(dataString);
            return data;
        } catch (ParseException e) {
            return null;
        }
    }

    public Robo consultaIdRobo(int id) {
        Robo robo = listaRobos.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
        if (    robo != null) {
            return robo;
        } else {
            return null;
        }
    }
    public boolean cadastraCliente(Cliente cliente) {
        if(consultaCodigoCliente(cliente.getCodigo())==null){
            clientes.add(cliente);
            return true;
        }
        else
            return false;
    }
    public Cliente consultaCodigoCliente(int codigo) {

        for (Cliente c : clientes){
            if(c.getCodigo()==codigo){
                return c;
            }
        } return null;
    }

    /*
       public Cliente consultaCodigo(int codigo) {
        Cliente cliente = listaClientes.stream()
                .filter(m -> m.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
        if (cliente != null) {
            return cliente;
        } else {
            return null;
        }
    }
     */

    public List<Robo> getListaRobos() {
        return listaRobos;
    }

    public List<Cliente> getListaClientes() {
        return clientes;
    }
    public ArrayList<Locacao> getListaLocacoes() {
        return listaLocacoes;
    }

    /*

    public consultaLocacao(){

    }

    */

    /*

    public void numeroRobosNaLocacao(int numero) {
        listaLocacoes.get(numero).getListaRobos().size();
    }

    */

    public double calculoValorFinal(int numero){
        double valorFinal;
        double porcentagemDesconto;
        double valorLocacaoRobos = 0;
        double desconto;
        int dias;
        Locacao locacao = null;
        for(Locacao loc : getListaLocacoes()) {
            if(loc.getNumero() == numero) {
                locacao = loc;
            }
        }
        dias = locacao.getDataFim();
        porcentagemDesconto = locacao.getCliente().calculaDesconto();
        for(Robo robo : locacao.getListaRobos()) {
            valorLocacaoRobos += robo.calculaLocacao(dias);
        }
        desconto = valorLocacaoRobos * porcentagemDesconto;
        valorFinal = valorLocacaoRobos - desconto;
        return valorFinal;
    }

    public void mostrarRelatorioGeral() {
        for(Locacao locacao : listaLocacoes) {
            if(locacao.getSituacao() == Status.CADASTRADA) {


                locacao.setSituacao(Status.EXECUTANDO);
            }
        }
    }


}
