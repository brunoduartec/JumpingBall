package ploobs.plantevolution;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GameActivity extends Activity {

	
	private MyGLSurfaceView mGLView;
	private ImageView bpush,bjump,brestart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create a GLSurfaceView instance and set it
		// as the ContentView for this Activity
		mGLView = new MyGLSurfaceView(this);
		//setContentView(mGLView);
		GraphicFactory.getInstance().setResources(this.getResources());
		setContentView(mGLView);

		mGLView.getmRenderer().set_activityhandle(this);

		LinearLayout ll = new LinearLayout(this);

// specifying vertical orientation
		ll.setOrientation(LinearLayout.HORIZONTAL);



	}
	
	
	 @Override
	    protected void onPause() {
	        super.onPause();
	        // The following call pauses the rendering thread.
	        // If your OpenGL application is memory intensive,
	        // you should consider de-allocating objects that
	        // consume significant memory here.
	        mGLView.onPause();
	    }

	    @Override
	    protected void onResume() {
	        super.onResume();
	        // The following call resumes a paused rendering thread.
	        // If you de-allocated graphic objects for onPause()
	        // this is a good place to re-allocate them.
	        mGLView.onResume();
	    }	
	
	
}
