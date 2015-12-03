package ploobs.plantevolution.Model.Model2D;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import ploobs.plantevolution.Model.IModel;

/**
 * Created by Bruno on 23/11/2015.
 */
public class RectangleModel implements IModel {

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;


    private float[] squareCoords;
    float size;



    float color[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };




    private void calculateSquareCoords(float width, float height)
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


        calculateSquareCoords(width,height);


        vertexBuffer = ByteBuffer.allocateDirect(squareCoords.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexBuffer.put(squareCoords).position(0);




    }
    @Override
    public float[] getVertices() {
        return  squareCoords;
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
    public FloatBuffer getNormalsBuffer() {
        return null;
    }


}
