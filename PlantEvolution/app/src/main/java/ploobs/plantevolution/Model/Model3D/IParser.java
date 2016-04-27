package ploobs.plantevolution.Model.Model3D;

import ploobs.plantevolution.World.ObjectContainer;

/**
 * Interface for 3D object parsers
 *
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
   // public AnimationObject3d getParsedAnimationObject();
}