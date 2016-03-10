package ploobs.plantevolution.Gameplay.States;

import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import java.io.IOException;

import ploobs.plantevolution.Audio.AudioPlayer;
import ploobs.plantevolution.Camera.Camera2D;
import ploobs.plantevolution.Camera.SimpleCamera;
import ploobs.plantevolution.Component.FpsCounterComponent;
import ploobs.plantevolution.Component.TimerComponent;
import ploobs.plantevolution.GUI.Element;
import ploobs.plantevolution.GUI.GuiManager;
import ploobs.plantevolution.GUI.IEventHandler;
import ploobs.plantevolution.GameState.GameStateUpdatableDrawable;
import ploobs.plantevolution.Gameplay.GameConstants;
import ploobs.plantevolution.Gameplay.StageManager;
import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Input.InputSystem;
import ploobs.plantevolution.Material.TextureManager;
import ploobs.plantevolution.Math.Vector2;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.ObjectFactory;
import ploobs.plantevolution.R;
import ploobs.plantevolution.Scene.IScene;
import ploobs.plantevolution.Scene.SimpleScene;
import ploobs.plantevolution.Utils;
import ploobs.plantevolution.World.IWorld;
import ploobs.plantevolution.World.SimpleWorld;

/**
 * Created by Bruno on 02/12/2015.
 */
public class MainScreenState extends GameStateUpdatableDrawable {





    private GameConstants.GAMECONTEXT _gamecontext = GameConstants.GAMECONTEXT.PLAYER;

    private IWorld world;
    private IWorld world2d;


    private IScene scene;


    private int width, height;


    private int actualplayerenergy;


    private float mAngle;
    float scale;
    float delta = 0.01f;

    int size = 8;
    float cameradistance = 3.0f;

    Vector2 direction = new Vector2(0, -1);
    Vector3 normalizeddirection = new Vector3(0,0,0);

    private float posx;
    private float posy;
    FpsCounterComponent fps;
    TimerComponent timer;
    //	private SimpleCamera camera;
    // Board board1;
    private boolean startmove = false;
    private int iter = 0;//used to made N moves

    StageManager stages;

    private boolean makeMovement = true;
    private Element jumpbutton;
    private Element pushbutton;
    private Element restartbutton;

    GuiManager gm;

    private void Init()
    {

        Bitmap b;

        b = Utils.makeBitmapFromResourceId(R.drawable.grass);
        TextureManager.getInstance().addTextureId(b, "grass", false);
        b.recycle();

        b = Utils.makeBitmapFromResourceId(R.drawable.white);
        TextureManager.getInstance().addTextureId(b, "white", false);
        b.recycle();

        b = Utils.makeBitmapFromResourceId(R.drawable.gem);
        TextureManager.getInstance().addTextureId(b, "gem", false);
        b.recycle();

        b = Utils.makeBitmapFromResourceId(R.drawable.stone);
        TextureManager.getInstance().addTextureId(b, "stone", false);
        b.recycle();



    }


    @Override
    public void Entered() {


        Init();


        this.scale = GameConstants.scale;
        DisplayMetrics metrics = GraphicFactory.getInstance().getGraphicContext().getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;


        GraphicFactory.getInstance().setWidth(this.width);
        GraphicFactory.getInstance().setHeight(this.height);

        fps = new FpsCounterComponent(60);
        timer = new TimerComponent(1000);


        world = new SimpleWorld();
        world2d = new SimpleWorld();
        gm = new GuiManager(world2d);

        Vector3 pos = new Vector3(cameradistance, cameradistance * 1.5f, cameradistance);
        Vector3 target = new Vector3(0.0f, 0.0f, 0.0f);
        SimpleCamera camera = new SimpleCamera("CAM1", 60, 1, 10, pos, target);





        world.getCameraManager().addCamera(camera);
        world.getCameraManager().setActualCamera("CAM1");


        Camera2D cam2D = new Camera2D("CAM2", 720, 1118, 0, 50, (float) (3 / 4));
        world2d.getCameraManager().addCamera(cam2D);
        world2d.getCameraManager().setActualCamera("CAM2");



      //  world2d.AddObject(ObjectFactory.getInstance().getRectangleObject("jumpbutton", GraphicFactory.getInstance().getWidth(),GraphicFactory.getInstance().getHeight(), new Vector2(0, 0)));



//ObjectFactory.getInstance().getOBJModel("planta","ploobs.plantevolution:raw/plant_player");

        scene = new SimpleScene(world,world2d);

        try {

            stages = new StageManager(world,_gamecontext);
            stages.NextStage();
        } catch (IOException e) {
            e.printStackTrace();
        }


        CreateHUD();

        AudioPlayer.getInstance().changeVolume("theme", 100);

        
    }

    private void CreateHUD()
    {

        float scale = 1.5f;
        jumpbutton = ObjectFactory.getInstance().getButtonObject("jumpbutton", R.drawable.ball, 118*scale, 133*scale, new Vector3(0.2f, -1.6f,0.0f));
        IEventHandler h1 = new IEventHandler() {
            @Override
            public void Execute() {
                stages.getBoard1().setPlayerAction(GameConstants.PLAYERRACTION.JUMP);
                AudioPlayer.getInstance().playAudio("switch_sound");
            }
        };
        jumpbutton.setOnClick(h1);
        gm.AddElement(jumpbutton);


        pushbutton = ObjectFactory.getInstance().getButtonObject("jumpbutton", R.drawable.box, 118*scale, 128*scale, new Vector3(0.6f, -1.6f,0.0f));
        IEventHandler h2 = new IEventHandler() {
            @Override
            public void Execute() {
                stages.getBoard1().setPlayerAction(GameConstants.PLAYERRACTION.PUSH);
                AudioPlayer.getInstance().playAudio("switch_sound");
            }
        };
        pushbutton.setOnClick(h2);
        gm.AddElement(pushbutton);



        restartbutton = ObjectFactory.getInstance().getButtonObject("restart", R.drawable.restart, 64*scale, 64*scale, new Vector3(0.85f, 0.0f,0.0f));
        IEventHandler h3 = new IEventHandler() {
            @Override
            public void Execute() {
                stages.RestartStage();
            }
        };
        restartbutton.setOnClick(h3);
        gm.AddElement(restartbutton);



        int energy = stages.getPlayer().getEnergy();
        actualplayerenergy = energy;
        //Add Energy bars

        Vector3 initpos = new Vector3(0.1f,0f,0);
        Vector3 delta =  new Vector3(0.2f,0,0);


        Vector3 pp = initpos;
        for (int i=0;i<energy;i++)
        {
            pp = initpos.add(delta.mul(i));
            world2d.AddObject(ObjectFactory.getInstance().getRectangleObject("energy_1", R.drawable.sun, 64*scale, 64*scale, pp));


        }


        //world2d.AddObject(ObjectFactory.getInstance().getRectangleObject("energy_1", R.drawable.sun, 64*scale, 64*scale, initpos));
        //world2d.AddObject(ObjectFactory.getInstance().getRectangleObject("energy_2", R.drawable.sun, 64*scale, 64*scale, initpos.add(delta)));
        //world2d.AddObject(ObjectFactory.getInstance().getRectangleObject("energy_3", R.drawable.sun, 64*scale, 64*scale, initpos.add(delta.mul(2))));



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
    public boolean isEnded() {
        return false;
    }

    @Override
    public void Update() {


        scene.getWorld().getCameraManager().getActualCamera().Update();

        //Here in the Prototype 1 i will implement a simple scene management
        if (stages.getBoard1().TestEnd()) {

            AudioPlayer.getInstance().playAudio("pickup_gem");
            try {
                stages.NextStage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fps.Update()) {
            scene.Update();

            if (startmove)
            {
                DirectionMade(direction);
                startmove = false;

                if (_gamecontext == GameConstants.GAMECONTEXT.PLAYER)
                {

                    Vector2 tdir = new Vector2(normalizeddirection.getX(), normalizeddirection.getZ());
                    stages.getBoard1().MovePlayer(tdir);

                }
            }
        }
    }

    @Override
    public void Draw() {
        scene.Draw();
    }

    @Override
    public void HandleEvent() {


        if(gm.HandleElements())
            return;


        MotionEvent e = InputSystem.getInstance().get_inputEvent();
        float mPreviousX = InputSystem.getInstance().getmPreviousX();
        float mPreviousY = InputSystem.getInstance().getmPreviousY();




        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {


            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_UP:
                //  mRenderer.changeCamera();
                makeMovement = true;
                break;

            case MotionEvent.ACTION_MOVE:

                if(makeMovement) {
                    float dx = x - mPreviousX;
                    float dy = y - mPreviousY;

                    Vector2 dir = new Vector2(dx, dy);//B-A


                    makeMovement = false;
                    StartMovement(dir);
                }

        }

       InputSystem.getInstance().setmPreviousX(x);
        InputSystem.getInstance().setmPreviousY(y);

    }

    public void StartMovement(Vector2 dir)
    {
        if (!startmove) {
            direction = dir;
            startmove = true;
        }
    }


    public void DirectionMade(Vector2 dir) {

        if (dir.x > 0 && dir.y < 0)
            normalizeddirection = new Vector3(0, 0, -1);
            // board1.MoveBlocks(new Vector3(0, 0, -1));
        else if (dir.x < 0 && dir.y > 0)
            normalizeddirection = new Vector3(0, 0, 1);
            // board1.MoveBlocks(new Vector3(0, 0, 1));
        else if (dir.x > 0 && dir.y > 0)
            normalizeddirection = new Vector3(1, 0, 0);
            // board1.MoveBlocks(new Vector3(1, 0, 0));
        else
            normalizeddirection = new Vector3(-1, 0, 0);
        // board1.MoveBlocks(new Vector3(-1, 0, 0));

        //board1.PlaceRandonBlock();

    }
}
