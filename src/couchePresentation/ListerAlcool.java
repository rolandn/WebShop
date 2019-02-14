package couchePresentation;

import ClassMetier.*;
import coucheAccesDB.*;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.stage.Stage;



public class ListerAlcool extends BaseFenetre{

    @FXML private TableView<Alcool> TVAlcool;
    @FXML private Button BFermer;
    @FXML private ImageView IVImage;


    /**
     * Constructeur : il crée la fenêtre
     * @param fenParent : l'objet Stage représentant la fenêtre parent
     */
    public ListerAlcool(Stage fenParent) throws ExeceptionAccessBD {
            // créer la fenêtre
        super(fenParent, "ListerAlcool.xml", "Lister les alcools", 1700, 700);
            // ajouter la liste des alcool à la table TVAlcool

        TVAlcool.itemsProperty().setValue(FXCollections.observableArrayList(
                FabriqueDAO.getInstance().getInstAlcoolDAO().ListerTous()));
        if(TVAlcool.getItems().size() == 0)
        {
            new MsgBox(this, AlertType.INFORMATION,
                    "Il n'y a aucun alcool dans la base de données!");
            return;
        }
// gérer l'affichage de l'image quand on clique sur un alcool dans la table
        TVAlcool.getSelectionModel().selectedItemProperty().addListener((obs, ancAlcool,
                                                                        nouvAlcool) ->
        {
            if (nouvAlcool != null)
                IVImage.setImage(new Image("file:imgs/" + nouvAlcool.getNomImage()));
        });
// sélectionner le 1er prof dans la table TVAlcool
        TVAlcool.getSelectionModel().selectFirst();
// afficher la fenêtre
        showAndWait();
    }
    /**
     * Méthode exécutée lorsque l'utilisateur clique sur le bouton Fermer
     */
    @FXML private void BFermer(ActionEvent event)
    {
        close();
    }
}


