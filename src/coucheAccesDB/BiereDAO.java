package coucheAccesDB;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ClassMetier.*;

public class BiereDAO extends BaseDAO<Biere>
{
    /**
     * Constructeur
     */
    public BiereDAO(Connection sqlConn) {
        super(sqlConn);
    }

    /**
     * Méthode qui lit dans la DB un client spécifique
     *
     * @param num : le numéro de produit
     * @return l'alcool lu dans la DB
     * Le caractère "?" en sql correspond à "1" dans sqlCmd et qui renvoie le "num".
     */

    public Biere Charger(int num) throws ExeceptionAccessBD
    {
        Biere biere = null;

        try
        {
            SqlConn.setAutoCommit(false);
            PreparedStatement sqlCmd =
                    SqlConn.prepareCall("select numArticle, nom, nomImage, prix, quantiteStock, Active" +
                            "from article" + "where numArticle = ? and Active = 1");
            sqlCmd.setInt(1, num);

            sqlCmd =
                    SqlConn.prepareCall("select Alcool, gout, recipient" +
                            "from biere" + "where numArticle = ?");
            sqlCmd.setInt(1, num);

            ResultSet iRes = sqlCmd.executeQuery();

            SqlConn.commit();
            SqlConn.setAutoCommit(true);


            if ((iRes.next() == true))
            {
                biere = new Biere(
                        iRes.getInt(1),       // numArticle
                        iRes.getString(2),    // nom
                        iRes.getString(3),    // nomImage
                        iRes.getInt(4),    // prix
                        iRes.getInt(5),    // quantiteStock
                        iRes.getBoolean(6), // Active
                        iRes.getBoolean(7),   // Alcool
                        iRes.getString(8),    // gout
                        iRes.getString(9));  // recipient
            }
            iRes.close();
            return biere;
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
            }
            throw new ExeceptionAccessBD(e.getMessage());
        }
    }

    public boolean Ajouter(Biere obj) throws ExeceptionAccessBD, ExeceptionAccessBD {

        try
        {
            SqlConn.setAutoCommit(false);

            PreparedStatement sqlCmd = SqlConn.prepareCall("select max(NumArticle) + 1 from produit");
            ResultSet sqlRes = sqlCmd.executeQuery();
            sqlRes.next();

            int NumArticle = sqlRes.getInt(1);
            if (sqlRes.wasNull()) NumArticle = 1;

            sqlCmd.close();

            sqlCmd = SqlConn.prepareCall("insert into produit values(?,?,?,?,?,1)");

            sqlCmd.setInt(1, NumArticle);
            sqlCmd.setString(2, obj.getNom());
            sqlCmd.setString(3, obj.getNomImage());
            sqlCmd.setInt(4, obj.getPrix());
            sqlCmd.setInt(5, obj.getQuantiteStock());
        //    sqlCmd.setBoolean(6,obj.getActive());

            sqlCmd.executeUpdate();

            /// VERIFIER SI PAS D'ERREUR ICI !!! //////

            //PreparedStatement sqlCmd2 = SqlConn.prepareCall("select max(NumArticle) from produit");
            //ResultSet sqlRes2 = sqlCmd.executeQuery();
            //sqlRes.next();

           // int NumArticle2 = sqlRes.getInt(1);


            sqlCmd = SqlConn.prepareCall("insert into biere values(?,?,?,?)");

            sqlCmd.setInt(1, NumArticle);
            sqlCmd.setString(2, obj.getGout());
            sqlCmd.setString(3, obj.getRecipient());
            sqlCmd.setBoolean(4, obj.getAlcool());

            int iRes = sqlCmd.executeUpdate();
            SqlConn.commit();
            SqlConn.setAutoCommit(true);

           return (iRes == 0)? false : true;
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
            }
            throw new ExeceptionAccessBD(e.getMessage());
        }
    }

    @Override
    public boolean Modifier(Biere obj) {
        return false;
    }

    @Override
    public List<Biere> ListerTous() throws ExeceptionAccessBD
    {
        ArrayList<Biere> liste = new ArrayList<Biere>();
        try
        {
            PreparedStatement sqlCmd = SqlConn.prepareCall(
                    "select produit.NumArticle, nom, nomImage, prix, quantiteStock, produit.Active, " +
                            "Alcolise, Gout, Recipient "+
                            " from produit, biere where produit.NumArticle = biere.NumArticle" +
                            " order by produit.NumArticle asc");
            ResultSet sqlRes = sqlCmd.executeQuery();
            while (sqlRes.next() == true)
                liste.add(new Biere(
                        sqlRes.getInt(1),       // numArticle
                        sqlRes.getString(2),    // nom
                        sqlRes.getString(3),    // nomImage
                        sqlRes.getInt(4),    // prix
                        sqlRes.getInt(5),    // quantiteStock
                        sqlRes.getBoolean(6), // Active
                        sqlRes.getBoolean(7),   // Alcool
                        sqlRes.getString(8),    // gout
                        sqlRes.getString(9)));  // recipient
            sqlRes.close();
        }
        catch(Exception e)
        {
            throw new ExeceptionAccessBD(e.getMessage());
        }
        return liste;
    }


    @Override
    public boolean Supprimer(int num) {
        return false;
    }
}
