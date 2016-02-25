package ploobs.plantevolution.Light;

import ploobs.plantevolution.Material.Color;
import ploobs.plantevolution.World.IObject;

public abstract class ILight extends IObject implements Cloneable
{
	
	private float Intensity;
	private Color color = Color.enumtoColor(Color.COLORNAME.WHITE);




	public Color getColor()
	{
		
		return this.color;
	}
	public void setColor(Color color)
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