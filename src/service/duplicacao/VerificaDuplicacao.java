package service.duplicacao;

import aplicacao.ACMERobots;
import dados.Locacao;
import dados.cliente.Cliente;
import dados.robo.Robo;

import java.util.*;

public class VerificaDuplicacao {

    public List<Robo> adicionarRobosUnicos(List<Robo> novosRobos) {
        ACMERobots acmeRobots = ACMERobots.getInstance();

        List<Robo> robosParaAdicionar = new ArrayList<>();
        Set<Integer> idsExistentes = new HashSet<>();
        for (Robo robo : acmeRobots.getListaRobos()) {
            idsExistentes.add(robo.getId());
        }

        for (Robo novoRobo : novosRobos) {
            if (!idsExistentes.contains(novoRobo.getId())) {
                robosParaAdicionar.add(novoRobo);
                System.out.println("Unicos" + novoRobo.toString());
            }
        }
        return robosParaAdicionar;
    }
    public List<Cliente> adicionarClientesUnicos(List<Cliente> novosClientes) {
        ACMERobots acmeRobots = ACMERobots.getInstance();

        List<Cliente> clientesParaAdicionar = new ArrayList<>();
        Set<Integer> codigosExistentes = new HashSet<>();
        for (Cliente cliente : acmeRobots.getListaClientes()) {
            codigosExistentes.add(cliente.getCodigo());
        }

        for (Cliente novoCliente : novosClientes) {
            if (!codigosExistentes.contains(novoCliente.getCodigo())) {
                clientesParaAdicionar.add(novoCliente);
                System.out.println("Unicos" + novoCliente.toString());
            }
        }
        return clientesParaAdicionar;
    }
    public Queue<Locacao> adicionarLocacoesUnicas(Queue<Locacao> novasLocacoes) {
        ACMERobots acmeRobots = ACMERobots.getInstance();
        Queue<Locacao> locacoesParaAdicionar = new LinkedList<>();
        Set<Integer> numerosExistentes = new HashSet<>();
        for (Locacao locacao : acmeRobots.getListaReserva()) {
            numerosExistentes.add(locacao.getNumero());
        }

        for (Locacao novaLocacao : novasLocacoes) {
            if (!numerosExistentes.contains(novaLocacao.getNumero())) {
                locacoesParaAdicionar.add(novaLocacao);
                System.out.println("Unicos" + novaLocacao.toString());

                for (Robo robo : novaLocacao.getListaDeRobos()) {
                    acmeRobots.adicionarRoboNaReserva(robo);
                }
                acmeRobots.adicionarReserva(novaLocacao);
            }
        }
        return locacoesParaAdicionar;
    }
}
