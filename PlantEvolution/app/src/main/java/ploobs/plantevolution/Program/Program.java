package ploobs.plantevolution.Program;

import android.opengl.GLES20;

import ploobs.plantevolution.Text.AttribVariable;
import ploobs.plantevolution.Utils;


public abstract class Program {

    private int programHandle;
    private int vertexShaderHandle;
    private int fragmentShaderHandle;
    private boolean mInitialized;

    public Program() {
        mInitialized = false;
    }

    public void init() {
        init(null, null, null);
    }

    public void init(String vertexShaderCode, String fragmentShaderCode, AttribVariable[] programVariables) {
        vertexShaderHandle = Utils.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        fragmentShaderHandle = Utils.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        programHandle = Utils.createAndLinkProgram(vertexShaderHandle,fragmentShaderHandle,programVariables);

       // programHandle = Utils.createProgram(
       //         vertexShaderHandle, fragmentShaderHandle, programVariables);

        mInitialized = true;
    }

    public int getHandle() {
        return programHandle;
    }

    public void delete() {
        GLES20.glDeleteShader(vertexShaderHandle);
        GLES20.glDeleteShader(fragmentShaderHandle);
        GLES20.glDeleteProgram(programHandle);
        mInitialized = false;
    }

    public boolean initialized() {
        return mInitialized;
    }
}