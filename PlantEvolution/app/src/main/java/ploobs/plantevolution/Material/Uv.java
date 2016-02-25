package ploobs.plantevolution.Material;

/**
 * Created by Bruno on 16/02/2016.
 */
public class Uv {
    public float u;
    public float v;

    public Uv()
    {
        u=0;
        v=0;
    }
    public Uv(float u, float v)
    {
        this.u = u;
        this.v = v;

    }
    public Uv clone()
    {
        return new Uv(u, v);
    }


}
