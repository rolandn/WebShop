package ClassMetier;

public class Produit
{
    private int NumArticle;
    private String Nom;
    private String NomImage;
    private int Prix;
    private int QuantiteStock;

    /**
     * Contructeurs
     */

    public Produit()
    {};

    public Produit (Produit produit)
    {
        NumArticle = produit.getNumArticle();
        Nom = produit.getNom();
        NomImage = produit.getNomImage();
        Prix = produit.getPrix();
        QuantiteStock = produit.getQuantiteStock();
    }

    public Produit (int numArticle, String nom, String nomImage, int prix, int quantiteStock)
    {
        NumArticle = numArticle;
        Nom = nom;
        NomImage =nomImage;
        Prix = prix;
        QuantiteStock = quantiteStock;
    }

    /**
     * Accès aux variables
     */

    public int getNumArticle() {
        return NumArticle;
    }
    public String getNom() {
        return Nom;
    }

    public String getNomImage() {
        return NomImage;
    }

    public int getPrix() {
        return Prix;
    }

    public int getQuantiteStock() {
        return QuantiteStock;
    }
}
