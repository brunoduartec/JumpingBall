package ploobs.plantevolution.Component;

/**
 * Created by Bruno on 29/09/2015.
 */
public class TimerComponent {

    private int tick;
    private float constant;
    private boolean started;


    public TimerComponent(int constant)
    {
        this.constant = constant;
        this.started = false;
        //get the current time


    }

    public boolean Update()
    {

        int gametime = (int) System.currentTimeMillis();
        boolean toUpdate = false;

      if(started) {
          if ((gametime - tick) > (constant)) {
              tick = gametime;
              toUpdate = true;

          } else
              toUpdate = false;
      }



        return toUpdate;
    }


    public void Start() {
        started = true;
        tick = (int) System.currentTimeMillis();
    }
}
