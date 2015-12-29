package ploobs.plantevolution.World;

import java.util.List;
import java.util.Map;

import ploobs.plantevolution.Camera.SceneCameraManager;
import ploobs.plantevolution.Light.ILight;

public interface IWorld {
	
	Map getObjectsMap();
	List getObjectsList();

	void Initialize();


	void AddObjectList(List<IObject> obj);
	int AddObject(IObject obj);
	IObject getObjectbyID(int ID);
	IObject RemoveObject(int ID);
	void CleanObjects();
	void Update();

	void AddLightList(List<ILight> obj);
	void AddLight(ILight l);
	void RemoveLight(ILight l);
	List<ILight> getLights();
	void CleanLights();



	SceneCameraManager getCameraManager();


}
