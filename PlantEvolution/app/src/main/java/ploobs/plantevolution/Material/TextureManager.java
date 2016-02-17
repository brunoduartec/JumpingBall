package ploobs.plantevolution.Material;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Bruno on 16/02/2016.
 */
public class TextureManager {

    private HashMap<String, Integer> _idToTextureName;
    private HashMap<String, Boolean> _idToHasMipMap;
    private static int _counter = 1000001;
    private static int _atlasId = 0;

    public boolean contains(String textureId) {
        return _idToTextureName.containsKey(textureId);
    }


    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class SingletonHolder {
        private static final TextureManager INSTANCE = new TextureManager();
    }

    public static TextureManager getInstance() {
        return SingletonHolder.INSTANCE;
    }





}
