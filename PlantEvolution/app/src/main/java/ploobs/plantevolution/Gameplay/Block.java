package ploobs.plantevolution.Gameplay;

import ploobs.plantevolution.Vector3;

import java.util.Comparator;
import java.util.Stack;

/**
 * Created by Bruno on 08/10/2015.
 */
public  abstract class Block {

    private int objectID;
    private Vector3 localposition;
    Stack<Block> child = new Stack<>();


    public abstract boolean canMove();
    public abstract boolean canStack();
    public abstract void onCollide(Block b);


public Block(){}

    public Block(int ID, Vector3 localposition)
    {
        this.objectID = ID;
        this.setLocalposition(localposition);

    }

    public int getChildreenCount()
    {

        return child.size();

    }

    public boolean equals(Block b)
    {

        return this.getObjectID()==b.getObjectID();


    }


    public Object[] getChildreen()
    {
        return child.toArray();


    }


    void MoveTo(Vector3 localpos)
    {

        setLocalposition(localpos);


        for (Block b:child) {


            b.getLocalposition().setX(localpos.getX());
            b.getLocalposition().setZ(localpos.getZ());


        }



    }

    void StackBlock(Block b)
    {

        int topstack = child.size()+1;

        b.getLocalposition().setX(getLocalposition().getX());
        b.getLocalposition().setY(getLocalposition().getY() + topstack);
        b.getLocalposition().setZ(getLocalposition().getZ());

        child.push(b);

    }
    Block[] UnStrackBlocks(int n)
    {

        // to don´t unstack more than the stack, to avoid overflow
        if (n>child.size())
            n=child.size();


        Stack<Block> btemp = new Stack<>();

        for (int i=0;i<n;i++)
        {
            btemp.push(child.pop());

        }

     /*
        if (n==1)n=2;
        Block[] bret = new Block[n];

        if (n>child.size())
            n=child.size();

        for (int i=0;i<n;i++)
        {
            Block tt = child.pop();
            bret[n-i] = tt;
        }
*/
        return btemp.toArray(new Block[btemp.size()]);

    }


    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public Vector3 getLocalposition() {
        return localposition;
    }

    public void setLocalposition(Vector3 localposition) {
        this.localposition = localposition;
    }
}


class BlockXASCComparator implements Comparator<Block>
{
    @Override
    public int compare(Block lhs, Block rhs) {

        float X1 = lhs.getLocalposition().getX();
        float X2 = rhs.getLocalposition().getX();
        int ret = (int)(X1-X2);

        return ret;
    }
}

class BlockXDESCComparator implements Comparator<Block>
{
    @Override
    public int compare(Block lhs, Block rhs) {

        float X1 = lhs.getLocalposition().getX();
        float X2 = rhs.getLocalposition().getX();
        int ret = (int)(X2-X1);

        return ret;
    }
}



class BlockYASCComparator implements Comparator<Block>
{
    @Override
    public int compare(Block lhs, Block rhs) {

        float Y1 = lhs.getLocalposition().getY();
        float Y2 = rhs.getLocalposition().getY();
        int ret = (int)(Y1-Y2);

        return ret;
    }
}
class BlockYDESCComparator implements Comparator<Block>
{
    @Override
    public int compare(Block lhs, Block rhs) {

        float Y1 = lhs.getLocalposition().getY();
        float Y2 = rhs.getLocalposition().getY();
        int ret = (int)(Y2-Y1);

        return ret;
    }
}

class BlockZASCComparator implements Comparator<Block>
{


    @Override
    public int compare(Block lhs, Block rhs) {

        float Z1 = lhs.getLocalposition().getZ();
        float Z2 = rhs.getLocalposition().getZ();
        int ret = (int)(Z1-Z2);

        return ret;
    }
}
class BlockZDESCComparator implements Comparator<Block>
{


    @Override
    public int compare(Block lhs, Block rhs) {

        float Z1 = lhs.getLocalposition().getZ();
        float Z2 = rhs.getLocalposition().getZ();
        int ret = (int)(Z2-Z1);

        return ret;
    }
}