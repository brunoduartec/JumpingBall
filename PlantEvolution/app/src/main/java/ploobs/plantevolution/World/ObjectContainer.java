package ploobs.plantevolution.World;

import org.w3c.dom.Node;

import java.util.ArrayList;

import ploobs.plantevolution.Material.TextureList;
import ploobs.plantevolution.Model.Model3D.FacesBufferList;
import ploobs.plantevolution.Model.Model3D.Vertices;

/**
 * Created by Bruno on 16/02/2016.
 */

public class ObjectContainer extends IObject implements IObjectContainer
{
    protected ArrayList<IObject> _children = new ArrayList<IObject>();

    public ObjectContainer()
    {
        localID = ++ID;

    }

    /**
     * This constructor is convenient for cloning purposes
     */
 //   public ObjectContainer(Vertices vertices, FacesBufferList faces, TextureList textures)
  //  {
   //     super(vertices, faces, textures);
  //  }

    public void addChild(IObject o)
    {
        _children.add(o);
        o.setParent(this);

    }

    public void addChildAt(IObject o, int index)
    {
        _children.add(index, o);

        o.setParent(this);
      //  o.scene(this.scene());
    }

    public boolean removeChild(IObject o)
    {
        boolean b = _children.remove(o);

        if (b) {
            o.setParent(null);
           // o.scene(null);
        }
        return b;
    }

    public IObject removeChildAt(int index)
    {
        IObject o = _children.remove(index);
        if (o != null) {
            o.setParent(null);
           // o.scene(null);
        }
        return o;
    }

    public IObject getChildAt(int index)
    {
        return _children.get(index);
    }

    /**
     * TODO: Use better lookup
     */
    public IObject getChildByName(String name)
    {
        for (int i = 0; i < _children.size(); i++)
        {
            if (_children.get(i).getName().equals(name)) return _children.get(i);
        }
        return null;
    }

    public int getChildIndexOf(IObject o)
    {
        return _children.indexOf(o);
    }


    public int numChildren()
    {
        return _children.size();
    }

    /*package-private*/
    public ArrayList<IObject> children()
    {
        return _children;
    }

    public IObjectContainer clone()
    {
        Vertices v = this.getModel().getVertices().clone();
        FacesBufferList f = this.getModel().getFaces().clone();


        ObjectContainer clone = new ObjectContainer();
        clone.setMaterial(this.getMaterial());
        clone.setModel(this.getModel());




        clone.getPosition().setX(getPosition().getX());
        clone.getPosition().setY(getPosition().getY());
        clone.getPosition().setZ(getPosition().getZ());


        clone.getRotation().setX( getRotation().getX());
        clone.getRotation().setY( getRotation().getY());
        clone.getRotation().setZ( getRotation().getZ());

        clone.getScale().setX( getScale().getX());
        clone.getScale().setY( getScale().getY());
        clone.getScale().setZ( getScale().getZ());



        for(int i = 0; i< this.numChildren();i++)
        {
            clone.addChild(this.getChildAt(i));
        }

        return clone;
    }

    @Override
    public void Draw(IWorld world) {

        for (IObject oo: _children)
        {
            oo.Draw(world);

        }

    }

    @Override
    public void Update() {

    }

    @Override
    public Object Parse(Node childnode) {
        return null;
    }
}