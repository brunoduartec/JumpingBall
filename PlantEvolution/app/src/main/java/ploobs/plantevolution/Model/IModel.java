package ploobs.plantevolution.Model;

import java.nio.FloatBuffer;

import ploobs.plantevolution.Model.Model3D.FacesBufferList;
import ploobs.plantevolution.Model.Model3D.Vertices;

public interface IModel
{

    /** How many bytes per float. */
    public final int mBytesPerFloat = 4;
    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;

    Vertices getVertices();
    FacesBufferList getFaces();

    int getVerticesCount();



float[] getNormals();

FloatBuffer getVertexBuffer();
FloatBuffer getNormalsBuffer();

}