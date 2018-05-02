package coucheAccesDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ClassMetier.Client;
import ClassMetier.Produit;

public class ProduitDAO extends BaseDAO<Produit>
{


    /**
     * Constructeur
     *
     * @param sqlConn
     */
    public ProduitDAO(Connection sqlConn) {
        super(sqlConn);
    }

    @Override
    public Produit Charger(int num) throws ExceptionAccessBD {
        return null;
    }

    @Override
    public boolean Ajouter(Produit obj) throws ExceptionAccessBD {
        return false;
    }

    /**
     *  A FAIRE !!! (c'est un copier/coller d'autre choses)
     *
     */
    @Override
    public boolean Modifier(Produit obj) throws ExceptionAccessBD {

        try {
            PreparedStatement sqlCmd = SqlConn.prepareCall("update produit" +
                    "set NumArticle = ?, " +
                    "nom = ?, " +
                    "prenom = ?, " +
                    "adresse = ?, " +
                    "email = ?, " +
                    "mdp = ?, ");
            /*sqlCmd.setInt(1, obj.getNumClient());
            sqlCmd.setString(2, obj.getNom());
            sqlCmd.setString(3, obj.getPrenom());
            sqlCmd.setString(4, obj.getAdresse());
            sqlCmd.setString(5, obj.getEmail());
            sqlCmd.setString(6, obj.getMdp());*/

            return (sqlCmd.executeUpdate() == 0) ? false : true;
        } catch (Exception e) {
            throw new ExceptionAccessBD(e.getMessage());
        }

    }

    @Override
    public boolean Supprimer(int num) throws ExceptionAccessBD {
        return false;
    }

    @Override
    public List<Produit> ListerTous() throws ExceptionAccessBD {

        ArrayList<Produit> list = new ArrayList<>();

        try
        {
            PreparedStatement sqlCmd = SqlConn.prepareCall("select NumArticle, nom, nomImage," +
                    "prix, quantiteStock" + "from produit" + "order by NumArticle asc");

            ResultSet sqlRes = sqlCmd.executeQuery();

            while (sqlRes.next() == true)
                list.add(new Produit(sqlRes.getInt(1),
                        sqlRes.getString(2),
                        sqlRes.getString(3),
                        sqlRes.getInt(4),
                        sqlRes.getInt(5)));
            sqlRes.close();
        }

        catch (Exception e)
        {
            throw new ExceptionAccessBD(e.getMessage());
        }

        return list;
    }
}
