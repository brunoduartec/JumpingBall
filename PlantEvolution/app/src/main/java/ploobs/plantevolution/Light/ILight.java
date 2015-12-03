package ploobs.plantevolution.Light;

import ploobs.plantevolution.World.IObject;

public abstract class ILight extends IObject
{
	
	private float Intensity;
	private float[] color = new float[4];
	
	
	public float[] getColor()
	{
		
		return this.color;
	}
	public void setColor(float[] color)
	{
		
		this.color = color;
		
	}
	
	
	
	
	
	public float getIntensity()
	{
		return this.Intensity;
	}
	public void setIntensity(float intens)
	{
		this.Intensity = intens;
	}
	





}