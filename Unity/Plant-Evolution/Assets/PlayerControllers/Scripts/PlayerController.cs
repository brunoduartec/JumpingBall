using UnityEngine;
using System.Collections;

public class PlayerController : MonoBehaviour {

    public float speed = 18;

    private Rigidbody rig;
    private bool isGrounded;

	// Use this for initialization
	void Start () 
    {
        rig = GetComponent<Rigidbody>();
	}
	
    void OnCollisionStay()
    {
        isGrounded = true;
    }
	// Update is called once per frame
	void Update () 
    {
        float hAxis = Input.GetAxis("Horizontal");
        float vAxis = Input.GetAxis("Vertical");

        Vector3 movement = new Vector3(hAxis, 0, vAxis) * speed * Time.deltaTime;



        rig.MovePosition(transform.position + movement);
	}
}
