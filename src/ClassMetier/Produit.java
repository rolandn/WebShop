package ClassMetier;

public class Produit
{
    public int NumArticle;
    public String Nom;
    public String NomImage;
    public int Prix;
    public int QuantiteStock;
    public boolean Active;

    /**
     * Contructeurs
     *
     */

    public Produit (Produit produit)
    {
        NumArticle = produit.getNumArticle();
        Nom = produit.getNom();
        NomImage = produit.getNomImage();

        Prix = produit.getPrix();
        QuantiteStock = produit.getQuantiteStock();
        Active = produit.getActive();
    }

    public Produit (int numArticle, String nom, String nomImage, int prix, int quantiteStock, boolean active)
    {
        NumArticle = numArticle;
        Nom = nom;
        NomImage =nomImage;
        Prix = prix;
        QuantiteStock = quantiteStock;
        Active = active;
    }

    public Produit(int anInt, String string, int quantiteCommande, double prixTotal) {

    }


    public Produit() {
        super();
    }

    /**
     * Accès aux variables
     */

    public int getNumArticle() {
        return NumArticle;
    }

    public void setNumArticle(int numArticle) {
        NumArticle = numArticle;
    }

    public boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {this.Active = active;}

    public String getNom() {
        return Nom;
    }



    public String getNomImage() {
        return NomImage;
    }

    public void setNom(String nom){Nom = nom;}

    public void setPrix(Integer prix) {Prix = prix;}

    public void setNomImage(String nomImage){NomImage = nomImage;};

    public int getPrix() {
        return Prix;
    }

    public int getQuantiteStock() {
        return QuantiteStock;
    }


    public int setQuantiteStock(int quantiteStock)
    {
        QuantiteStock = quantiteStock;
        return QuantiteStock;
    }

    @Override
    public String toString() {
        return Nom + " "+ NumArticle; }




    /**
     * Modifier une quantité en stock
     */



}
