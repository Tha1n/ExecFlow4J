package core;

import java.awt.*;

/**
 * Created by steve on 07/12/2016.
 */
public interface Drawing {

    /**
     * Dessine une représentation de l'objet
     * @param g objet sur lequel dessiner
     */
    public void draw(Graphics2D g);

    /**
     * Dessine une représentation de l'objet
     * @param g objet sur lequel dessiner
     * @param xy positions sur g de la forme {x, y}
     */
    public int[] draw(Graphics2D g, int[] xy);
}
