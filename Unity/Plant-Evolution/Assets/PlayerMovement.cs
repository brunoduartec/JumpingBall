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

	private Vector2 swipeDirection;
	public void setSwipeDirection(Vector2 direction)
	{
		swipeDirection = direction;
	}
	
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

	public void Update()
	{


	}
	void FixedUpdate(){
	
	
		
		{

			float hAxis = 0;
			float vAxis = 0;
		
			// if  (Application.platform == RuntimePlatform.Android)
			// {
				hAxis = swipeDirection.x;
				vAxis = swipeDirection.y;	
			// }
			// else
			// {
			// 	hAxis = Input.GetAxis("Horizontal");
			// 	vAxis = Input.GetAxis("Vertical");
			// }

		

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
