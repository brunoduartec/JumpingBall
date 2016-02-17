package ploobs.plantevolution.Material;

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

public Color(float r,float g, float b, float a)
{
	this.r = r;
	this.g = g;
	this.b = b;
	this.a =a;

}



	public static float[] enumtoColor(COLORNAME cc)
	{
		float[] color =  {10.0f, 10.0f, 10.0f,1.0f};
		
		switch (cc) {
		case BLACK:
			color = new float[]{0.0f,0.0f,0.0f,1.0f};
		break;

		case WHITE:
			color = new float[]{1.0f,1.0f,1.0f,1.0f};
		break;

			case YELLOW:
				color = new float[]{1.0f,1.0f,0.0f,1.0f};
				break;


			default:
			break;
		}
		
		return color;
		
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
	
	

}
