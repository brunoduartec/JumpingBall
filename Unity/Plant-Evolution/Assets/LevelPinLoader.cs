using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine;
using System;
using TouchScript.Gestures;

public class LevelPinLoader : MonoBehaviour {

	// Use this for initialization
	 private Collider coll;
	 public string levelToLoad;
	 private bool clicked = false;

	
	void Start () {
		coll = GetComponent<Collider>();
		SceneManager.sceneLoaded += openNewScene;
	}

    private void openNewScene(Scene arg0, LoadSceneMode arg1)
    {
		if (clicked)
		{
			GameObject boardObject = GameObject.FindGameObjectWithTag("board");
			BoardManager board = boardObject.GetComponent<BoardManager>();
			board.loadLevel(levelToLoad);
			clicked = false;
		}
        
    }


    // Update is called once per frame
   void Update () {
		Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
		RaycastHit hit;
		if (coll.Raycast(ray, out hit, 10.0F))
		{
			LevelPinLoader pinLoader = coll.gameObject.GetComponent<LevelPinLoader>();
			pinLoader.levelToLoad = levelToLoad;
			SceneManager.LoadScene("mainScene");
			clicked = true;	
		}
	}
}
