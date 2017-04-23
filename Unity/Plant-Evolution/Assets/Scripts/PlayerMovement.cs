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


	// Use this for initialization
	void Start () {
		body = GetComponent<Rigidbody>();
	
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
		
	}
}
