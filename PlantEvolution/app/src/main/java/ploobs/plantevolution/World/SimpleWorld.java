package ploobs.plantevolution.World;

import java.io.IOError;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ploobs.plantevolution.Camera.SceneCameraManager;
import ploobs.plantevolution.Light.ILight;


class DepthComparator implements Comparator<IObject>
{
	@Override
	public int compare(IObject lhs, IObject rhs) {

		float Z1 = lhs.getPosition()[2]*10000;
		float Z2 = rhs.getPosition()[2]*10000;
		int ret = (int)(Z1-Z2);

		return ret;
	}
}
public class SimpleWorld implements IWorld {


	private SceneCameraManager camManager;
	private Map<Integer, IObject> Objs = new HashMap<>();
	private List<ILight> lights = new LinkedList<ILight>();


	@Override
	//public Map<Integer,IObject> getObjectsList() {
	public List<IObject> getObjectsList() {
		// TODO Auto-generated method stub
		Collection<IObject> a = Objs.values();
		List<IObject> ret = new ArrayList<>();
		ret.addAll(a);

		Collections.sort(ret, new DepthComparator());

		return ret;
	}


	@Override
	public Map<Integer,IObject> getObjectsMap() {

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

		List<IObject> ot =  getObjectsList();


			for (int i = 0; i < ot.size(); i++) {
			IObject o = ot.get(i);
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
