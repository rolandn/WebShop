package ClassMetier;

public class Biere extends Produit {
    private String Gout;
    private String Recipient;
    private Boolean Alcoolise;


    public Biere(Biere e) {
        super(e.getNumArticle(), e.getNom(), e.getNomImage(), e.getPrix(), e.getQuantiteStock(), e.getActive());
        Alcoolise = e.getAlcool();
        Gout = e.getGout();
        Recipient = e.getRecipient();
    }

    public Biere(int numArticle, String nom, String nomImage, int prix, int quantiteStock, boolean active, Boolean alcoolise, String gout, String recipient)
    {
        super(numArticle, nom, nomImage, prix, quantiteStock, active);
        Alcoolise = alcoolise;
        Gout = gout;
        Recipient = recipient;

    }

    public Biere() {
        super();
    }

    public Biere(int anInt, String string, String string1, boolean aBoolean) {
    }

    /**
     * Méthodes GET SET pour les variables venant de Biere
     */


    public Boolean getAlcool() {
        return this.Alcoolise;
    }

    public void setAlcool(Boolean alcoolise) {this.Alcoolise = alcoolise;}



    public String getGout() {
        return Gout;
    }

    public void setGout(String g) {
        Gout = g;
    }



    public String getRecipient() {
        return Recipient;
    }

    public void setRecipient(String p) {
        Recipient = p;
    }


    /**
     * Méthode "SET" pour les variables venant de Produit
     */





    public void setAlcool(String text) {
    }
}





