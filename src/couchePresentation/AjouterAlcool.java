package couchePresentation;

import com.intellij.diagnostic.TestMessageBoxAction;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.io.File;
import java.nio.file.Files;
import ClassMetier.*;
import coucheAccesDB.*;

public class AjouterAlcool
{
    private final int Largeur = 900;
    private final int Hauteur = 500;
    private Stage Fenetre = new Stage();
    private Scene SceneObj;
    private HBox HBSaisies = new HBox(15);
    private HBox HBBoutons = new HBox(15);
    private HBox HBImg = new HBox(15);
    private GridPane GPSaisies = new GridPane();
    private VBox VBZonesFenetre = new VBox();

    private TextField TFNumArticle = new TextField();
    private TextField TFNom = new TextField();
    private TextField TFNomIMage = new TextField();
    private TextField TFPrix = new TextField();
    private TextField TFQuantiteStock = new TextField();
    private TextField TFDegreAlcool = new TextField();
    private TextField TFGout = new TextField();
    private TextField TFProvenance = new TextField();


    private Button BAjouterAlcool = new Button("Ajouter");
    private Button BFermer = new Button("Fermer");
    private Separator SLigne = new Separator();

    /**
     * Constructeur : il crée la fenêtre
     *
     * @param  : l'objet Stage représentant la fenêtre principale
     */
    public AjouterAlcool() {
        GPSaisies.add(new Label("Numéro d'article"), 0, 0);
        GPSaisies.add(TFNumArticle, 1, 0);

        GPSaisies.add(new Label("Nom"), 0, 1);
        GPSaisies.add(TFNom, 1, 1);

        GPSaisies.add(new Label("Numéro d'image"), 0, 2);
        GPSaisies.add(TFNomIMage, 1, 2);

        GPSaisies.add(new Label("Prix"), 0, 3);
        GPSaisies.add(TFPrix, 1, 3);

        GPSaisies.add(new Label("Quantité en stock"), 0, 4);
        GPSaisies.add(TFQuantiteStock, 1, 4);

        GPSaisies.add(new Label("Degré d'alcool"), 0, 5);
        GPSaisies.add(TFDegreAlcool, 1, 5);

        GPSaisies.add(new Label("Goût"), 0, 6);
        GPSaisies.add(TFGout, 1, 6);

        GPSaisies.add(new Label("Provenance"), 0, 7);
        GPSaisies.add(TFQuantiteStock, 1, 7);

        // espacement entre les cellules de GPSaisies
        GPSaisies.setHgap(8);
        GPSaisies.setVgap(8);

        // changer la taille des boites
        TFNom.setMaxWidth(600);


        // Paramétrer les boutons BAjouter et BFermer
        BAjouterAlcool.setPrefSize(150, 20);
        BAjouterAlcool.setOnAction(event -> {BAjouterAlcool();} );

        BFermer.setPrefSize(150, 20);
        BFermer.setOnAction(e -> {Fenetre.close();});

        // GPSaisies et IVImage -> HBSaisies
        HBSaisies.getChildren().addAll(GPSaisies);  // LIGNE QUI VA FAIRE APPARAITRE LES CHAMPS !!

        // BAjouter et BFermer -> HBBoutons
        HBBoutons.getChildren().addAll(BAjouterAlcool, BFermer);

        // HBSaisies, SLigne et HBBoutons -> VBZonesFenetre
        VBZonesFenetre.getChildren().addAll(HBSaisies, SLigne, HBBoutons);

        // définir les marges autour des objets dans VBZonesFenetre
        VBox.setMargin(HBSaisies, new Insets(15, 15, 10, 15));
        VBox.setMargin(SLigne, new Insets(0, 15, 0, 15));
        VBox.setMargin(HBBoutons, new Insets(10, 0, 15, 400));


        // définir les marges autour des objets dans VBZonesFenetre
        VBox.setMargin(HBSaisies, new Insets(15, 15, 10, 15));
        VBox.setMargin(SLigne, new Insets(0, 15, 0, 15));
        VBox.setMargin(HBBoutons, new Insets(10, 0, 15, 400));

        SceneObj = new Scene(VBZonesFenetre, Largeur, Hauteur);
        Fenetre.setScene(SceneObj);

        SceneObj.getStylesheets().add("couchePresentation/styleComboBox.css");

        // paramétrer la fenêtre, puis l'afficher
        Fenetre.setTitle("Ajouter un nouvel alcool");
        Fenetre.setResizable(true);
        Fenetre.setX(FenetrePrincipale.getInstance().getX() +
                (FenetrePrincipale.getInstance().getWidth() - Largeur) / 2);
        Fenetre.setY(FenetrePrincipale.getInstance().getY() +
                (FenetrePrincipale.getInstance().getHeight() - Hauteur) / 2);
        Fenetre.initOwner(FenetrePrincipale.getInstance());
        Fenetre.initModality(Modality.APPLICATION_MODAL);
        Fenetre.showAndWait();
    }

    /**
     * Méthode qui exécute l'ajout quand on clique sur le bouton ajouter.
     */

    private void BAjouterAlcool() {
        try {
            Alcool alcool = new Alcool();

            alcool.setNumArticle(Integer.parseInt(TFNumArticle.getText()));
            alcool.setNomImage(TFNomIMage.getText());
            alcool.setPrix(Integer.parseInt(TFPrix.getText()));
            alcool.setQuantiteStock(Integer.parseInt(TFQuantiteStock.getText()));
            alcool.setDegreAlcool(Integer.parseInt(TFDegreAlcool.getText()));
            alcool.setGout(TFGout.getText());
            alcool.setProvenance(TFProvenance.getText());

            if (FabriqueDAO.getInstance().getInstAlcoolDAO().Ajouter(alcool) == false)
                new MessageBox(AlertType.INFORMATION, "L'ajout n'a pas eu lieu.");

            else
                new MessageBox(AlertType.INFORMATION, "L'ajout s'est bien passé.");
        }

        catch (ExceptionAccessBD e)
        {
            GererErreur.GererErreurSQL("AjouterAlcool", "BAjouterAlcool", e.getMessage());
            new MessageBox(AlertType.ERROR, "Problème de base de données lors de l'ajout du client");
        }

        //catch (ExceptionMetier e)
        //{
        //    new MessageBox(AlertType.WARNING, e.getMessage());
        //    return;
        //}

        catch (Exception e) {
            GererErreur.GererErreurSQL("AjouterClient", "BAAjouterClient", e.getMessage());
            new MessageBox(AlertType.ERROR, "problème inattendu lors de l'ajout du client");
        }

        Fenetre.close();

    }

}
