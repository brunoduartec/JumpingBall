package ploobs.plantevolution.Model.Model3D;

import ploobs.plantevolution.Material.Color;
import ploobs.plantevolution.Model.IModel;
import ploobs.plantevolution.Utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class BoxModel implements IModel
{
	protected Vertices _vertices;
	protected FacesBufferList _faces;

	//private Color[] _cols;
	private Color _color;
	float _scale;


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
		return _vertices.size();//  squareCoords.length;
	}

	public BoxModel(float scale)
	{
		_vertices = new Vertices(4*6,true,true,true);
		_faces = new FacesBufferList(2*6);


		_scale = scale;
		{
			//_cols = new Color[6];
			_color = Color.enumtoColor(Color.COLORNAME.WHITE);

		}

		make();
		

	}

	private void make()
	{
		float w = _scale / 2f;
		float h = _scale / 2f;
		float d = _scale / 2f;

		short ul, ur, lr, ll;

		// front
		ul = _vertices.addVertex(-w, +h, +d, 0.0f, 0.0f, 0, 0, 1,  _color.r,  _color.g, _color.b, _color.a);
		ur = _vertices.addVertex(+w, +h, +d, 0.0f, 1.0f, 0, 0, 1,  _color.r,  _color.g, _color.b, _color.a);
		lr = _vertices.addVertex(+w, -h, +d, 1.0f, 1.0f, 0, 0, 1,  _color.r,  _color.g, _color.b, _color.a);
		ll = _vertices.addVertex(-w, -h, +d, 1.0f, 0.0f, 0, 0, 1, _color.r,  _color.g, _color.b, _color.a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// right
		ul = _vertices.addVertex(+w, +h, +d, 0.0f, 0.0f, 1, 0, 0,  _color.r,  _color.g, _color.b, _color.a);
		ur = _vertices.addVertex(+w, +h, -d, 0.0f, 1.0f, 1, 0, 0,  _color.r,  _color.g, _color.b, _color.a);
		lr = _vertices.addVertex(+w, -h, -d, 1.0f, 1.0f, 1, 0, 0,  _color.r,  _color.g, _color.b, _color.a);
		ll = _vertices.addVertex(+w, -h, +d, 1.0f, 0.0f, 1, 0, 0,  _color.r,  _color.g, _color.b, _color.a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// back
		ul = _vertices.addVertex(+w, +h, -d, 0.0f, 0.0f, 0, 0, -1,  _color.r,  _color.g, _color.b, _color.a);
		ur = _vertices.addVertex(-w, +h, -d, 0.0f, 1.0f, 0, 0, -1,  _color.r,  _color.g, _color.b, _color.a);
		lr = _vertices.addVertex(-w, -h, -d, 1.0f, 1.0f, 0, 0, -1,  _color.r,  _color.g, _color.b, _color.a);
		ll = _vertices.addVertex(+w, -h, -d, 1.0f, 0.0f, 0, 0, -1,  _color.r,  _color.g, _color.b, _color.a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// left
		ul = _vertices.addVertex(-w, +h, -d, 0.0f, 0.0f, -1, 0, 0,  _color.r,  _color.g, _color.b, _color.a);
		ur = _vertices.addVertex(-w, +h, +d, 0.0f, 1.0f, -1, 0, 0, _color.r,  _color.g, _color.b, _color.a);
		lr = _vertices.addVertex(-w, -h, +d, 1.0f, 1.0f, -1, 0, 0, _color.r,  _color.g, _color.b, _color.a);
		ll = _vertices.addVertex(-w, -h, -d, 1.0f, 0.0f, -1, 0, 0,  _color.r,  _color.g, _color.b, _color.a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// top
		ul = _vertices.addVertex(-w, +h, -d, 0.0f, 0.0f, 0, 1, 0,  _color.r,  _color.g, _color.b, _color.a);
		ur = _vertices.addVertex(+w, +h, -d, 0.0f, 1.0f, 0, 1, 0,  _color.r,  _color.g, _color.b, _color.a);
		lr = _vertices.addVertex(+w, +h, +d, 1.0f, 1.0f, 0, 1, 0,  _color.r,  _color.g, _color.b, _color.a);
		ll = _vertices.addVertex(-w, +h, +d, 1.0f, 0.0f, 0, 1, 0,  _color.r,  _color.g, _color.b, _color.a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// bottom
		ul = _vertices.addVertex(-w, -h, +d, 0.0f, 0.0f, 0, -1, 0, _color.r,  _color.g, _color.b, _color.a);
		ur = _vertices.addVertex(+w, -h, +d, 0.0f, 1.0f, 0, -1, 0,  _color.r,  _color.g, _color.b, _color.a);
		lr = _vertices.addVertex(+w, -h, -d, 1.0f, 1.0f, 0, -1, 0,  _color.r,  _color.g, _color.b, _color.a);
		ll = _vertices.addVertex(-w, -h, -d, 1.0f, 0.0f, 0, -1, 0,  _color.r,  _color.g, _color.b, _color.a);
		Utils.addQuad(_faces, ul, ur, lr, ll);
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

	@Override
	public float[] getNormals() {
		// TODO Auto-generated method stub
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
