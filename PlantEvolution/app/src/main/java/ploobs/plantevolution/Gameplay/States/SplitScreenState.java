package ploobs.plantevolution.Gameplay.States;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;

import ploobs.plantevolution.Audio.AudioPlayer;
import ploobs.plantevolution.Camera.Camera2D;
import ploobs.plantevolution.GameState.TimeBasedGameStateUpdatableDrawable;
import ploobs.plantevolution.Gameplay.GameConstants;
import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Material.TextureManager;
import ploobs.plantevolution.Math.Vector2;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.ObjectFactory;
import ploobs.plantevolution.R;
import ploobs.plantevolution.Scene.SimpleScene;
import ploobs.plantevolution.Utils;
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
        world.AddObject(ObjectFactory.getInstance().getRectangleObject("splitscreen",R.drawable.splitscreen, GraphicFactory.getInstance().getWidth(), GraphicFactory.getInstance().getHeight(), new Vector3(0,GraphicFactory.getInstance().getHeight() -0.01f,0)));


        scene = new SimpleScene(world,true);


   //     MediaPlayer mediaPlayer = MediaPlayer.create(GraphicFactory.getInstance().getGraphicContext(), R.raw.hopeful_theme_music_1);
    //    mediaPlayer.start(); // no need to call prepare(); create() does that for you

        AudioPlayer.getInstance().addAudio("theme",R.raw.hopeful_theme_music_1);
        AudioPlayer.getInstance().addAudio("button_click", R.raw.button_click);
        AudioPlayer.getInstance().addAudio("switch_sound", R.raw.switch_sound);
        AudioPlayer.getInstance().addAudio("pickup_gem", R.raw.pickup_gem_1);

        int volume = 3;

        AudioPlayer.getInstance().changeVolume("pickup_gem", 50 * volume);





        AudioPlayer.getInstance().addAudio("jump_sound", R.raw.jump);
        AudioPlayer.getInstance().changeVolume("jump_sound", 30 * volume);


        AudioPlayer.getInstance().changeVolume("switch_sound", 20 * volume);


        AudioPlayer.getInstance().changeVolume("theme", 60 * volume);
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
