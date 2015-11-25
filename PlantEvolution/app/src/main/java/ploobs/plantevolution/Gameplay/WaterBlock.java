package ploobs.plantevolution.Gameplay;

/**
 * Created by Bruno on 20/10/2015.
 */
public class WaterBlock extends Block {
   // @Override
    //public float[] getColor() {
     //   return new float[]{0.0f, 0.0f, 0.5f, 1.0f};
    //}

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean canStack() {
        return true;
    }

    @Override
    public void onCollide(Block b) {

    }
}
