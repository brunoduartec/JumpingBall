package ploobs.plantevolution.Gameplay.States;

import android.util.DisplayMetrics;

import ploobs.plantevolution.Audio.AudioPlayer;
import ploobs.plantevolution.Camera.Camera2D;
import ploobs.plantevolution.GameState.TimeBasedGameStateUpdatableDrawable;
import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.ObjectFactory;
import ploobs.plantevolution.R;
import ploobs.plantevolution.Scene.SimpleScene;
import ploobs.plantevolution.World.SimpleWorld;

/**
 * Created by Bruno on 02/12/2015.
 */
public class InitStageScreenState extends TimeBasedGameStateUpdatableDrawable {



    public InitStageScreenState(int time)
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

        world = new SimpleWorld();
        Camera2D cam2D = new Camera2D("CAM2", 720, 1118, 0, 50, (float) (3 / 4));
        world.getCameraManager().addCamera(cam2D);
        world.getCameraManager().setActualCamera("CAM2");
        world.AddObject(ObjectFactory.getInstance().getRectangleObject("button", R.drawable.phrase_stage1, GraphicFactory.getInstance().getWidth(), GraphicFactory.getInstance().getHeight(), Vector3.Zero));


        scene = new SimpleScene(world);







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
