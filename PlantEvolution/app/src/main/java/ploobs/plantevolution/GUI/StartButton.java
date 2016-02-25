package ploobs.plantevolution.GUI;

import ploobs.plantevolution.Material.IMaterial;
import ploobs.plantevolution.Math.Vector2;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.Model.IModel;

/**
 * Created by Bruno on 08/12/2015.
 */
public class StartButton extends Element {


    private boolean clicked;

    //(IMaterial mat, IModel mod, String nm)
    public StartButton(IMaterial mat, IModel mod, String nm,Vector2 position, float width, float height)
    {
        super(mat,mod,nm);
        this.setPosition(new Vector3(position.getX(),position.getY(),0));
        this.setWidth(width);
        this.setHeight(height);
    }

    public void onClick()
    {

        setClicked(true);

    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
