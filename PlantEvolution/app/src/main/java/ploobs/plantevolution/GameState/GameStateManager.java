package ploobs.plantevolution.GameState;

import java.sql.Time;
import java.util.Stack;

/**
 * Created by Bruno on 02/12/2015.
 */
public class GameStateManager {

    Stack<IGameState> currentStates = new Stack<>();
    IGameState actualState;

    public void Switch(IGameState state )
    {}

    public IGameState Pop()
    {
        IGameState ret = null;
        if (currentStates.size()>=1) {
            currentStates.peek().Leaving();
            ret = currentStates.pop();
            currentStates.peek().Revealed();
            actualState = currentStates.peek();
        }
            return ret;
    }

    public void Push(IGameState state)
    {
        if (currentStates.size()>=1)
        currentStates.peek().Obscuring();

        currentStates.push(state);
        state.Entered();
        actualState = currentStates.peek();
    }

    public IGameState Peek()
    {

        return currentStates.peek();
    }


    public void Update()
    {

        if (actualState instanceof GameStateUpdatableDrawable)
        {

            GameStateUpdatableDrawable up = (GameStateUpdatableDrawable)actualState;
            up.Update();
        }

        if (actualState instanceof TimeBasedGameStateUpdatableDrawable)
        {
            TimeBasedGameStateUpdatableDrawable tt = (TimeBasedGameStateUpdatableDrawable)actualState;
            if (tt.isEnded())
                Pop();

        }


    }

    public void Draw()
    {

        if (actualState instanceof GameStateUpdatableDrawable) {
            GameStateUpdatableDrawable up = (GameStateUpdatableDrawable)actualState;
            up.Draw();
        }


    }


    public void HandleEvent()
    {
        if (actualState instanceof GameStateUpdatableDrawable) {
            GameStateUpdatableDrawable up = (GameStateUpdatableDrawable)actualState;
            up.HandleEvent();

        }

    }




}
