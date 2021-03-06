package couchePresentation;

import ClassMetier.*;
import coucheAccesDB.*;
import ClassMetier.ExceptionMetier;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.*;


import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.stage.*;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.nio.file.Files;


public class AjouterBiere extends BaseFenetre {


 //   @FXML private TextField TFNumArticle;
    @FXML private TextField TFNom;
    @FXML private TextField TFNomImage;
    @FXML private TextField TFPrix;
    @FXML private TextField TFQuantiteStock;
    @FXML private TextField TFGout;
    @FXML private ComboBox CBbiere;
    @FXML private TextField TFRecipient;
    @FXML private javafx.scene.image.ImageView IVImage;
//    @FXML private ImageView IVImage;
    @FXML private Button BChargerImage;
    @FXML private Button BFermer;
    @FXML private Button BAjouter;



    private File FichierSrc = null;


    /**
     * Constructeur : il crée la fenêtre
     * @param fenParent : l'objet Stage représentant la fenêtre parent
     */
    public AjouterBiere(Stage fenParent)
    {
        // créer la fenêtre
        super(fenParent, "AjouterBiere.xml", "Ajouter une nouvelle bière", 820, 680);
        // afficher la fenêtre

        showAndWait();
    }

    /**
     * Ouvrir une boîte de dialogue permettant de sélectionner une image sur un disque
     */

    @FXML
    private void BOuvrirFichierImg()
    {
        FileChooser btNomImage = new FileChooser();
        btNomImage.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        FichierSrc = btNomImage.showOpenDialog(null);
        if(FichierSrc != null)
        {
            TFNomImage.setText(FichierSrc.getName());
            IVImage.setImage(new Image("file:" + FichierSrc.getPath()));
        }
    }


    /**
     * méthode exécutée quand on clique sur le bouton Ajouter
     */
    @FXML
    private void MAjouterBiere()
    {
        try
        {
            Biere biere = new Biere();


  //          biere.setNumArticle(Integer.parseInt(TFNumArticle.getText()));
            biere.setNom(TFNom.getText());
            biere.setNomImage(TFNomImage.getText());
            biere.setPrix(Integer.parseInt(TFPrix.getText()));
            biere.setQuantiteStock(Integer.parseInt(TFQuantiteStock.getText()));
            biere.setGout(TFGout.getText());
            biere.setRecipient(TFRecipient.getText());
            biere.setAlcool(("oui".equals(CBbiere.getValue().toString())));

            if ((FabriqueDAO.getInstance().getInstBiereDAO()).Ajouter(biere) == false)
                new MsgBox(this, AlertType.INFORMATION, "L'ajout n'a pas eu lieu!");
            else
            {
                new MsgBox(this, AlertType.INFORMATION, "L'ajout s'est bien déroulé!");

                // copier le fichier de l'image vers le répertoire des images

                if (FichierSrc != null)
                {
                    File FichierDst = new File(System.getProperty("user.dir") +
                            "/imgs/" + FichierSrc.getName());
                    Files.copy(FichierSrc.toPath(), FichierDst.toPath(), REPLACE_EXISTING);
                }

            }
        }
        catch(ExeceptionAccessBD e)
        {
            GererErreur.ErreurGen("AjouterBiere", "BAjouterBiere()", e.getMessage());
            new MsgBox(this, AlertType.ERROR,
                    "Problème de base de données lors de l'ajout de la bière!");
        }

        /*catch (ExceptionMetier e)
        {
            new MsgBox(this, AlertType.WARNING, e.getMessage());
            return;
        }*/
        catch (Exception e)
        {
            GererErreur.ErreurGen("AjouterBiere", "BAjouterBiere()", e.getMessage());
            new MsgBox(this, AlertType.ERROR, "Problème inattendu lors de l'ajout de la bière !");
        }
        close();
    }

    /**
     * Méthode exécutée lorsque l'utilisateur clique sur le bouton Fermer
     */
    @FXML
    private void BFermer(ActionEvent event)
    {
        close();
    }


}
