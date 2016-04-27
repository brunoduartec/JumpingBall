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
import ploobs.plantevolution.World.SimpleWorld;

/**
 * Created by Bruno on 02/12/2015.
 */
public class InitStageScreenState extends TimeBasedGameStateUpdatableDrawable {

    private TextManager tm;


    private void InitTextShader()
    {
        // Text shader
        int vshadert = riGraphicTools.loadShader(GLES20.GL_VERTEX_SHADER, riGraphicTools.vs_Text);
        int fshadert = riGraphicTools.loadShader(GLES20.GL_FRAGMENT_SHADER, riGraphicTools.fs_Text);

        riGraphicTools.sp_Text = GLES20.glCreateProgram();
        GLES20.glAttachShader(riGraphicTools.sp_Text, vshadert);
        GLES20.glAttachShader(riGraphicTools.sp_Text, fshadert); 		// add the fragment shader to program
        GLES20.glLinkProgram(riGraphicTools.sp_Text);                  // creates OpenGL ES program executables

    }


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

        // Prepare the text for rendering
        tm.PrepareDraw();
        scene.setTm(tm);

    }

    public InitStageScreenState(int time)
    {
        super(time);


    }


    @Override
    public void Entered() {



        InitTextShader();


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


        scene = new SimpleScene(world,true);



        SetupText();



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
