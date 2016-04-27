package ploobs.plantevolution.Light;

import ploobs.plantevolution.Material.Color4;
import ploobs.plantevolution.World.IObject;

public abstract class ILight extends IObject implements Cloneable
{
	
	private float diffuseIntensity;
	private Color4 color = Color4.enumtoColor(Color4.COLORNAME.WHITE);


	private float ambientIntensity;
	private float specularIntensity;


	public Color4 getColor()
	{
		
		return this.color;
	}
	public void setColor(Color4 color)
	{
		
		this.color = color;
		
	}
	
	
	
	
	
	public float getDiffuseIntensity()
	{
		return this.diffuseIntensity;
	}
	public void setDiffuseIntensity(float intens)
	{
		this.diffuseIntensity = intens;
	}


	public float getAmbientIntensity() {
		return ambientIntensity;
	}

	public void setAmbientIntensity(float ambientIntensity) {
		this.ambientIntensity = ambientIntensity;
	}

	public float getSpecularIntensity() {
		return specularIntensity;
	}

	public void setSpecularIntensity(float specularIntensity) {
		this.specularIntensity = specularIntensity;
	}
}