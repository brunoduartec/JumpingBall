using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LevelPosition : MonoBehaviour {

	// Use this for initialization

	public float radius = 1;

	void Start () {
		
		placeAddLevel(45,0 , "stage1");
		// placeAddLevel(90,0 , "stage2");
		// placeAddLevel(45,45, "stage3");

		// placeAddLevel(45,0 , "stage4");
		// placeAddLevel(90,0 , "stage5");
		
	
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void placeAddLevel(float alpha, float teta, string levelToLoad){

		float alphaRad = alpha * Mathf.Deg2Rad;
		float tetaRad  = teta  * Mathf.Deg2Rad;

		float x = radius * Mathf.Sin(alphaRad) * Mathf.Cos(tetaRad);
		float z = -radius * Mathf.Sin(alphaRad) * Mathf.Sin(tetaRad);
		float y = radius * Mathf.Cos(alphaRad);

		GameObject pin = Resources.Load ("levelPin") as GameObject;

		LevelPinLoader pinLoader = pin.GetComponent<LevelPinLoader>();
		pinLoader.levelToLoad = levelToLoad;

		pin.transform.position = new Vector3(x,y,z);
		Instantiate(pin, this.transform);
	}
}
