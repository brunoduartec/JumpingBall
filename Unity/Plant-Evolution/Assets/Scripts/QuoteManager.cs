using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class QuoteManager : MonoBehaviour {

	// Use this for initialization
	public Text quoteLabel = null;
	public Text levelLabel = null;
	private Canvas canvas;
	void Start () {
			// quoteLabel =gameObject.GetComponent<Text>();
	}
	
	// Update is called once per frame
	void Update () {
		// quoteLabel.text = "BOLINHA";
	}

	public void showQuoteOverlay(string quote, string levelName)
	{
	
		quoteLabel.text = quote;
		levelLabel.text = levelName;

		canvas = GetComponent<Canvas>();

		canvas.enabled = true;
	}

	public void hideQuoteOverlay()
	{
		canvas.enabled = false;
	}
}
