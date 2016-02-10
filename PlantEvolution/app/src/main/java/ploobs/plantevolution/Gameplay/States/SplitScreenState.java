package ploobs.plantevolution.Gameplay.States;

import android.media.MediaPlayer;
import android.util.DisplayMetrics;

import ploobs.plantevolution.Audio.AudioPlayer;
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

        world = new SimpleWorld();
        Camera2D cam2D = new Camera2D("CAM2", 720, 1118, 0, 50, (float) (3 / 4));
        world.getCameraManager().addCamera(cam2D);
        world.getCameraManager().setActualCamera("CAM2");
        world.AddObject(ObjectFactory.getInstance().getRectangleObject("button", R.drawable.splitscreen, GraphicFactory.getInstance().getWidth(), GraphicFactory.getInstance().getHeight()));


        scene = new SimpleScene(world);


   //     MediaPlayer mediaPlayer = MediaPlayer.create(GraphicFactory.getInstance().getGraphicContext(), R.raw.hopeful_theme_music_1);
    //    mediaPlayer.start(); // no need to call prepare(); create() does that for you

        AudioPlayer.getInstance().addAudio("theme", R.raw.hopeful_theme_music_1);
        AudioPlayer.getInstance().addAudio("button_click", R.raw.button_click);
        AudioPlayer.getInstance().addAudio("switch_sound", R.raw.switch_sound);
        AudioPlayer.getInstance().addAudio("pickup_gem", R.raw.pickup_gem_1);





        AudioPlayer.getInstance().addAudio("jump_sound", R.raw.jump);
        AudioPlayer.getInstance().changeVolume("jump_sound", 8);


        AudioPlayer.getInstance().changeVolume("switch_sound", 20);


        AudioPlayer.getInstance().changeVolume("theme", 10);
       AudioPlayer.getInstance().playAudio("theme");




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
