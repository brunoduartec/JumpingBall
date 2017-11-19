using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerReload : MonoBehaviour {


	private Vector3 playerInitialPos = new Vector3(2,2,2);
	private GameObject collider;

	// Use this for initialization
	void Start () {

	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void OnCollisionEnter (Collision col)
    {
        if(col.gameObject == this.collider)
        {
			transform.position = this.playerInitialPos;
        }
    }

	public void setCollider(GameObject collider)
	{
		this.collider = collider;
	}

	public void setPlayerInitialPos(Vector3 pos)
	{
		this.playerInitialPos = pos;
	}

}
