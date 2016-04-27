package ploobs.plantevolution.Material;

import java.nio.FloatBuffer;

import ploobs.plantevolution.Utils;

public class Color4 {


	public enum COLORNAME
	{
		BLACK,
		WHITE,
		RED,
		BLUE,
		GREEN,
		YELLOW,
		PURPLE,
		GRAY
	}


	public short r=0;
	public short g=0;
	public short b=0;
	public short a=255;


	public Color4(short[] color)
	{

		this.r = color[0];
		this.g = color[1];
		this.b = color[2];
		this.a =color[3];

	}

public Color4(short r, short g, short b, short a)
{
	this.r = r;
	this.g = g;
	this.b = b;
	this.a =a;

}


	public Color4(int r, int g, int b, int a)
	{
		this.r = (short)r;
		this.g = (short)g;
		this.b = (short)b;
		this.a =(short)a;

	}


	public static Color4 enumtoColor(COLORNAME cc)
	{
		short[] color =  {255, 255, 255,255};
		
		switch (cc) {
		case BLACK:
			color = new short[]{0,0,0,255};

		break;

		case WHITE:
			color = new short[]{255,255,255,255};
		break;

		case YELLOW:
				color = new short[]{255,255,0,255};
		break;

		case PURPLE:
				color = new short[]{128,0,128,255};
		break;

			case GRAY:
				color = new short[]{112,128,144,255};
				break;


			default:
			break;
		}
		
		return new Color4(color[0],color[1],color[2],color[3]);
		
	}
	
	
	
	float[] getColor()
	{
		float[] color = new float[4];
		color[0] = (float)r/255.0f;
		color[1] = (float)g/255.0f;
		color[2] = (float)b/255.0f;
		color[3] = (float)a/255.0f;


		return color;
	}


	public FloatBuffer toFloatBuffer()
	{
		return Utils.makeFloatBuffer4(r, g, b, a);
	}
	

}
