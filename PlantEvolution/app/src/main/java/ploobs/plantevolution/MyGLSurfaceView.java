/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ploobs.plantevolution;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import ploobs.plantevolution.Audio.AudioPlayer;
import ploobs.plantevolution.Input.InputSystem;
import ploobs.plantevolution.Math.Vector2;

/**
 * A view container where OpenGL ES graphics can be drawn on screen.
 * This view can also be used to capture touch events, such as a user
 * interacting with drawn objects.
 */
public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;



    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        GraphicFactory.getInstance().setGraphicContext(context);



        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(3);

        // set the Renderer for drawing on the GLSurfaceView
        mRenderer = new MyGLRenderer();
        setRenderer(getmRenderer());

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

  //  public MyGLSurfaceView(Context context, AttributeSet attrs, int defStyle) {
    //    super(context, attrs, defStyle);
       // init(context);
   // }

    public MyGLSurfaceView(Context context) {
        super(context);




        
        GraphicFactory.getInstance().setGraphicContext(context);
        
        
        
        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(3);

        // set the Renderer for drawing on the GLSurfaceView
        mRenderer = new MyGLRenderer();
        setRenderer(getmRenderer());

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);




    }








    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        InputSystem.getInstance().set_inputEvent(e);


        mRenderer.HandleEvent();


        return true;
    }

    public MyGLRenderer getmRenderer() {
        return mRenderer;
    }


    @Override
    public void onPause() {
        super.onPause();

        AudioPlayer.getInstance().pausePlayer();

    }

}
