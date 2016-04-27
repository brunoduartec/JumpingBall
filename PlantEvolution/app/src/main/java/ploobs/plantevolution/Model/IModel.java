package ploobs.plantevolution.Model;

import java.nio.FloatBuffer;

import ploobs.plantevolution.Model.Model3D.FacesBufferList;
import ploobs.plantevolution.Model.Model3D.Vertices;

public interface IModel
{

    /** How many bytes per float. */
    int mBytesPerFloat = 4;
    // number of coordinates per vertex in this array
    int COORDS_PER_VERTEX = 3;

    Vertices getVertices();
    FacesBufferList getFaces();
    void setFaceBufferList(FacesBufferList faces);

    int getVerticesCount();



float[] getNormals();

FloatBuffer getVertexBuffer();
    void setVertices(Vertices vertices);
FloatBuffer getNormalsBuffer();
    void setNormalBuffer(FloatBuffer normals);

}