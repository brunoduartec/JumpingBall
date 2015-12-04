package ploobs.plantevolution.Gameplay;

import ploobs.plantevolution.World.IObject;
import ploobs.plantevolution.World.IWorld;
import ploobs.plantevolution.Light.ILight;
import ploobs.plantevolution.MyGLRenderer;
import ploobs.plantevolution.ObjectFactory;
import ploobs.plantevolution.World.SimpleObject;
import ploobs.plantevolution.Math.Vector2;
import ploobs.plantevolution.Math.Vector3;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Bruno on 25/09/2015.
 */
public class Board {






    private float scale;
    private String name;

    int count = 0; // used to name objects


    Vector2 _direction = new Vector2(0,0);
    Vector2 _playerpos;


    private LinkedList<Block> bk;// = new LinkedList<Block>();

    Block higherblock;

    Player p1;
    SimpleObject gema;


    private int size;
    private int maxheight;


    private IWorld localWorld;
    private int gemaheight;


    public void Initialize()
    {
        bk = new LinkedList<>();

        List<ILight> ll = this.localWorld.getLights();
        this.localWorld.Initialize();
    //    this.localWorld.AddLightList(ll);
       // this.p1 = null;
      //  this.gema = null;


    }

    public boolean TestEnd()
    {

        Vector3 ppos = new Vector3(p1.getPosition());
        Vector3 gemapos = new Vector3(gema.getPosition());

         return ppos.distance(gemapos) <= GameConstants.scale;


    }

    public Board(IWorld w) {
        this.localWorld = w;
        this.setScale(GameConstants.scale);
        this.size= GameConstants.size;
        this.setGemaheight(size);

        _playerpos = new Vector2((float)(size-1),(float)size);
        Initialize();



    }


//considering that the origin > destiny
    private void swapTopBlocks(Block origin,Block destiny)
    {

        Object[] partsOrigin = origin.getChildreen();//retrieving childreen


        int originlenght = origin.getChildreenCount();
        int destinylenght = destiny.getChildreenCount();

        int delta = originlenght - destinylenght;

        int count = 0;

        Block[] bchanged =    origin.UnStrackBlocks(delta);

        for (int i=0;i<delta;i++)
        {
            destiny.StackBlock(bchanged[i]);

        }



    }



    private float[] convertLocalPosWorldPos(float[] localpos)
    {

        float x = localpos[0]* getScale() - (this.size/2)* getScale();
        float y = localpos[1]* getScale();
        float z = localpos[2]* getScale() - (this.size/2)* getScale();

        return new float[]{x,y,z,1};



    }

    public boolean testposintheBoard(Vector2 p)
    {

        if (p.getX()-1 ==  size && p.getY()==size)
            return true;

        return (p.getX()<=(size-1))&&(p.getY()<=(size-1))&&(p.getX()>=0)&&(p.getY()>=0);
    }

    public boolean intotheBoard(Block b, Vector3 dir)
    {

        Vector3 go = b.getLocalposition().add(dir);

        if (go.getX()<0 || go.getX()>size-1 || go.getZ()<0 || go.getZ()>size-1)
            return false;
        else
        return true;



    }

    public void MovePlayer(Vector2 dir)
    {

        Vector3 playerpos = p1.getLocalPos();


        int h1,h2;

        float[] actualpos = p1.getPosition();

        float x,y;

        x = playerpos.getX()+ dir.getX();
        y = playerpos.getZ()+ dir.getY();


            if (!testposintheBoard(new Vector2(x, y)))
                return;


        this._direction = dir;
        Block btemp =BlockExistAt(x, y);
        if (btemp != null)
            h2 = btemp.getChildreenCount()+1;
        else
            h2 = 0;



        if (p1.isJumping() ) {

            boolean validpos = true;
            if (btemp !=null)
            {
                validpos = btemp.canStack();
            }
            if (validpos)
                p1.setDirection(dir, (h2) * getScale());

        }
            else {

            if (h2 == 0)
                p1.setDirection(dir, (h2) * getScale());
            else if (!p1.isJumping())
                PushBlock(dir);

        }




    }

public void MergeBlock(Block origin, Block destiny)
{
    destiny.StackBlock(origin);
}

    public void MoveBlocks(Vector3 dir)
    {


      if (dir.getX()>0)
            Collections.sort(bk, new BlockXDESCComparator());

      if (dir.getX()<0)
            Collections.sort(bk, new BlockXASCComparator());

        if (dir.getZ()>0)
            Collections.sort(bk, new BlockZDESCComparator());

        if (dir.getZ()<0)
            Collections.sort(bk, new BlockZASCComparator());

        List<Block> marktoremove = new LinkedList<>();

        boolean moved = false;

       // for (int i=0; i< bk.size();i++)
        for (Block bOrigin:bk )

        {


           // Block b = bk.get(i);

            if (!bOrigin.canMove())//in the case the block cannot move
                continue;

            Vector3 posA = bOrigin.getLocalposition();
            boolean trymove=true;


            if(intotheBoard(bOrigin, dir)) {
                for (Block bdestiny:bk)
                {

                //for (int j = 0; j < bk.size(); j++) {
                    Vector3 posB = bdestiny.getLocalposition();
                    if (posA.add(dir).equals(posB) && !bOrigin.equals(bdestiny) ) {//there is a Block blocking it

                        //if (b.getChildreenCount()>bk.get(j).getChildreenCount() && bk.get(j).canStack())
                        if(bOrigin.getChildreenCount() == bdestiny.getChildreenCount() && bdestiny.canStack())
                        {
                          //  swapTopBlocks(b,bk.get(j));
                            MergeBlock(bOrigin,bdestiny);
                            marktoremove.add(bOrigin);
                            //bk.remove(bdestiny);
                            moved = true;
                        }

                           trymove = false;
                        break;
                    }
                }

                if (trymove) {
                    moved=true;
                    bOrigin.MoveTo(posA.add(dir));
                }
            }
        }

       for (Block b:marktoremove) {
            bk.remove(b);
        }

        for (Block b:bk ) {//updating the IObject instance

            IObject ob = localWorld.getObjectbyID(b.getObjectID());
            float[] newpos = convertLocalPosWorldPos(b.getLocalposition().get());
            ob.setPosition(newpos);

            Object[] children = b.getChildreen();

            for (Object aChildren : children) {
                Block bb = (Block) aChildren;
                IObject ob1 = localWorld.getObjectbyID(bb.getObjectID());
                float[] newpos1 = convertLocalPosWorldPos(bb.getLocalposition().get());
                ob1.setPosition(newpos1);
            }

        }





        if (moved) {
          //  HigherBlock();
           // PlaceRandonBlock();
          //  PlaceHeuristicBlock();
            Place0x0Block();
        }

    }

    public void setPlayerAction(GameConstants.PLAYERRACTION act)
    {

        switch (act) {
            case JUMP:
                p1.setJumping(true);
                break;
            case PUSH:
                p1.setJumping(false);
                break;
        }

    }


    public void PushBlock(Vector2 dir)
    {


        Vector2 doubledir = dir.mul(2);
        Block bb =BlockExistAt(p1.getLocalPos().getX() + dir.x, p1.getLocalPos().getZ() + dir.y);

        float xtry = p1.getLocalPos().getX()+doubledir.x;
        float ytry = p1.getLocalPos().getZ()+doubledir.y;

Block bprox = BlockExistAt(xtry,ytry);
        if (bprox==null && testposintheBoard(new Vector2(xtry,ytry)) )
        {
            if (bb.canMove())
            {
                Vector3 pv = bb.getLocalposition();

                //moving visualblock
                bb.MoveTo(new Vector3(pv.getX() + dir.getX(), pv.getY(), pv.getZ() + dir.getY()));

                IObject ob = localWorld.getObjectbyID(bb.getObjectID());
                float[] newpos = convertLocalPosWorldPos(bb.getLocalposition().get());
                ob.setPosition(newpos);


                Object[] children = bb.getChildreen();

                for (Object aChildren : children) {
                    Block bt = (Block) aChildren;
                    IObject ob1 = localWorld.getObjectbyID(bt.getObjectID());
                    float[] newpos1 = convertLocalPosWorldPos(bt.getLocalposition().get());
                    ob1.setPosition(newpos1);
                }


                p1.setDirection(dir, 0);
            }

        }


    }



    public void CreateBoard(int size)
    {
        this.size = size;
        this.setMaxheight(3);

        float x,z;

        Vector3 scale = new Vector3(getScale()*size,getScale(),getScale()*size);



        SimpleObject bbd = ObjectFactory.getInstance().getNormalBoxObject("board", getScale());
        bbd.setScale(scale.get());
        bbd.setPosition( new Vector3(0,0,0).get() );

        localWorld.AddObject(bbd);
/*
        for (int i=0;i<size;i++)
        {
            for (int j=0;j<size;j++) {

                SimpleObject b1 = ObjectFactory.getInstance().getNormalBoxObject("box" + i+"_"+j, getScale());



                if ( i == size/2 && j == size/2) {
                    float dark = -0.2f;
                    FaceShadedCubeMaterial m1 = (FaceShadedCubeMaterial)b1.getMaterial();



                    m1.setColor(new float[]{0.2695f+dark, 0.921875f+dark, 0.109375f+dark, 1.0f});
                }

                b1.setPosition( convertLocalPosWorldPos(new float[]{i,0,j}));

                localWorld.AddObject(b1);


            }


        }
    */

//Adding Plateau


        SimpleObject b1 = ObjectFactory.getInstance().getNormalBoxObject("box" + size + "_" + size, getScale());



        b1.setPosition(convertLocalPosWorldPos(new float[]{size - 1, 0, size}));

        localWorld.AddObject(b1);

            CreatePlayer();
        CreateGema();


    }

    private void CreateGema()
    {

        gema =  ObjectFactory.getInstance().getGemaObject("gema" + size + "_" + size, getScale());


        gema.setPosition(convertLocalPosWorldPos(new float[]{size / 2, getGemaheight(), size / 2}));

        localWorld.getLights().get(0).setPosition(gema.getPosition());

        localWorld.AddObject(gema);

    }

    private void CreatePlayer()
    {
        p1 = ObjectFactory.getInstance().getPlayer("P1", getScale());


     //   FaceShadedCubeMaterial mt1 = (FaceShadedCubeMaterial)p1.getMaterial();
      //  mt1.setColor(Color.enumtoColor(Color.COLORNAME.WHITE));

        p1.setPosition(convertLocalPosWorldPos(new float[]{size - 1, 1, size}));
        p1.setLocalPos(new Vector3(size - 1, 3, size));

        localWorld.AddObject(p1);

    }



    public void PlaceBlocksat(Class<?> t, int n, int x, int y)
    {

        Block b1=new NormalBlock();

        if (t == StoneBlock.class)
            b1 = new StoneBlock();




        Block b2 = BlockExistAt(x,y);


        int init=0;
        if ( b2 != null ) {
            b1 = b2;
            init = b2.getChildreenCount()+1;

            if (!b2.canStack())// in the case the block cannot be stacked
                return;

        }

        for (int i=init;i<(n+init);i++)
        {
            count++;
            String obname = Integer.toString(count);


            float[] localposition = new float[3];
            localposition[0] = x;
            localposition[1] = i+1;
            localposition[2] = y;

            IObject ob1 = null;
            if (t == NormalBlock.class)
                ob1 = ObjectFactory.getInstance().getNormalBoxObject(obname, getScale());
            else if (t == StoneBlock.class)
                ob1 = ObjectFactory.getInstance().getStoneBoxObject(obname, getScale());



            float[] position;

            position = convertLocalPosWorldPos(localposition);

            ob1.setPosition(position);
            int id = localWorld.AddObject(ob1);


            if(i==0) {//the first block


                b1 = getBlockbytype(t,id,new Vector3(localposition));

                bk.add(b1);
            }
            else // stacking blocks
            {
                Block bn = getBlockbytype(t,id,new Vector3(localposition));
                b1.StackBlock(bn);

            }


        }





    }

    private Block getBlockbytype(Class<?> t, int id, Vector3 lpos)
    {
        Block b1 = null;
        if (t == NormalBlock.class)

            b1 = new NormalBlock(id, lpos);
        else if (t == StoneBlock.class)
            b1 = new StoneBlock(id, lpos);


        return b1;


    }

public void HigherBlock()
{

    for (Block b:bk) {

        if (higherblock == null)
            higherblock = b;
        else if (b.getChildreenCount() > higherblock.getChildreenCount())
            higherblock = b;

    }



}



    public Block BlockExistAt(float x, float y)
    {
        Block i = null;
        for (Block b:bk ) {
            if (b.getLocalposition().getX() == x && b.getLocalposition().getZ() == y)
               i=b;
                //return i;
        }

        return i;
    }

public void Place0x0Block()
{



    float[] localposition = new float[3];
    localposition[0] =0 ;
    localposition[1] = 0+1;
    localposition[2] = 0;


    String obname = Integer.toString(count);
    IObject ob1 = ObjectFactory.getInstance().getNormalBoxObject(obname, getScale());

    float[] position;

    position = convertLocalPosWorldPos(localposition);
    ob1.setPosition(position);
    int id = localWorld.AddObject(ob1);

    Block b1 = new NormalBlock(id, new Vector3(localposition));
    bk.add(b1);


}

    public void PlaceHeuristicBlock()
    {
        Random rnd = new Random();
        int x,y;
        int kernelsize = 2;
        boolean canplace = false;


        do {
            x = rnd.nextInt(kernelsize);
            boolean signal = rnd.nextBoolean();
            if (!signal)
                x = -x;

            y = rnd.nextInt(kernelsize);
            signal = rnd.nextBoolean();
            if (!signal)
                y = -y;



            x = (int)higherblock.getLocalposition().getX()+ x;
            y = (int)higherblock.getLocalposition().getY()+ y;


            Block bb = BlockExistAt(x,y);

            if (bb ==null)
                canplace=true;
            else {
                if ((bb.getChildreenCount() + 1) <= getMaxheight())
                    canplace = true;
            }


        }while(!canplace);

        PlaceBlocksat(NormalBlock.class,1,x, y);



    }

    public void PlaceRandonBlock()
    {

        Random rnd = new Random();
        int x,y;
        boolean canplace = false;


        do {
            x = rnd.nextInt(size);
            y = rnd.nextInt(size);
            Block bb = BlockExistAt(x,y);

            if (bb ==null)
                canplace=true;
            else {
                if ((bb.getChildreenCount() + 1) <= getMaxheight())
                    canplace = true;
            }


        }while(!canplace);

        PlaceBlocksat(NormalBlock.class,1,x, y);

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getMaxheight() {
        return maxheight;
    }

    public void setMaxheight(int maxheight) {
        this.maxheight = maxheight;
    }

    public int getGemaheight() {
        return gemaheight;
    }

    public void setGemaheight(int gemaheight) {
        this.gemaheight = gemaheight;
    }
}
