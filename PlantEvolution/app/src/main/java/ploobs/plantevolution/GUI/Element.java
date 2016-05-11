package ploobs.plantevolution.GUI;

import java.util.Vector;

import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Material.IMaterial;
import ploobs.plantevolution.Math.Vector2;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.Model.IModel;
import ploobs.plantevolution.World.SimpleObject;

/**
 * Created by Bruno on 10/12/2015.
 */
public class Element extends SimpleObject {

    private boolean enabled;
    private boolean visible;

    private float width;
    private float height;

    private IEventHandler onClick;

    public Element(IMaterial mat, IModel mod, String nm) {
        super(mat,mod,nm);
    }

    public Element(IMaterial mat, IModel mod, String nm, Vector3 position, float width, float height) {
        super(mat,mod,nm);

        this.setPosition(position);
        this.setWidth(width);
        this.setHeight(height);
    }



int count =0;
    public boolean checkColision(Vector2 mousepos)
    {

        count++;
        boolean ret = true;

        float mposX = mousepos.getX();
        float mposY = GraphicFactory.getInstance().getHeight() - mousepos.getY();

        float posXconvert = getPosition().getX();//*GraphicFactory.getInstance().getRatio();
        float posYconvert = getPosition().getY();///2;



        float widthconverted =width; //GraphicFactory.getInstance().getRatio()*width/GraphicFactory.getInstance().getWidth();
        float heightconverted =height;//height/GraphicFactory.getInstance().getHeight();

        //ret = (mposX > posXconvert && mposX < (posXconvert + widthconverted)) && (mposY > posYconvert && mposY < (posYconvert + heightconverted));
count++;
        ret =(mposX > posXconvert && mposX < (posXconvert + widthconverted));
        ret = ret && (mposY < posYconvert && mposY > (posYconvert - heightconverted));


        return ret;

    }

  //  public abstract void onClick();


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

    public void setOnClick(IEventHandler onClick) {
        this.onClick = onClick;
    }

    public IEventHandler getOnClick() {
        return onClick;
    }
}



