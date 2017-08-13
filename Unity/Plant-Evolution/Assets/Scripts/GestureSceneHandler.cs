using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TouchScript.Gestures;

public class GestureSceneHandler : MonoBehaviour {

	public float RotationSpeed = 1;
	public float ZoomSpeed = 10f;

	public ScreenTransformGesture ManipulationGesture;

	public Camera cam;
 	private void OnEnable()
	{
		ManipulationGesture.Transformed += manipulationTransformedHandler;
	}

	private void OnDisable()
	{
		ManipulationGesture.Transformed -= manipulationTransformedHandler;
	}
	// Use this for initialization
	void Start () {
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	
	private void manipulationTransformedHandler(object sender, System.EventArgs e)
	{
		// var rotation = Quaternion.Euler(ManipulationGesture.DeltaPosition.y/Screen.height*RotationSpeed,
		// 								-ManipulationGesture.DeltaPosition.x/Screen.width*RotationSpeed,
		// 								ManipulationGesture.DeltaRotation);
		
		float xDelta = ManipulationGesture.DeltaPosition.x/Screen.width*RotationSpeed;
		float yDelta = ManipulationGesture.DeltaPosition.y/Screen.height*RotationSpeed;

		// Vector3 lookPosition = new Vector3(0,0,0);
		// cam.transform.localPosition += new Vector3(xDelta,0,xDelta);

		// cam.transform.LookAt(lookPosition,Vector3.up);

		transform.Rotate( 0, -xDelta, 0);
 

		//cam.transform.localRotation *= rotation;
		//cam.transform.localPosition += Vector3.forward*(ManipulationGesture.DeltaScale - 1f)*ZoomSpeed;

	}

}
