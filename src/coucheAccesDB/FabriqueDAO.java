package coucheAccesDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabriqueDAO
{
    private static  FabriqueDAO instance = new FabriqueDAO();

    private  Connection SqlConn = null;

    private  FabriqueDAO(){}

    public static  FabriqueDAO getInstance()
    {
        return instance;
    }

    public  void  creerConnexion() throws ExceptionAccessBD
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            SqlConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
            "database=WEBSHOP;" +
            "user=genial;" +
            "password=super;");
        }

        catch (Exception e)
        {
            throw new ExceptionAccessBD(e.getMessage());
        }
    }

    public ClientDAO getInstClientDAO()
    {
        return new ClientDAO(SqlConn);
    }
}
