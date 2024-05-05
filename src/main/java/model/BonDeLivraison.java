package model;

import java.time.LocalDate;

public class BonDeLivraison {
    private int id; // Identifiant unique du bon
    private LocalDate date; // Date de création du bon
    private String detailsClient;
    private String detailsProduit;

    public BonDeLivraison(int id, LocalDate date, String detailsClient, String detailsProduit) {
        this.id = id;
        this.date = date;
        this.detailsClient = detailsClient;
        this.detailsProduit = detailsProduit;
    }

    // Getters
    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDetailsClient() {
        return detailsClient;
    }

    public String getDetailsProduit() {
        return detailsProduit;
    }
}
