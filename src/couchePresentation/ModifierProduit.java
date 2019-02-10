package couchePresentation;

import ClassMetier.*;
import coucheAccesDB.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.stage.*;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ModifierProduit extends BaseFenetre
{
    @FXML private TextField TFNumArticle;
    @FXML private TextField TFNom;
    @FXML private ComboBox<Boolean> CBArchive;
    @FXML private TextField TFquantiteStock;
    @FXML private TextField TFNomImage;
    @FXML private Button BChargerImage;
    @FXML private Button BAjouter;
    @FXML private Button BFermer;
    @FXML private ImageView IVImage;

    @FXML private ComboBox<Produit> CBProduit;

    private File FichierSrc = null;
    /**
     * Constructeur : il crée la fenêtre
     * @param fenParent : l'objet Stage représentant la fenêtre parent
     */
    public ModifierProduit(Stage fenParent)
    {
// créer la fenêtre
        super(fenParent, "ModifierProduitVue.xml", "Modifier le stock ou supprimer un article", 580, 300);
// Ajoute dans la boîte combo CBproduit


        try
        {
            CBProduit.setItems(FXCollections.observableArrayList(
                    FabriqueDAO.getInstance().getInsProduitDAO().ListerTous()));
        }

        catch (ExeceptionAccessBD e) {
            GererErreur.ErreurSQL("ModifierProduit", "ModifierProduit()", e.getMessage());
            new MsgBox(this, AlertType.ERROR,
                    "Problème de base de données lors du listage des produits !");
            return;
        }
        if(CBProduit.getItems().size() == 0)
        {
            new MsgBox(this, AlertType.INFORMATION,
                    "Il n'y a aucun produit dans la base de données!");
            return;
        }
        CBProduit.getSelectionModel().selectFirst();
        CBChangerProduit(CBProduit.getItems().get(0));

// gérer le changement de l'article bière courant dans la boîte combo CBBiere
        CBProduit.getSelectionModel().selectedItemProperty().addListener((obs, ancProduit,
                                                                         nouvProduit) ->
        {
            if (nouvProduit != null) CBChangerProduit(nouvProduit);
        });
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
     * Méthode qui met à jour le contenu des contrôles dans la fenêtre lors du changement ...
     * ... de l'élève courant dans la boîte combo
     * @param produit : un objet Produit contenant les informations
     */
    private void CBChangerProduit(Produit produit)
    {
        TFNom.setText(produit.getNom());
        TFNumArticle.setText(Integer.toString(produit.getNumArticle()));
        TFquantiteStock.setText(Integer.toString(produit.getQuantiteStock()));
        CBArchive.setValue(Boolean.valueOf(Boolean.toString(produit.getActive())));

        TFNomImage.setText(produit.getNomImage());
        IVImage.setImage(new Image("file:imgs/imgseleves/" + TFNomImage.getText()));
    }
    /**
     * Méthode exécutée quand on clique sur le bouton Modifier
     */
    @FXML
    private void BModifierProduit()
    {
        try
        {
            Produit produit = new Produit();
            produit.setNumArticle(CBProduit.getSelectionModel().getSelectedItem().getNumArticle());
            produit.setQuantiteStock(Integer.parseInt(TFquantiteStock.getText()));
            produit.setActive(CBArchive.getValue());
            produit.setNomImage(TFNomImage.getText());

            if (FabriqueDAO.getInstance().getInsProduitDAO().Modifier(produit) == false)
                new MsgBox(this, AlertType.INFORMATION, "La modification n'a pas eu lieu!");
            else
            {
                new MsgBox(this, AlertType.INFORMATION, "La modification s'est bien déroulée!");
// copier le fichier de l'image vers le répertoire des images
                if (FichierSrc != null)
                {
                    File FichierDst = new File(System.getProperty("user.dir") +
                            "/imgs/imgseleves/" + FichierSrc.getName());
                    Files.copy(FichierSrc.toPath(), FichierDst.toPath(), REPLACE_EXISTING);
                }
            }
        }
        catch(ExeceptionAccessBD e)
        {
            GererErreur.ErreurSQL("ModifierProduit", "BModifierproduit()", e.getMessage());
            new MsgBox(this, AlertType.ERROR,
                    "Problème de base de données lors de la modification d'un produit !");
        }
        /*catch(ExeceptionAccessBD e)
        {
            new MsgBox(this, AlertType.WARNING, e.getMessage());
            return;
        }*/
        catch (Exception e)
        {
            GererErreur.ErreurGen("ModifierProduit", "BModifierProduit()", e.getMessage());
            new MsgBox(this, AlertType.ERROR,
                    "Problème inattendu lors de la modification du produit!");
        }
        close();
    }
    /**
     * méthode exécutée quand on clique sur le bouton Fermer
     */
    @FXML
    private void BFermer(ActionEvent event)
    {
        close();
    }
}
