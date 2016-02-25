package ploobs.plantevolution.Model.Model3D;

import ploobs.plantevolution.Material.Color;
import ploobs.plantevolution.Model.IModel;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.Utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Bruno on 28/10/2015.
 */
public class SphereModel implements IModel {


   protected Vertices _vertices;
    protected FacesBufferList _faces;

    private  float _radius;
    int _rows,_cols;

    Color defaultColor;



    public SphereModel(float radius, int columns, int rows)
    {
        defaultColor =  Color.enumtoColor(Color.COLORNAME.WHITE);

        this._radius = radius/2;
        this._cols = columns;
        this._rows = rows;

        _vertices = new Vertices((columns+1)*(rows+1),true,true,true);
        _faces = new FacesBufferList(columns*rows*2);

        calculateSphereCoords();

    }

    @Override
    public Vertices getVertices() {
        return _vertices;

    }

    @Override
    public FacesBufferList getFaces() {
        return _faces;
    }

    @Override
    public void setFaceBufferList(FacesBufferList faces) {
        this._faces = faces;
    }


    @Override
    public int getVerticesCount() {
        return _vertices.size();
    }

    @Override
    public float[] getNormals() {
        return new float[0];
    }

    @Override
    public FloatBuffer getVertexBuffer() {
        // TODO Auto-generated method stub
        return _vertices.points().buffer();
    }

    @Override
    public void setVertices(Vertices vertices) {
this._vertices = vertices;
    }

    @Override
    public FloatBuffer getNormalsBuffer() {
        // TODO Auto-generated method stub
        return _vertices.normals().buffer();
    }

    @Override
    public void setNormalBuffer(FloatBuffer normals) {
        this._vertices.overwriteNormals(normals.array());
    }


    private void calculateSphereCoords()
    {

        int r, c;

        Vector3 n = new Vector3();
        Vector3 pos = new Vector3();
        Vector3 posFull = new Vector3();


        if( defaultColor == null )
            defaultColor = Color.enumtoColor(Color.COLORNAME.WHITE);

        // Build vertices

        for (r = 0; r <= _rows; r++)
        {
            float v = (float)r / (float)_rows; // [0,1]
            float theta1 = v * (float)Math.PI; // [0,PI]

            n.set(0, 1, 0);
            n.rotateZ(theta1);

            // each 'row' assigned random color. for the hell of it.

            for (c = 0; c <= _cols; c++)
            {
                float u = (float)c / (float)_cols; // [0,1]
                float theta2 = u * (float)(Math.PI * 2f); // [0,2PI]
                pos = n;
               pos.rotateY(theta2);

                posFull = pos;
                posFull = posFull.mul(_radius);

               _vertices.addVertex(posFull.getX(), posFull.getY(), posFull.getZ(), u, v, pos.getX(), pos.getY(), pos.getZ(), defaultColor.r, defaultColor.g, defaultColor.b, defaultColor.a);
            }
        }
        // Add faces

        int colLength = _cols + 1;

        for (r = 0; r < _rows; r++)
        {
            int offset = r * colLength;

            for (c = 0; c < _cols; c++)
            {
                int ul = offset  +  c;
                int ur = offset  +  c+1;
                int br = offset  +  (int)(c + 1 + colLength);
                int bl = offset  +  (int)(c + 0 + colLength);

                Utils.addQuad(_faces, ul, ur, br, bl);
            }
        }

    }


}
