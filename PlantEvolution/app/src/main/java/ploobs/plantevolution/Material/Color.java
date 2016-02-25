package ploobs.plantevolution.Material;

import java.nio.FloatBuffer;

import ploobs.plantevolution.Utils;

public class Color {


	public enum COLORNAME
	{
		BLACK,
		WHITE,
		RED,
		BLUE,
		GREEN,
		YELLOW
	};


	public float r=0;
	public float g=0;
	public float b=0;
	public float a=1;


	public Color (float[] color)
	{

		this.r = color[0];
		this.g = color[1];
		this.b = color[2];
		this.a =color[3];

	}

public Color(float r, float g, float b, float a)
{
	this.r = r;
	this.g = g;
	this.b = b;
	this.a =a;

}



	public static Color enumtoColor(COLORNAME cc)
	{
		float[] color =  {0.1f, 0.1f, 0.1f,1.0f};
		
		switch (cc) {
		case BLACK:
			color = new float[]{0,0,0,1.0f};
		break;

		case WHITE:
			color = new float[]{1.0f,1.0f,1.0f,1.0f};
		break;

			case YELLOW:
				color = new float[]{1.0f,1.0f,0,1.0f};
				break;


			default:
			break;
		}
		
		return new Color(color[0],color[1],color[2],color[3]);
		
	}
	
	
	
	float[] getColor()
	{
		float[] color = new float[4];
		color[0] = r;
		color[1] = g;
		color[2] = b;
		color[3] = a;


		return color;
	}


	public FloatBuffer toFloatBuffer()
	{
		return Utils.makeFloatBuffer4(r, g, b, a);
	}
	

}
