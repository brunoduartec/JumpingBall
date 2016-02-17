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
      //  super(0, 0, false, false, false);
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
    ArrayList<IObject> children()
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




        clone.getPosition()[0] =getPosition()[0];
        clone.getPosition()[1] =getPosition()[1];
        clone.getPosition()[2] =getPosition()[2];


        clone.getRotation()[0] = getRotation()[0];
        clone.getRotation()[1] = getRotation()[1];
        clone.getRotation()[2] = getRotation()[2];

        clone.getScale()[0] = getScale()[0];
        clone.getScale()[1] = getScale()[1];
        clone.getScale()[2] = getScale()[2];



        for(int i = 0; i< this.numChildren();i++)
        {
            clone.addChild(this.getChildAt(i));
        }

        return clone;
    }

    @Override
    public void Draw(IWorld world) {

    }

    @Override
    public void Update() {

    }

    @Override
    public Object Parse(Node childnode) {
        return null;
    }
}