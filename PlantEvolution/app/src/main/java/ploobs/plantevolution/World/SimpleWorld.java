package ploobs.plantevolution.World;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ploobs.plantevolution.Camera.SceneCameraManager;
import ploobs.plantevolution.Light.ILight;

public class SimpleWorld implements IWorld {

	
	private SceneCameraManager camManager;
	private Map<Integer,IObject> Objs = new HashMap<>();
	private List<ILight> lights = new LinkedList<ILight>();
	

	
	@Override
	public Map<Integer,IObject> getObjectsList() {
		// TODO Auto-generated method stub
		return Objs;
	}

	@Override
	public void Initialize() {
		// TODO Auto-generated method stub
		Objs.clear();
		IObject.ID=0;


	//	lights.clear();
	}

	@Override
	public void AddObjectList(List<IObject> obj) {
		for (IObject oo :obj) {
			AddObject(oo);
		}	

	}
	
	
	



	public SimpleWorld()
	{
		
		//Objs= new HashMap<Integer,IObject>();
		camManager = new SceneCameraManager();

		Initialize();
	}
	
	
	@Override
	public int AddObject(IObject obj) {
		// TODO Auto-generated method stub
		
		Objs.put(obj.getID(),obj);
		return obj.getID();
	}

	@Override
	public IObject getObjectbyID(int ID) {



		return  Objs.get(ID);
	}

	@Override
	public IObject RemoveObject(int ID) {
		// TODO Auto-generated method stub
		
		IObject o =  Objs.get(ID);
		if (o != null)
			Objs.remove(o);
		return o;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub


			for (int i = 0; i < Objs.size(); i++) {
			IObject o = Objs.get(i);
			o.Update();
		}
		
		
	getCameraManager().getActualCamera().Update();
	}

	@Override
	public void AddLightList(List<ILight> obj) {
		for (ILight oo :obj) {
			AddLight(oo);
		}
	}


	@Override
	public void AddLight(ILight l) {
		// TODO Auto-generated method stub
		lights.add(l);
	}

	@Override
	public void RemoveLight(ILight l) {
		// TODO Auto-generated method stub
		lights.remove(l);
	}

	@Override
	public List<ILight> getLights() {
		// TODO Auto-generated method stub
		return lights;
	}

	
	@Override
	public SceneCameraManager getCameraManager() {
		// TODO Auto-generated method stub
		return camManager;
	}

	

}
