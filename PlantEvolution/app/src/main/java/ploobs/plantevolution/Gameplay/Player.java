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

    private Vector2 _direction = new Vector2();

    private Vector3 localPos = new Vector3();

    private Vector3 velocity = new Vector3(0,0,0);

    private Vector2 tendency = new Vector2();

    private float moveamount = 0;
    private float deltamove = 0.2f;



    private Vector3 g = new Vector3(0,-9.81f,0);


    private float mass;

    private int energy;



    private float minimunY=GameConstants.scale;
    private float scale;

    private boolean isColiding;


    private boolean jumping;


    public Player(IMaterial mat, IModel mod, String nm,int energy)
    {
        //public SimpleObject(IMaterial mat, IModel mod, String nm)

        super(mat,mod,nm);
        this.scale = GameConstants.scale;
        this.setEnergy(energy);
        this.deltamove = scale/4;

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

this.moveamount = scale;

        if (getPosition().getY()>= after)
        {
          // in the case the ball goes up a degree
            this.minimunY = after+scale;




        }
    }





    @Override
    public void Update()
    {


        if (jumping) //|| this.getPosition()[1]>=minimunY)
            Jump();
        else {
            Vector3 pp = this.getPosition();
            pp.setY(minimunY);
            this.setPosition(pp);
        }

        if (_direction.getMagnitude()>0)
            Move();






    }

    void Move()
    {

        Vector3 pp = this.getPosition();

        this.moveamount -= deltamove;

      Vector3 delta = new Vector3(_direction.getX(),0,_direction.getY()).mul(deltamove);
        this.setPosition(pp.add(delta));

        if (this.moveamount <= 0) {
            // at this moment set the local position
            setLocalPos(new Vector3(localPos.getX() + _direction.getX(), this.minimunY, localPos.getZ() + _direction.getY()));
            _direction = new Vector2();
        }
    }

    void Jump()
    {

        float dt = 0.1f;


        Vector3 Vft = velocity.add(g.mul(dt));
        Vector3 dx = velocity.mul(dt).add(g.mul(0.5f * dt * dt));
        velocity = Vft;

        Vector3 pp = this.getPosition();


       pp =  pp.add(dx);


        if ( pp.getY()<=minimunY) {

            pp.setY(minimunY);



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
