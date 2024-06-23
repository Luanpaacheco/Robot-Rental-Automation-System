package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dados.robo.Robo;

public class GsonService {
    private static Gson gsonInstance;

    public static Gson getGson() {
        if (gsonInstance == null) {
            synchronized (GsonService.class) {
                if (gsonInstance == null) {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.registerTypeAdapter(Robo.class, new RoboTypeAdapter());
                    gsonInstance = gsonBuilder.create();
                }
            }
        }
        return gsonInstance;
    }
}
