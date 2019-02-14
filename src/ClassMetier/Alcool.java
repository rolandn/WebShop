package ClassMetier;

public class Alcool extends Produit {
    private int DegreAlcool;
    private String Gout;
    private String Provenance;


    public Alcool(Alcool e) {
        super(e.getNumArticle(), e.getNom(), e.getNomImage(), e.getPrix(), e.getQuantiteStock(), e.getActive());
        DegreAlcool = e.getDegreAlcool();
        Gout = e.getGout();
        Provenance = e.getProvenance();
    }

    public Alcool(int numArticle, String nom, String nomImage, int prix, int quantiteStock, boolean active, int degreAlcool, String gout, String provenance)
    {
        super(numArticle, nom, nomImage, prix, quantiteStock, active);
        DegreAlcool = degreAlcool;
        Gout = gout;
        Provenance = provenance;

    }

    public Alcool() {
        super();
    }



    public Alcool(int anInt, int anInt1, String string, String string1, byte aByte) {
    }

    /*
    *    Fonctions GET
   */

    public int getDegreAlcool() {
        return DegreAlcool;
    }

    public String getGout() {
        return Gout;
    }

    public String getProvenance() {
        return Provenance;
    }


    /*
     *    Fonctions SET
     */

    public int setDegreAlcool(int i) {
        DegreAlcool = i;
        return i;
    }

    public void setGout(String g) {
        Gout = g;
    }

    public void setProvenance(String p) {
        Provenance = p;
    }








}





