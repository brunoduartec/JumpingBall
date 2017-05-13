using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine;
using System;
using TouchScript.Gestures;

public class LevelPinLoader : MonoBehaviour {

	// Use this for initialization
	 public Collider coll;
	 public string levelToLoad;
	 private bool clicked = false;

	 private Camera activeCamera;
	
	void Start () {
		coll = GetComponent<Collider>();
		activeCamera = GameObject.FindGameObjectWithTag("MainCamera").GetComponent<Camera>(); // GameObject.Find("Scene Camera").GetComponent<Camera>();
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
            if (coll.Raycast(ray, out hit, 100.0F))
			{
				LevelPinLoader pinLoader = coll.gameObject.GetComponent<LevelPinLoader>();
				pinLoader.levelToLoad = levelToLoad;
				SceneManager.LoadScene("mainScene");
				clicked = true;	
				print("--------ENTROU-----");
			}
	}
}
