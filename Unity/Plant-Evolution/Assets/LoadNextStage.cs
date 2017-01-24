using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LoadNextStage : MonoBehaviour {

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
			GameObject boardObject = GameObject.FindGameObjectWithTag("board");
			BoardManager board = boardObject.GetComponent<BoardManager>();
			board.loadNextLevel();


        }
    }
}
