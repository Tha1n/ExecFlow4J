package core;

import java.util.List;

/**
 * Created by steve on 17/11/2016.
 */
public interface Program extends Drawing {

    /**
     * Nom du programme
     * @return le nom du programme
     */
    public String getName();

    /**
     * Regroupe l'ensemble des appels de fonction (typiquement, la fonction main en fait partie)
     * @return Liste de fonctions appelé à la racine du programme
     */
    public List<Function> getCalledFunctions();

    /**
     * Retourne la fonction actuelle dans lequel l'exécution se fait.
     * @return Function, si on tourne dans une fonction, null le cas échéant
     */
    public Function getCurrentFunction();

    /**
     * Modifie la fonction actuelle. Ne doit pas être appelée par autre chose qu'une classe du core.
     * @param function Nouvelle fonction actuelle
     */
    public void setCurrentFunction(Function function);
}
