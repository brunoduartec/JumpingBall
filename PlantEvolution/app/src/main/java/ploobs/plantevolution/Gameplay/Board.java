package ploobs.plantevolution.Gameplay;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.SparseArray;

import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Material.Color4;
import ploobs.plantevolution.Light.AmbientLight;
import ploobs.plantevolution.Material.DiffuseMaterial;
import ploobs.plantevolution.World.IObject;
import ploobs.plantevolution.World.IWorld;
import ploobs.plantevolution.Light.ILight;
import ploobs.plantevolution.ObjectFactory;
import ploobs.plantevolution.World.ObjectContainer;
import ploobs.plantevolution.World.SimpleObject;
import ploobs.plantevolution.Math.Vector2;
import ploobs.plantevolution.Math.Vector3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Bruno on 25/09/2015.
 */
public class Board {






    private float scale;
    private String name;

    int count = 0; // used to name objects


    Vector2 _direction = new Vector2(0,0);
    Vector2 _playerpos;

    Vector3 _playerInitpos;


    private ArrayList<Block> bk;// = new LinkedList<Block>();

   // private LinkedList<Block> initialstage;// = new LinkedList<Block>();
    private Map<Integer,Vector3> initialstage = new HashMap<Integer, Vector3>();


    private Stack<BlockMovement> undomoves = new Stack<>(); // used to stack moves done to perform undo


    Block higherblock;

    Player p1;
    IObject gema;


    private int size;
    private int maxheight;


    private IWorld localWorld;
    private int gemaheight;


    public void Initialize()
    {



        bk = new ArrayList<>();
        this.localWorld.Initialize();


        AmbientLight light1 = new AmbientLight(Color4.enumtoColor(Color4.COLORNAME.WHITE),0.2f,0.7f,0.2f, new Vector3(0,1,0));

        localWorld.AddLight(light1);


    }
    public void Reset()
    {
        setBoardbyList();
        SetPlayerPos(new Vector3(_playerInitpos));

    }
    private Block findBlockbyID(Integer id)
    {
    Block ret = null;

        for (Block bb: bk ) {
            if(bb.getObjectID() == id) {
                ret = bb;
                break;
            }
        }

    return ret;

    }


    private void setBoardbyList( )
    {

        Iterator it = initialstage.entrySet().iterator();

        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            Integer id = (Integer) pair.getKey();
            Vector3 pos = (Vector3)pair.getValue();

            Block btemp = findBlockbyID(id);
            if (btemp != null) {
                btemp.MoveTo(pos);
                SyncPhysicObject(btemp);
            }
        }

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



    private Vector3 convertLocalPosWorldPos(Vector3 localpos)
    {

        float x = localpos.getX()* getScale() - (this.size/2)* getScale();
        float y = localpos.getY()* getScale();
        float z = localpos.getZ()* getScale() - (this.size/2)* getScale();

        return new Vector3(x,y,z);



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

        return !(go.getX() < 0 || go.getX() > size - 1 || go.getZ() < 0 || go.getZ() > size - 1);



    }

public void SetPlayerPos(Vector3 pos)
{

    Block btemp =BlockExistAt(pos.getX(), pos.getZ());



    if (btemp == null)
    {
        p1.setPosition(convertLocalPosWorldPos(pos));
        p1.setLocalPos(pos);
    }






}



    //Moving the player, using the action selected at the moment
    public void MovePlayer(Vector2 dir)
    {

        Vector3 playerpos = p1.getLocalPos();


        int h1,h2;

        //Vector3 actualpos = p1.getPosition();

        h1 = (int)(p1.getLocalPos().getY())-1;

        float x,y;

        x = playerpos.getX()+ dir.getX();
        y = playerpos.getZ()+ dir.getY();


            if (!testposintheBoard(new Vector2(x, y)))
                return;


        this._direction = dir;

        //calculating the height of the next block
        Block btemp =BlockExistAt(x,y);
        if (btemp != null)
            h2 = btemp.getChildreenCount()+1;
        else
            h2 = 0;

        //calculating the delta between the actual pos and the next pos
            int delta_h = h2-h1;






        if (p1.isJumping() )
        {

            boolean validpos = true;
            if (btemp !=null)
            {
                validpos = btemp.canStack();
            }
            if (validpos && delta_h <= 1)
                p1.setDirection(dir, (h2) * getScale());//walking to a valid position

        }
            else {

            if (delta_h <= 0) // can just move if the next block were less or equal
                p1.setDirection(dir, (h2) * getScale());
            else if (!p1.isJumping() && p1.getLocalPos().getY() == 1)
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

        for (Block b:bk )
        {
            SyncPhysicObject(b);
        }


        /*
        for (Block b:bk )
        {//updating the IObject instance

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
        */





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

//used to foreseen two blocks ahead
        Vector2 doubledir = dir.mul(2);

// see if there is a block in the moving direction
        Block bb =BlockExistAt(p1.getLocalPos().getX() + dir.x, p1.getLocalPos().getZ() + dir.y);


        //calc the position behind the block to be moved
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
                //saving the
                BlockMovement undo = new BlockMovement(BlockMovement.DIRECTION.BACK,bb.getObjectID());
                SyncPhysicObject(bb);

              /*
                IObject ob = localWorld.getObjectbyID(bb.getObjectID());
                float[] newpos = convertLocalPosWorldPos(bb.getLocalposition().get());
                ob.setPosition(newpos);

                Object[] children = bb.getChildreen();

                //physic block movement
                for (Object aChildren : children)
                {
                    Block bt = (Block) aChildren;
                    IObject ob1 = localWorld.getObjectbyID(bt.getObjectID());
                    float[] newpos1 = convertLocalPosWorldPos(bt.getLocalposition().get());
                    ob1.setPosition(newpos1);
                }

                */
                p1.setDirection(dir, 0);
            }

        }


    }


    //Sincronyze virtual and physic world
    private void SyncPhysicObject(Block bb)
    {

        IObject ob = localWorld.getObjectbyID(bb.getObjectID());
        Vector3 newpos = convertLocalPosWorldPos(bb.getLocalposition());
        ob.setPosition(newpos);

        Object[] children = bb.getChildreen();

//physic block movement
        for (Object aChildren : children) {
            Block bt = (Block) aChildren;
            IObject ob1 = localWorld.getObjectbyID(bt.getObjectID());
            Vector3 newpos1 = convertLocalPosWorldPos(bt.getLocalposition());
            ob1.setPosition(newpos1);
        }



    }


    public void CreateBoard(int size)
    {



        this.size = size;
        this.setMaxheight(3);

        float x,z;



        for (int i=0;i<size;i++)
        {
            for (int j=0;j<size;j++) {

                SimpleObject b1 = ObjectFactory.getInstance().getNormalBoxObject("box" + i+"_"+j, getScale());



                if ( i == size/2 && j == size/2) {
                    float dark = -0.2f;
                   DiffuseMaterial m1 = (DiffuseMaterial)b1.getMaterial();
                    m1.setShadowed(1);

                }

                b1.setPosition( convertLocalPosWorldPos(new Vector3(i,0,j)));

                localWorld.AddObject(b1);


            }


        }


    }

    private void CreateGemaat(Vector3 position)
    {

        gema =  ObjectFactory.getInstance().getGemaObject("gema" + size + "_" + size, getScale(),convertLocalPosWorldPos(position));


      //  gema.setPosition();

        ILight ll = localWorld.getLights().get(0);
        ll.setPosition(gema.getPosition());
      //  ll.setColor(Utils.RandColor());

        localWorld.AddObject(gema);




    }

    private void CreateGema()
    {

        gema =  ObjectFactory.getInstance().getGemaObject("gema" + size + "_" + size, getScale(),convertLocalPosWorldPos(new Vector3(size / 2, getGemaheight(), size / 2)));


        //gema.setPosition();

        ILight ll = localWorld.getLights().get(0);
        ll.setPosition(gema.getPosition());
      //  ll.setColor(Utils.RandColor());

        localWorld.AddObject(gema);


    }

    public Player getPlayer()
    {

        return p1;
    }
    private void CreatePlayer()
    {
        p1 = ObjectFactory.getInstance().getPlayer("P1", getScale(),3);


        p1.setPosition(convertLocalPosWorldPos(new Vector3(size - 1, 1, size)));
        p1.setLocalPos(new Vector3(size - 1, 1, size));

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


            Vector3 localposition = new Vector3(x,i+1,y);


            IObject ob1 = null;
            if (t == NormalBlock.class)
                ob1 = ObjectFactory.getInstance().getNormalBoxObject(obname, getScale());
            else if (t == StoneBlock.class)
                ob1 = ObjectFactory.getInstance().getStoneBoxObject(obname, getScale());





            Vector3 position;

            position = convertLocalPosWorldPos(localposition);

            ob1.setPosition(position);
            int id = localWorld.AddObject(ob1);


            if(i==0) {//the first block


                b1 = getBlockbytype(t,id,new Vector3(localposition));

                bk.add(b1);

                initialstage.put(b1.getObjectID(),b1.getLocalposition());


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

        //if(bk == null)



        for (Block b:bk ) {
            if (b.getLocalposition().getX() == x && b.getLocalposition().getZ() == y)
               i=b;
                //return i;
        }

        return i;
    }

public void Place0x0Block()
{



    Vector3 localposition = new Vector3(0,0+1,0);


    String obname = Integer.toString(count);
    IObject ob1 = ObjectFactory.getInstance().getNormalBoxObject(obname, getScale());

    Vector3 position;

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

        PlaceBlocksat(NormalBlock.class, 1, x, y);



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


    public void Load(String idfile) throws IOException {
        InputStream is;
        Context localContext = GraphicFactory.getInstance().getGraphicContext();
        AssetManager am = localContext.getAssets();



            is = am.open(idfile);




        InputStreamReader inputreader = new InputStreamReader(is);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line,line1 = "";
        try
        {

            int cX=0,cY=0;
            String[] pos;
            Vector3 position;

            while ((line = buffreader.readLine()) != null)
            {
                line1 += line;


                String[] obj = line.split("=");


                switch (obj[0])
                {
                    case "S":

                        GameConstants.size = Integer.parseInt(obj[1]);
                        CreateBoard(GameConstants.size);

                        break;
                    case "P":

                        CreatePlayer();
                       pos = obj[1].split(",");

                        position = new Vector3(Float.parseFloat(pos[0]), Float.parseFloat(pos[1]), Float.parseFloat(pos[2]));


                        p1.setPosition(convertLocalPosWorldPos(position));

                        _playerInitpos = position;
                        p1.setLocalPos(new Vector3(position));




                        break;

                    case "G":

                        pos = obj[1].split(",");

                        position = new Vector3(Float.parseFloat(pos[0]), Float.parseFloat(pos[1]), Float.parseFloat(pos[2]));
                        CreateGemaat(position);


                        break;
                    case "M":

                        do {


                            String[] bb = obj[1].split("\t");
                            cX = 0;
                            for (String b : bb) {

                                if (!b.contains("0")) {
                                    String[] objecttoplace = b.split("_");
                                    switch (objecttoplace[0]) {

                                        case "S":
                                            PlaceBlocksat(StoneBlock.class, Integer.parseInt(objecttoplace[1]), cX, cY);
                                            break;

                                        case "B":
                                            PlaceBlocksat(NormalBlock.class, Integer.parseInt(objecttoplace[1]), cX, cY);
                                            break;


                                    }

                                }
                                cX++;
                            }
                            cY++;
                            obj[1] = buffreader.readLine();
                        }while(cY < GameConstants.size);

                        break;


                }

            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

}
