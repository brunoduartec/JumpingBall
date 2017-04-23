using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LevelLoader : MonoBehaviour {

	// Use this for initialization

	GameObject quoteObject;
	void Start () {
		quoteObject = GameObject.FindGameObjectWithTag("quote");
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
