using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraHandler : MonoBehaviour {

	// Use this for initialization
	private float distance = 5;
	void Start () {
		
	}

	public void setCameraDistance(float d)
	{
		distance = d;
	}
	
	// Update is called once per frame
	void Update () {

		Vector3 position = new Vector3(distance,distance + 3,-distance);
		transform.position = position;

		Vector3 lookPosition = new Vector3(0,3,0);
		transform.LookAt(lookPosition,Vector3.up);
	}
}
