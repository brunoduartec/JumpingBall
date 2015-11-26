package ploobs.plantevolution.Camera;

import android.opengl.Matrix;

import ploobs.plantevolution.GraphicFactory;

/**
 * Created by Bruno on 28/09/2015.
 */
public class Camera2D extends SimpleCamera {





    public Camera2D(String name,float width,float height,float nearplane, float farplane,float ratio)
    {
        this.setName(name);
        this.setRatio(ratio);
        this.setWidth(width);
        this.setHeight(height);
        this.setNearPlane(nearplane);
        this.setFarPlane(farplane);


        float[] pos =  {0.0f, 0.0f, 1.5f};
        float[] target =  {0.0f, 0.0f, -5.0f};



        this.setPosition(pos);
        this.setTarget(target);


        CalcViewMatrix();
        CalcProjectionMatrix();







        CalcViewProjectionMatrix();

    }

    @Override
    public void CalcViewMatrix()
    {
        float[] position = this.getPosition();
        float[] target = this.getTarget();







        Matrix.setLookAtM(this.getViewMatrix(), 0, position[0], position[1], position[2], target[0], target[1], target[2], 0f, 1.0f, 0.0f);

    }


    @Override
    public void CalcProjectionMatrix()
    {





        // Create a new perspective projection matrix. The height will stay the same
        // while the width will vary as per aspect ratio.
        final float ratio = (float) width / height;
        final float left = -ratio;
        final float right = ratio;
        final float bottom = -1.0f;
        final float top = 1.0f;
        final float near = 1.0f;
        final float far = 1000.0f;


        Matrix.translateM(mProjectionMatrix, 0, -1.0f, 1.0f/ratio, 0); // Multiply by translation to the position
        Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
        //	Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, getNearPlane(), getFarPlane());

//		Matrix.orthoM(mProjectionMatrix, 0, 0, width,0, height, -10,100);


    }







}
