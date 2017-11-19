using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class BackkeyManager : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKey(KeyCode.Escape)){
			Scene activeScene = SceneManager.GetActiveScene();
			int activeSceneIndex = activeScene.buildIndex;

			if (activeSceneIndex >0)
			{
				SceneManager.LoadScene(activeSceneIndex-1);
			}
			else
			{
				Application.Quit();
			}	
  		}
		
	}
}
