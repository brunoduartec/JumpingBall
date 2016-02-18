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

	float[] squareCoords;

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
	public int getVerticesCount() {
		return _vertices.size();//  squareCoords.length;
	}

	public BoxModel(float scale)
	{
		_vertices = new Vertices(4*6,false,false,false);
		_faces = new FacesBufferList(2*6);


		_scale = scale;
		{
			_cols = new Color[6];
			_cols[0] = new Color(255,0,0,255);
			_cols[1] = new Color(0,255,0,255);
			_cols[2] = new Color(0,0,255,255);
			_cols[3] = new Color(255,255,0,255);
			_cols[4] = new Color(0,255,255,255);
			_cols[5] = new Color(255,0,255,255);

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
		ul = _vertices.addVertex(-w, +h, +d, 0f, 0f, 0, 0, 1, (short) _cols[0].r, (short) _cols[0].g, (short)_cols[0].b,(short) _cols[0].a);
		ur = _vertices.addVertex(+w, +h, +d, 1f, 0f, 0, 0, 1, (short) _cols[0].r, (short) _cols[0].g, (short)_cols[0].b,(short) _cols[0].a);
		lr = _vertices.addVertex(+w, -h, +d, 1f, 1f, 0, 0, 1, (short) _cols[0].r, (short) _cols[0].g, (short)_cols[0].b,(short) _cols[0].a);
		ll = _vertices.addVertex(-w, -h, +d, 0f, 1f, 0, 0, 1,(short) _cols[0].r, (short) _cols[0].g, (short)_cols[0].b,(short) _cols[0].a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// right
		ul = _vertices.addVertex(+w, +h, +d, 0f, 0f, 1, 0, 0, (short) _cols[1].r, (short) _cols[1].g, (short)_cols[1].b,(short) _cols[1].a);
		ur = _vertices.addVertex(+w, +h, -d, 1f, 0f, 1, 0, 0, (short) _cols[1].r, (short) _cols[1].g, (short)_cols[1].b,(short) _cols[1].a);
		lr = _vertices.addVertex(+w, -h, -d, 1f, 1f, 1, 0, 0, (short) _cols[1].r, (short) _cols[1].g, (short)_cols[1].b,(short) _cols[1].a);
		ll = _vertices.addVertex(+w, -h, +d, 0f, 1f, 1, 0, 0, (short) _cols[1].r, (short) _cols[1].g, (short)_cols[1].b,(short) _cols[1].a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// back
		ul = _vertices.addVertex(+w, +h, -d, 0f, 0f, 0, 0, -1, (short) _cols[2].r, (short) _cols[2].g, (short)_cols[2].b,(short) _cols[2].a);
		ur = _vertices.addVertex(-w, +h, -d, 1f, 0f, 0, 0, -1, (short) _cols[2].r, (short) _cols[2].g, (short)_cols[2].b,(short) _cols[2].a);
		lr = _vertices.addVertex(-w, -h, -d, 1f, 1f, 0, 0, -1, (short) _cols[2].r, (short) _cols[2].g, (short)_cols[2].b,(short) _cols[2].a);
		ll = _vertices.addVertex(+w, -h, -d, 0f, 1f, 0, 0, -1, (short) _cols[2].r, (short) _cols[2].g, (short)_cols[2].b,(short) _cols[2].a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// left
		ul = _vertices.addVertex(-w, +h, -d, 0f, 0f, -1, 0, 0, (short) _cols[3].r, (short) _cols[3].g, (short)_cols[3].b,(short) _cols[3].a);
		ur = _vertices.addVertex(-w, +h, +d, 1f, 0f, -1, 0, 0,(short) _cols[3].r, (short) _cols[3].g, (short)_cols[3].b,(short) _cols[3].a);
		lr = _vertices.addVertex(-w, -h, +d, 1f, 1f, -1, 0, 0,(short) _cols[3].r, (short) _cols[3].g, (short)_cols[3].b,(short) _cols[3].a);
		ll = _vertices.addVertex(-w, -h, -d, 0f, 1f, -1, 0, 0, (short) _cols[3].r, (short) _cols[3].g, (short)_cols[3].b,(short) _cols[3].a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// top
		ul = _vertices.addVertex(-w, +h, -d, 0f, 0f, 0, 1, 0, (short) _cols[4].r, (short) _cols[4].g, (short)_cols[4].b,(short) _cols[4].a);
		ur = _vertices.addVertex(+w, +h, -d, 1f, 0f, 0, 1, 0, (short) _cols[4].r, (short) _cols[4].g, (short)_cols[4].b,(short) _cols[4].a);
		lr = _vertices.addVertex(+w, +h, +d, 1f, 1f, 0, 1, 0, (short) _cols[4].r, (short) _cols[4].g, (short)_cols[4].b,(short) _cols[4].a);
		ll = _vertices.addVertex(-w, +h, +d, 0f, 1f, 0, 1, 0, (short) _cols[4].r, (short) _cols[4].g, (short)_cols[4].b,(short) _cols[4].a);
		Utils.addQuad(_faces, ul,ur,lr,ll);

		// bottom
		ul = _vertices.addVertex(-w, -h, +d, 0f, 0f, 0, -1, 0,(short) _cols[5].r, (short) _cols[5].g, (short)_cols[5].b,(short) _cols[5].a);
		ur = _vertices.addVertex(+w, -h, +d, 1f, 0f, 0, -1, 0, (short) _cols[5].r, (short) _cols[5].g, (short)_cols[5].b,(short) _cols[5].a);
		lr = _vertices.addVertex(+w, -h, -d, 1f, 1f, 0, -1, 0, (short) _cols[5].r, (short) _cols[5].g, (short)_cols[5].b,(short) _cols[5].a);
		ll = _vertices.addVertex(-w, -h, -d, 0f, 1f, 0, -1, 0, (short) _cols[5].r, (short) _cols[5].g, (short)_cols[5].b,(short) _cols[5].a);
		Utils.addQuad(_faces, ul, ur, lr, ll);
	}
	private void calculateSquareCoords(float size)
    {

		// fixing the box size to have
    	size = size/2;
       	
    	 float squareTemp[] = {  // Vertices of the 6 faces
				 // In OpenGL counter-clockwise winding is default. This means that when we look at a triangle,
				 // if the points are counter-clockwise we are looking at the "front". If not we are looking at
				 // the back. OpenGL has an optimization where all back-facing triangles are culled, since they
				 // usually represent the backside of an object and aren't visible anyways.

				 // Front face
				 -size, size, size,
				 -size, -size, size,
				 size, size, size,
				 -size, -size, size,
				 size, -size, size,
				 size, size, size,

				 // Right face
				 size, size, size,
				 size, -size, size,
				 size, size, -size,
				 size, -size, size,
				 size, -size, -size,
				 size, size, -size,

				 // Back face
				 size, size, -size,
				 size, -size, -size,
				 -size, size, -size,
				 size, -size, -size,
				 -size, -size, -size,
				 -size, size, -size,

				 // Left face
				 -size, size, -size,
				 -size, -size, -size,
				 -size, size, size,
				 -size, -size, -size,
				 -size, -size, size,
				 -size, size, size,

				 // Top face
				 -size, size, -size,
				 -size, size, size,
				 size, size, -size,
				 -size, size, size,
				 size, size, size,
				 size, size, -size,

				 // Bottom face
				 size, -size, -size,
				 size, -size, size,
				 -size, -size, -size,
				 size, -size, size,
				 -size, -size, size,
				 -size, -size, -size,
    		   };
    	squareCoords = squareTemp;
     	 	
    	
    	
    }

	@Override
	public FloatBuffer getVertexBuffer() {
		// TODO Auto-generated method stub
		return _vertices.points().buffer();
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

	
	

}
