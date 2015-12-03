package ploobs.plantevolution.GameState;

/**
 * Created by Bruno on 02/12/2015.
 */
public abstract class GameStateUpdatableDrawable implements IGameState {
    @Override
    public void Entered() {

    }

    @Override
    public void Leaving() {

    }

    @Override
    public void Obscuring() {

    }

    @Override
    public void Revealed() {

    }

    public abstract void Update();
    public abstract void Draw();

}
