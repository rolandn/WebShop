package coucheAccesDB;

import com.intellij.ide.ui.EditorOptionsTopHitProvider;

import java.util.List;
import java.sql.Connection;

public abstract class BaseDAO<T>
{
    protected Connection SqlConn;

    /**
     * Constructeur
     */
    public BaseDAO(Connection sqlConn)
    {
        SqlConn = sqlConn;
    }

    /**
     * Méthodes abstaites
     */

    public abstract T Charger(int num) throws ExceptionAccessBD;
    public abstract boolean Ajouter(T obj) throws ExceptionAccessBD;
    public abstract boolean Modifier(T obj) throws ExceptionAccessBD;
    public abstract boolean Supprimer(int num) throws  ExceptionAccessBD;
    public abstract List<T> ListerTous() throws ExceptionAccessBD;
}
