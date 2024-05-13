package model;

import java.time.LocalDate;
import java.util.List;

public class BonCommande {


        private int idCommande;
        private String client;
        private Produit produit;
        private int quantite;
        private LocalDate dateCommande;

        public BonCommande(int idCommande, String client, Produit produit, int quantite, LocalDate dateCommande) {
            this.idCommande = idCommande;
            this.client = client;
            this.produit = produit;
            this.quantite = quantite;
            this.dateCommande = dateCommande;
        }

        public int getIdCommande() {
            return idCommande;
        }

        public String getClient() {
            return client;
        }

        public Produit getProduit() {
            return produit;
        }

        public int getQuantite() {
            return quantite;
        }

        public LocalDate getDateCommande() {
            return dateCommande;
        }

        @Override
        public String toString() {
            return "BonDeCommande{" +
                    "idCommande=" + idCommande +
                    ", client='" + client + '\'' +
                    ", produit=" + produit +
                    ", quantite=" + quantite +
                    ", dateCommande=" + dateCommande +
                    '}';
        }
    }

