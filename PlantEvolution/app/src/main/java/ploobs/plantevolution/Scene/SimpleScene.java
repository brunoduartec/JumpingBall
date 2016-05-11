package ploobs.plantevolution.Scene;

import android.opengl.GLES20;
import android.opengl.Matrix;

import ploobs.plantevolution.Camera.ICamera;
import ploobs.plantevolution.Gameplay.GameConstants;
import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Text.TextManager;
import ploobs.plantevolution.Text.riGraphicTools;
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
			if(getTm() !=null) {
				getTm().Draw(world2d.getCameraManager().getActualCamera().getViewProjectionMatrix());

			}
		}


		GLES20.glDisable(GLES20.GL_BLEND);


	}
	
	public SimpleScene(IWorld w)
	{

		this.world = w;
		Initialize();
		
	}

	public SimpleScene(IWorld w, boolean d2)
	{
		if (d2)
			this.world2d = w;
		else
			this.world = w;

		Initialize();
	}


	public SimpleScene(IWorld d3, IWorld w2)
	{
		this.world2d = w2;
		this.world = d3;
		Initialize();
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
		InitTextShader();
	}

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
