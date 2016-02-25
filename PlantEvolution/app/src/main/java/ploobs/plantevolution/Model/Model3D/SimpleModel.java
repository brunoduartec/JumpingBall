package ploobs.plantevolution.Model.Model3D;

import ploobs.plantevolution.Model.IModel;

import java.nio.FloatBuffer;


public class SimpleModel implements IModel
{

	protected FacesBufferList _faces;

	@Override
	public Vertices getVertices() {
		// TODO Auto-generated method stub
		return null;
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
		return 0;
	}

	@Override
	public FloatBuffer getVertexBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVertices(Vertices vertices) {

	}


	public void setVertexBuffer(FloatBuffer vertex) {

	}

	@Override
	public float[] getNormals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FloatBuffer getNormalsBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNormalBuffer(FloatBuffer normals) {

	}

}