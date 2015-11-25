package ploobs.plantevolution.Component;

/**
 * Created by Bruno on 29/09/2015.
 */
public class FpsCounterComponent {

    private int tick;
    private float fps;


    public FpsCounterComponent(float fps)
    {
        this.fps = fps;

        //get the current time
        tick = (int) System.currentTimeMillis();

    }

    public boolean Update()
    {

        int gametime = (int) System.currentTimeMillis();
        boolean toUpdate = false;
        if ((gametime - tick)>(1000/fps))
        {
            tick = gametime;
            toUpdate= true;

        }
        else
        toUpdate=false;




        return toUpdate;
    }




}
