package couchePresentation;

import com.intellij.diagnostic.TestMessageBoxAction;
import javafx.collections.FXCollections;
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


    public class ModifierStock
    {
        private final int Largeur = 580;
        private final int Hauteur = 300;

        private Stage Fenetre = new Stage();
        private Scene SceneObj;
        private HBox HBSaisies = new HBox(15);
        private HBox HBBoutons = new HBox(15);
        private HBox HBoxImg = new HBox(15);
        private GridPane GPSaisies = new GridPane();
        private VBox VBZonesFenetre = new VBox();
        private ComboBox<Produit> CBProduit = new ComboBox<>();
        private TextField TFNumArticle = new TextField();
        private TextField TFnom = new TextField();
        private TextField TFnomIMage = new TextField();
        private TextField TFprix = new TextField();
        private TextField TFquantiteStock = new TextField();
        private Button BChargerImage = new Button("...");
        private Button BModifier = new Button("Modifier");
        private Button BFermer = new Button("Fermer");
        private Separator SLigne = new Separator();
        private Separator SLigne1 = new Separator();
        private ImageView IVImage = new ImageView();
        private File FichierSrc;



    /**
     * Constructeur : il crée la fenêtre
     * @param fenParent : l'objet Stage représentant la fenêtre principale
     */

        public ModifierProduit()
        {
            try
            {
                CBProduit.setItems(FXCollections.observableArrayList(
                        FabriqueDAO.getInstance().getInsProduitDAO().ListerTous()
                ));
            }

            catch (ExceptionAccessBD e)
            {
                GererErreur.GererErreurSQL("ModifierStock, ", "ModifierProduit()", e.getMessage());
                new MessageBox(AlertType.INFORMATION,
                        "un problème est survenu lors du listage des produits.");

                        return;
            }

            if (CBProduit.getItems().size()==0)
            {
                new MessageBox(AlertType.INFORMATION, "Il n'y a encore aucun produit dans la DB.");
                return;
            }

            CBProduit.setVisibleRowCount(5);
            CBProduit.getSelectionModel().selectFirst();

            // gérer le changement de Produit courant dans la boîte combo CBChoixProduit
            CBChoixProduit.getSelectionModel().selectedItemProperty().addListener((obs,
                                                                                   ancProduit,nouvProduit)->{
                if(nouvProduit != null) CBChangerQuantite((nouvProduit));





                
                private void CBChangerQuantite(Produit nouvProduit)
                {
                    TFChangementQuantite.setText(nouvProduit.getQuantiteStockTxt());
                }

    }