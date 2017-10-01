using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TouchScript.Gestures;

public class GestureSceneHandler : MonoBehaviour {

	public float rotationSpeed = 1;
	public float zoomSpeed = 10f;

	public ScreenTransformGesture ManipulationGesture;

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
		float xDelta = ManipulationGesture.DeltaPosition.x/Screen.width*rotationSpeed;
		float yDelta = ManipulationGesture.DeltaPosition.y/Screen.height*rotationSpeed;

		transform.Rotate( 0, -xDelta, 0);
	}

}
