package aplicacao;

import dados.Status;
import dados.cliente.Cliente;
import dados.cliente.Empresarial;
import dados.cliente.Individual;
import dados.robo.Agricola;
import dados.robo.Domestico;
import dados.robo.Industrial;

import dados.cliente.Empresarial;
import dados.cliente.Individual;

import dados.robo.Robo;
import dados.Locacao;
import org.springframework.scheduling.config.TaskNamespaceHandler;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ACMERobots {
    private static ACMERobots instance; // Instância única da classe ACMERobots

    private List<Robo> listaRobos = new ArrayList<>();
    private List<Cliente>clientes;
    private ArrayList<Locacao> listaLocacoes;
    private Queue<Locacao> reservas;


    public ACMERobots() {
        this.listaRobos = listaRobos;
        this.clientes = new ArrayList<>();
        this.listaLocacoes = new ArrayList<>();
        this.reservas = new LinkedList<>();
    }

    public void criarRobosEClientes() {
        Agricola robo1 = new Agricola(209534, "super", 2.7, "fertilizante");
        Industrial robo2 = new Industrial(506257, "prime", "automotivo");
        Domestico robo3 = new Domestico(764905, "special", 2);
        Agricola robo4 = new Agricola(254375, "epic", 3.8, "colheita");
        Industrial robo5 = new Industrial(538952, "senior", "textil");
        Domestico robo6 = new Domestico(738581, "house", 1);
        Individual cliente1 = new Individual(23200003, "Arthur", "67485408095");
        Empresarial cliente2 = new Empresarial(23104235, "Luan", 2012);
        Empresarial cliente3 = new Empresarial(23280307, "Luis", 2017);
        Individual cliente4 = new Individual(27653092, "Pedro", "72097426486");
        Individual cliente5 = new Individual(20905235, "Leonardo", "74735867285");
        Empresarial cliente6 = new Empresarial(27658629, "Olivia", 2001);
        listaRobos.add(robo1);
        listaRobos.add(robo2);
        listaRobos.add(robo3);
        listaRobos.add(robo4);
        listaRobos.add(robo5);
        listaRobos.add(robo6);
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);
        clientes.add(cliente4);
        clientes.add(cliente5);
        clientes.add(cliente6);
    }


    public boolean adicionarRobo(Robo novoRobo) {
        if (consultaIdRobo(novoRobo.getId()) != null) {
            return false;
        }
        return listaRobos.add(novoRobo);
    }
    public static ACMERobots getInstance() {
        if (instance == null) {
            instance = new ACMERobots();
        }
        return instance;
    }


    public boolean adicionarLocacao(Locacao novaLocacao) {
        for(Locacao l : listaLocacoes) {
            if(novaLocacao.getNumero() == l.getNumero()) {
                return false;
            }
        }
        return listaLocacoes.add(novaLocacao);
    }

    public boolean adicionarReserva(Locacao novaLocacao) {
        for(Locacao l : listaLocacoes) {
            if(novaLocacao.getNumero() == l.getNumero()) {
                return false;
            }
        }
        return reservas.add(novaLocacao);
    }

    public boolean adicionarRoboNaReserva(int numero, Robo novoRobo) {
        //Locacao l = listaLocacoes.get(numero);
        Locacao r = getListaReserva();
        r.adicionaRobos(novoRobo);
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
    public Locacao consultaLocacaoPorNuumero(int numero) {

        for (Locacao c : listaLocacoes){
            if(c.getNumero()==numero){
                return c;
            }
        } return null;
    }

    public Locacao consultaLocacaoPorNuumeroReserva(int numero) {

        for (Locacao c : reservas){
            if(c.getNumero()==numero){
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

    public Queue<Locacao> getListaReserva() {
        return reservas;
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

    public void processarLocacoes() {

        if(listaLocacoes.isEmpty()) {
            listaLocacoes.add(reservas.poll());
        }

        for(Locacao locacao : reservas) {
            for(Locacao loc : listaLocacoes) {
                for(Robo robo : loc.getListaRobos())
                    for(Robo rob : locacao.getListaRobos()) {
                    if(robo != rob) {
                        listaLocacoes.add(reservas.poll());
                        locacao.setSituacao(Status.EXECUTANDO);
                    }
                }
            }
        }

    }


}

