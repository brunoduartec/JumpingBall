package ploobs.plantevolution.GameState;

/**
 * Created by Bruno on 02/12/2015.
 */
interface IGameState {

    void Entered();
    void Leaving();
    void Obscuring();
    void Revealed();

    boolean isEnded();


}
