using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerMovement : MonoBehaviour {


	private Rigidbody body;
	public float speed = 6.0F;
    public float jumpSpeed = 1F;
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

	void Update(){
		 // Store the input axes.
            // Cache the inputs.
		float hAxis = Input.GetAxis("Horizontal");
		float vAxis = Input.GetAxis("Vertical");
		
		Vector3 movement = new Vector3(hAxis, 0, vAxis) * speed * Time.deltaTime;
		
		if (Input.GetButton("Jump") && isGrounded){
			body.AddForce(new Vector3(0,jumpSpeed,0), ForceMode.Impulse);
			isGrounded = false;
		}

		

        body.MovePosition(transform.position + movement);

	/*	Vector3 moveDirection = new Vector3(Input.GetAxis("Horizontal"), 0, Input.GetAxis("Vertical"));
            moveDirection = transform.TransformDirection(moveDirection);
            moveDirection *= speed;
            if (Input.GetButton("Jump"))
                moveDirection.y = jumpSpeed;
			
			moveDirection.y -= gravity * Time.deltaTime;
        	controller.Move(moveDirection * Time.deltaTime);
*/
	}
}
