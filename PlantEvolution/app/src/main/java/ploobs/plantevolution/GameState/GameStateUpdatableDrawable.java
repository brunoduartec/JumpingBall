package ploobs.plantevolution.GameState;

/**
 * Created by Bruno on 02/12/2015.
 */
public abstract class GameStateUpdatableDrawable implements IGameState {
    @Override
    public abstract void Entered();

    @Override
    public abstract void Leaving();

    @Override
    public abstract void Obscuring();

    @Override
    public abstract void Revealed();

    public abstract void Update();
    public abstract void Draw();

    public abstract void HandleEvent();

}
