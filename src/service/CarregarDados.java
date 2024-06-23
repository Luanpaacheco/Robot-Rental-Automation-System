package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dados.robo.Robo;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CarregarDados {

    private List<Robo> robos;

    public CarregarDados(List<Robo> robos) {
        this.robos = robos;
    }

    public void carregarDadosDoArquivoRobo(String nomeArquivo) {

    }

}
