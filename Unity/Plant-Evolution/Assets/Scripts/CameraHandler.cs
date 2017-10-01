using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TouchScript.Gestures;

public class CameraHandler : MonoBehaviour {

	// Use this for initialization
	private float distance = 5;

	private bool wasInitialized = false;

	void Start () {

	}

	public void setDeltaCameraDistance(float delta)
	{
		setCameraDistance(distance + delta);
	}

	public void setCameraDistance(float d)
	{
		distance = d;
		wasInitialized = false;
	}
	
	// Update is called once per frame
	void Update () {

		if (!wasInitialized)
		{
			wasInitialized = true;

			// MOCK - aqui tambem colocar a posicao inicial
			Vector3 position = new Vector3(distance, distance + 3, -distance);
			transform.position = position;

			// MOCK - colocar o centro da fase aqui
			Vector3 lookPosition = new Vector3(0,3,0);
			transform.LookAt(lookPosition,Vector3.up);
		}
	}
}
