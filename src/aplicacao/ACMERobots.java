package aplicacao;

import dados.cliente.Cliente;
import dados.robo.Robo;

import java.util.ArrayList;
import java.util.List;

public class ACMERobots {
    private List<Robo> listaRobos = new ArrayList<>();
    private List<Cliente>clientes;



    public ACMERobots() {
        this.listaRobos = listaRobos;
        this.clientes = new ArrayList<>();

    }

    public boolean adicionarRobo(Robo novoRobo) {
        if (consultaId(novoRobo.getId()) != null) {
            return false;
        }
        return listaRobos.add(novoRobo);
    }

    public Robo consultaId(int id) {
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
        if(consultaCodigo(cliente.getCodigo())==null){
            clientes.add(cliente);
            return true;
        }
        else
            return false;
    }
    public Cliente consultaCodigo(int codigo) {

        for (Cliente c : clientes){
            if(c.getCodigo()==codigo){
                return c;
            }
        } return null;
    }


    public List<Robo> getListaRobos() {
        return listaRobos;
    }
    public List<Cliente> getListaCliente() {
        return clientes;
    }

}
