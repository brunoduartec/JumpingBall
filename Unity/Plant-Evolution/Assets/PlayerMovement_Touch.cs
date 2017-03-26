using System.Collections;
using System.Collections.Generic;
using UnityEngine;


using TouchScript.Gestures;
using System;
 

public class PlayerMovement_Touch : MonoBehaviour {


	public float Speed;

	public ScreenTransformGesture OneFingerMoveGesture;

	private GameObject player;

	private Rigidbody body;

	 private void OnEnable()
	{
		OneFingerMoveGesture.Transformed += oneFingerTransformHandler;
		GetComponent<TapGesture>().Tapped += tappedHandler;
		
	}

    private void OnDisabled()
	{
		OneFingerMoveGesture.Transformed -= oneFingerTransformHandler;
		GetComponent<TapGesture>().Tapped -= tappedHandler;
	}

    private void oneFingerTransformHandler(object sender, EventArgs e)
    {

		Vector3 currentSwipe = OneFingerMoveGesture.DeltaPosition;
		Vector2 swipeDirection;

		//swipe upwards
		if(currentSwipe.x >0 &&  currentSwipe.y< 0)
		{
			swipeDirection = new Vector2(1,0);
		}
		else if (currentSwipe.x <0 &&  currentSwipe.y> 0)
		{
			swipeDirection = new Vector2(-1,0);
		}
		else if (currentSwipe.x >0 &&  currentSwipe.y > 0)
		{
			swipeDirection = new Vector2(0,1);
		}
		else
		{
			swipeDirection = new Vector2(0,-1);
		}
	
		
		Vector3 rawMovement = new Vector3(swipeDirection.x, 0, swipeDirection.y);
		Vector3 movement = rawMovement * Speed;


		if (movement.magnitude > 0)
		{
			Debug.Log(movement + " " + Speed);
			body.AddForce(movement,ForceMode.Impulse);
			Quaternion rotation = Quaternion.LookRotation(rawMovement, Vector3.up);
			body.transform.rotation = rotation;
		}

        //throw new NotImplementedException();
    }

	private void tappedHandler(object sender, EventArgs e)
    {
       Debug.Log("--------TAPPED----");
    }

	


    // Use this for initialization
    void Start () {
		player = GameObject.FindGameObjectsWithTag("Player")[0];
		body = player.GetComponent<Rigidbody>();
	}
	
	// Update is called once per frame
	void Update () {
		
	}

}