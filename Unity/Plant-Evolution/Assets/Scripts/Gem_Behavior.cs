using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Gem_Behavior : MonoBehaviour {

	// Use this for initialization

	void Start () {

	}
	
	// Update is called once per frame
	void FixedUpdate(){
		transform.Rotate( 0, 10* Time.deltaTime, 0);
	}
 
}
