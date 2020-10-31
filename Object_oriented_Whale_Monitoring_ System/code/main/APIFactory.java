package ca.uvic.seng330.ex5;

public class APIFactory {

    private static API instance;

    public static void setInstance(API api) {
        instance = api;
    }

    public static API getInstance() {
        if (instance == null) {
            return new RealAPI();
        }
        return instance;
    }


}
