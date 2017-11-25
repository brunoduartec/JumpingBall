using System.Collections;
using System.Collections.Generic;
using UnityEngine;


using TouchScript.Gestures;
using System;
 

public class PlayerMovement_Touch : MonoBehaviour {
	public float Speed;
	public float jumpSpeed;

	private bool directionJump = false;
	private Vector3 direction;

	public ScreenTransformGesture OneFingerMoveGesture;

	private GameObject player;

	private Rigidbody body;

	private bool isGrounded;
    private bool movementStarted;

	private enum MOVIMENT{
		IDLE,
		MOVING
	};

	private MOVIMENT playerMoviment;

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

	void OnCollisionStay()
    {
        isGrounded = true;
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
	
		
		direction = new Vector3(swipeDirection.x, 0, swipeDirection.y);
		Vector3 movement = direction * Speed;


		if(playerMoviment == MOVIMENT.IDLE)
		{
			movementStarted = true;
			playerMoviment = MOVIMENT.MOVING;
		}
	
	

		if (movementStarted && body)
		{
		    body.AddForce(movement);
			//body.transform.position += movement;
			Quaternion rotation = Quaternion.LookRotation(direction, Vector3.up);
			body.transform.rotation = rotation;
			movementStarted = false;
		}
		
    }

	private void tappedHandler(object sender, EventArgs e)
    {
		body.AddForce(new Vector3(0,jumpSpeed,0), ForceMode.Impulse);	
		isGrounded = false;
		directionJump = true;
    }

	


    // Use this for initialization
    void Start () {
		setPlayer();
	}

	public void setPlayer(){
		GameObject[] pList = GameObject.FindGameObjectsWithTag("Player");
		
		if (pList.Length>0)
		{
			player = pList[0];
			body = player.GetComponent<Rigidbody>();
			movementStarted = false;
			playerMoviment = MOVIMENT.IDLE;
		}
	}

	
	// Update is called once per frame
	void Update () {
		
	}
	void FixedUpdate(){
		if (!body)
		{
			setPlayer();
		}
		else
		{
			if (body.velocity.magnitude == 0)
			{
				playerMoviment = MOVIMENT.IDLE;
			}
			if (isGrounded && body.velocity.y < 0 && directionJump){
				Vector3 directionJumpForce = direction * 2;
				body.AddForce(directionJumpForce,ForceMode.Impulse);
				directionJump = false;
			}	
		}
	}

}