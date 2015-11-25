package ploobs.plantevolution.Gameplay;

import ploobs.plantevolution.Vector3;

/**
 * Created by Bruno on 20/10/2015.
 */
public class NormalBlock extends Block {


public NormalBlock(){}


    public NormalBlock(int ID, Vector3 localposition)
    {
     super(ID,localposition);

    }

    //@Override
   // public float[] getColor() {
    //    return new float[]{0.5f, 0.5f, 0.5f, 1.0f};
    //}

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean canStack() {
        return true;
    }

    @Override
    public void onCollide(Block b) {

    }
}
