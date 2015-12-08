package ploobs.plantevolution.Gameplay.States;

import android.util.DisplayMetrics;

import ploobs.plantevolution.Camera.Camera2D;
import ploobs.plantevolution.GameState.TimeBasedGameStateUpdatableDrawable;
import ploobs.plantevolution.Gameplay.GameConstants;
import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Math.Vector2;
import ploobs.plantevolution.ObjectFactory;
import ploobs.plantevolution.R;
import ploobs.plantevolution.Scene.SimpleScene;
import ploobs.plantevolution.World.IWorld;
import ploobs.plantevolution.World.SimpleWorld;

/**
 * Created by Bruno on 02/12/2015.
 */
public class SplitScreenState extends TimeBasedGameStateUpdatableDrawable {

    private int width;
    private int height;
    private IWorld world2d;
    private SimpleScene scene;

    public SplitScreenState(int time)
    {
        super(time);


    }


    @Override
    public void Entered() {


        DisplayMetrics metrics = GraphicFactory.getInstance().getGraphicContext().getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;


        GraphicFactory.getInstance().setWidth(this.width);
        GraphicFactory.getInstance().setHeight(this.height);

        world2d = new SimpleWorld();
        Camera2D cam2D = new Camera2D("CAM2", 720, 1118, 0, 50, (float) (3 / 4));
        world2d.getCameraManager().addCamera(cam2D);
        world2d.getCameraManager().setActualCamera("CAM2");
        world2d.AddObject(ObjectFactory.getInstance().getRectangleObject("button", R.drawable.splitscreen, GraphicFactory.getInstance().getWidth(),GraphicFactory.getInstance().getHeight(), new Vector2(0, 0)));


        scene = new SimpleScene(world2d);
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
    public void Draw() {
        scene.Draw();
    }



    @Override
    public void HandleEvent() {

    }
}
