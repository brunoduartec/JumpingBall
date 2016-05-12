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

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ploobs.plantevolution.GameState.GameStateManager;
import ploobs.plantevolution.Gameplay.States.InitStageScreenState;
import ploobs.plantevolution.Gameplay.States.MainScreenState;
import ploobs.plantevolution.Gameplay.States.MenuScreenState;
import ploobs.plantevolution.Gameplay.States.SplitScreenState;

/**
 * Provides drawing instructions for a GLSurfaceView object. This class
 * must override the OpenGL ES drawing lifecycle methods:
 * <ul>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onSurfaceCreated}</li>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onDrawFrame}</li>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onSurfaceChanged}</li>
 * </ul>
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {


    public GameActivity get_activityhandle() {
        return _activityhandle;
    }

    public void set_activityhandle(GameActivity _activityhandle) {
        this._activityhandle = _activityhandle;
    }





    private GameStateManager gsmanager = new GameStateManager();

    private GameActivity _activityhandle;
    private static final String TAG = "MyGLRenderer";


    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {


      //  this.scale = GameConstants.scale;

        // set the background frame color
        GLES20.glClearColor(0.29f, 0.95f, 0.88f, 1.0f);

        // Use culling to remove back faces.
        GLES20.glEnable(GLES20.GL_CULL_FACE);

        // Enable depth testing
       // GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);

        gsmanager.Push(new MainScreenState());
        gsmanager.Push(new InitStageScreenState(1500));
        gsmanager.Push(new MenuScreenState());
        gsmanager.Push(new SplitScreenState(2000));


    }





    @Override
    public void onDrawFrame(GL10 unused) {

        // Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        // set the camera position (View matrix)



        gsmanager.Update();
        gsmanager.Draw();
    }


    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES20.glViewport(0, 0, width, height);




    }


    /**
     * Utility method for debugging OpenGL calls. Provide the name of the call
     * just after making it:
     * <p/>
     * <pre>
     * mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
     * MyGLRenderer.checkGlError("glGetUniformLocation");</pre>
     *
     * If the operation is not successful, the check throws an error.
     *
     * @param glOperation - Name of the OpenGL call to check.
     */
    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    public void HandleEvent()
    {
        gsmanager.HandleEvent();
    }



}