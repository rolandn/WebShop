package ClassMetier;

public class Produit
{
    public int NumArticle;
    public String Nom;
    public String NomImage;
    public int Prix;
    public int QuantiteStock;

    /**
     * Contructeurs
     * @param anInt
     * @param string
     * @param sqlResString
     * @param resString
     * @param s
     * @param string1
     */

    public Produit(int anInt, String string, String sqlResString, String resString, String s, String string1)
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


    public void setQuantiteStock(int quantiteStock)
    {
        QuantiteStock = quantiteStock;
    }

    /**
     * Modifier une quantité en stock
     */



}
