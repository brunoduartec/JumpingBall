using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TouchScript.Gestures;
using System;

public class TapHandler : MonoBehaviour {

	// Use this for initialization

	void OnEnable()
	{
		 GetComponent<TapGesture>().Tapped += tappedHandler;
	}
	void OnDisable()
	{
		// don't forget to unsubscribe
		GetComponent<TapGesture>().Tapped -= tappedHandler;
	}

    private void tappedHandler(object sender, EventArgs e)
    {
    }

    void Start () {
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
