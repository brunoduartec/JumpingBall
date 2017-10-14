using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine;
using System;
using TouchScript.Gestures;

public class LevelPinLoader : MonoBehaviour {

	// Use this for initialization
	 public string levelToLoad;
	
	void Start () {
		SceneManager.sceneLoaded += openNewScene;
	}

    private void openNewScene(Scene arg0, LoadSceneMode arg1)
    {
		GameObject boardObject = GameObject.FindGameObjectWithTag("board");
		BoardManager board = boardObject.GetComponent<BoardManager>();
		board.loadLevel(levelToLoad);

    }



    // Update is called once per frame
   void Update () {
		Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
		RaycastHit hit;

        if (Physics.Raycast(ray, out hit) && Input.GetMouseButton(0)){
			LevelPinLoader pinLoader = hit.collider.gameObject.GetComponent<LevelPinLoader>();
			levelToLoad = pinLoader.levelToLoad;
			SceneManager.LoadScene("mainScene");
		}
	}
}
