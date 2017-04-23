using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerReload : MonoBehaviour {


	private Vector3 playerInitialPos = new Vector3(2,2,2);
	// Use this for initialization
	void Start () {

	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void OnCollisionEnter (Collision col)
    {
        if(col.gameObject.name.Contains("player"))
        {
			GameObject player = GameObject.FindGameObjectsWithTag("Player")[0];
			player.transform.position = playerInitialPos;


        }
    }

	public void setPlayerInitialPos(Vector3 pos)
	{
		this.playerInitialPos = pos;
	}

}
