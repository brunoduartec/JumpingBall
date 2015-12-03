package ploobs.plantevolution.GameState;

import ploobs.plantevolution.Component.TimerComponent;

/**
 * Created by Bruno on 02/12/2015.
 */
public abstract class TimeBasedGameStateUpdatableDrawable extends GameStateUpdatableDrawable {


    private TimerComponent timer;
    private boolean ended = false;

    public TimeBasedGameStateUpdatableDrawable( int timeconst)
    {
        timer = new TimerComponent(timeconst);


    }



    @Override
    public  void Update()
    {
        if(timer.Update())
            setEnded(true);
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }
}
