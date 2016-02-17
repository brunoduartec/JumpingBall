package ploobs.plantevolution.Model.Model2D;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import ploobs.plantevolution.Model.IModel;
import ploobs.plantevolution.Model.Model3D.FacesBufferList;
import ploobs.plantevolution.Model.Model3D.Vertices;

/**
 * Created by Bruno on 23/11/2015.
 */
public class RectangleModel implements IModel {

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;
    protected Vertices _vertices;

    private float[] squareCoords;
    float size;



    short color[] = {(short) 0.2, (short) 0.709803922, (short) 0.898039216, (short) 1.0};




    private void calculateSquareCoords(float width, float height)
    {

        this.getVertices().addVertex(0.0f,0.0f,0.0f,0.0f,0.0f,0,0,0,color[0],color[1],color[2],color[3]);
        this.getVertices().addVertex(0.0f, -height, 0.0f,0.0f,1.0f,0,0,0,color[0],color[1],color[2],color[3]);
        this.getVertices().addVertex( width, -height, 0.0f, 1.0f,1.0f,0,0,0,color[0],color[1],color[2],color[3]);
        this.getVertices().addVertex(width, 0.0f, 0.0f,1.0f,0.0f,0,0,0,color[0],color[1],color[2],color[3]);

    }


    public RectangleModel(float width, float height)
    {


        calculateSquareCoords(width,height);




    }
    @Override
    public Vertices getVertices() {
        return  this._vertices;
    }

    @Override
    public FacesBufferList getFaces() {
        return null;
    }

    @Override
    public int getVerticesCount() {
        return 0;
    }

    @Override
    public float[] getNormals() {
        return new float[0];
    }

    @Override
    public FloatBuffer getVertexBuffer() {
       // return vertexBuffer;
        return this.getVertices().points().buffer();
    }

    @Override
    public FloatBuffer getNormalsBuffer() {
        return null;
    }


}
