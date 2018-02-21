package coucheAccesDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ClassMetier.Alcool;

public class AlcoolDAO extends BaseDAO<Alcool>
{
    /**
     * Constructeur
     */
    public AlcoolDAO(Connection sqlConn) {
        super(sqlConn);
    }

    /**
     * Méthode qui lit dans la DB un client spécifique
     *
     * @param num : le numéro de client
     * @return le client lu dans la DB
     * Le caractère "?" en sql correspond à "1" dans sqlCmd et qui renvoie le "num".
     */

    public Alcool Charger(int num) throws ExceptionAccessBD
    {
        Alcool alcool = null;

        try
        {
            SqlConn.setAutoCommit(false);
            PreparedStatement sqlCmd =
                    SqlConn.prepareCall("select numArticle, nom, nomImage, prix, quantiteStock" +
                            "from article" + "where numArticle = ?");
            sqlCmd.setInt(1, num);

            sqlCmd =
                    SqlConn.prepareCall("select degreAlcool, gout, provenance" +
                            "from alcool" + "where numArticle = ?");
            sqlCmd.setInt(1, num);

            ResultSet iRes = sqlCmd.executeQuery();

            SqlConn.commit();
            SqlConn.setAutoCommit(true);


            if ((iRes.next() == true))
            {
                alcool = new Alcool(
                        iRes.getInt(1),       // numArticle
                        iRes.getString(2),    // nom
                        iRes.getString(3),    // nomImage
                        iRes.getInt(4),    // prix
                        iRes.getInt(5),    // quantiteStock
                        iRes.getInt(6),   // degreAlcool
                        iRes.getString(7),    // gout
                        iRes.getString(8));  // provenance
            }
            iRes.close();
            return alcool;
        }

        catch (Exception e)
        {
            try
            {
                SqlConn.rollback();
                SqlConn.setAutoCommit(true);
            }
            catch (Exception e1)
            {
                throw new ExceptionAccessBD(e1.getMessage());
            }

        }
    }

    public boolean Ajouter(Alcool obj) throws ExceptionAccessBD
    {
        Alcool alcool = null;

        try  // A ré-écrire !!
        {
            PreparedStatement sqlCmd = SqlConn.prepareCall("select max(NumArticle) + 1 from produit");
            ResultSet sqlRes = sqlCmd.executeQuery();
            sqlRes.next();

            int NumArticle = sqlRes.getInt(1);
            if (sqlRes.wasNull()) NumArticle = 1;

            sqlCmd.close();

            sqlCmd = SqlConn.prepareCall("insert into produit values(?,?,?,?,?)");

            sqlCmd.setInt(1, NumArticle);
            sqlCmd.setString(2, obj.getNom());
            sqlCmd.setString(3, obj.getNomImage());
            sqlCmd.setInt(4, obj.getPrix());
            sqlCmd.setInt(5, obj.getQuantiteStock());

            if ((sqlCmd.executeUpdate() == 0) ? false : true) return true;
            else return false;

            // Peut-on faire les requêtes pour alimenter la deuxième table ici dans le foulée ???

            PreparedStatement sqlCmd = SqlConn.prepareCall("select max(NumArticle) + 1 from alcool");
            ResultSet sqlRes = sqlCmd.executeQuery();
            sqlRes.next();

            int NumArticle = sqlRes.getInt(1);
            if (sqlRes.wasNull()) NumArticle = 1;

            sqlCmd.close();

            sqlCmd = SqlConn.prepareCall("insert into alcool values(?,?,?,?)");

            sqlCmd.setInt(1, NumArticle);
            sqlCmd.setInt(2, obj.getDegreAlcool());
            sqlCmd.setString(3, obj.getGout());
            sqlCmd.setString(4, obj.getProvenance());

            if ((sqlCmd.executeUpdate() == 0) ? false : true) return true;
            else return false;
        }

        catch (Exception e)
        {
            try
            {
                SqlConn.rollback();
                SqlConn.setAutoCommit(true);
            }
            catch (Exception e1)
            {
                throw new ExceptionAccessBD(e1.getMessage());
            }

        }
    }

}