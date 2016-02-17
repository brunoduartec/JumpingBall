package ploobs.plantevolution.Gameplay;

import ploobs.plantevolution.Audio.AudioPlayer;
import ploobs.plantevolution.Material.IMaterial;
import ploobs.plantevolution.Model.IModel;
import ploobs.plantevolution.World.SimpleObject;
import ploobs.plantevolution.Math.Vector2;
import ploobs.plantevolution.Math.Vector3;

/**
 * Created by Bruno on 21/10/2015.
 */
public class Player extends SimpleObject {


    private int objectID;
private Vector2 _direction;

    private Vector3 localPos = new Vector3();

    private Vector3 velocity = new Vector3(0,0,0);

    private Vector3 g = new Vector3(0,-9.81f,0);


    private float mass;

    private int energy;



    private float minimunY=0.7f;
    private float scale;

    private boolean isColiding;


    private boolean jumping;


    public Player(IMaterial mat, IModel mod, String nm,int energy)
    {
        //public SimpleObject(IMaterial mat, IModel mod, String nm)

        super(mat,mod,nm);
        this.scale = GameConstants.scale;
        this.setEnergy(energy);

    }



    public void Collide(float min)
    {

        //velocity.setY(-velocity.getY());
    this.minimunY = min;

    }

    //The direction is assincronous
    public void setDirection(Vector2 dir,float after)
    {
       this._direction = dir;


        if (getPosition()[1]>= after)
        {
            float[] oldpos = getPosition();
            oldpos[0] += (dir.getX()*scale);
            oldpos[2] += (dir.getY()*scale);
            this.minimunY = after+scale;

            setPosition(oldpos);
            setLocalPos( new Vector3(localPos.getX()+dir.getX(),this.minimunY,localPos.getZ()+dir.getY()));



        }
    }





    @Override
    public void Update()
    {


        if (jumping) //|| this.getPosition()[1]>=minimunY)
            Jump();
        else
        {
            float[] pp = this.getPosition();
            pp[1] = minimunY;
            this.setPosition(pp);

        }




    }

    void Move()
    {



    }

    void Jump()
    {

        float dt = 0.05f;


        Vector3 Vft = velocity.add(g.mul(dt));
        Vector3 dx = velocity.mul(dt).add(g.mul(0.5f * dt * dt));
        velocity = Vft;

        float[] pp = this.getPosition();

        pp[0] += dx.getX();
        pp[1] += dx.getY();
        pp[2] += dx.getZ();


        if ( pp[1]<=minimunY) {

            pp[1] = minimunY;



            float Vinit = (float)Math.sqrt(-2*g.getY()*1.3f*scale);
            velocity = new Vector3(0, Vinit, 0);
            AudioPlayer.getInstance().playAudio("jump_sound");


        }
        this.setPosition(pp);




    }


    public Vector3 getLocalPos() {
        return localPos;
    }

    public void setLocalPos(Vector3 localPos) {
        this.localPos = localPos;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
