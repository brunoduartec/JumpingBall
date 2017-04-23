using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class InputControl : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKey(KeyCode.Escape)){
  			//Escape button codes
			  Scene activeScene = SceneManager.GetActiveScene();

			  SceneManager.LoadScene("menuScene");
  		}
		
	}
}
