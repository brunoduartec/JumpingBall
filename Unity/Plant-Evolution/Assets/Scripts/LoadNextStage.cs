using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine;
using System;

public class LoadNextStage : MonoBehaviour {

	// Use this for initialization
	void Start () {
		SceneManager.sceneLoaded += openNewScene;
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	
	void OnCollisionEnter (Collision col)
    {
        if (col.gameObject.name.Contains("menu"))
		{
			SceneManager.LoadScene("worldScene");
		}
		else if(col.gameObject.tag.Equals("Player"))
        {
			GameObject boardObject = GameObject.FindGameObjectWithTag("board");
			BoardManager board = boardObject.GetComponent<BoardManager>();
			board.loadNextLevel();
        }
    }

    private void openNewScene(Scene arg0, LoadSceneMode arg1)
    {
		if (arg0.name == "mainScene")
		{
			GameObject boardObject = GameObject.FindGameObjectWithTag("board");
			BoardManager board = boardObject.GetComponent<BoardManager>();
			board.loadLevel("stage2");
		}  	
    }
}
