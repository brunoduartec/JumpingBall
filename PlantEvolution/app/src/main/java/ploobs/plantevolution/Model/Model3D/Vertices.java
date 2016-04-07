package ploobs.plantevolution.Model.Model3D;

import ploobs.plantevolution.Material.Color;
import ploobs.plantevolution.Material.ColorBufferList;
import ploobs.plantevolution.Material.Uv;
import ploobs.plantevolution.Material.UvBufferList;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.Math.Vector3BufferList;

/**
 * Created by Bruno on 16/02/2016.
 */

public class Vertices
{
    private Vector3BufferList _points;
    private UvBufferList _uvs;
    private Vector3BufferList _normals;
    private ColorBufferList _colors;

    private boolean _hasUvs;
    private boolean _hasNormals;
    private boolean _hasColors;


    /**
     * Used by Object3d to hold the lists of vertex points, texture coordinates (UV), normals, and vertex colors.
     * Use "addVertex()" to build the vertex data for the Object3d instance associated with this instance.
     *
     * Direct manipulation of position, UV, normal, or color data can be done directly through the associated
     * 'buffer list' instances contained herein.
     */
    public Vertices(int maxElements)
    {
        _points = new Vector3BufferList(maxElements);

        _hasUvs = true;
        _hasNormals = true;
        _hasColors = true;

        if (_hasUvs) _uvs = new UvBufferList(maxElements);
        if (_hasNormals) _normals = new Vector3BufferList(maxElements);
        if (_hasColors) _colors = new ColorBufferList(maxElements);
    }

    /**
     * This version of the constructor adds 3 boolean arguments determine whether
     * uv, normal, and color lists will be used by this instance.
     * Set to false when appropriate to save memory, increase performance.
     */
    public Vertices(int maxElements, Boolean useUvs, Boolean useNormals, Boolean useColors)
    {
        _points = new Vector3BufferList(maxElements);

        _hasUvs = useUvs;
        _hasNormals = useNormals;
        _hasColors = useColors;

        if (_hasUvs) _uvs = new UvBufferList(maxElements);
        if (_hasNormals) _normals = new Vector3BufferList(maxElements);
        if (_hasColors) _colors = new ColorBufferList(maxElements);
    }

    public Vertices(Vector3BufferList points, UvBufferList uvs, Vector3BufferList normals,
                    ColorBufferList colors)
    {
        _points = points;
        _uvs = uvs;
        _normals = normals;
        _colors = colors;

        _hasUvs = _uvs != null && _uvs.size() > 0;
        _hasNormals = _normals != null && _normals.size() > 0;
        _hasColors = _colors != null && _colors.size() > 0;
    }

    public int size()
    {
        return _points.size();
    }

    public int capacity()
    {
        return _points.capacity();
    }

    public boolean hasUvs()
    {
        return _hasUvs;
    }

    public boolean hasNormals()
    {
        return _hasNormals;
    }

    public boolean hasColors()
    {
        return _hasColors;
    }


    /**
     * Use this to populate an Object3d's vertex data.
     * Return's newly added vertex's index
     *
     *  	If hasUvs, hasNormals, or hasColors was set to false,
     * 		their corresponding arguments are just ignored.
     */
    public short addVertex(
            float pointX, float pointY, float pointZ,
            float textureU, float textureV,
            float normalX, float normalY, float normalZ,
          //  float colorR, float colorG, float colorB, float colorA)
            short colorR, short colorG, short colorB, short colorA)
    {
        _points.add(pointX, pointY, pointZ);

        if (_hasUvs) _uvs.add(textureU, textureV);
        if (_hasNormals) _normals.add(normalX, normalY, normalZ);
        if (_hasColors) _colors.add(colorR, colorG, colorB, colorA);

        return (short)(_points.size()-1);
    }

    /**
     * More structured-looking way of adding a vertex (but potentially wasteful).
     * The VO's taken in as arguments are only used to read the values they hold
     * (no references are kept to them).
     * Return's newly added vertex's index
     *
     * 		If hasUvs, hasNormals, or hasColors was set to false,
     * 		their corresponding arguments are just ignored.
     */
    public short addVertex(Vector3 point, Uv textureUv, Vector3 normal, Color color)
    {
        _points.add(point);

        if (_hasUvs) _uvs.add(textureUv);
        if (_hasNormals) _normals.add(normal);
        if (_hasColors) _colors.add(color);

        return (short)(_points.size()-1);
    }




    public void overwriteVerts(float[] newVerts)
    {
        _points.overwrite(newVerts);
    }

    public void overwriteNormals(float[] newNormals)
    {
        _normals.overwrite(newNormals);
    }

    public Vector3BufferList points() /*package-private*/
    {
        return _points;
    }

    /**
     * List of texture coordinates
     */
    public UvBufferList uvs() /*package-private*/
    {
        return _uvs;
    }

    /**
     * List of normal values
     */
    Vector3BufferList normals() /*package-private*/
    {
        return _normals;
    }

    /**
     * List of color values
     */
    public ColorBufferList colors() /*package-private*/
    {
        return _colors;
    }

    public Vertices clone()
    {
        Vertices v = new Vertices(_points.clone(), _uvs.clone(), _normals.clone(), _colors.clone());
        return v;
    }
}