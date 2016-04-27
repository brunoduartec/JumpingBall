package ploobs.plantevolution;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import ploobs.plantevolution.Material.Color4;
import ploobs.plantevolution.Model.Model3D.FacesBufferList;

public class Utils {
	
    private static final String TAG = "SAMPLESTUDY";

	public static final float DEG = (float)(Math.PI / 180f);

	private static final int BYTES_PER_FLOAT = 4;

	/**
     * Utility method for compiling a OpenGL shader.
     *
     * <p><strong>Note:</strong> When developing shaders, use the checkGlError()
     * method to debug shader coding errors.</p>
     *
     * @param type - Vertex or fragment shader type.
     * @param shaderCode - String containing the shader code.
     * @return - Returns an id for the shader.
     */
	
	public static Color4 RandColor()
	{
		Random rnd = new Random();
		//int[] color = { rnd.nextInt(255), rnd.nextInt(255),rnd.nextInt(255), rnd.nextInt(255) };

int ii=0;

		while(ii== 0 || ii==1 || ii==7 ) {
		ii = rnd.nextInt(Color4.COLORNAME.values().length-1);
		}



		Color4 ret = Color4.enumtoColor(Color4.COLORNAME.values()[ii]);

		return ret;
	
		
	}



	/**
	 * Helper function to compile and link a program.
	 * 
	 * @param vertexShaderHandle An OpenGL handle to an already-compiled vertex shader.
	 * @param fragmentShaderHandle An OpenGL handle to an already-compiled fragment shader.
	 * @param attributes Attributes that need to be bound to the program.
	 * @return An OpenGL handle to the program.
	 */
	public static int createAndLinkProgram(final int vertexShaderHandle, final int fragmentShaderHandle, final String[] attributes) 
	{
		int programHandle = GLES20.glCreateProgram();
		
		if (programHandle != 0) 
		{
			// Bind the vertex shader to the program.
			GLES20.glAttachShader(programHandle, vertexShaderHandle);			

			// Bind the fragment shader to the program.
			GLES20.glAttachShader(programHandle, fragmentShaderHandle);
			
			// Bind attributes
			if (attributes != null)
			{
				final int size = attributes.length;
				for (int i = 0; i < size; i++)
				{
					GLES20.glBindAttribLocation(programHandle, i, attributes[i]);
				}						
			}
			
			// Link the two shaders together into a program.
			GLES20.glLinkProgram(programHandle);

			// get the link status.
			final int[] linkStatus = new int[1];
			GLES20.glGetProgramiv(programHandle, GLES20.GL_LINK_STATUS, linkStatus, 0);

			// If the link failed, delete the program.
			if (linkStatus[0] == 0) 
			{				
				Log.e(TAG, "Error compiling program: " + GLES20.glGetProgramInfoLog(programHandle));
				GLES20.glDeleteProgram(programHandle);
				programHandle = 0;
			}
		}
		
		if (programHandle == 0)
		{
			throw new RuntimeException("Error creating program.");
		}
		
		return programHandle;
	}

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        //GLES20.glShaderSource(shader, shaderCode);
        //GLES20.glCompileShader(shader);

        
		if (shader != 0) 
		{
			// Pass in the shader source.
			GLES20.glShaderSource(shader, shaderCode);

			// Compile the shader.
			GLES20.glCompileShader(shader);

			// get the compilation status.
			final int[] compileStatus = new int[1];
			GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

			// If the compilation failed, delete the shader.
			if (compileStatus[0] == 0) 
			{				
				GLES20.glDeleteShader(shader);
				shader = 0;
			}
		}

		if (shader == 0)
		{
			throw new RuntimeException("Error creating vertex shader.");
		}
        
        
        
        
        return shader;
    }


	// For the tags title and summary, extracts their text values.
	public static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText();
			parser.nextTag();
		}
		return result;
	}

	public static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
		if (parser.getEventType() != XmlPullParser.START_TAG) {
			throw new IllegalStateException();
		}
		int depth = 1;
		while (depth != 0) {
			switch (parser.next()) {
				case XmlPullParser.END_TAG:
					depth--;
					break;
				case XmlPullParser.START_TAG:
					depth++;
					break;
			}
		}
	}

	/**
	 * Add two triangles to the Object3d's faces using the supplied indices
	 */
	public static void addQuad(FacesBufferList faces, int upperLeft, int upperRight, int lowerRight, int lowerLeft)
	{
		faces.add((short) upperLeft, (short) lowerRight, (short) upperRight);
		faces.add((short)upperLeft, (short)lowerLeft, (short)lowerRight);
	}


	public static int loadTexture(final Context context, final int resourceId)
	{
		final int[] textureHandle = new int[1];

		GLES20.glGenTextures(1, textureHandle, 0);

		if (textureHandle[0] != 0)
		{
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inScaled = false;	// No pre-scaling

			// Read in the resource
			final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);

			// Bind to the texture in OpenGL
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);

			// Set filtering
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

			// Load the bitmap into the bound texture.
			GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

			// Recycle the bitmap, since its data has been loaded into OpenGL.
			bitmap.recycle();
		}

		if (textureHandle[0] == 0)
		{
			throw new RuntimeException("Error loading texture.");
		}

		return textureHandle[0];
	}
	public static FloatBuffer makeFloatBuffer3(float $a, float $b, float $c)
	{
		ByteBuffer b = ByteBuffer.allocateDirect(3 * BYTES_PER_FLOAT);
		b.order(ByteOrder.nativeOrder());
		FloatBuffer buffer = b.asFloatBuffer();
		buffer.put($a);
		buffer.put($b);
		buffer.put($c);
		buffer.position(0);
		return buffer;
	}
	public static FloatBuffer makeFloatBuffer4(float $a, float $b, float $c, float $d)
	{
		ByteBuffer b = ByteBuffer.allocateDirect(4 * BYTES_PER_FLOAT);
		b.order(ByteOrder.nativeOrder());
		FloatBuffer buffer = b.asFloatBuffer();
		buffer.put($a);
		buffer.put($b);
		buffer.put($c);
		buffer.put($d);
		buffer.position(0);
		return buffer;
	}

	/**
	 * Convenience method to create a Bitmap given a Context's drawable resource ID.
	 */
	public static Bitmap makeBitmapFromResourceId(Context $context, int $id)
	{
		InputStream is = $context.getResources().openRawResource($id);

		Bitmap bitmap;
		try {
			bitmap = BitmapFactory.decodeStream(is);
		} finally {
			try {
				is.close();
			} catch(IOException e) {
				// Ignore.
			}
		}

		return bitmap;
	}

	/**
	 * Convenience method to create a Bitmap given a drawable resource ID from the application Context.
	 */
	public static Bitmap makeBitmapFromResourceId(int $id)
	{
		return makeBitmapFromResourceId(GraphicFactory.getInstance().getGraphicContext(), $id);
	}

	/**
	 * Used by TextureManager
	 */
	public static int uploadTextureAndReturnId(Bitmap $bitmap, boolean $generateMipMap) /*package-private*/
	{
		int glTextureId;

		int[] a = new int[1];
		GLES20.glGenTextures(1, a, 0); // create a 'texture name' and put it in array element 0
		glTextureId = a[0];
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, glTextureId);

	//	if($generateMipMap) {
	//		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_GENERATE_MIPMAP_HINT, GLES20.GL_TRUE);
	//	} else {
	//		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_GENERATE_MIPMAP_HINT, GLES20.GL_FALSE);
	//	}
		// Set filtering
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
		// 'upload' to gpu
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, $bitmap, 0);

		return glTextureId;
	}

}
