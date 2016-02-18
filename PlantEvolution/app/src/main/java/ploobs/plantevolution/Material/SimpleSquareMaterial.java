package ploobs.plantevolution.Material;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.Matrix;

import ploobs.plantevolution.Camera.ICamera;
import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.World.IObject;
import ploobs.plantevolution.World.IWorld;
import ploobs.plantevolution.R;
import ploobs.plantevolution.RawResourceReader;
import ploobs.plantevolution.Utils;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class SimpleSquareMaterial extends IMaterial
{


	 private final int mProgram;
    private final FloatBuffer textureCoordinates;
    private int mPositionHandle;
	private int mColorHandle;
    private int mTextureCoordinateHandle;
    private int mTextureUniformHandle;

    private float color[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };

   private float[] squareColorData;


    private final FloatBuffer mSquareColors;

	private int mMVPMatrixHandle;
    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
	private float[] mMVPMatrix =new float[16];


    private final float[] textureCoordinateData =
            {
                    // Front face
                    0.0f, 0.0f,
                    0.0f, 1.0f,
                    1.0f, 1.0f,
                    1.0f, 0.0f
                   // 1.0f, 1.0f,
                   // 1.0f, 0.0f
            };
    /** This is a handle to our texture data. */
    private int mTextureDataHandle;
    private int mTextureCoordinateDataSize = 2;


    public SimpleSquareMaterial(final int resourceId)
{
	
	color = Utils.RandColor();
	Context localContext = GraphicFactory.getInstance().getGraphicContext();
	String frag = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_fragment_tex);
	String vert = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_vertex);
	 int vertexShaderHandle = Utils.loadShader(	GLES30.GL_VERTEX_SHADER, vert);
	 int fragmentShaderHandle = Utils.loadShader(	GLES30.GL_FRAGMENT_SHADER, frag);
	 mProgram = GLES30.glCreateProgram();             // create empty OpenGL Program
      GLES30.glAttachShader(mProgram, vertexShaderHandle);   // add the vertex shader to program
      GLES30.glAttachShader(mProgram, fragmentShaderHandle); // add the fragment shader to program
      GLES30.glLinkProgram(mProgram);

    mSquareColors = ByteBuffer.allocateDirect(4*4 * 4)
            .order(ByteOrder.nativeOrder()).asFloatBuffer();


    textureCoordinates = ByteBuffer.allocateDirect(textureCoordinateData.length * GraphicFactory.getInstance().mBytesPerFloat)
            .order(ByteOrder.nativeOrder()).asFloatBuffer();
    textureCoordinates.put(textureCoordinateData).position(0);


    // Load the texture
    mTextureDataHandle = Utils.loadTexture(GraphicFactory.getInstance().getGraphicContext(), resourceId);

}
    public void setTexture(int resourceId)
    {
        // Load the texture
        mTextureDataHandle = Utils.loadTexture(GraphicFactory.getInstance().getGraphicContext(), resourceId);


    }


	
	public void setColor(float[] color)
    {

        this.color = color;
        this.squareColorData = setSquareColorData(color);
        mSquareColors.put(squareColorData).position(0);

    }


    private float[] setSquareColorData(float[] cc)
    {


        float[] cubeColor =
                {
                        // Front face (color)
                        cc[0], cc[1], cc[2], 1.0f,
                        cc[0], cc[1], cc[2], 1.0f,
                        cc[0], cc[1], cc[2], 1.0f,
                        cc[0], cc[1], cc[2], 1.0f
                       // cc[0], cc[1], cc[2], 1.0f,
                      //  cc[0], cc[1], cc[2], 1.0f

                };
        return cubeColor;


    }


    @Override
    public TextureList getTextures() {
        return null;
    }

    @Override
	public void Draw(IObject obj, IWorld world) {
		
		// TODO Auto-generated method stub

// Use culling to remove back faces.
        GLES30.glEnable(GLES30.GL_CULL_FACE);

        // Enable depth testing
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);

		   // Add program to OpenGL environment
    	GLES30.glUseProgram(mProgram);



        mPositionHandle = GLES30.glGetAttribLocation(mProgram, "a_Position");
        mColorHandle = GLES30.glGetAttribLocation(mProgram, "a_Color");
        mMVPMatrixHandle = GLES30.glGetUniformLocation(mProgram, "uMVPMatrix");
        mTextureCoordinateHandle = GLES30.glGetAttribLocation(mProgram, "a_TexCoordinate");
        mTextureUniformHandle = GLES30.glGetUniformLocation(mProgram, "u_Texture");



        // Set the active texture unit to texture unit 0.
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);

        // Bind the texture to this unit.
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, mTextureDataHandle);
        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES30.glUniform1i(mTextureUniformHandle, 0);

        // Pass in the texture coordinate information
        textureCoordinates.position(0);
        GLES30.glVertexAttribPointer(mTextureCoordinateHandle, mTextureCoordinateDataSize, GLES30.GL_FLOAT, false,
                0, textureCoordinates);

        GLES30.glEnableVertexAttribArray(mTextureCoordinateHandle);



        // Enable a handle to the triangle vertices
        GLES30.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES30.glVertexAttribPointer(
                mPositionHandle, COORDS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                vertexStride,obj.getModel().getVertexBuffer());



        // set color for drawing the triangle
       // GLES30.glUniform4fv(mColorHandle, 1, color, 0);
        mSquareColors.position(0);
        GLES30.glVertexAttribPointer(mColorHandle, 4, GLES30.GL_FLOAT, false,
                0, mSquareColors);

        GLES30.glEnableVertexAttribArray(mColorHandle);


        ICamera cam = world.getCameraManager().getActualCamera();

       // Matrix.setIdentityM(mMVPMatrix, 0);
        
     // This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
        // (which currently contains model * view).
        Matrix.multiplyMM(mMVPMatrix, 0,cam.getViewMatrix() , 0,obj.getLocalTransformation(), 0);
        
        // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
        // (which now contains model * view * projection).
        Matrix.multiplyMM(mMVPMatrix, 0,cam.getProjectionMatrix() , 0, mMVPMatrix, 0);
        
        
        // Apply the projection and view transformation
        GLES30.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);



       // MyGLRenderer.checkGlError("glUniformMatrix4fv");

        // Draw the square
         
    //int verticescount = obj.getModel().getVerticesCount()/3;
       int verticescount = obj.getModel().getVerticesCount();

        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_FAN,0,verticescount);
        
        
        // Disable vertex array
        GLES30.glDisableVertexAttribArray(mPositionHandle);
		
	}


    @Override
    public Object Parse(Node childnode) {


        NodeList childnodeList = childnode.getChildNodes();

                                for (int w = 0; w < childnodeList.getLength(); w++) {
                                    Node collisionchildnode = childnodeList.item(w);
                                    String collisionchildnodename = collisionchildnode.getNodeName();



                                switch(collisionchildnodename) {
                                    case "color": {
                                       // Node positionnode = collisionchildnode.getChildNodes().item(1);
                                        NodeList colornodes = collisionchildnode.getChildNodes();
                                        float pp[] = new float[4];

                                        for (int k = 0; k < colornodes.getLength(); k++) {

                                            if (colornodes.item(k) instanceof org.w3c.dom.Element) {

                                                switch (colornodes.item(k).getNodeName()) {
                                                    case "r":


                                                        pp[0] = Float.parseFloat(colornodes.item(k).getLastChild().getTextContent().trim());
                                                        break;
                                                    case "g":
                                                        pp[1] = Float.parseFloat(colornodes.item(k).getLastChild().getTextContent().trim());
                                                        break;
                                                    case "b":
                                                        pp[2] = Float.parseFloat(colornodes.item(k).getLastChild().getTextContent().trim());
                                                        break;
                                                    case "a":
                                                        pp[3] = Float.parseFloat(colornodes.item(k).getLastChild().getTextContent().trim());
                                                        break;

                                                }
                                            }
                                        }
                                        this.setColor(pp);
                                    }
                                    break;




                                }
                        }




        return this;
    }
}