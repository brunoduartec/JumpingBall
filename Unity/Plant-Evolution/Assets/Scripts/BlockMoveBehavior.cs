using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BlockMoveBehavior : MonoBehaviour {

    private Rigidbody body;
	// Use this for initialization
	void Start () {
		body = GetComponent<Rigidbody>();
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	 public float pushPower = 2.0F;
     void OnCollisionEnter (Collision col){
        
         if (col.collider.tag.Equals("Player") && col.impactForceSum.y ==0 )
         {
            int a =0;
            Vector3 direction = col.impulse.normalized;
           body.transform.position -= direction * 0.5f;
         }

     }
    
}
