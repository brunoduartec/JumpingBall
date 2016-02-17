package ploobs.plantevolution.World;

/**
 * Created by Bruno on 16/02/2016.
 */

public interface IObjectContainer
{
    public void addChild(IObject child);
    public void addChildAt(IObject child, int index);
    public boolean removeChild(IObject child);
    public IObject removeChildAt(int index);
    public IObject getChildAt(int index);
    public IObject getChildByName(String string);
    public int getChildIndexOf(IObject o);
    public int numChildren();
}
