package ploobs.plantevolution.Scene;

import android.opengl.GLES20;
import android.opengl.Matrix;

import ploobs.plantevolution.Camera.ICamera;
import ploobs.plantevolution.Gameplay.GameConstants;
import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Text.TextManager;
import ploobs.plantevolution.World.IObject;
import ploobs.plantevolution.World.IWorld;
import ploobs.plantevolution.World.ObjectContainer;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class SimpleScene implements IScene {

	private IWorld world;
	private IWorld world2d;

	//temporary, i will make the text be a 2dcomponent further
	private TextManager tm;

	@Override
	public void Draw() {
		// TODO Auto-generated method stub
// clear Screen and Depth Buffer, we have set the clear color as black.
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		//GLES20.glClearColor(0.34375f, 0.84375f, 0.7109375f, 1.0f);



		if (world!=null) {
			//Map<Integer, IObject> ot = world.getObjectsList();
			List<IObject> ot = getWorld().getObjectsList();
			for (int i = 0; i < ot.size(); i++) {


				IObject o1 = ot.get(i);



				if (o1 instanceof ObjectContainer)
				{
					ObjectContainer oo = (ObjectContainer)o1;
					for (IObject o2:oo.children() ) {
						o2.Draw(world);
					}

				}
				else {

					o1.Draw(world);
				}
			}
		}

		GLES20.glEnable(GLES20.GL_BLEND);
		GLES20.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);


		if (world2d !=null)
		{
			List<IObject> ot =  getWorld2d().getObjectsList();



			for (int i = 0; i < ot.size(); i++) {
				IObject o1 = ot.get(i);
				o1.Draw(world2d);
			}



			// Render the text GAMBIARRA MOR
			if(getTm() !=null) {

				float[] mMVPMatrix =new float[16];
				float[] mtrxProjection = new float[16];
				float[] mtrxView = new float[16];
				float[] mtrxProjectionAndView = new float[16];

				// Setup our screen width and height for normal sprite translation.
				Matrix.orthoM(mtrxProjection, 0, 0f, GraphicFactory.getInstance().getWidth(), 0.0f, GraphicFactory.getInstance().getHeight(), 0, 50);

				// Set the camera position (View matrix)
				Matrix.setLookAtM(mtrxView, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

				// Calculate the projection and view transformation
				Matrix.multiplyMM(mtrxProjectionAndView, 0, mtrxProjection, 0, mtrxView, 0);


				//Multiply the worldspace position by the projection matrix and obtain the screen position
			//	ICamera cam = world.getCameraManager().getActualCamera();
			//	Matrix.multiplyMM(mMVPMatrix, 0, cam.getProjectionMatrix(), 0, cam.getViewMatrix(), 0);

				getTm().Draw(mtrxProjectionAndView);
			//	getTm().Draw(mMVPMatrix);
			}
		}


		GLES20.glDisable(GLES20.GL_BLEND);


	}
	
	public SimpleScene(IWorld w)
	{

		this.world = w;
		
	}

	public SimpleScene(IWorld w, boolean d2)
	{
		if (d2)
			this.world2d = w;
		else
			this.world = w;

	}


	public SimpleScene(IWorld d3, IWorld w2)
	{
		this.world2d = w2;
		this.world = d3;

	}


	@Override
	public void Update() {
		// TODO Auto-generated method stub
		if (world !=null)
			world.Update();
		if (world2d!=null)
			world2d.Update();

		
	}

	@Override
	public void Initialize() {

	//	GLES20.glDisable(GLES20.GL_CULL_FACE);
	
	}

	@Override
	public IWorld getWorld() {
		// TODO Auto-generated method stub
		return world;
	}

	public IWorld getWorld2d() {
		return world2d;
	}

	public void setWorld2d(IWorld world2d) {
		this.world2d = world2d;
	}

	public TextManager getTm() {
		return tm;
	}

	public void setTm(TextManager tm) {
		this.tm = tm;
	}
}
