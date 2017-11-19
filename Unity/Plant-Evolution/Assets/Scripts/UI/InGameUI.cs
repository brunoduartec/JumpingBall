using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using UnityEngine.UI;

public class InGameUI : MonoBehaviour {

	public Text levelName;
	public Text levelHint;
	public Text Energy;

	// Use this for initialization
	void Start () {
		Energy.text = "0";
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
