package ploobs.plantevolution.Material;

import ploobs.plantevolution.World.IObject;
import ploobs.plantevolution.ISerializable;
import ploobs.plantevolution.World.IWorld;

public abstract class IMaterial implements ISerializable
{
	
	
	int vertexShaderHandle=-1;
	int fragmentShaderHandle=-1;
	int mProgram;
	Color color;


	private String diffuseTextureMap;
	
	public int getVertexShader() {
		// TODO Auto-generated method stub
		return vertexShaderHandle;
	}
	
	public int getFragmentShader() {
		// TODO Auto-generated method stub
		return fragmentShaderHandle;
	}

	public abstract TextureList getTextures();

	public abstract Color getDiffuseColor();

public abstract void Draw(IObject obj, IWorld world);


	public  void setDiffuseColor(Color diffuseColor) {
		this.color = diffuseColor;
	}

	public String getDiffuseTextureMap() {
		return diffuseTextureMap;
	}

	public void setDiffuseTextureMap(String diffuseTextureMap) {
		this.diffuseTextureMap = diffuseTextureMap;
	}
}