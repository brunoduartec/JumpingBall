package ploobs.plantevolution.GUI;

import android.view.MotionEvent;

import java.util.LinkedList;
import java.util.List;

import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Input.InputSystem;
import ploobs.plantevolution.Light.ILight;
import ploobs.plantevolution.Math.Vector2;
import ploobs.plantevolution.World.IWorld;

/**
 * Created by Bruno on 08/12/2015.
 */
public class GuiManager {

List<Element> _element = new LinkedList<Element>();

IWorld _localworld;


    public void AddElement(Element e)
    {
        _element.add(e);
        this._localworld.AddObject(e);

    }

    public GuiManager(IWorld w)
    {
        _localworld = w;

    }



    public void HandleElements()
    {

        MotionEvent ev = InputSystem.getInstance().get_inputEvent();



        if (_element.size()>0 && ev !=null) {

            float mouseX = ev.getX()/GraphicFactory.getInstance().getWidth()  * GraphicFactory.getInstance().getRatio();
            float mouseY = ev.getY()/GraphicFactory.getInstance().getHeight();

            for (Element e : _element) {
                if (e.checkColision(new Vector2(mouseX,mouseY))) {
                    e.getOnClick().Execute();
                }


            }
        }

    }






}
