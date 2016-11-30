package core;

import java.util.List;

/**
 * Created by steve on 16/11/2016.
 */
public interface Function {

    /**
     * Nom de la fonction
     * @return le nom de la fonction
     */
    public String getName();

    /**
     * Classe auquelle est rattachée la fonction
     * @return Le nom court de la classe de la fonction
     */
    public String getClassName();

    /**
     * Retourne la fonction parente de cette fonction
     * @return Function si elle existe, null s'il s'agit d'une fonction racine
     */
    public Function getCaller();

    /**
     * Regroupe l'ensemble des fonctions appelés par cette même fonction
     * @return les fonctions appelées par cette fonction
     */
    public List<Function> getCallees();

    /**
     * Application contenant cette fonction
     * @return le programme de cette fonction
     */
    public Program getProgram();

    /**
     * Temps émis par la fonction en milisecondes
     * @return le temps pris par cette fonction
     */
    public long getTime();

    /**
     * Cette fonction n'est à appeler qu'une seule fois.
     * Elle permet de débuter les calculs concernant le temps émis et de la dessiner
     */
    public void startFunction();

    /**
     * Cette fonction n'est à appeler qu'une seule fois.
     * Permet de terminer les calculs et de fixer le dessin.
     */
    public void endFunction();
}
