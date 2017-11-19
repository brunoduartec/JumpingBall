using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TouchScript.Gestures;
using System;

public class TapHandler : MonoBehaviour {

	// Use this for initialization

	private TapGesture gesture;

	private Rigidbody body;

	private void OnEnable()
	{
		body    = GetComponent<Rigidbody>();
		gesture = GetComponent<TapGesture>();
		
		gesture.Tapped += tappedHandler;
	}

	private void OnDisable()
	{
		gesture.Tapped -= tappedHandler;
	}

 	private void tappedHandler(object sender, System.EventArgs e)
	{		
		body.AddForce(new Vector3(0,10,0), ForceMode.Impulse);	
	}

    void Start () {
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
