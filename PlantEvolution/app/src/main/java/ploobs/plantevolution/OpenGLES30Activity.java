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

public class OpenGLES30Activity extends Activity {

	
	private MyGLSurfaceView mGLView;
	private ImageView bpush,bjump,brestart;
/*
public void setContextImage(MyGLRenderer.GAMECONTEXT cc)
{
	switch (cc) {
		case PLAYER:
			b.setImageResource(R.drawable.ballcontext);
			break;
		case BLOCK:
			b.setImageResource(R.drawable.boxcontext);
			break;
	}


}
*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create a GLSurfaceView instance and set it
		// as the ContentView for this Activity
		mGLView = new MyGLSurfaceView(this);
		//setContentView(mGLView);
		//setContentView(R.layout.activity_open_gles20);
		setContentView(mGLView);

		mGLView.getmRenderer().set_activityhandle(this);

		LinearLayout ll = new LinearLayout(this);

// specifying vertical orientation
		ll.setOrientation(LinearLayout.HORIZONTAL);

/*
		bjump = new ImageButton(this);
		bjump.setBackgroundColor(0);

		bjump.setImageResource(R.drawable.ball);


		ll.addView(bjump);
		ll.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);


		bjump.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				//mGLView.getmRenderer().setAction(MyGLRenderer.PLAYERRACTION.JUMP);

				// setContextImage(gt);

			}
		});


		bpush = new ImageButton(this);
		bpush.setBackgroundColor(0);

		bpush.setImageResource(R.drawable.box);


		ll.addView(bpush);
		//ll.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);

		bpush.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
		//		mGLView.getmRenderer().setAction(MyGLRenderer.PLAYERRACTION.PUSH);


			}
		});






		this.addContentView(ll, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));


		LinearLayout llr = new LinearLayout(this);

// specifying vertical orientation
		llr.setOrientation(LinearLayout.HORIZONTAL);

	/*
			brestart = new ImageButton(this);
		brestart.setBackgroundColor(0);

		brestart.setImageResource(R.drawable.restart);


		llr.addView(brestart);
		llr.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);

		brestart.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				mGLView.getmRenderer().RestartStage();


			}
		});
		this.addContentView(llr, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
*/


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
