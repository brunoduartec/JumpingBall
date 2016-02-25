package ploobs.plantevolution.Model.Model2D;

import ploobs.plantevolution.Model.IModel;
import ploobs.plantevolution.Model.Model3D.FacesBufferList;
import ploobs.plantevolution.Model.Model3D.Vertices;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Bruno on 23/11/2015.
 */
public class SquareModel implements IModel {

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;
    //private int mProgram;
    //private int mPositionHandle;
    //private int mColorHandle;
    //private int mMVPMatrixHandle;

    private float[] squareCoords;
    float size;



    float color[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };




    private void calculateSquareCoords(float size)
    {


        float squareTemp[] = {
                0.0f,  0.0f, 0.0f,   // top left
                0.0f, -size, 0.0f,   // bottom left
                size, -size, 0.0f,   // bottom right
                size, 0.0f, 0.0f }; // top right

        // Front face


      /*
        float squareTemp[] = {
                -size, size, size,
                -size, -size, size,
                size, size, size,
                -size, -size, size,
                size, -size, size,
                size, size, size,
        };
*/

        squareCoords = squareTemp;

    }


    public SquareModel(float scale)
    {


        calculateSquareCoords(scale);


        vertexBuffer = ByteBuffer.allocateDirect(squareCoords.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexBuffer.put(squareCoords).position(0);




    }
    @Override
    public Vertices getVertices() {
        return  null;
    }

    @Override
    public FacesBufferList getFaces() {
        return null;
    }

    @Override
    public void setFaceBufferList(FacesBufferList faces) {

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
        return vertexBuffer;
    }

    @Override
    public void setVertices(Vertices vertices) {

    }

    @Override
    public FloatBuffer getNormalsBuffer() {
        return null;
    }

    @Override
    public void setNormalBuffer(FloatBuffer normals) {

    }


}
