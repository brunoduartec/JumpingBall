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

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import ploobs.plantevolution.Camera.Camera2D;
import ploobs.plantevolution.Camera.SimpleCamera;
import ploobs.plantevolution.Component.FpsCounterComponent;
import ploobs.plantevolution.Component.TimerComponent;
import ploobs.plantevolution.GameState.GameStateManager;
import ploobs.plantevolution.Gameplay.GameConstants;
import ploobs.plantevolution.Gameplay.StageManager;
import ploobs.plantevolution.Gameplay.States.MainScreenState;
import ploobs.plantevolution.Gameplay.States.MenuScreenState;
import ploobs.plantevolution.Gameplay.States.SplitScreenState;
import ploobs.plantevolution.Light.AmbientLight;
import ploobs.plantevolution.Math.Vector2;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.Scene.IScene;
import ploobs.plantevolution.Scene.SimpleScene;
import ploobs.plantevolution.World.IWorld;
import ploobs.plantevolution.World.SimpleWorld;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.util.DisplayMetrics;
import android.util.Log;

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


    public OpenGLES30Activity get_activityhandle() {
        return _activityhandle;
    }

    public void set_activityhandle(OpenGLES30Activity _activityhandle) {
        this._activityhandle = _activityhandle;
    }





    private GameStateManager gsmanager = new GameStateManager();

    private OpenGLES30Activity _activityhandle;
    private static final String TAG = "MyGLRenderer";


    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {


      //  this.scale = GameConstants.scale;

        // set the background frame color
        GLES30.glClearColor(0.29f, 0.95f, 0.88f, 1.0f);

        // Use culling to remove back faces.
        GLES30.glEnable(GLES30.GL_CULL_FACE);

        // Enable depth testing
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);

        GLES30.glEnable(GLES30.GL_BLEND);
        GLES30.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);

        gsmanager.Push(new MainScreenState());
        gsmanager.Push(new MenuScreenState());
        gsmanager.Push(new SplitScreenState(2000));


    }





    @Override
    public void onDrawFrame(GL10 unused) {

        // Draw background color
        GLES30.glClear(GLES30.GL_DEPTH_BUFFER_BIT | GLES30.GL_COLOR_BUFFER_BIT);
        // set the camera position (View matrix)



        gsmanager.Update();
        gsmanager.Draw();
    }


    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES30.glViewport(0, 0, width, height);




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
        while ((error = GLES30.glGetError()) != GLES30.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    public void HandleEvent()
    {
        gsmanager.HandleEvent();
    }



}