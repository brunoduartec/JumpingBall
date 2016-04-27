package ploobs.plantevolution.Camera;

import android.opengl.Matrix;

import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Math.Vector3;

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


        Vector3 pos =  new Vector3(0.0f, 0.0f, 1f);
        Vector3 target = new Vector3(0.0f, 0.0f, -3.0f);



        this.setPosition(pos);
        this.setTarget(target);


        CalcViewMatrix();
        CalcProjectionMatrix();







        CalcViewProjectionMatrix();

    }

    @Override
    public void CalcViewMatrix()
    {
        Vector3 position = this.getPosition();
        Vector3 target = this.getTarget();





        Matrix.setLookAtM(this.getViewMatrix(), 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);


       // Matrix.setLookAtM(this.getViewMatrix(), 0, position.getX(), position.getY(), position.getZ(), target.getX(), target.getY(), target.getZ(), 0f, 1.0f, 0.0f);

    }


    @Override
    public void CalcProjectionMatrix()
    {





        // Create a new perspective projection matrix. The height will stay the same
        // while the width will vary as per aspect ratio.
        final float ratio = GraphicFactory.getInstance().getRatio();
        final float left = -ratio;
        final float right = ratio;
        final float bottom = -1.0f;
        final float top = 1.0f;
        final float near = 1.0f;
        final float far = 1000.0f;



        Matrix.orthoM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, -1, 1);
      //  Matrix.orthoM(mProjectionMatrix, 0, 0f, GraphicFactory.getInstance().getWidth(), 0.0f, GraphicFactory.getInstance().getHeight(), 0, 50);

      //  Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);

        Matrix.translateM(mProjectionMatrix, 0, -ratio, 1.0f, 0); // Multiply by translation to the position

        //	Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, getNearPlane(), getFarPlane());

//		Matrix.orthoM(mProjectionMatrix, 0, 0, width,0, height, -10,100);


    }







}
