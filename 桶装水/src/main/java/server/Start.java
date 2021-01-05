package server;

import serverUI.InfoView;

import java.io.IOException;
import java.util.List;
import entity.*;
public class Start {
    public static void main(String[] args) {
        List<Order> orders;
        List<Product> products;
        try {
            new Register().start();
            new Deal().start();
            new InfoView().main(null);
            new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
