package ploobs.plantevolution.Model.Model2D;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import ploobs.plantevolution.Model.IModel;
import ploobs.plantevolution.Model.Model3D.FacesBufferList;
import ploobs.plantevolution.Model.Model3D.Vertices;
import ploobs.plantevolution.Utils;

/**
 * Created by Bruno on 23/11/2015.
 */
public class RectangleModel implements IModel {

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;

    protected Vertices _vertices;
    protected FacesBufferList _faces;


    private float[] squareCoords;
    float size;



    short color[] = {(short) 0.2, (short) 0.709803922, (short) 0.898039216, (short) 1.0};




    private void calculateSquareCoords(float width, float height)
    {

        short ul, ur, lr, ll;
                            //      x   y   z               u   v       n       r       g       b           a
        ul = _vertices.addVertex(0.0f,0.0f,0.0f,           0.0f,0.0f,  0,0,0,  color[0],color[1],color[2],color[3]);
        ll = _vertices.addVertex(0.0f, -height,0.0f,       0.0f,1.0f,  0,0,0,  color[0],color[1],color[2],color[3]);
        lr = _vertices.addVertex( width, -height,0.0f,     1.0f,1.0f,  0,0,0,  color[0],color[1],color[2],color[3]);
        ur = _vertices.addVertex(width, 0.0f, 0.0f,        1.0f,0.0f,  0,0,0,  color[0],color[1],color[2],color[3]);

        Utils.addQuad(_faces, ul, ur, lr, ll);


    }

    private void calculateSquareCoordsOld(float width, float height)
    {


        float squareTemp[] = {
                0.0f,  0.0f, 0.0f,   // top left
                0.0f, -height, 0.0f,   // bottom left
                width, -height, 0.0f,   // bottom right
                width, 0.0f, 0.0f }; // top right

        // Front face



        squareCoords = squareTemp;

    }


    public RectangleModel(float width, float height)
    {

        _vertices = new Vertices(4,false,false,false);
        _faces = new FacesBufferList(2);

        calculateSquareCoords(width,height);
        calculateSquareCoordsOld(width, height);

        vertexBuffer = ByteBuffer.allocateDirect(squareCoords.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexBuffer.put(squareCoords).position(0);
     //   vertexBuffer.put(squareCoords);


    }
    @Override
    public Vertices getVertices() {
        return  this._vertices;
    }

    @Override
    public FacesBufferList getFaces() {
        return _faces;
    }

    @Override
    public void setFaceBufferList(FacesBufferList faces) {

    }

    @Override
    public int getVerticesCount() {

        int a = _vertices.size();
        int b = squareCoords.length;
        return _vertices.size();
       // return squareCoords.length;
    }

    @Override
    public float[] getNormals() {
        return new float[0];
    }

    @Override
    public FloatBuffer getVertexBuffer() {
     //   return vertexBuffer;
        return _vertices.points().buffer();
    }

    @Override
    public void setVertices(Vertices vertices) {

    }

    @Override
    public FloatBuffer getNormalsBuffer() {
        // TODO Auto-generated method stub
        return null;//_vertices.normals().buffer();
    }

    @Override
    public void setNormalBuffer(FloatBuffer normals) {

    }


}
