using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BlockMoveBehavior : MonoBehaviour {

    private Rigidbody body;
    public float intensity = 1;
    public float pushPower = 2.0F;
	// Use this for initialization
	void Start () {
		body = GetComponent<Rigidbody>();
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	 
     void OnCollisionEnter (Collision col){
         var contact = col.contacts[0];



         if (col.collider.tag.Equals("Player") && Mathf.Abs(contact.normal.y) < Mathf.Epsilon )
         {
            Vector3 direction = col.contacts[0].normal;//col.impulse.normalized;
            body.transform.position += direction * intensity;
            //body.AddForce(-direction * intensity);
         }

     }
    
}
