using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraHandler : MonoBehaviour {

	// Use this for initialization
	public float distance = 10;
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		Vector3 position = new Vector3(distance,distance,-distance);
		transform.position = position;

		transform.LookAt(Vector3.zero,Vector3.up);
	}
}
