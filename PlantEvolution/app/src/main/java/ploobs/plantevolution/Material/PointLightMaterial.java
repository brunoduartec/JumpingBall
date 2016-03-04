package ploobs.plantevolution.Material;

import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.World.IObject;
import ploobs.plantevolution.World.IWorld;
import ploobs.plantevolution.MyGLRenderer;
import ploobs.plantevolution.Utils;
import ploobs.plantevolution.Camera.ICamera;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import org.w3c.dom.Node;

public class PointLightMaterial extends IMaterial
{

	private int mPositionHandle;
	private int mColorHandle;

	private int mMVPMatrixHandle;
    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
	private float[] mMVPMatrix =new float[16];

    
	 // Define a simple shader program for our point.
    final String pointVertexShader =
    	"uniform mat4 uMVPMatrix;      \n"		
      +	"attribute vec4 vPosition;     \n"		
      + "void main()                    \n"
      + "{                              \n"
      + "   gl_Position = uMVPMatrix   \n"
      + "               * vPosition;   \n"
      + "   gl_PointSize = 5.0;         \n"
      + "}                              \n";
    
    final String pointFragmentShader = 
    	"precision mediump float;       \n"					          
      + "void main()                    \n"
      + "{                              \n"
      + "   gl_FragColor = vec4(1.0,    \n" 
      + "   1.0, 1.0, 1.0);             \n"
      + "}                              \n";
	
	
public PointLightMaterial()
{


    color = Color.enumtoColor(Color.COLORNAME.WHITE);
	
	Context localContext = GraphicFactory.getInstance().getGraphicContext();
	

	 int vertexShaderHandle = Utils.loadShader(	GLES20.GL_VERTEX_SHADER, pointVertexShader);
	 int fragmentShaderHandle = Utils.loadShader(	GLES20.GL_FRAGMENT_SHADER, pointFragmentShader);
	
	 
   
	 mProgram = Utils.createAndLinkProgram(vertexShaderHandle, fragmentShaderHandle, 
     		new String[] {"a_Position"}); 
	 
	 
      
     

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
		
		// TODO Auto-generated method stub
		
		 // Add program to OpenGL environment
    	GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        // get handle to shape's transformation matrix
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        MyGLRenderer.checkGlError("glGetUniformLocation");        
        
        
        
        
        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(
                mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride,obj.getModel().getVertexBuffer());



        // set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color.getColor(), 0);

ICamera cam = world.getCameraManager().getActualCamera();

       // Matrix.setIdentityM(mMVPMatrix, 0);
        
     // This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
        // (which currently contains model * view).
        Matrix.multiplyMM(mMVPMatrix, 0,cam.getViewMatrix() , 0,obj.getLocalTransformation(), 0);
        
        // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
        // (which now contains model * view * projection).
        Matrix.multiplyMM(mMVPMatrix, 0,cam.getProjectionMatrix() , 0, mMVPMatrix, 0);
        
        
        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false,mMVPMatrix, 0);
        MyGLRenderer.checkGlError("glUniformMatrix4fv");

        // Draw the square
         
     // Render all the faces
        for (int face = 0; face < 6; face++) {
           // set the color for each of the faces
           //gl.glColor4f(colors[face][0], colors[face][1], colors[face][2], colors[face][3]);
           // Draw the primitive from the vertex-array directly
           GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, face*4, 4);
        }
        
        
        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
		
	}


    @Override
    public Object Parse(Node childnode) {
        return null;
    }
}