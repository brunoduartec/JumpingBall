package ploobs.plantevolution.Gameplay;

/**
 * Created by Bruno on 27/12/2015.
 */
public class BlockMovement {

    public DIRECTION getDirectionmade() {
        return directionmade;
    }

    public void setDirectionmade(DIRECTION directionmade) {
        this.directionmade = directionmade;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public enum DIRECTION{RIGHT,LEFT,FRONT,BACK};


    private DIRECTION directionmade;
    private int blockID;// tenho que ver qual id que vou armazenar


    public BlockMovement(DIRECTION dir, int objectID)
    {

        this.setDirectionmade(dir);
        this.setBlockID(objectID);

    }


}
