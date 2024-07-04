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
    public boolean adicionarLocacaoUnica(Locacao novaLocacao, ACMERobots acmeRobots) {
        Set<Integer> numerosExistentes = new HashSet<>();

        // Coletar os números das locações já existentes
        for (Locacao locacao : acmeRobots.getListaLocacoes()) {
            numerosExistentes.add(locacao.getNumero());
        }

        // Verificar se a locação já existe
        if (!numerosExistentes.contains(novaLocacao.getNumero())) {
            // Verificar se os robôs já estão associados a outras locações
            Set<Integer> idsRobosJaAssociados = new HashSet<>();
            boolean robosDisponiveis = true;

            for (Robo robo : novaLocacao.getListaDeRobos()) {
                if (!idsRobosJaAssociados.add(robo.getId())) {
                    robosDisponiveis = false;
                    break;
                }
            }

            if (robosDisponiveis) {
                for (Robo robo : novaLocacao.getListaDeRobos()) {
                    acmeRobots.adicionarRoboNaReserva(robo);
                }
                acmeRobots.adicionarReserva(novaLocacao);
                System.out.println("Locação única adicionada: " + novaLocacao.toString());
                return true;
            } else {
                System.out.println("Um ou mais robôs já estão associados a outras locações. Locação não adicionada: " + novaLocacao.getNumero());
            }
        } else {
            System.out.println("Locação duplicada encontrada e não será adicionada: " + novaLocacao.getNumero());
        }

        return false;
    }
}
