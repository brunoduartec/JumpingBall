package ploobs.plantevolution.Material;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Set;

import ploobs.plantevolution.Utils;

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
     * 'Uploads' a texture via OpenGL which is mapped to a textureId to the TextureManager,
     * which can subsequently be used to assign textures to Object3d's.
     *
     * @return The textureId as added to TextureManager, which is identical to $id
     */
    public String addTextureId(Bitmap $b, String $id, boolean $generateMipMap)
    {
        if (_idToTextureName.containsKey($id)) throw new Error("Texture id \"" + $id + "\" already exists.");

        int glId = Utils.uploadTextureAndReturnId($b, $generateMipMap);

        String s = $id;
        _idToTextureName.put(s, glId);
        _idToHasMipMap.put(s, $generateMipMap);

        _counter++;

        // For debugging purposes (potentially adds a lot of chatter)
        // logContents();

        return s;
    }
    private TextureManager()
    {
        _idToTextureName = new HashMap<>();
        _idToHasMipMap = new HashMap<>();
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

    public String getNewAtlasId() {
        return "atlas".concat(Integer.toString(_atlasId++));
    }

    int getGlTextureId(String textureId) /*package-private*/
    {
        return _idToTextureName.get(textureId);
    }

}
