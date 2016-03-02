package ploobs.plantevolution;

import ploobs.plantevolution.GUI.Element;
import ploobs.plantevolution.Gameplay.Player;
import ploobs.plantevolution.Material.Color;
import ploobs.plantevolution.Material.DiffuseMaterial;
import ploobs.plantevolution.Material.IMaterial;
import ploobs.plantevolution.Material.FaceShadedCubeMaterial;
import ploobs.plantevolution.Material.SimpleSquareMaterial;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.Model.Model2D.RectangleModel;
import ploobs.plantevolution.Model.Model2D.Square;
import ploobs.plantevolution.Model.Model2D.SquareModel;
import ploobs.plantevolution.Model.Model3D.BoxModel;
import ploobs.plantevolution.Model.IModel;
import ploobs.plantevolution.Model.Model3D.SphereModel;
import ploobs.plantevolution.World.SimpleObject;

public class ObjectFactory {
	
	private ObjectFactory(){}
	
	
	/**
	   * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
	   * or the first access to SingletonHolder.INSTANCE, not before.
	   */
	  private static class SingletonHolder { 
	    private static final ObjectFactory INSTANCE = new ObjectFactory();
	  }

	  public static ObjectFactory getInstance() {
		    return SingletonHolder.INSTANCE;
		  }

	public Player getPlayer(String name,float scale,int energy)
	{
		Player obj;
		//IModel m1 = new BoxModel(scale);
		//
		IModel m1 = new SphereModel(scale,16,16);

		//FaceShadedCubeMaterial mat1 = new FaceShadedCubeMaterial();////DiffuseMaterial();
		DiffuseMaterial mat1 = new DiffuseMaterial();
		//mat1.setColor(Color.enumtoColor(Color.COLORNAME.WHITE));
		mat1.setDiffuseColor(Color.enumtoColor(Color.COLORNAME.WHITE));
		obj = new Player(mat1,m1, name,energy);
		obj.setScale(new Vector3(scale, scale, scale));
		return obj;

	}

	public SimpleObject getNormalBoxObject(String name, float scale)
	{
		SimpleObject obj;
		IModel m1 = new BoxModel(scale);
		//FaceShadedCubeMaterial mat1 = new FaceShadedCubeMaterial();////DiffuseMaterial();
		DiffuseMaterial mat1 = new DiffuseMaterial();


		//mat1.setColor(new Color(0.2705f, 0.9216f, 0.1058f, 1.0f));
		mat1.setDiffuseColor(Color.enumtoColor(Color.COLORNAME.PURPLE));

		obj = new SimpleObject(mat1,m1, name);
		obj.setScale(new Vector3(scale, scale, scale));
		return obj;
	}

	public SimpleObject getStoneBoxObject(String name, float scale)
	{
		SimpleObject obj;
		IModel m1 = new BoxModel(scale);
		DiffuseMaterial mat1 = new DiffuseMaterial();////DiffuseMaterial();

		//mat1.setColor(new Color(0.1f, 0.1f, 0.1f, 1.0f));
		mat1.setDiffuseColor(Color.enumtoColor(Color.COLORNAME.GRAY));

		obj = new SimpleObject(mat1,m1, name);
		obj.setScale(new Vector3(scale, scale, scale));
		return obj;
	}

	public SimpleObject getGemaObject(String name, float scale)
	{
		SimpleObject obj;
		IModel m1 = new BoxModel(scale);
		//FaceShadedCubeMaterial mat1 = new FaceShadedCubeMaterial();////DiffuseMaterial();
		DiffuseMaterial mat1 = new DiffuseMaterial();
		//mat1.setColor(Color.enumtoColor(Color.COLORNAME.YELLOW));
		mat1.setDiffuseColor(Color.enumtoColor(Color.COLORNAME.YELLOW));
		obj = new SimpleObject(mat1,m1, name);
		obj.setScale(new Vector3(scale, scale, scale));
		return obj;
	}


	
	public SimpleObject getBoxObject(String name,float scale)
	{
		SimpleObject obj;
		IModel m1 = new BoxModel(scale);
		IMaterial mat1 = new FaceShadedCubeMaterial();////DiffuseMaterial();
		obj = new SimpleObject(mat1,m1, name);
		obj.setScale(new Vector3(scale, scale, scale));
		return obj;
		
	}


	public SimpleObject getSquareObject(String name, float size)
	{
		Square sq;

		SimpleObject obj;
		IModel m1 = new SquareModel(size);
		SimpleSquareMaterial mat1 = new SimpleSquareMaterial(R.drawable.splitscreen);////DiffuseMaterial();
		mat1.setColor(Color.enumtoColor(Color.COLORNAME.YELLOW));
		obj = new SimpleObject(mat1,m1, name);

		float ratio = GraphicFactory.getInstance().getRatio();

		obj.setScale(new Vector3(ratio, ratio, ratio));


		return obj;
	}

	public SimpleObject getRectangleObject(String name,final int resourceId, float width, float height, Vector3 position)
	{
		Square sq;

		SimpleObject obj;
		IModel m1 = new RectangleModel((2*GraphicFactory.getInstance().getRatio())*(width/GraphicFactory.getInstance().getWidth()),2*height/GraphicFactory.getInstance().getHeight());
		SimpleSquareMaterial mat1 = new SimpleSquareMaterial(resourceId);////DiffuseMaterial();
		mat1.setColor(Color.enumtoColor(Color.COLORNAME.YELLOW));
		obj = new SimpleObject(mat1,m1, name);

		obj.setScale(new Vector3(1, 1, 1));
		obj.setPosition(position);



		return obj;
	}


	public Element getButtonObject(String name,final int resourceId, float width, float height, Vector3 position)
	{
		Square sq;

		Element obj;
		IModel m1 = new RectangleModel((2*GraphicFactory.getInstance().getRatio())*(width/GraphicFactory.getInstance().getWidth()),2*height/GraphicFactory.getInstance().getHeight());
		SimpleSquareMaterial mat1 = new SimpleSquareMaterial(resourceId);////DiffuseMaterial();

		mat1.setColor(Color.enumtoColor(Color.COLORNAME.YELLOW));
		obj = new Element(mat1,m1, name,position,width,height);

		obj.setScale(new Vector3(1, 1, 1));
		//	obj.setScale(new Vector3ratio, ratio, ratio)):
        obj.setPosition(position);

		return obj;
	}




}
