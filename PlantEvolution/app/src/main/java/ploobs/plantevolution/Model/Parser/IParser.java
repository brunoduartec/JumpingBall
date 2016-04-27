package ploobs.plantevolution.Model.Parser;

/**
 * Created by Bruno on 23/02/2016.
 */


import ploobs.plantevolution.World.Animation.AnimationObject;
import ploobs.plantevolution.World.ObjectContainer;

/**
 * Interface for 3D object parsers
 *
 * @author dennis.ippel
 *
 */
public interface IParser {
    /**
     * Start parsing the 3D object
     */
    void parse();
    /**
     * Returns the parsed object
     * @return
     */
    ObjectContainer getParsedObject();
    /**
     * Returns the parsed animation object
     * @return
     */
    AnimationObject getParsedAnimationObject();
}