package ploobs.plantevolution.Scene;

import android.opengl.GLES20;

import ploobs.plantevolution.Material.DiffuseMaterial;
import ploobs.plantevolution.World.IObject;
import ploobs.plantevolution.World.IWorld;
import ploobs.plantevolution.World.ObjectContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

public class SimpleScene implements IScene {

	private IWorld world;
	private IWorld world2d;

	@Override
	public void Draw() {
		// TODO Auto-generated method stub

		if (world!=null) {
			//Map<Integer, IObject> ot = world.getObjectsList();
			List<IObject> ot = getWorld().getObjectsList();
			for (int i = 0; i < ot.size(); i++) {


				IObject o1 = (IObject) ot.get(i);



				if (o1 instanceof ObjectContainer)
				{
					ObjectContainer oo = (ObjectContainer)o1;
					for (IObject o2:oo.children() ) {
						o2.Draw(world);
					}

				}
				else {

					//o1.Draw(world);
				}
			}
		}

		GLES20.glEnable(GLES20.GL_BLEND);
		GLES20.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);


		if (world2d !=null)
		{
			List<IObject> ot =  getWorld2d().getObjectsList();



			for (int i = 0; i < ot.size(); i++) {
				IObject o1 = (IObject) ot.get(i);
				o1.Draw(world2d);
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
}
