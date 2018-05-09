package ClassMetier;

public class Alcool extends Produit {
    private int DegreAlcool;
    private String Gout;
    private String Provenance;


    public Alcool(Alcool e) {
        super(e.getNumArticle(), e.getNom(), e.getNomImage(), e.getPrix(), e.getQuantiteStock());
        DegreAlcool = e.getDegreAlcool();
        Gout = e.getGout();
        Provenance = e.getProvenance();
    }

    public Alcool(int numArticle, String nom, String nomImage, int prix, int quantiteStock, int degreAlcool, String gout, String provenance)
    {
        super(numArticle, nom, nomImage, prix, quantiteStock);
        DegreAlcool = degreAlcool;
        Gout = gout;
        Provenance = provenance;

    }

    public Alcool() {
        super();
    }

    public int getDegreAlcool() {
        return DegreAlcool;
    }

    public void setDegreAlcool(int i) {
        DegreAlcool = i;
    }

    public String getGout() {
        return Gout;
    }

    public void setGout(String g) {
        Gout = g;
    }

    public String getProvenance() {
        return Provenance;
    }

    public void setProvenance(String p) {
        Provenance = p;
    }


    /**
     * Méthode "SET" pour les variables venant de Produit
     */

    public void setNumArticle(int numArticle){
        NumArticle = numArticle;
    }

    public void setNom(String nom) {Nom = nom;}

    public void setNomImage(String nomImage){
        NomImage = nomImage;
    }

    public void setPrix(int prix){
        Prix = prix;
    }

    public void setQuantiteStock(int quantiteStock){
        QuantiteStock = quantiteStock;
    }




}





