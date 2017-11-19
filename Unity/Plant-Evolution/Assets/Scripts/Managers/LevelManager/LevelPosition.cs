using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LevelPosition : MonoBehaviour {

	// Use this for initialization

	public float radius = 3;



	void Start () {
        loadLevels();     
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    public void loadLevels()
    {
        placePinLoader(0, 0, "stage3", false);

        placePinLoader(30, 30, "stage2", false);
        placePinLoader(60, 45, "stage1", true);

        placePinLoader(90, 30, "stage4", false);
        placePinLoader(120, 0, "stage5", false);
    }

	LevelPinLoader createLevelPinLoader(string levelToLoad, bool enabled)
	{
		GameObject pin           = Resources.Load ("levelPin") as GameObject;
		LevelPinLoader pinLoader = pin.GetComponent<LevelPinLoader>();
		
        pinLoader.levelToLoad = levelToLoad;
        pinLoader.enabled = enabled;

		return pinLoader;
	}

	void placePinLoader(float alpha, float teta, string levelToLoad, bool enabled){

		float alphaRad = alpha * Mathf.Deg2Rad;
		float tetaRad  = teta  * Mathf.Deg2Rad;

		float x = radius * Mathf.Sin(alphaRad) * Mathf.Cos(tetaRad);
		float z = -radius * Mathf.Sin(alphaRad) * Mathf.Sin(tetaRad);
		float y = radius * Mathf.Cos(alphaRad);

		LevelPinLoader pinLoader = createLevelPinLoader(levelToLoad, enabled);
		
		pinLoader.transform.position = new Vector3(x,y,z);
		Instantiate(pinLoader, this.transform);
	}
}
