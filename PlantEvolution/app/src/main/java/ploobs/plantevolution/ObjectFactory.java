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
		IModel m1 = new BoxModel(scale);
		//
		//IModel m1 = new SphereModel(scale,4,4);

		//FaceShadedCubeMaterial mat1 = new FaceShadedCubeMaterial();////DiffuseMaterial();
		DiffuseMaterial mat1 = new DiffuseMaterial();
		mat1.setColor(Color.enumtoColor(Color.COLORNAME.WHITE));

		obj = new Player(mat1,m1, name,energy);
		obj.setScale(new float[]{scale, scale, scale});
		return obj;

	}

	public SimpleObject getNormalBoxObject(String name, float scale)
	{
		SimpleObject obj;
		IModel m1 = new BoxModel(scale);
		//FaceShadedCubeMaterial mat1 = new FaceShadedCubeMaterial();////DiffuseMaterial();
		DiffuseMaterial mat1 = new DiffuseMaterial();


		mat1.setColor(new float[]{0.2695f, 0.921875f, 0.109375f, 1.0f});
		obj = new SimpleObject(mat1,m1, name);
		obj.setScale(new float[]{scale, scale, scale});
		return obj;
	}

	public SimpleObject getStoneBoxObject(String name, float scale)
	{
		SimpleObject obj;
		IModel m1 = new BoxModel(scale);
		DiffuseMaterial mat1 = new DiffuseMaterial();////DiffuseMaterial();

		mat1.setColor(new float[]{.1f, .1f, .1f, 1.0f});
		obj = new SimpleObject(mat1,m1, name);
		obj.setScale(new float[]{scale, scale, scale});
		return obj;
	}

	public SimpleObject getGemaObject(String name, float scale)
	{
		SimpleObject obj;
		IModel m1 = new BoxModel(scale);
		//FaceShadedCubeMaterial mat1 = new FaceShadedCubeMaterial();////DiffuseMaterial();
		DiffuseMaterial mat1 = new DiffuseMaterial();
		mat1.setColor(Color.enumtoColor(Color.COLORNAME.YELLOW));
		obj = new SimpleObject(mat1,m1, name);
		obj.setScale(new float[]{scale, scale, scale});
		return obj;
	}


	
	public SimpleObject getBoxObject(String name,float scale)
	{
		SimpleObject obj;
		IModel m1 = new BoxModel(scale);
		IMaterial mat1 = new FaceShadedCubeMaterial();////DiffuseMaterial();
		obj = new SimpleObject(mat1,m1, name);
		obj.setScale(new float[]{scale, scale, scale});
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

		obj.setScale(new float[]{ratio, ratio, ratio});


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

		obj.setScale(new float[]{1, 1, 1});
		obj.setPosition(position.get());



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

		obj.setScale(new float[]{1, 1, 1});
		//	obj.setScale(new float[]{ratio, ratio, ratio});
        obj.setPosition(position.get());

		return obj;
	}




}
