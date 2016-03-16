package ploobs.plantevolution.Material;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Model.Model3D.FacesBufferList;
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

public class DiffuseMaterial extends IMaterial {


	boolean colorseted = false;
	private String name;
	 
	private int mPositionHandle;
	private int mColorHandle;
	//private float[] color = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };

	private int shadowed = 0;

	// R, G, B, A
	float[] cubeColorData;
	private FloatBuffer mCubeColors;
	//private final ShortBuffer mCubeColors;

	private int mMVPMatrixHandle;
    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
	private int mTextureCoordinateDataSize = 2;


	private float[] mMVPMatrix =new float[16];
	private float[] mMVMatrix = new float[16];


	private int mMVMatrixHandle;

	private int mNormalHandle;


	private int mLightIntensityHandle;
	private int mLightPosHandle;
	private int mCameraPosHandle;

	private int mLightColorHandle;

	private float[] mLightPosInEyeSpace = new float[16];
	private int mAmbientColorHandle;
	private int mDiffuseColorHandle;
	private int mSpecularColorHandle;
	private int mu_MatNormHandle;

	private int mAmbientIntensityHandle;
	private int mSpecularIntensityHandle;
	private int mTextureDataHandle;
	private int mTextureCoordinateHandle;
	private int mTextureUniformHandle;
	private int mShadowed;


	public DiffuseMaterial(String name)
	{

		this.name = name;
		color = Utils.RandColor();
		//setColor(Utils.RandColor());
		Context localContext = GraphicFactory.getInstance().getGraphicContext();
		String frag = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_fragmentlight);
		String vert = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_vertexlight);


	//	String frag = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_phong_fragment);
		//String vert = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_phong_vertex);

		int vertexShaderHandle = Utils.loadShader(	GLES20.GL_VERTEX_SHADER, vert);
		int fragmentShaderHandle = Utils.loadShader(	GLES20.GL_FRAGMENT_SHADER, frag);

		mProgram = Utils.createAndLinkProgram(vertexShaderHandle, fragmentShaderHandle,
				new String[]{"a_Position", "a_Color", "a_Normal","a_TexCoordinate"});


	//	mCubeColors = ByteBuffer.allocateDirect(144 * 4)
		//		.order(ByteOrder.nativeOrder()).asFloatBuffer();
	}


	public void setTexture(String texturename)
	{
		// Load the texture
		mTextureDataHandle = TextureManager.getInstance().getGlTextureId(texturename);


	}

	public DiffuseMaterial()
	{
		color = Utils.RandColor();
		//setColor(Utils.RandColor());
		Context localContext = GraphicFactory.getInstance().getGraphicContext();
		//String frag = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_phong_fragment);
		//String vert = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_phong_vertex);
		String frag = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_fragmentlight);
		String vert = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_vertexlight);

		 int vertexShaderHandle = Utils.loadShader(	GLES20.GL_VERTEX_SHADER, vert);
		 int fragmentShaderHandle = Utils.loadShader(GLES20.GL_FRAGMENT_SHADER, frag);
			
			mProgram = Utils.createAndLinkProgram(vertexShaderHandle, fragmentShaderHandle,
					new String[]{"a_Position", "a_Color", "a_Normal","a_TexCoordinate"});




		
	}
	public void setColor(Color color,IObject obj)
	{
		if (colorseted)
			return;

		colorseted=true;

		this.color = color;//new Color(color);

		this.cubeColorData = TintMaterial(obj);
		mCubeColors.put(cubeColorData).position(0);



	}
	//Uses the vertex information to obtain the color buffer size ant tint it in the diffuse color
	public float[] TintMaterial(IObject obj)
	{float[] cubeColor;

		int colorsize =  obj.getModel().getVertices().colors().size();

		mCubeColors = ByteBuffer.allocateDirect(colorsize*4 * 4)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();


		cubeColor = new float[colorsize*4];

		ColorBufferList cc = obj.getModel().getVertices().colors();

		for (int i=0;i<colorsize;i++)
		{

			cubeColor[i*4] 	  = (float)(getDiffuseColor().r )/255.0f;
			cubeColor[i*4 +1] = (float)(getDiffuseColor().g )/255.0f;
			cubeColor[i*4 +2] = (float)(getDiffuseColor().b )/255.0f;
			cubeColor[i*4 +3] = (float)(getDiffuseColor().a )/255.0f;


		}



		return cubeColor;
	}



	@Override
	public TextureList getTextures() {
		return null;
	}

	@Override
	public Color getDiffuseColor() {
		return this.color;
	}

	@Override
	public void Draw(IObject obj, IWorld world) {


// Use culling to remove back faces.
		GLES20.glEnable(GLES20.GL_CULL_FACE);

		// Enable depth testing
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		GLES20.glUseProgram(mProgram);
		 
		// set program handles for cube drawing.
	        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVPMatrix");
	        mMVMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVMatrix");

		mTextureCoordinateHandle = GLES20.glGetAttribLocation(mProgram, "a_TexCoordinate");
		mPositionHandle = GLES20.glGetAttribLocation(mProgram, "a_Position");
		mColorHandle = GLES20.glGetAttribLocation(mProgram, "a_Color");
		mNormalHandle = GLES20.glGetAttribLocation(mProgram, "a_Normal");


		mShadowed = GLES20.glGetUniformLocation(mProgram, "u_Shadowit");
		GLES20.glUniform1i(mShadowed,shadowed);


				mTextureUniformHandle = GLES20.glGetUniformLocation(mProgram, "u_Texture");



		mLightPosHandle = GLES20.glGetUniformLocation(mProgram, "u_LightPos");
			mCameraPosHandle  = GLES20.glGetUniformLocation(mProgram, "v_CameraPosition");

		    mLightIntensityHandle = GLES20.glGetUniformLocation(mProgram, "u_LightIntensity");


		mAmbientIntensityHandle = GLES20.glGetUniformLocation(mProgram, "u_AmbientLightIntensity");
		mAmbientColorHandle = GLES20.glGetUniformLocation(mProgram, "u_AmbientLightColor");


		mSpecularIntensityHandle= GLES20.glGetUniformLocation(mProgram, "u_SpecularLightIntensity");


	//	mDiffuseColorHandle = GLES20.glGetUniformLocation(mProgram, "u_DiffuseColor");
	//	mSpecularColorHandle = GLES20.glGetUniformLocation(mProgram, "u_SpecColor");



		// Set the active texture unit to texture unit 0.
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

		// Bind the texture to this unit.
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);

		// Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
		GLES20.glUniform1i(mTextureUniformHandle, 0);

		obj.getModel().getVertices().uvs().buffer().position(0);

		GLES20.glVertexAttribPointer(mTextureCoordinateHandle, mTextureCoordinateDataSize, GLES20.GL_FLOAT, false,
				0, obj.getModel().getVertices().uvs().buffer());

		GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);



		ILight l1 = world.getLights().get(0);
		float[] colortemp = l1.getColor().getColor();

		GLES20.glUniform4f(mAmbientColorHandle, colortemp[0],colortemp[1],colortemp[2],colortemp[3]);
	//	GLES20.glUniform4f(mDiffuseColorHandle, colortemp[0],colortemp[1],colortemp[2],colortemp[3]);
	//	GLES20.glUniform4f(mSpecularColorHandle, colortemp[0],colortemp[1],colortemp[2],colortemp[3]);

		//uniform vec3 u_AmbientColor;// = vec3(0.1, 0.0, 0.0);
		//uniform vec3 u_DiffuseColor;// = vec3(0.5, 0.0, 0.0);
		//uniform vec3 u_SpecColor;// = vec3(1.0, 1.0, 1.0);
		
		



	        
	        // Enable a handle to the triangle vertices

			GLES20.glEnableVertexAttribArray(mPositionHandle);
		// Prepare the triangle coordinate data
		obj.getModel().getVertexBuffer().position(0);
		GLES20.glVertexAttribPointer(
				mPositionHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false,
				0, obj.getModel().getVertexBuffer());


		// set color for drawing the triangle
		// set color for drawing the triangle
		//GLES20.glUniform4fv(mColorHandle, 1, color, 0);

	      //  GLES20.glUniform4f(mColorHandle, color[0],color[1],color[2],color[3]);
		//GLES20.glEnableVertexAttribArray(mColorHandle);
		GLES20.glEnableVertexAttribArray(mColorHandle);
// Pass in the color information





/*
		if (mCubeColors == null)
		{
			obj.getModel().getVertices().colors().buffer().position(0);
			GLES20.glVertexAttribPointer(mColorHandle, 4, GLES20.GL_BYTE, false,
			0, obj.getModel().getVertices().colors().buffer());
		}
		else*/
		setColor(getDiffuseColor(), obj);
		{
			obj.getModel().getVertices().colors().buffer().position(0);
			GLES20.glVertexAttribPointer(mColorHandle, 4, GLES20.GL_FLOAT, false,
					0, mCubeColors);

		}


		GLES20.glEnableVertexAttribArray(mNormalHandle);
	        // Prepare the triangle coordinate data

		obj.getModel().getNormalsBuffer().position(0);
		GLES20.glVertexAttribPointer(
				mNormalHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false,
				vertexStride, obj.getModel().getNormalsBuffer());







		ICamera cam = world.getCameraManager().getActualCamera();



		GLES20.glUniform3f(mCameraPosHandle,cam.getPosition().getX(),cam.getPosition().getY(),cam.getPosition().getZ());

		
		//Multiply the ViewMatrix by the Object local position to obtain position in worldspace
		Matrix.multiplyMM(mMVMatrix, 0, cam.getViewMatrix(), 0, obj.getLocalTransformation(), 0);
        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mMVMatrix, 0);




		//Multiply the worldspace position by the projection matrix and obtain the screen position
		Matrix.multiplyMM(mMVPMatrix, 0, cam.getProjectionMatrix(), 0, mMVMatrix, 0);
        // Apply the projection and view transformation
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
		




		GLES20.glUniform1f(mLightIntensityHandle, l1.getDiffuseIntensity());

		GLES20.glUniform1f(mAmbientIntensityHandle, l1.getAmbientIntensity());

		GLES20.glUniform1f(mSpecularIntensityHandle, l1.getSpecularIntensity());




        Matrix.multiplyMV(mLightPosInEyeSpace, 0, cam.getViewMatrix(), 0, l1.getLocalTransformation(), 0);



		//GLES20.glUniform3f(mLightPosHandle, mLightPosInEyeSpace[0],mLightPosInEyeSpace[1],mLightPosInEyeSpace[2]);
		GLES20.glUniform3f(mLightPosHandle, l1.getPosition().getX(),l1.getPosition().getY(),l1.getPosition().getZ());


		int verticescount = obj.getModel().getVerticesCount();


		obj.getModel().getFaces().buffer().position(0);

		GLES20.glDrawElements ( GLES20.GL_TRIANGLES, obj.getModel().getFaces().size()* FacesBufferList.PROPERTIES_PER_ELEMENT, GLES20.GL_UNSIGNED_SHORT, obj.getModel().getFaces().buffer() );


		// Disable vertex array
		GLES20.glDisableVertexAttribArray(mPositionHandle);
	}


	@Override
	public Object Parse(Node childnode) {
		return null;
	}

	public Color getColor() {
		return color;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int isShadowed() {
		return shadowed;
	}

	public void setShadowed(int shadowed) {
		this.shadowed = shadowed;
	}
}
