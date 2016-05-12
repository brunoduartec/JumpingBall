package ploobs.plantevolution.Gameplay.States;

import android.opengl.GLES20;
import android.util.DisplayMetrics;

import ploobs.plantevolution.Audio.AudioPlayer;
import ploobs.plantevolution.Camera.Camera2D;
import ploobs.plantevolution.GameState.TimeBasedGameStateUpdatableDrawable;
import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.ObjectFactory;
import ploobs.plantevolution.R;
import ploobs.plantevolution.Scene.SimpleScene;
import ploobs.plantevolution.Text.TextManager;
import ploobs.plantevolution.Text.TextObject;
import ploobs.plantevolution.Text.riGraphicTools;
import ploobs.plantevolution.World.IObject;
import ploobs.plantevolution.World.IWorld;
import ploobs.plantevolution.World.ObjectContainer;
import ploobs.plantevolution.World.SimpleWorld;

/**
 * Created by Bruno on 02/12/2015.
 */
public class InitStageScreenState extends TimeBasedGameStateUpdatableDrawable {

    private TextManager tm;



    public void SetupText()
    {

        float ssu = 2.0f;
        // Create our text manager
        tm = new TextManager();

        tm.setTexture("basicfont");

        // Pass the uniform scale
        tm.setUniformscale(ssu);


        // Create our new textobject
        TextObject txt1 = new TextObject("hurry up", 10, 200);
        TextObject txt2 = new TextObject("makes you strong", 10, 150);
        TextObject txt3 = new TextObject("So, push it now", 10, 100);
        TextObject txt4 = new TextObject("and reach the stone", 10, 50);


        // Add it to our manager
        tm.addText(txt1);
        tm.addText(txt2);
       tm.addText(txt3);
        tm.addText(txt4);


        scene.setTm(tm);

    }

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
      //  world.AddObject(ObjectFactory.getInstance().getRectangleObject("button", R.drawable.phrase_stage1, GraphicFactory.getInstance().getWidth(), GraphicFactory.getInstance().getHeight(), Vector3.Zero));

        IWorld world3d = new SimpleWorld();
        scene = new SimpleScene(world,true);

     //   ObjectContainer tt = ObjectFactory.getInstance().getOBJModel("box_obj","ploobs.plantevolution:raw/box");
        // tt.setScale(new Vector3(30,30,30));
       // tt.setPosition(new Vector3(0, 0, 0));
        //world3d.AddObject(tt);


        SetupText();

        //colocar posição relativa
       // IObject toadd = ObjectFactory.getInstance().getPopUpObject("score",R.drawable.red_panel,400,500,new Vector3(0.2f,-0.6f,0.9f));
    //    world.AddObject(toadd);





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

        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        scene.Draw();
    }



    @Override
    public void HandleEvent() {

    }
}
