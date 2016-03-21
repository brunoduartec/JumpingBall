package ploobs.plantevolution.Gameplay.States;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.DisplayMetrics;

import ploobs.plantevolution.Audio.AudioPlayer;
import ploobs.plantevolution.Camera.Camera2D;
import ploobs.plantevolution.GUI.Element;
import ploobs.plantevolution.GUI.GuiManager;
import ploobs.plantevolution.GUI.IEventHandler;
import ploobs.plantevolution.GameState.GameStateUpdatableDrawable;
import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Math.Vector3;
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
    private Element button;
    boolean end=false;
    private Element soundbutton;


    @Override
    public void Entered() {

        Context localContext = GraphicFactory.getInstance().getGraphicContext();
        // Create the GLText



        //AudioPlayer.getInstance().changeVolume("theme", 20);

        DisplayMetrics metrics = localContext.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;


        GraphicFactory.getInstance().setWidth(this.width);
        GraphicFactory.getInstance().setHeight(this.height);

        world = new SimpleWorld();
        gm = new GuiManager(world);

        Camera2D cam2D = new Camera2D("CAM2", 720, 1118, 0, 50, (float) (3 / 4));
        world.getCameraManager().addCamera(cam2D);
        world.getCameraManager().setActualCamera("CAM2");

        IObject screen =ObjectFactory.getInstance().getRectangleObject("screen", R.drawable.menuscreen, GraphicFactory.getInstance().getWidth(), GraphicFactory.getInstance().getHeight(),new Vector3(0, 0, -0.0001f));
        //screen.setPosition(new float[]{0, 0, -0.0001f});
        world.AddObject(screen);



        button= ObjectFactory.getInstance().getButtonObject("button", R.drawable.startbutton, 371, 108, new Vector3(0.2f, -1.5f,0.0f));
        IEventHandler h1 = new IEventHandler() {
            @Override
            public void Execute() {
                end = true;
                AudioPlayer.getInstance().playAudio("button_click");
            }
        };
        button.setOnClick(h1);


        gm.AddElement(button);


        final int soundbuttonstate;
        if(AudioPlayer.getInstance().getSoundState())
        {
            soundbuttonstate = R.drawable.sound;

        }
        else
        {
            soundbuttonstate = R.drawable.nosound;
        }

//Sound Button
        soundbutton= ObjectFactory.getInstance().getButtonObject("soundbutton", soundbuttonstate, 64, 64, new Vector3(0.02f, -0.02f,0.0f));
        IEventHandler h2 = new IEventHandler() {
            @Override
            public void Execute() {

                AudioPlayer.getInstance().toogleSound();
               // SimpleSquareMaterial stemp = (SimpleSquareMaterial)soundbutton.getMaterial();
                //stemp.setTexture(soundbuttonstate);



            }
        };
        soundbutton.setOnClick(h2);


      //  gm.AddElement(soundbutton);



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

        gm.HandleElements();
    }
}

//public class MainScreenState extends GameStateUpdatableDrawable {