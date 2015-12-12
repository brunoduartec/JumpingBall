package ploobs.plantevolution.Gameplay.States;

import android.util.DisplayMetrics;

import ploobs.plantevolution.Camera.Camera2D;
import ploobs.plantevolution.GUI.Element;
import ploobs.plantevolution.GUI.GuiManager;
import ploobs.plantevolution.GUI.StartButton;
import ploobs.plantevolution.GameState.GameStateUpdatableDrawable;
import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Math.Vector2;
import ploobs.plantevolution.ObjectFactory;
import ploobs.plantevolution.R;
import ploobs.plantevolution.Scene.SimpleScene;
import ploobs.plantevolution.World.IObject;
import ploobs.plantevolution.World.SimpleWorld;

/**
 * Created by Bruno on 08/12/2015.
 */
public class MenuScreenState extends GameStateUpdatableDrawable {

    GuiManager gm;
    StartButton button;
    boolean end=false;
    @Override
    public void Entered() {

        DisplayMetrics metrics = GraphicFactory.getInstance().getGraphicContext().getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;


        GraphicFactory.getInstance().setWidth(this.width);
        GraphicFactory.getInstance().setHeight(this.height);

        world = new SimpleWorld();
        gm = new GuiManager(world);

        Camera2D cam2D = new Camera2D("CAM2", 720, 1118, 0, 50, (float) (3 / 4));
        world.getCameraManager().addCamera(cam2D);
        world.getCameraManager().setActualCamera("CAM2");

        IObject screen =ObjectFactory.getInstance().getRectangleObject("screen", R.drawable.menuscreen, GraphicFactory.getInstance().getWidth(), GraphicFactory.getInstance().getHeight());
        screen.setPosition(new float[]{0, 0, -0.0001f});
        world.AddObject(screen);

        //IObject button = ObjectFactory.getInstance().getRectangleObject("button", R.drawable.startbutton, 371, 108);

        button= (StartButton)ObjectFactory.getInstance().getStartButtonObject("button", R.drawable.startbutton, 371, 108, new Vector2(0.2f, -1.5f));

        gm.AddElement(button);

        button.setPosition(new float[]{0.2f,-1.5f,0f});
//button.setPosition(new float[]{0,0,0});

        //world.AddObject(button);





        scene = new SimpleScene(world,true);
    }

    @Override
    public void Leaving() {

    }

    @Override
    public void Obscuring() {

    }

    @Override
    public void Revealed() {

    }

    @Override
    public void Update() {
        gm.HandleElements();
    }

    @Override
    public void Draw() {

            scene.Draw();
    }

    @Override
    public boolean isEnded()
    {

        return end;
    }

    @Override
    public void HandleEvent() {
        if (button.isClicked())
        {
            end = true;
        }

    }
}

//public class MainScreenState extends GameStateUpdatableDrawable {