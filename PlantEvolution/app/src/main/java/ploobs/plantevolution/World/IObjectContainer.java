package ploobs.plantevolution.World;

/**
 * Created by Bruno on 16/02/2016.
 */

public interface IObjectContainer
{
    void addChild(IObject child);
    void addChildAt(IObject child, int index);
    boolean removeChild(IObject child);
    IObject removeChildAt(int index);
    IObject getChildAt(int index);
    IObject getChildByName(String string);
    int getChildIndexOf(IObject o);
    int numChildren();
}
