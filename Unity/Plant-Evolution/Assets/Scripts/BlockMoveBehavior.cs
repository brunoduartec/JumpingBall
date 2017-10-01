using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BlockMoveBehavior : MonoBehaviour {

    private Rigidbody body;
    public float intensity = 0.5f;
	// Use this for initialization
	void Start () {
		body = GetComponent<Rigidbody>();
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	 public float pushPower = 2.0F;
     void OnCollisionEnter (Collision col){
        
         if (col.collider.tag.Equals("Player") && col.relativeVelocity.y ==0 )
         {
            Vector3 direction = col.impulse.normalized;
            body.transform.position -= direction * intensity;
         }

     }
    
}
