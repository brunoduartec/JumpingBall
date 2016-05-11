package ploobs.plantevolution;



import java.util.HashMap;

import ploobs.plantevolution.GUI.Element;
import ploobs.plantevolution.Gameplay.Player;
import ploobs.plantevolution.Material.Color4;
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
import ploobs.plantevolution.Model.Parser.IParser;
import ploobs.plantevolution.Model.Parser.Parser;
import ploobs.plantevolution.World.IObject;
import ploobs.plantevolution.World.ObjectContainer;
import ploobs.plantevolution.World.SimpleObject;

public class ObjectFactory {


	HashMap<String, IMaterial> _materials = new HashMap<>();
	HashMap<String, IModel> _models = new HashMap<>();



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


	public ObjectContainer getOBJModel(String name, String model)
	{
		ObjectContainer objModel;

		IParser parser = Parser.createParser(Parser.Type.OBJ,GraphicFactory.getInstance().getResources(), model, true);
		parser.parse();

		objModel = parser.getParsedObject();



		objModel.setName(name);

		//FaceShadedCubeMaterial mat1 = new FaceShadedCubeMaterial();////DiffuseMaterial();
		//DiffuseMaterial mat1 = new DiffuseMaterial();
		DiffuseMaterial mat1;
		if (_models.containsKey("diffusematerial"))
			mat1 = (DiffuseMaterial)_materials.get("diffusematerial");
		else
			mat1 = new DiffuseMaterial();


		//mat1.setColor(Color4.enumtoColor(Color4.COLORNAME.WHITE));
		mat1.setDiffuseColor(Color4.enumtoColor(Color4.COLORNAME.WHITE));

		objModel.setMaterial(mat1);

		return objModel;
	}


	public Player getPlayer(String name,float scale,int energy)
	{
		Player obj;
		//IModel m1 = new BoxModel(scale);
		//
		IModel m1 = new SphereModel(scale,16,16);

		DiffuseMaterial mat1;
		if (_models.containsKey("diffusematerial"))
			mat1 = (DiffuseMaterial)_materials.get("diffusematerial");
		else
			mat1 = new DiffuseMaterial();


		mat1.setDiffuseColor(Color4.enumtoColor(Color4.COLORNAME.WHITE));
		mat1.setTexture("white");
		obj = new Player(mat1,m1, name,energy);
		return obj;

	}

	public SimpleObject getNormalBoxObject(String name, float scale)
	{
		SimpleObject obj;

		IModel m1;

		if (_models.containsKey("boxmodel"))
			m1 = _models.get("boxmodel");
		else
		    m1 = new BoxModel(scale);

		DiffuseMaterial mat1;

		if (_models.containsKey("diffusematerial"))
			mat1 = (DiffuseMaterial)_materials.get("diffusematerial");
		else
			mat1 = new DiffuseMaterial();


		//mat1.setColor(new Color4(0.2705f, 0.9216f, 0.1058f, 1.0f));
		mat1.setDiffuseColor(Color4.enumtoColor(Color4.COLORNAME.PURPLE));
		//mat1.setTexture(R.drawable.grass);
		mat1.setTexture("grass");
		obj = new SimpleObject(mat1,m1, name);
		//obj.setScale(new Vector3(scale, scale, scale));
		return obj;
	}

	public SimpleObject getStoneBoxObject(String name, float scale)
	{
		SimpleObject obj;
		IModel m1;

		if (_models.containsKey("boxmodel"))
			m1 = _models.get("boxmodel");
		else
			m1 = new BoxModel(scale);

		//DiffuseMaterial mat1 = new DiffuseMaterial();////DiffuseMaterial();
		DiffuseMaterial mat1;
		if (_models.containsKey("diffusematerial"))
			mat1 = (DiffuseMaterial)_materials.get("diffusematerial");
		else
			mat1 = new DiffuseMaterial();


		//mat1.setColor(new Color4(0.1f, 0.1f, 0.1f, 1.0f));
		mat1.setDiffuseColor(Color4.enumtoColor(Color4.COLORNAME.GRAY));
		mat1.setTexture("stone");
		obj = new SimpleObject(mat1,m1, name);
		//obj.setScale(new Vector3(scale, scale, scale));
		return obj;
	}

	public IObject getGemaObject(String name, float scale, Vector3 position)
	{

		ObjectContainer obc = new ObjectContainer();


		SimpleObject obj;
		IModel m1;

		if (_models.containsKey("boxmodel"))
			m1 = _models.get("boxmodel");
		else
			m1 = new BoxModel(scale);
		//FaceShadedCubeMaterial mat1 = new FaceShadedCubeMaterial();////DiffuseMaterial();
	//	DiffuseMaterial mat1 = new DiffuseMaterial();
		DiffuseMaterial mat1;
		if (_models.containsKey("diffusematerial"))
			mat1 = (DiffuseMaterial)_materials.get("diffusematerial");
		else
			mat1 = new DiffuseMaterial();
		Color4 cc = Color4.enumtoColor(Color4.COLORNAME.YELLOW);
		//cc.a = 10;
				//mat1.setColor(Color4.enumtoColor(Color4.COLORNAME.YELLOW));
		mat1.setDiffuseColor(cc);





		mat1.setTexture("red_gem");



		obj = new SimpleObject(mat1,m1, name);
		obj.setPosition(position);
		//obj.setScale(new Vector3(scale, scale, scale));


		/*
		IObject shadowwhite = getBoxObject("shadow",scale,scale*3,scale);

		Vector3 nwepos = position.sub(new Vector3(0,-1.5f*scale,0));
		shadowwhite.setPosition(nwepos);

		obc.children().add(obj);
		obc.children().add(shadowwhite);

*/
		return obj;
	}




	public SimpleObject getBoxObject(String name,float width, float height, float depth)
	{
		SimpleObject obj;

		IModel m1;

		if (_models.containsKey("boxmodel"))
			m1 = _models.get("boxmodel");
		else
			m1 = new BoxModel(width,height,depth);

		DiffuseMaterial mat1;

		if (_models.containsKey("diffusematerial"))
			mat1 = (DiffuseMaterial)_materials.get("diffusematerial");
		else
			mat1 = new DiffuseMaterial();


		//mat1.setColor(new Color4(0.2705f, 0.9216f, 0.1058f, 1.0f));
		mat1.setDiffuseColor(Color4.enumtoColor(Color4.COLORNAME.PURPLE));
		//mat1.setTexture(R.drawable.grass);
		mat1.setTexture("white");
		obj = new SimpleObject(mat1,m1, name);
		//obj.setScale(new Vector3(scale, scale, scale));
		return obj;

	}
	
	public SimpleObject getBoxObject(String name,float scale)
	{
		SimpleObject obj;
		IModel m1;

		if (_models.containsKey("boxmodel"))
			m1 = _models.get("boxmodel");
		else
			m1 = new BoxModel(scale);

		IMaterial mat1 = new FaceShadedCubeMaterial();////DiffuseMaterial();
		obj = new SimpleObject(mat1,m1, name);
		//obj.setScale(new Vector3(scale, scale, scale));
		return obj;
		
	}


	public SimpleObject getSquareObject(String name, float size)
	{
		Square sq;

		SimpleObject obj;
		IModel m1 = new SquareModel(size);
		SimpleSquareMaterial mat1 = new SimpleSquareMaterial(R.drawable.splitscreen);////DiffuseMaterial();
		mat1.setColor(Color4.enumtoColor(Color4.COLORNAME.YELLOW));
		obj = new SimpleObject(mat1,m1, name);

		float ratio = GraphicFactory.getInstance().getRatio();

		obj.setScale(new Vector3(ratio, ratio, ratio));


		return obj;
	}

	public SimpleObject getRectangleObject(String name,final int resourceId, float width, float height, Vector3 position)
	{
		Square sq;

		SimpleObject obj;
		//IModel m1 = new RectangleModel((2*GraphicFactory.getInstance().getRatio())*(width/GraphicFactory.getInstance().getWidth()),2*height/GraphicFactory.getInstance().getHeight());
		IModel m1 = new RectangleModel(GraphicFactory.getInstance().getWidth(),GraphicFactory.getInstance().getHeight());



		SimpleSquareMaterial mat1 = new SimpleSquareMaterial(resourceId);////DiffuseMaterial();
		mat1.setColor(Color4.enumtoColor(Color4.COLORNAME.YELLOW));
		obj = new SimpleObject(mat1,m1, name);

		obj.setScale(new Vector3(1, 1, 1));
		obj.setPosition(position);

		return obj;
	}


	public ObjectContainer getPopUpObject(String name,final int resourceId, float width, float height, Vector3 position)
	{
		Square sq;

		ObjectContainer cont = new ObjectContainer();

		Element obj;

		float w = (2*GraphicFactory.getInstance().getRatio())*(width/GraphicFactory.getInstance().getWidth());
		float h = 2*height/GraphicFactory.getInstance().getHeight();

		IModel m1 = new RectangleModel(w,h);
		SimpleSquareMaterial mat1 = new SimpleSquareMaterial(resourceId);////DiffuseMaterial();

		mat1.setColor(Color4.enumtoColor(Color4.COLORNAME.YELLOW));
		obj = new Element(mat1,m1, name,position,width,height);

		//obj.setScale(new Vector3(1, 1, 1));
		//	obj.setScale(new Vector3ratio, ratio, ratio)):
		obj.setPosition(position);


		IObject background_noise = getRectangleObject("noise",R.drawable.perlin_noise, GraphicFactory.getInstance().getWidth(), GraphicFactory.getInstance().getHeight(), Vector3.Zero);

		cont.addChild(background_noise);
		cont.addChild(obj);


		return cont;

	}



	public Element getButtonObject(String name,final int resourceId, float width, float height, Vector3 position)
	{
		Square sq;

		Element obj;

		float w = (2*GraphicFactory.getInstance().getRatio())*(width/GraphicFactory.getInstance().getWidth());
		float h = 2*height/GraphicFactory.getInstance().getHeight();

		//IModel m1 = new RectangleModel(w,h);
		IModel m1 = new RectangleModel(width,height);
		SimpleSquareMaterial mat1 = new SimpleSquareMaterial(resourceId);////DiffuseMaterial();

		mat1.setColor(Color4.enumtoColor(Color4.COLORNAME.YELLOW));
		obj = new Element(mat1,m1, name,position,width,height);

		obj.setScale(new Vector3(1, 1, 1));
		//	obj.setScale(new Vector3ratio, ratio, ratio)):



       // obj.setPosition(position);
//		obj.setPosition(Utils.converttoScreenSpace(position));
		obj.setPosition(position);
		return obj;
	}




}
