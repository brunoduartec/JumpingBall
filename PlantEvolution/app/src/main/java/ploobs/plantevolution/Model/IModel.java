package ploobs.plantevolution.Model;

import java.nio.FloatBuffer;

public interface IModel
{

    /** How many bytes per float. */
    public final int mBytesPerFloat = 4;
    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;

float[] getVertices();
float[] getNormals();

FloatBuffer getVertexBuffer();
FloatBuffer getNormalsBuffer();

}