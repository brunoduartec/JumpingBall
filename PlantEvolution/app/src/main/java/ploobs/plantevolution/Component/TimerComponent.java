package ploobs.plantevolution.Component;

/**
 * Created by Bruno on 29/09/2015.
 */
public class TimerComponent {

    private int tick;
    private float constant;


    public TimerComponent(int constant)
    {
        this.constant = constant;

        //get the current time
        tick = (int) System.currentTimeMillis();

    }

    public boolean Update()
    {

        int gametime = (int) System.currentTimeMillis();
        boolean toUpdate = false;
        if ((gametime - tick)>(constant))
        {
            tick = gametime;
            toUpdate= true;

        }
        else
            toUpdate=false;




        return toUpdate;
    }




}
