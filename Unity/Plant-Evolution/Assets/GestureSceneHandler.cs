using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TouchScript.Gestures;

public class GestureSceneHandler : MonoBehaviour {

	public float RotationSpeed = 200f;
	public float ZoomSpeed = 10f;

	public ScreenTransformGesture TwoFingerMoveGesture;
	public ScreenTransformGesture ManipulationGesture;

	public Camera cam;
	private CameraHandler cameraHandler;
 	private void OnEnable()
	{
		TwoFingerMoveGesture.Transformed += twoFingerTransformHandler;
		ManipulationGesture.Transformed += manipulationTransformedHandler;
	}

	private void OnDisable()
	{
		ManipulationGesture.Transformed -= manipulationTransformedHandler;
	}
	// Use this for initialization
	void Start () {
		cameraHandler = cam.GetComponent<CameraHandler>();
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	
	private void manipulationTransformedHandler(object sender, System.EventArgs e)
	{
		var rotation = Quaternion.Euler(ManipulationGesture.DeltaPosition.y/Screen.height*RotationSpeed,
			-ManipulationGesture.DeltaPosition.x/Screen.width*RotationSpeed,
			ManipulationGesture.DeltaRotation);
	//	cam.transform.localRotation *= rotation;
		//cam.transform.localPosition += Vector3.forward*(ManipulationGesture.DeltaScale - 1f)*ZoomSpeed;

		cameraHandler.setDeltaCameraDistance(ManipulationGesture.DeltaScale);
	}

	private void twoFingerTransformHandler(object sender, System.EventArgs e)
	{

	}

}
