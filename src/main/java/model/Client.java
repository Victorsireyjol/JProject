package model;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private String nom;


    public Client( String nom) {
        this.nom = nom;

    }

    public Client() {

    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    @Override
    public String toString() {

        return nom;
    }


}
