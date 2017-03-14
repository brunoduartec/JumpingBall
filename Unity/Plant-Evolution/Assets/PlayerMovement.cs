using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerMovement : MonoBehaviour {


	private Rigidbody body;
	public float speed = 6.0F;
    public float jumpSpeed = 10F;
    public float gravity = 20.0F;

	private bool isGrounded;

	//inside class
	Vector2 firstPressPos;
	Vector2 secondPressPos;
	Vector2 currentSwipe;

	Vector2 swipeDirection;
	
	float tapTimeWindow = 0;
	float tapTimeDelta = 100;

	Vector3 movement;
	bool changedDirection;
	bool startMoving = false;

	float waitTime = 0f;

	int tapCount;

	float lasthAxis = -1;
	float lastvAxis = -1;
	
	Animation animate;

	// Use this for initialization
	void Start () {
		body = GetComponent<Rigidbody>();
		waitTime = 100.0f;
		swipeDirection = Vector2.zero;
		animate = GetComponent<Animation>();
	}
	
	// Update is called once per frame
	
   void OnCollisionStay()
    {
        isGrounded = true;
    }

	

	public void Tap()
	{

		if ( tapTimeWindow > 0 ) {
         tapTimeWindow -= Time.deltaTime;
		}
		else {
			tapCount = 0;
		}
		if ( tapTimeWindow > 0 )
		tapCount++;
		else
		{
		tapCount = 1;
		tapTimeWindow = tapTimeDelta;
		}

	}
	public void Update()
	{
	//	swipeDirection = Vector2.zero;
		

		//if (Time.time < waitTime && waitTime != 0f)
		//if(Time.frameCount%waitTime==0)
		{

			if(Input.touches.Length > 0)
			{

				Tap();

				Touch t = Input.GetTouch(0);

				if(t.phase == TouchPhase.Began)
				{
					//save began touch 2d point
					firstPressPos = new Vector2(t.position.x,t.position.y);

					tapCount = t.tapCount;


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
				}
			}

		}

	}
	void FixedUpdate(){
		 // Store the input axes.
            // Cache the inputs.
			//Debug.Log("VLAUS" + Time.frameCount + " " + waitTime );
		//	Debug.Log((float)(Time.frameCount)%waitTime);
		//if(Time.frameCount%waitTime==0)
	
		
		{

			float hAxis = 0;
			float vAxis = 0;
		
			if  (Application.platform == RuntimePlatform.Android)
			{
				hAxis = swipeDirection.x;
				vAxis = swipeDirection.y;	
			}
			else
			{
				hAxis = Input.GetAxis("Horizontal");
				vAxis = Input.GetAxis("Vertical");
			}

		

			if ( (hAxis != lasthAxis || vAxis !=lastvAxis)	)
			{
				changedDirection = true;
				
				lasthAxis = hAxis;
				lastvAxis = vAxis;	
				
				Debug.Log("CHANGED" + vAxis + " " + hAxis);
				
			}
			
/*
			if (vAxis == 0 && hAxis == 0)
			{
				hAxis = lasthAxis;
				vAxis = lastvAxis;
			}

 */
			Vector3 rawMovement = new Vector3(hAxis, 0, vAxis);

			//if (movement.magnitude > 0) 
			if (changedDirection && movement.magnitude > 0)
			{
				movement = rawMovement * speed;
			}

			if (( Input.GetButton("Jump") ) && isGrounded){
				//body.
				//body.MovePosition(transform.position + new Vector3(0,jumpSpeed,0));
				body.AddForce(new Vector3(0,jumpSpeed,0), ForceMode.Impulse);
				isGrounded = false;
			}

			if (movement.magnitude > 0)
			{
				Debug.Log(movement + " " + speed);
				body.AddForce(movement,ForceMode.Impulse);
				Quaternion rotation = Quaternion.LookRotation(rawMovement, Vector3.up);
				transform.rotation = rotation;
			}
			

		}
	}
}
