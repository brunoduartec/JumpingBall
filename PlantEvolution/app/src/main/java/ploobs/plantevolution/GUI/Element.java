package ploobs.plantevolution.GUI;

import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Material.IMaterial;
import ploobs.plantevolution.Math.Vector2;
import ploobs.plantevolution.Model.IModel;
import ploobs.plantevolution.World.SimpleObject;

/**
 * Created by Bruno on 10/12/2015.
 */
public abstract class Element extends SimpleObject {

    private boolean enabled;
    private boolean visible;

    private float width;
    private float height;

    public Element(IMaterial mat, IModel mod, String nm) {
        super(mat,mod,nm);
    }


    public boolean checkColision(Vector2 mousepos)
    {
        boolean ret = true;

        float mposX = mousepos.getX();
        float mposY = mousepos.getY();

        float posXconvert = getPosition()[0]*GraphicFactory.getInstance().getRatio();
        float posYconvert = -getPosition()[1]/2;



        float widthconverted = GraphicFactory.getInstance().getRatio()*width/GraphicFactory.getInstance().getWidth();
        float heightconverted = height/GraphicFactory.getInstance().getHeight();




        if ( (mposX>posXconvert && mposX < posXconvert + widthconverted) && (mposY>posYconvert && mposY < posYconvert + heightconverted  ) )
            ret = true;
        else
            ret = false;

        return ret;

    }

    public abstract void onClick();


    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}



