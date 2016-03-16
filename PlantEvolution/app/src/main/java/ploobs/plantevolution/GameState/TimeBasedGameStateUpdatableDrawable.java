package ploobs.plantevolution.GameState;

import java.util.Timer;

import ploobs.plantevolution.Component.TimerComponent;

/**
 * Created by Bruno on 02/12/2015.
 */
public abstract class TimeBasedGameStateUpdatableDrawable extends GameStateUpdatableDrawable {


    private TimerComponent timer;
    private boolean ended = false;
    private boolean started = false;


    public TimeBasedGameStateUpdatableDrawable( int timeconst)
    {
        timer = new TimerComponent(timeconst);


    }



    @Override
    public  void Update()
    {
      //  if(timer == null)
          //  timer = new TimerComponent(timerconst);

        if(timer.Update())
            setEnded(true);
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }


    public boolean isStarted() {

        return started;
    }

    public void setStarted(boolean started) {
        timer.Start();
        this.started = started;
    }
}
