package ploobs.plantevolution.Material;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.Matrix;

import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.World.IObject;
import ploobs.plantevolution.World.IWorld;
import ploobs.plantevolution.Light.ILight;
import ploobs.plantevolution.R;
import ploobs.plantevolution.RawResourceReader;
import ploobs.plantevolution.Utils;
import ploobs.plantevolution.Camera.ICamera;

import org.w3c.dom.Node;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import ploobs.plantevolution.Model.Model3D.Vertices;

public class DiffuseMaterial extends IMaterial {

	
	 
	private int mPositionHandle;
	private int mColorHandle;
	//private float[] color = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };
private Color color = new Color(51,180,229,255);

	// R, G, B, A
	float[] cubeColorData;
	private FloatBuffer mCubeColors;
	//private final ShortBuffer mCubeColors;

	private int mMVPMatrixHandle;
    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
	private float[] mMVPMatrix =new float[16];
	private float[] mMVMatrix = new float[16];

	private int mMVMatrixHandle;

	private int mNormalHandle;


	private int mLightIntensityHandle;
	private int mLightPosHandle;
	private int mLightColorHandle;

	private float[] mLightPosInEyeSpace = new float[16];



	public DiffuseMaterial()
	{
		color = Utils.RandColor();
		//setColor(Utils.RandColor());
		Context localContext = GraphicFactory.getInstance().getGraphicContext();
		String frag = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_fragmentlight);
		String vert = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_vertexlight);

		 int vertexShaderHandle = Utils.loadShader(	GLES30.GL_VERTEX_SHADER, vert);
		 int fragmentShaderHandle = Utils.loadShader(	GLES30.GL_FRAGMENT_SHADER, frag);
			
			mProgram = Utils.createAndLinkProgram(vertexShaderHandle, fragmentShaderHandle,
					new String[]{"a_Position", "a_Color", "a_Normal"});


		mCubeColors = ByteBuffer.allocateDirect(144 * 4)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();

		//mCubeColors.put(cubeColorData).position(0);

		
	}
	public void setColor(Color color)
	{

		this.color = color;//new Color(color);
		this.cubeColorData = setColorCubeData(color);
		mCubeColors.put(cubeColorData).position(0);
		//mCubeColors = color.toFloatBuffer();

	}

	private float[] setColorCubeData(Color cc)
	{

		float frontfactor = -51/255.0f;
		float topfactor = 76/255.0f;
		float leftfactor = 100/255.0f;
		
		
		float[] cc1 = cc.getColorf();

		float[] cubeColor =
				{
						// Front face (color)
						 (cc1[0]+frontfactor),  (cc1[1]+frontfactor),  (cc1[2]+frontfactor), cc1[3],
						 (cc1[0]+frontfactor),  (cc1[1]+frontfactor),  (cc1[2]+frontfactor), cc1[3],
						 (cc1[0]+frontfactor),  (cc1[1]+frontfactor),  (cc1[2]+frontfactor), cc1[3],
						 (cc1[0]+frontfactor),  (cc1[1]+frontfactor),  (cc1[2]+frontfactor), cc1[3],
						 (cc1[0]+frontfactor),  (cc1[1]+frontfactor),  (cc1[2]+frontfactor), cc1[3],
						 (cc1[0]+frontfactor),  (cc1[1]+frontfactor),  (cc1[2]+frontfactor), cc1[3],

						// Right face (green)
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],

						// Back face (blue)
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],

						// Left face (yellow)


						 (cc1[0]+leftfactor),  (cc1[1]+leftfactor),  (cc1[2]+leftfactor), cc1[3],
						 (cc1[0]+leftfactor),  (cc1[1]+leftfactor),  (cc1[2]+leftfactor), cc1[3],
						 (cc1[0]+leftfactor),  (cc1[1]+leftfactor),  (cc1[2]+leftfactor), cc1[3],
						 (cc1[0]+leftfactor),  (cc1[1]+leftfactor),  (cc1[2]+leftfactor), cc1[3],
						 (cc1[0]+leftfactor),  (cc1[1]+leftfactor),  (cc1[2]+leftfactor), cc1[3],
						 (cc1[0]+leftfactor),  (cc1[1]+leftfactor),  (cc1[2]+leftfactor), cc1[3],



						// Top face (cyan)
						 (cc1[0]+topfactor),  (cc1[1]+topfactor),  (cc1[2]+topfactor), cc1[3],
						 (cc1[0]+topfactor),  (cc1[1]+topfactor),  (cc1[2]+topfactor), cc1[3],
						 (cc1[0]+topfactor),  (cc1[1]+topfactor),  (cc1[2]+topfactor), cc1[3],
						 (cc1[0]+topfactor),  (cc1[1]+topfactor),  (cc1[2]+topfactor), cc1[3],
						 (cc1[0]+topfactor),  (cc1[1]+topfactor),  (cc1[2]+topfactor), cc1[3],
						 (cc1[0]+topfactor),  (cc1[1]+topfactor),  (cc1[2]+topfactor), cc1[3],


						// Bottom face (magenta)
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3],
						cc1[0], cc1[1], cc1[2], cc1[3]
				};
		return cubeColor;


	}


	@Override
	public TextureList getTextures() {
		return null;
	}

	@Override
	public void Draw(IObject obj, IWorld world) {


// Use culling to remove back faces.
		GLES30.glEnable(GLES30.GL_CULL_FACE);

		// Enable depth testing
		GLES30.glEnable(GLES30.GL_DEPTH_TEST);
		GLES30.glUseProgram(mProgram);
		 
		// set program handles for cube drawing.
	        mMVPMatrixHandle = GLES30.glGetUniformLocation(mProgram, "u_MVPMatrix");
	        mMVMatrixHandle = GLES30.glGetUniformLocation(mProgram, "u_MVMatrix"); 
	        
	        mLightPosHandle = GLES30.glGetUniformLocation(mProgram, "u_LightPos");
		mLightIntensityHandle = GLES30.glGetUniformLocation(mProgram, "u_LightIntensity");

	        
	        mPositionHandle = GLES30.glGetAttribLocation(mProgram, "a_Position");
	        mColorHandle = GLES30.glGetAttribLocation(mProgram, "a_Color");
	        mNormalHandle = GLES30.glGetAttribLocation(mProgram, "a_Normal"); 
	
	       
	        
	        // Enable a handle to the triangle vertices

			GLES30.glEnableVertexAttribArray(mPositionHandle);
		// Prepare the triangle coordinate data
		obj.getModel().getVertexBuffer().position(0);
		GLES30.glVertexAttribPointer(
				mPositionHandle, COORDS_PER_VERTEX,
				GLES30.GL_FLOAT, false,
				vertexStride, obj.getModel().getVertexBuffer());


		// set color for drawing the triangle
		// set color for drawing the triangle
		//GLES30.glUniform4fv(mColorHandle, 1, color, 0);

	      //  GLES30.glUniform4f(mColorHandle, color[0],color[1],color[2],color[3]);
		//GLES30.glEnableVertexAttribArray(mColorHandle);
		GLES30.glEnableVertexAttribArray(mColorHandle);
// Pass in the color information




		mCubeColors.position(0);
		GLES30.glVertexAttribPointer(mColorHandle, 4, GLES30.GL_FLOAT, false,
				//	0, mCubeColors);
				0, obj.getModel().getVertices().colors().buffer());


						GLES30.glEnableVertexAttribArray(mNormalHandle);
	        // Prepare the triangle coordinate data

		obj.getModel().getNormalsBuffer().position(0);
		GLES30.glVertexAttribPointer(
					mNormalHandle, COORDS_PER_VERTEX,
					GLES30.GL_FLOAT, false,
					vertexStride, obj.getModel().getNormalsBuffer());







		ICamera cam = world.getCameraManager().getActualCamera();
		
		//Multiply the ViewMatrix by the Object local position to obtain position in worldspace
		Matrix.multiplyMM(mMVMatrix, 0, cam.getViewMatrix(), 0, obj.getLocalTransformation(), 0);
        // Apply the projection and view transformation
        GLES30.glUniformMatrix4fv(mMVMatrixHandle, 1, false,mMVMatrix, 0);
		
		
		//Multiply the worldspace position by the projection matrix and obtain the screen position
		Matrix.multiplyMM(mMVPMatrix, 0, cam.getProjectionMatrix(), 0, mMVMatrix, 0);
        // Apply the projection and view transformation
		GLES30.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
		

        ILight l1 = world.getLights().get(0);
        
        Matrix.multiplyMV(mLightPosInEyeSpace, 0, cam.getViewMatrix(), 0, l1.getLocalTransformation(), 0);


		GLES30.glUniform1f(mLightIntensityHandle,l1.getIntensity());
        // Apply the projection and view transformation
        GLES30.glUniformMatrix4fv(mLightPosHandle, 1, false,mLightPosInEyeSpace, 0);
	
                
        // Draw the cube.
      //  GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, 24);
           
           // Disable vertex array
       //  GLES30.glDisableVertexAttribArray(mPositionHandle);

		//int verticescount = obj.getModel().getVerticesCount()/3;
		int verticescount = obj.getModel().getVerticesCount();

		//GLES30.glDrawArrays(GLES30.GL_TRIANGLE_FAN,0,verticescount);
		//GLES30.glDrawElements(GLES30.GL_TRIANGLES,0,verticescount);
		obj.getModel().getFaces().buffer().position(0);

		GLES30.glDrawElements ( GLES30.GL_TRIANGLES, obj.getModel().getFaces().size()*3, GLES30.GL_UNSIGNED_SHORT, obj.getModel().getFaces().buffer() );


		// Disable vertex array
		GLES30.glDisableVertexAttribArray(mPositionHandle);
	}


	@Override
	public Object Parse(Node childnode) {
		return null;
	}

	public Color getColor() {
		return color;
	}






}
