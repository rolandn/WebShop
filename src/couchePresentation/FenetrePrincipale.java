package couchePresentation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import coucheAccesDB.*;
import sun.misc.Launcher;


public class FenetrePrincipale extends Application {
    private final int Largeur = 750;
    private final int Longueur = 500;

    private static Stage Instance;

    private Scene SceneObj;
    private BorderPane BPane = new BorderPane();
    private MenuBar BarreMenu = new MenuBar();

    private Menu MenuClient = new Menu("Clients");
    private MenuItem MIAjouterClient = new MenuItem("Ajouter un client");
    private MenuItem MISupprimerClient = new MenuItem("Supprimer un client");
    private MenuItem MIModifierClient = new MenuItem("Modifier un client");
    private MenuItem MIListerClient = new MenuItem("Lister les clients");

    private Menu MenuAlcool = new Menu("Alcools");
    private MenuItem MIAjouterAlcool = new MenuItem("Ajouter un alcool");

    private Menu Quitter = new Menu("Quitter");
    private MenuItem QuitterProgramme = new MenuItem("Quitter le programme");


    /**
     * Méthode statique qui retourne l'objet Stage de la fenêtre principale
     *
     * @return l'objet Stage de la fenêtre
     */

    public static Stage getInstance()
    {
        return Instance;
    }

    /**
     * Constructeur: il ajoute tous les objets à la fenêtre
     * @param fenetre: l'objet Stage représentant la fenêtre principale
     */

    @Override
    public void start(Stage fenetre) {
        Instance = fenetre;

        // barre des menus

        MenuClient.getItems().addAll(MIAjouterClient, MISupprimerClient, MIModifierClient, MIListerClient);
        MenuAlcool.getItems().addAll(MIAjouterAlcool);

        BarreMenu.getMenus().addAll(MenuClient);
        BarreMenu.getMenus().addAll(MenuAlcool);

        BarreMenu.prefWidthProperty().bind(fenetre.widthProperty());

        // Actions liées éléments de menu

        MIAjouterClient.setOnAction(event -> {
            new AjouterClient();
        });
        MISupprimerClient.setOnAction(event -> {
            new SupprimerClient();
        });
        MIModifierClient.setOnAction(event -> {
            new ModifierClient();
        });
        MIListerClient.setOnAction(event -> {
            new ListerClient();
        });

        MIAjouterAlcool.setOnAction(event -> {
                    new AjouterAlcool();
                });

        QuitterProgramme.setOnAction(event -> {
            System.exit(0);
        });

        // Barre des menus BPaine et Scene

        BPane.setTop(BarreMenu);
        SceneObj = new Scene(BPane, Largeur, Longueur, Color.WHITE);
        fenetre.setScene(SceneObj);

        // Style CSS

        SceneObj.getStylesheets().add("couchePresentation/styleMenu.css");

        // Parametrer la fenetre puis l'afficher

        fenetre.setTitle("Backoffice WebShop");
        fenetre.setResizable(false);
        fenetre.centerOnScreen();
        fenetre.show();
    }
        /**
         * Méthode exécutée au démarrage de l'application
         */

        public static void main(String[] args)
        {
            try
            {
                FabriqueDAO.getInstance().creerConnexion();
            }
            catch (ExceptionAccessBD e)
            {
                GererErreur.GererErreurGen("FenêtrePrincipale", "start()", e.getMessage() );
                System.out.println("problème pour se connecter à la DB");
                System.exit(0);
            }


        // Charger la fenêtre principale

        launch(args);
    }

}
