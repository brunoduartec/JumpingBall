package ploobs.plantevolution.Model.Model3D;

import ploobs.plantevolution.Model.IModel;

import java.nio.FloatBuffer;


public class SimpleModel implements IModel
{

	protected FacesBufferList _faces;
	protected Vertices _vertices;

	public SimpleModel(int vertices,int faces)
	{

		_vertices = new Vertices(vertices,true,true,true);//new Vertices(4*6,true,true,true);
		_faces = new FacesBufferList(faces);
	}


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
		return _vertices.points().buffer();
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
		//return _vertices.normals().;
		return null;
	}

	@Override
	public FloatBuffer getNormalsBuffer() {
		// TODO Auto-generated method stub
		return _vertices.normals().buffer();
	}

	@Override
	public void setNormalBuffer(FloatBuffer normals) {
		this._vertices.overwriteNormals(normals.array());
	}

}