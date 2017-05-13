using System.Collections;
using System.Collections.Generic;
using UnityEngine;



public class WanderBehavior : MonoBehaviour {
	public float speed= 5.0f;
	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		transform.Rotate( 0, speed* Time.deltaTime, 0);
	}
}
