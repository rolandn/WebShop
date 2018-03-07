package coucheAccesDB;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ClassMetier.LigneCom;

public class LigneComDAO extends BaseDAO<LigneCom> {
    /**
     * Constructeur
     */
    public LigneComDAO(Connection sqlConn) {
        super(sqlConn);
    }

    /**
     * Méthode qui lit dans la DB une ligne spécifique
     *
     * @param num : le numéro de ligne de commande
     */

    public LigneCom Charger(int num) throws ExceptionAccessBD {
        try {
            SqlConn.setAutoCommit(false);
            PreparedStatement sqlCmd =
                    SqlConn.prepareCall("select idLigne, idCom, idProduit, quantite" +
                            "from ligne" + "where idLigne = ?");
            sqlCmd.setInt(1, num);

            ResultSet iRes = sqlCmd.executeQuery();

            SqlConn.commit();
            SqlConn.setAutoCommit(true);


            if ((iRes.next() == true)) {
                ligneCom = new LigneCom(
                        iRes.getInt(1)  // Id Ligne
                        iRes.getInt(2)  // Id Commande
                        iRes.getInt(3)  // Id Produit
                        iRes.getInt(4));  // Quantite
            }

            iRes.close();
            return ligneCom;
        } catch (Exception e) {
            try {
                SqlConn.rollback();
                SqlConn.setAutoCommit(true);
            } catch (Exception e1) {
            }
            throw new ExceptionAccessBD(e.getMessage());
        }

        @Override
        public boolean Modifier (LigneCom obj){
            return false;
        }

        @Override
        public List<LigneCom> ListerTous () {
            return null;
        }

        @Override
        public boolean Supprimer ( int num){
            return false;
        }

    }
}
