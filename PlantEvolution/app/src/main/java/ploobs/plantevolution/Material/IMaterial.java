package ploobs.plantevolution.Material;

import ploobs.plantevolution.World.IObject;
import ploobs.plantevolution.ISerializable;
import ploobs.plantevolution.World.IWorld;

public abstract class IMaterial implements ISerializable
{
	
	
	int vertexShaderHandle=-1;
	int fragmentShaderHandle=-1;
	int mProgram;
	
	public int getVertexShader() {
		// TODO Auto-generated method stub
		return vertexShaderHandle;
	}
	
	public int getFragmentShader() {
		// TODO Auto-generated method stub
		return fragmentShaderHandle;
	}
public abstract void Draw(IObject obj, IWorld world);







}