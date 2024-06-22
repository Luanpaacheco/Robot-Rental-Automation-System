package aplicacao;

import dados.robo.Robo;

import java.util.ArrayList;
import java.util.List;

public class ACMERobots {
    private List<Robo> listaRobos = new ArrayList<>();


    public ACMERobots() {
        this.listaRobos = listaRobos;
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

    public List<Robo> getListaRobos() {
        return listaRobos;
    }
}
