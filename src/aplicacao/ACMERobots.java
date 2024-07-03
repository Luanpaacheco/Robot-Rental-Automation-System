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
import service.CarregarDados;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ACMERobots {
    private static ACMERobots instance; // Instância única da classe ACMERobots

    private List<Robo> listaRobos = new ArrayList<>();
    private List<Cliente>clientes;
    private ArrayList<Locacao> listaLocacoes;
    private Queue<Locacao> reservas;
    private Status status;
    private CarregarDados carrega = new CarregarDados();


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
        Locacao locacao1 = new Locacao(10, Status.CADASTRADA, dataConvertida("15/04/2004"), 7, cliente1);
        locacao1.adicionaRobos(robo1);
        locacao1.adicionaRobos(robo2);
        reservas.add(locacao1);
        Locacao locacao2 = new Locacao(11, Status.CADASTRADA, dataConvertida("16/04/2004"), 7, cliente2);
        locacao2.adicionaRobos(robo3);
        reservas.add(locacao2);
        Locacao locacao3 = new Locacao(12, Status.CADASTRADA, dataConvertida("17/04/2004"), 7, cliente3);
        locacao3.adicionaRobos(robo4);
        locacao3.adicionaRobos(robo1);
        reservas.add(locacao3);
        Locacao locacao4 = new Locacao(13, Status.CADASTRADA, dataConvertida("18/04/2004"), 7, cliente4);
        locacao4.adicionaRobos(robo5);
        reservas.add(locacao4);
        Locacao locacao5 = new Locacao(14, Status.CADASTRADA, dataConvertida("19/04/2004"), 7, cliente5);
        locacao5.adicionaRobos(robo6);
        reservas.add(locacao5);
        Locacao locacao6 = new Locacao(15, Status.CADASTRADA, dataConvertida("20/04/2004"), 7, cliente1);
        locacao6.adicionaRobos(robo4);
        reservas.add(locacao6);
        Locacao locacao7 = new Locacao(16, Status.CADASTRADA, dataConvertida("21/04/2004"), 7, cliente2);
        locacao7.adicionaRobos(robo2);
        reservas.add(locacao7);
        Locacao locacao8 = new Locacao(17, Status.CADASTRADA, dataConvertida("22/04/2004"), 7, cliente3);
        locacao8.adicionaRobos(robo3);
        reservas.add(locacao8);
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

    public boolean adicionarRoboNaReserva( Robo novoRobo) {
        //Locacao l = listaLocacoes.get(numero);
        Locacao ultimaLocacao = null;

        for (Locacao locacao : getListaReserva()) {
            ultimaLocacao = locacao;
        }

        if (ultimaLocacao != null) {
            ultimaLocacao.adicionaRobos(novoRobo);
            return true;
        }
      return false;
    }

    public Cliente getUltimoClienteReserva() {
        Locacao ultimaLocacao = null;

        for (Locacao locacao : getListaReserva()) {
            ultimaLocacao = locacao;
        }

        return ultimaLocacao.getCliente();
    }

    public Locacao getUltimaLocacaoReserva() {
        Locacao ultimaLocacao = null;

        for (Locacao locacao : getListaReserva()) {
            ultimaLocacao = locacao;
        }

        return ultimaLocacao;
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
        }
        return null;
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

//    public void processarLocacoes() {
//
//        if(listaLocacoes.isEmpty()) {
//            listaLocacoes.add(reservas.poll());
//            listaLocacoes.get(0).setSituacao(Status.EXECUTANDO);
//        }
//
//        for(Locacao locacao : reservas) {
//            for(Locacao loc : listaLocacoes) {
//                for(Robo robo : loc.getListaRobos())
//                    for(Robo rob : locacao.getListaRobos()) {
//                    if(robo != rob) {
//                        listaLocacoes.add(reservas.poll());
//                        locacao.setSituacao(Status.EXECUTANDO);
//                    }
//                }
//            }
//        }
//
//    }


    public void processarLocacoes() {
        if (listaLocacoes.isEmpty()) {
            Locacao primeiraReserva = reservas.poll();
            if (primeiraReserva != null) {
                listaLocacoes.add(primeiraReserva);
                primeiraReserva.setSituacao(Status.EXECUTANDO);
                //System.out.println("Primeira locação adicionada: " + primeiraReserva);
            }
        }

        Queue<Locacao> reservasPendentes = new LinkedList<>();

        while (!reservas.isEmpty()) {
            Locacao reservaAtual = reservas.poll();
            boolean temRoboRepetido = false;

            for (Locacao locacao : listaLocacoes) {
                for (Robo roboLocacao : locacao.getListaRobos()) {
                    for (Robo roboReserva : reservaAtual.getListaRobos()) {
                        if (roboLocacao.equals(roboReserva)) {
                            temRoboRepetido = true;
                            System.out.println("Robô repetido encontrado: " + roboReserva);
                            break;
                        }
                        if(locacao.getSituacao() == Status.CANCELADA || locacao.getSituacao() == Status.FINALIZADA){
                            if (roboLocacao.equals(roboReserva)) {
                                temRoboRepetido = true;
                                System.out.println("Robô repetido encontrado: " + roboReserva);
                                break;
                            }
                            break;
                        }
                    }
                    if (temRoboRepetido) {
                        break;
                    }
                }
                if (temRoboRepetido) {
                    break;
                }
            }

            if (!temRoboRepetido) {
                listaLocacoes.add(reservaAtual);
                reservaAtual.setSituacao(Status.EXECUTANDO);
                //System.out.println("Locação adicionada: " + reservaAtual);
            } else {
                reservaAtual.setSituacao(Status.CADASTRADA);
                reservasPendentes.add(reservaAtual);
                //System.out.println("Locação com robôs repetidos: " + reservaAtual);
            }
        }

        reservas.addAll(reservasPendentes);
    }
    public boolean alterarSituacao(int numero, String situacao) {
        Locacao locacao = consultaLocacaoPorNuumero(numero);

        if(locacao == null) {
            locacao = consultaLocacaoPorNuumeroReserva(numero);
        }

        if(locacao == null) {
            //System.out.println("Locação não encontrada.");
            return false;
        }

        if(locacao.getSituacao() == Status.EXECUTANDO) {
            System.out.println(situacao);
            if("CANCELADA".equals(situacao)) {
                locacao.setSituacao(Status.CANCELADA);
                System.out.println("Locação cancelada com sucesso.");
                return true;
            } else if("FINALIZADA".equals(situacao)) {
                locacao.setSituacao(Status.FINALIZADA);
                System.out.println("Locação finalizada com sucesso.");
                return true;
            }
        } else if(locacao.getSituacao() == Status.CADASTRADA) {
            if("CANCELADA".equals(situacao)) {
                locacao.setSituacao(Status.CANCELADA);
                System.out.println("Locação cancelada com sucesso.");
                return true;
            } else if("FINALIZADA".equals(situacao)) {
                locacao.setSituacao(Status.FINALIZADA);
                System.out.println("Locação finalizada com sucesso.");
                return true;
            }
        }

        //System.out.println("Operação não permitida para o estado atual da locação.");
        return false;
    }


    public Locacao locacaoCerta(int numero) {
        Locacao locacao = consultaLocacaoPorNuumero(numero);
        if(locacao == null) {
            locacao = consultaLocacaoPorNuumeroReserva(numero);
        }
        return locacao;
    }
    public void carregaDados(String arquivoRobo, String arquivoCliente){
        //clientes.addAll(carrega.carregarClientesDados(arquivoCliente));
        listaRobos.addAll(carrega.carregarRobosDados(arquivoRobo));
    }
}

