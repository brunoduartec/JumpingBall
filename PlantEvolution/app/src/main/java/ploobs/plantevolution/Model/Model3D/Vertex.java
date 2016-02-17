package ploobs.plantevolution.Model.Model3D;

import ploobs.plantevolution.Material.Color;
import ploobs.plantevolution.Material.Uv;
import ploobs.plantevolution.Math.Vector3;

/**
 * Created by Bruno on 16/02/2016.
 */
public class Vertex {
    public Vector3  position = new Vector3();
    public Uv uv;
    public Vector3 normal;
    public Color color;

    public Vertex(){}

}
