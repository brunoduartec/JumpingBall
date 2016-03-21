package ploobs.plantevolution.Model.Model3D;

import ploobs.plantevolution.Model.IModel;

import java.nio.FloatBuffer;


public class SimpleModel implements IModel
{

	protected FacesBufferList _faces;
	protected Vertices _vertices;

	@Override
	public Vertices getVertices() {
		// TODO Auto-generated method stub
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
		return 0;
	}

	@Override
	public FloatBuffer getVertexBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVertices(Vertices vertices) {
this._vertices = vertices;
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