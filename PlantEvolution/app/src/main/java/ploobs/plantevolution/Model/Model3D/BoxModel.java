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

	private Color[] _cols;
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
			_cols = new Color[6];
			/*_cols[0] = new Color(1.0f,0,0,		1.0f);
			_cols[1] = new Color(0,1.0f,0,		1.0f);
			_cols[2] = new Color(0,0,1.0f,		1.0f);
			_cols[3] = new Color(1.0f,1.0f,0,	1.0f);
			_cols[4] = new Color(0,1.0f,1.0f,	1.0f);
			_cols[5] = new Color(1.0f,0,1.0f,	1.0f);
*/

			_cols[0] = Color.enumtoColor(Color.COLORNAME.WHITE);
			_cols[1] = Color.enumtoColor(Color.COLORNAME.WHITE);
			_cols[2] = Color.enumtoColor(Color.COLORNAME.WHITE);
			_cols[3] = Color.enumtoColor(Color.COLORNAME.WHITE);
			_cols[4] = Color.enumtoColor(Color.COLORNAME.WHITE);
			_cols[5] = Color.enumtoColor(Color.COLORNAME.WHITE);
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
		ul = _vertices.addVertex(-w, +h, +d, 0f, 0f, 0, 0, 1,  _cols[0].r,  _cols[0].g, _cols[0].b, _cols[0].a);
		ur = _vertices.addVertex(+w, +h, +d, 1f, 0f, 0, 0, 1,  _cols[0].r,  _cols[0].g, _cols[0].b, _cols[0].a);
		lr = _vertices.addVertex(+w, -h, +d, 1f, 1f, 0, 0, 1,  _cols[0].r,  _cols[0].g, _cols[0].b, _cols[0].a);
		ll = _vertices.addVertex(-w, -h, +d, 0f, 1f, 0, 0, 1, _cols[0].r,  _cols[0].g, _cols[0].b, _cols[0].a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// right
		ul = _vertices.addVertex(+w, +h, +d, 0f, 0f, 1, 0, 0,  _cols[1].r,  _cols[1].g, _cols[1].b, _cols[1].a);
		ur = _vertices.addVertex(+w, +h, -d, 1f, 0f, 1, 0, 0,  _cols[1].r,  _cols[1].g, _cols[1].b, _cols[1].a);
		lr = _vertices.addVertex(+w, -h, -d, 1f, 1f, 1, 0, 0,  _cols[1].r,  _cols[1].g, _cols[1].b, _cols[1].a);
		ll = _vertices.addVertex(+w, -h, +d, 0f, 1f, 1, 0, 0,  _cols[1].r,  _cols[1].g, _cols[1].b, _cols[1].a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// back
		ul = _vertices.addVertex(+w, +h, -d, 0f, 0f, 0, 0, -1,  _cols[2].r,  _cols[2].g, _cols[2].b, _cols[2].a);
		ur = _vertices.addVertex(-w, +h, -d, 1f, 0f, 0, 0, -1,  _cols[2].r,  _cols[2].g, _cols[2].b, _cols[2].a);
		lr = _vertices.addVertex(-w, -h, -d, 1f, 1f, 0, 0, -1,  _cols[2].r,  _cols[2].g, _cols[2].b, _cols[2].a);
		ll = _vertices.addVertex(+w, -h, -d, 0f, 1f, 0, 0, -1,  _cols[2].r,  _cols[2].g, _cols[2].b, _cols[2].a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// left
		ul = _vertices.addVertex(-w, +h, -d, 0f, 0f, -1, 0, 0,  _cols[3].r,  _cols[3].g, _cols[3].b, _cols[3].a);
		ur = _vertices.addVertex(-w, +h, +d, 1f, 0f, -1, 0, 0, _cols[3].r,  _cols[3].g, _cols[3].b, _cols[3].a);
		lr = _vertices.addVertex(-w, -h, +d, 1f, 1f, -1, 0, 0, _cols[3].r,  _cols[3].g, _cols[3].b, _cols[3].a);
		ll = _vertices.addVertex(-w, -h, -d, 0f, 1f, -1, 0, 0,  _cols[3].r,  _cols[3].g, _cols[3].b, _cols[3].a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// top
		ul = _vertices.addVertex(-w, +h, -d, 0f, 0f, 0, 1, 0,  _cols[4].r,  _cols[4].g, _cols[4].b, _cols[4].a);
		ur = _vertices.addVertex(+w, +h, -d, 1f, 0f, 0, 1, 0,  _cols[4].r,  _cols[4].g, _cols[4].b, _cols[4].a);
		lr = _vertices.addVertex(+w, +h, +d, 1f, 1f, 0, 1, 0,  _cols[4].r,  _cols[4].g, _cols[4].b, _cols[4].a);
		ll = _vertices.addVertex(-w, +h, +d, 0f, 1f, 0, 1, 0,  _cols[4].r,  _cols[4].g, _cols[4].b, _cols[4].a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// bottom
		ul = _vertices.addVertex(-w, -h, +d, 0f, 0f, 0, -1, 0, _cols[5].r,  _cols[5].g, _cols[5].b, _cols[5].a);
		ur = _vertices.addVertex(+w, -h, +d, 1f, 0f, 0, -1, 0,  _cols[5].r,  _cols[5].g, _cols[5].b, _cols[5].a);
		lr = _vertices.addVertex(+w, -h, -d, 1f, 1f, 0, -1, 0,  _cols[5].r,  _cols[5].g, _cols[5].b, _cols[5].a);
		ll = _vertices.addVertex(-w, -h, -d, 0f, 1f, 0, -1, 0,  _cols[5].r,  _cols[5].g, _cols[5].b, _cols[5].a);
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
