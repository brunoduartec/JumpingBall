package ploobs.plantevolution.Text;

import ploobs.plantevolution.Material.Color4;


/**
 * Created by Bruno on 12/04/2016.
 */
public class TextObject {

    public String text;
    public float x;
    public float y;
    public float[] color;



    public TextObject()
    {
        text = "default";
        x = 0f;
        y = 0f;
        color = new float[] {1f, 1f, 1f, 1.0f};
    }

    public TextObject(String txt, float xcoord, float ycoord)
    {
        text = txt;
        x = xcoord;
        y = ycoord;
        color = new float[] {1f, 1f, 1f, 1.0f};
    }
}
