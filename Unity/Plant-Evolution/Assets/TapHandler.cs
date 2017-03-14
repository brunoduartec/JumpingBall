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
	  Debug.Log("---------ENABLE-------");
	}
	void OnDisable()
	{
		// don't forget to unsubscribe
		GetComponent<TapGesture>().Tapped -= tappedHandler;
	  Debug.Log("---------DISABLE-------");
	}

    private void tappedHandler(object sender, EventArgs e)
    {
       Debug.Log("---------TAPPED-------");
    }

    void Start () {
		Debug.Log("-------STARTED------");
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
