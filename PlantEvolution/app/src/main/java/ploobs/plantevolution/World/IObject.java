package ploobs.plantevolution.World;

import android.opengl.Matrix;

import ploobs.plantevolution.ISerializable;
import ploobs.plantevolution.Material.IMaterial;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.Model.IModel;
import ploobs.plantevolution.World.IWorld;


public abstract class IObject implements ISerializable
{

	private IObjectContainer parent;
	
	float[] localTransformation = new float[16];



	Vector3 position = new Vector3();
	Vector3 rotation = new Vector3();
	Vector3 scale = new Vector3();
	private IMaterial material;
	private IModel model;
	boolean Enabled=true;
	boolean Visible=true;
	public String name=null;
	static int ID=0;
	public int localID=-1;

	@Override
	public String toString()
	{

		return this.getName();
	}
	
public Vector3 getPosition()
{
	return position;
}

public  void setPosition(Vector3 pos)
{
	 this.position = pos;
}
 

public Vector3 getRotation()
{
	return this.rotation;
}



public void setRotation(Vector3 rot)
{
	this.rotation=rot;	
}

public Vector3 getScale()
{
	return this.scale;	
}



public void setScale(Vector3 scale)
{
	this.scale = scale;	
}



public float[] getLocalTransformation()
{
	
	 float[] localTransformation= new float[16];
	 Vector3 position = getPosition();
	 
	Matrix.setIdentityM(localTransformation, 0); // initialize to identity matrix


	Matrix.scaleM(localTransformation, 0, scale.getX(), scale.getY(), scale.getZ());


	 Matrix.translateM(localTransformation, 0, position.getX(), position.getY(), position.getZ()); // Multiply by translation to the position


	return localTransformation;
	
}

public void setLocalTransformation(float[] trans)
{
	this.localTransformation = trans;

}

public boolean getEnabled()
{
	
	return this.Enabled;

}

public boolean getVisible()
{
	
	return this.Visible;

}




public String getName()
{
return name;	
}
public void setName(String name){this.name = name;}


public int getID()
{
	return ID;	
}
public abstract void Draw(IWorld world);
public abstract void Update();


public IMaterial getMaterial()
{
	return material;
}

public void setMaterial(IMaterial mat){this.material = mat;}

public IModel getModel()
{
	return model;
}

	public void setModel(IModel model){this.model = model;}


	public IObjectContainer getParent() {
		return parent;
	}

	public void setParent(IObjectContainer parent) {
		this.parent = parent;
	}
}