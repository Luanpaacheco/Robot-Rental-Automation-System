package aplicacao;

import ch.qos.logback.core.joran.action.NOPAction;
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
import service.carregarDados.csv.CarregarDadosCsv;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ACMERobots {
    private static ACMERobots instance = ACMERobots.getInstance(); // Instância única da classe ACMERobots

    private List<Robo> listaRobos = new ArrayList<>();
    private List<Cliente> clientes;
    private ArrayList<Locacao> listaLocacoes;
    private Queue<Locacao> reservas;
    private Status status;
    private CarregarDadosCsv carrega = new CarregarDadosCsv();

    private ACMERobots() {
        this.listaRobos = listaRobos;
        this.clientes = new ArrayList<>();
        this.listaLocacoes = new ArrayList<>();
        this.reservas = new LinkedList<>();
    }


    public boolean adicionarRobo(Robo novoRobo) {
        if (consultaIdRobo(novoRobo.getId()) != null) {
            return false;
        }
        return listaRobos.add(novoRobo);
    }
    public static synchronized ACMERobots getInstance() {
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
            if(consultaLocacaoPorNuumero(novaLocacao.getNumero()) == null){
                System.out.println("AAAAAA " + novaLocacao.toString()  );
                if(novaLocacao.getNumero() == l.getNumero()) {
                    return false;
                }
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
            for(Robo robo : ultimaLocacao.getListaDeRobos()) {
                if(robo == novoRobo) {
                    return false;
               }
            }
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
    public void setListaReserva(Queue<Locacao> a ) {
        reservas.addAll(a);
    }


    public double calculoValorFinal(int numero) {
        double valorFinal = 0;
        double porcentagemDesconto = 0;
        double valorLocacaoRobos = 0;
        double desconto = 0;
        long dias = 0;

        // Busca pela locação com o número especificado
        Locacao locacao = null;
        for (Locacao loc : getListaLocacoes()) {
            if (loc.getNumero() == numero) {
                locacao = loc;
                break; // Encontrou a locação, sai do loop
            }
        }

        if(locacao==null){
            for (Locacao loc : getListaReserva()) {
                if (loc.getNumero() == numero) {
                    locacao = loc;
                    break; // Encontrou a locação, sai do loop
                }
            }
        }

        if (locacao == null) {
            System.err.println("Locação não encontrada para o número: " + numero);
            return 0;
        }


        dias =  locacao.getDiasEntreDatas();


        porcentagemDesconto = locacao.getCliente().calculaDesconto();


        for (Robo robo : locacao.getListaRobos()) {
            valorLocacaoRobos += robo.calculaLocacao(Math.toIntExact(dias));
        }

        desconto = valorLocacaoRobos * (porcentagemDesconto);

        valorFinal = valorLocacaoRobos - desconto;

        return valorFinal;
    }


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
            } else {
                reservaAtual.setSituacao(Status.CADASTRADA);
                reservasPendentes.add(reservaAtual);
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
        return false;
    }


    public Locacao locacaoCerta(int numero) {
        Locacao locacao = consultaLocacaoPorNuumero(numero);
        if(locacao == null) {
            locacao = consultaLocacaoPorNuumeroReserva(numero);
        }
        return locacao;
    }
    public void carregaDados(String arquivoRobo, String arquivoCliente,String arquivoLocacao){
        clientes.addAll(carrega.carregarClientesDados(arquivoCliente));
        listaRobos.addAll(carrega.carregarRobosDados(arquivoRobo));
        instance.reservas.addAll(carrega.carregarLocacoesDados(arquivoLocacao));
        System.out.println(reservas);
    }
    public void carregarLocacoes(String arquivoLocacao){
        carrega.carregarLocacoesDados(arquivoLocacao);

    }
}

