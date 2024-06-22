package service;

import aplicacao.ACMERobots;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class converteJson {
    private ACMERobots acmeRobots;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public converteJson(ACMERobots acmeRobots) {
        this.acmeRobots = acmeRobots;
    }

    public void converte(Object a) {
        String json = gson.toJson(acmeRobots.getListaRobos());
        System.out.println(json);
    }
}
