using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerMovement : MonoBehaviour {


	private Rigidbody body;
	public float speed = 6.0F;
    public float jumpSpeed = 10F;
    public float gravity = 20.0F;

	private bool isGrounded;
	

	// Use this for initialization
	void Start () {
		body = GetComponent<Rigidbody>();
	}
	
	// Update is called once per frame
	
   void OnCollisionStay()
    {
        isGrounded = true;
    }

	//inside class
	Vector2 firstPressPos;
	Vector2 secondPressPos;
	Vector2 currentSwipe;

	Vector2 swipeDirection;
	
	public void Update()
	{
		swipeDirection = Vector2.zero;

		if(Input.touches.Length > 0)
		{
			Touch t = Input.GetTouch(0);
			if(t.phase == TouchPhase.Began)
			{
				//save began touch 2d point
				firstPressPos = new Vector2(t.position.x,t.position.y);
			}
			if(t.phase == TouchPhase.Ended)
			{
				//save ended touch 2d point
				secondPressPos = new Vector2(t.position.x,t.position.y);
							
				//create vector from the two points
				currentSwipe = new Vector3(secondPressPos.x - firstPressPos.x, secondPressPos.y - firstPressPos.y);
				
				//normalize the 2d vector
				currentSwipe.Normalize();
	
				//swipe upwards
				//if(currentSwipe.y > 0 && currentSwipe.x > -0.5f &&  currentSwipe.x < 0.5f)
				if(currentSwipe.x >0 &&  currentSwipe.y< 0) ///dir.x > 0 && dir.y < 0
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
				/*
				//swipe down
				if(currentSwipe.y < 0 &&  currentSwipe.x > -0.5f &&  currentSwipe.x < 0.5f)
				{
					swipeDirection = new Vector2(0,-1);
				}
				//swipe left
				if(currentSwipe.x < 0 &&  currentSwipe.y > -0.5f &&  currentSwipe.y < 0.5f)
				{
					swipeDirection = new Vector2(-1,0);
				}
				//swipe right
				if(currentSwipe.x > 0 &&  currentSwipe.y > -0.5f &&  currentSwipe.y < 0.5f)
				{
					swipeDirection = new Vector2(1,0);
				}
				*/
			}
		}
		
	}
	void FixedUpdate(){
		 // Store the input axes.
            // Cache the inputs.
		float hAxis = 0;
		float vAxis = 0;
		if  (Application.platform == RuntimePlatform.Android)
		{
			hAxis = swipeDirection.x * 3;
			vAxis = swipeDirection.y * 3;
		}
		else
		{
			hAxis = Input.GetAxis("Horizontal");
			vAxis = Input.GetAxis("Vertical");
		}
		Vector3 rawMovement = new Vector3(hAxis, 0, vAxis);
		

		Vector3 movement = rawMovement * speed * Time.deltaTime;
		
		if (Input.GetButton("Jump") && isGrounded){
			//body.
			//body.MovePosition(transform.position + new Vector3(0,jumpSpeed,0));
			body.AddForce(new Vector3(0,jumpSpeed,0), ForceMode.Impulse);
			isGrounded = false;
		}

	
     
		if (movement.magnitude > 0) {
			body.MovePosition(transform.position + movement);
			Quaternion rotation = Quaternion.LookRotation(rawMovement, Vector3.up);
			transform.rotation = rotation;
		}
	}
}
