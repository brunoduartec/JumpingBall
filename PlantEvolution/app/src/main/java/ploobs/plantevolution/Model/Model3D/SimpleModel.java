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
	public int getVerticesCount() {
		return 0;
	}

	@Override
	public FloatBuffer getVertexBuffer() {
		// TODO Auto-generated method stub
		return null;
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
	
}