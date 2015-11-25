package ploobs.plantevolution.Camera;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bruno on 28/09/2015.
 */
public class SceneCameraManager {



    ICamera actualCamera;
    private Map<String,ICamera> cams = new HashMap<String,ICamera>();

public SceneCameraManager(){}

   /*
    private static class SingletonHolder {
        private static final SceneCameraManager INSTANCE = new SceneCameraManager();
    }
    public static SceneCameraManager getInstance() {
        return SingletonHolder.INSTANCE;
    }
*/

    public ICamera GetCamerabyName(String name)
    {
        return cams.get(name);
    }
    public void addCamera(ICamera cam)
    {
        cams.put(cam.getName(),cam);

        if (cams.size() == 1)
            actualCamera = cam;
    }

    public void setActualCamera(String name)
    {
        actualCamera = cams.get(name);

    }

    public ICamera getActualCamera()
    {
        return actualCamera;
    }


}
