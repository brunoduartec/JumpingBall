package ploobs.plantevolution.Gameplay;

import android.content.Context;

import java.io.IOException;

import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.World.IWorld;

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
    Context localContext;

    int stagescount=0;

    public StageManager(IWorld w,GameConstants.GAMECONTEXT gt) throws IOException {
        localContext = GraphicFactory.getInstance().getGraphicContext();
        this._localworld = w;
        setBoard1(new Board(w));
        this._gamecontext = gt;

        stagescount = localContext.getAssets().list("stages").length;


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




        if (restart) {
            getBoard1().Reset();
            restart = false;
        }
            else {
            getBoard1().Initialize();

            try {

                if (actualstage <= stagescount)
                {
                    CreateStageFile("stages/stage"+actualstage+".txt");

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


    getBoard1().Load(name);

    _gamecontext = GameConstants.GAMECONTEXT.BLOCK;


   // cameradistance = 2f;

    cameradistance = Math.round((GameConstants.size*GameConstants.scale*GameConstants.scale));



    Vector3 pos = new Vector3(cameradistance, cameradistance * 1.5f, cameradistance);

    _localworld.getCameraManager().getActualCamera().setPosition(pos);



}


    public Board getBoard1() {
        return board1;
    }

    public void setBoard1(Board board1) {
        this.board1 = board1;
    }
}
