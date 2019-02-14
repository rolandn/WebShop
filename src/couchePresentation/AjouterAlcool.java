package couchePresentation;

import ClassMetier.Alcool;
import ClassMetier.Biere;
import coucheAccesDB.ExeceptionAccessBD;
import coucheAccesDB.FabriqueDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class AjouterAlcool extends BaseFenetre {


 //   @FXML private TextField TFNumArticle;
    @FXML private TextField TFNom;
    @FXML private TextField TFNomImage;
    @FXML private TextField TFPrix;
    @FXML private TextField TFQuantiteStock;
    @FXML private TextField TFGout;
    @FXML private TextField TFDegre;
    @FXML private TextField TFProvenance;
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
    public AjouterAlcool(Stage fenParent)
    {
        // créer la fenêtre
        super(fenParent, "AjouterAlcool.xml", "Ajouter un nouvel alcool", 820, 680);
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
    private void MAjouterAlcool()
    {
        try
        {
            Alcool alcool = new Alcool();


  //          biere.setNumArticle(Integer.parseInt(TFNumArticle.getText()));
            alcool.setNom(TFNom.getText());
            alcool.setNomImage(TFNomImage.getText());
            alcool.setPrix(Integer.parseInt(TFPrix.getText()));
            alcool.setQuantiteStock(Integer.parseInt(TFQuantiteStock.getText()));
            alcool.setGout(TFGout.getText());
            alcool.setProvenance(TFProvenance.getText());
            alcool.setDegreAlcool(Integer.parseInt(TFDegre.getText()));



            if ((FabriqueDAO.getInstance().getInstAlcoolDAO()).Ajouter(alcool) == false)
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
            GererErreur.ErreurGen("AjouterAlcool", "BAjouterAlcool()", e.getMessage());
            new MsgBox(this, AlertType.ERROR,
                    "Problème de base de données lors de l'ajout de l'alcool!");
        }

        /*catch (ExceptionMetier e)
        {
            new MsgBox(this, AlertType.WARNING, e.getMessage());
            return;
        }*/
        catch (Exception e)
        {
            GererErreur.ErreurGen("AjouterAlcool", "BAjouterAlcool()", e.getMessage());
            new MsgBox(this, AlertType.ERROR, "Problème inattendu lors de l'ajout de l'alcool !");
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
