package ploobs.plantevolution.Model.Model3D;

import ploobs.plantevolution.Model.IModel;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class BoxModel implements IModel
{

	float[] squareCoords;
	private final FloatBuffer vertexBuffer;
	private final FloatBuffer mCubeNormals;
	


	  /** Size of the normal data in elements. */
		private final int mNormalDataSize = 3;
		
		/** How many bytes per float. */
		private final int mBytesPerFloat = 4;	
		// X, Y, Z
		// The normal is used in light calculations and is a vector which points
		// orthogonal to the plane of the surface. For a cube model, the normals
		// should be orthogonal to the points of each face.
		final float[] cubeNormalData =
		{												
				// Front face
				0.0f, 0.0f, 1.0f,				
				0.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f,				
				0.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f,
				
				// Right face 
				1.0f, 0.0f, 0.0f,				
				1.0f, 0.0f, 0.0f,
				1.0f, 0.0f, 0.0f,
				1.0f, 0.0f, 0.0f,				
				1.0f, 0.0f, 0.0f,
				1.0f, 0.0f, 0.0f,
				
				// Back face 
				0.0f, 0.0f, -1.0f,				
				0.0f, 0.0f, -1.0f,
				0.0f, 0.0f, -1.0f,
				0.0f, 0.0f, -1.0f,				
				0.0f, 0.0f, -1.0f,
				0.0f, 0.0f, -1.0f,
				
				// Left face 
				-1.0f, 0.0f, 0.0f,				
				-1.0f, 0.0f, 0.0f,
				-1.0f, 0.0f, 0.0f,
				-1.0f, 0.0f, 0.0f,				
				-1.0f, 0.0f, 0.0f,
				-1.0f, 0.0f, 0.0f,
				
				// Top face 
				0.0f, 1.0f, 0.0f,			
				0.0f, 1.0f, 0.0f,
				0.0f, 1.0f, 0.0f,
				0.0f, 1.0f, 0.0f,				
				0.0f, 1.0f, 0.0f,
				0.0f, 1.0f, 0.0f,
				
				// Bottom face 
				0.0f, -1.0f, 0.0f,			
				0.0f, -1.0f, 0.0f,
				0.0f, -1.0f, 0.0f,
				0.0f, -1.0f, 0.0f,				
				0.0f, -1.0f, 0.0f,
				0.0f, -1.0f, 0.0f
		};
	
	@Override
	public float[] getVertices() {
		// TODO Auto-generated method stub
		return squareCoords;
	}
	
	public BoxModel(float scale)
	{
		
		
		calculateSquareCoords(scale);
		

        vertexBuffer = ByteBuffer.allocateDirect(squareCoords.length * mBytesPerFloat)
		        .order(ByteOrder.nativeOrder()).asFloatBuffer();							
        vertexBuffer.put(squareCoords).position(0);	

        
		mCubeNormals = ByteBuffer.allocateDirect(cubeNormalData.length * mBytesPerFloat)
		        .order(ByteOrder.nativeOrder()).asFloatBuffer();							
				mCubeNormals.put(cubeNormalData).position(0);
		
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
		return vertexBuffer;
	}

	@Override
	public float[] getNormals() {
		// TODO Auto-generated method stub
		return cubeNormalData;
	}

	@Override
	public FloatBuffer getNormalsBuffer() {
		// TODO Auto-generated method stub
		return mCubeNormals;
	}

	
	

}
