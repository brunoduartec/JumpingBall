package ploobs.plantevolution.Gameplay;

import android.content.Context;

import java.io.IOException;

import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.World.IWorld;
import ploobs.plantevolution.MyGLRenderer;

/**
 * Created by Bruno on 11/11/2015.
 */
public class StageManager {

    private Board board1;

    int actualstage=0;
IWorld _localworld;
    private float cameradistance;
    GameConstants.GAMECONTEXT _gamecontext;
    int size;

    public StageManager(IWorld w,GameConstants.GAMECONTEXT gt)
    {
        this._localworld = w;
        setBoard1(new Board(w));
        this._gamecontext = gt;

    }

    public Player getPlayer()
    {
        return getBoard1().getPlayer();
    }

    public void RestartStage()
    {

        try {
            StartStage(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void StartStage(boolean restart) throws IOException {

        if (restart)
            getBoard1().Reset();
        else {
            getBoard1().Initialize();

            try {
                switch (actualstage) {
                    case 1:

                        CreateStageFile("stages/stage0.txt");
                        break;
                    case 2:
                        CreateStageFile("stages/stage1.txt");
                        break;
                    case 3:
                        CreateStage3();
                        break;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public void NextStage() throws IOException {
        actualstage++;

        try {
            StartStage(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


private void CreateStageFile(String name) throws IOException {

    _gamecontext = GameConstants.GAMECONTEXT.BLOCK;
    GameConstants.size =5;
    size = GameConstants.size;

    cameradistance = 2f;

    Vector3 pos = new Vector3(cameradistance, cameradistance * 1.5f, cameradistance);

    _localworld.getCameraManager().getActualCamera().setPosition(pos);

    getBoard1().Load(name);

}



    private void CreateStage1()
    {


        _gamecontext = GameConstants.GAMECONTEXT.BLOCK;


        GameConstants.size =5;
        size = GameConstants.size;



        getBoard1().setGemaheight(4);

        cameradistance = 2f;

        Vector3 pos = new Vector3(cameradistance, cameradistance * 1.5f, cameradistance);

        _localworld.getCameraManager().getActualCamera().setPosition(pos);




        getBoard1().CreateBoard(GameConstants.size);



        getBoard1().PlaceBlocksat(NormalBlock.class, 1, size - 2, size - 2);
        getBoard1().PlaceBlocksat(NormalBlock.class, 2, 1, 1);

        //   board1.PlaceBlocksat(StoneBlock.class, 1, 3, 0);




    }
    private void CreateStage2()
    {
        _gamecontext = GameConstants.GAMECONTEXT.BLOCK;
        //  _activityhandle.setContextImage(_gamecontext);
        GameConstants.size = 5;
        size = GameConstants.size;

     //   getBoard1().Initialize();


        cameradistance = 2f;
        getBoard1().setGemaheight(4);

        Vector3 pos = new Vector3(cameradistance, cameradistance * 1.5f, cameradistance);

        _localworld.getCameraManager().getActualCamera().setPosition(pos);



        getBoard1().CreateBoard(GameConstants.size);



      //  getBoard1().PlaceRandonBlock();
        getBoard1().PlaceBlocksat(StoneBlock.class, 1, 3, 0);

        getBoard1().PlaceBlocksat(StoneBlock.class, 1, 2, 1);


        getBoard1().PlaceBlocksat(NormalBlock.class, 1, size - 2, size - 2);
        getBoard1().PlaceBlocksat(NormalBlock.class, 2, 1, 1);

    }


    private void CreateStage3()
    {
        _gamecontext = GameConstants.GAMECONTEXT.BLOCK;
        //  _activityhandle.setContextImage(_gamecontext);
        GameConstants.size = 9;
        size = GameConstants.size;

    //    getBoard1().Initialize();
        getBoard1().setGemaheight(4);

        cameradistance = 3f;

        Vector3 pos =new Vector3(cameradistance, cameradistance * 1.5f, cameradistance);

        _localworld.getCameraManager().getActualCamera().setPosition(pos);



        getBoard1().CreateBoard(GameConstants.size);



        //getBoard1().PlaceRandonBlock();
        getBoard1().PlaceBlocksat(StoneBlock.class, 1, 3, 0);
        //getBoard1().PlaceBlocksat(StoneBlock.class, 2, 3, 1);
        getBoard1().PlaceBlocksat(StoneBlock.class, 2, 2, 2);

        getBoard1().PlaceBlocksat(NormalBlock.class, 1, size - 1, size - 2);
        getBoard1().PlaceBlocksat(NormalBlock.class, 2, 1, 1);

    }

    public Board getBoard1() {
        return board1;
    }

    public void setBoard1(Board board1) {
        this.board1 = board1;
    }
}
