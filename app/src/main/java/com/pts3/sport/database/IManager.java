package com.pts3.sport.database;

import java.util.List;

/**
 * Created by Ragnulf on 12/12/2017.
 */

public interface IManager<Type> {

    public Type recuperer(int id);
    /*
     * Permet de recupérer toutes les infos en renvoyant une liste.
     */
    public List<Type> recupererTout();
    public void ajouter(Type objet);
    public void modifier(Type objet);
    public void supprimer(Type objet);
}
