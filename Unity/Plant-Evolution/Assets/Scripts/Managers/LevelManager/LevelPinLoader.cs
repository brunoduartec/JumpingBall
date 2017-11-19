using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine;
using System;
using TouchScript.Gestures;

public class LevelPinLoader : MonoBehaviour {

	// Use this for initialization
    public string levelToLoad;
    public bool enabled = false;

    public Color enabledColor = Color.yellow;
    public Color disabledColor = Color.gray;

    private TapGesture gesture;

    private void OnEnable(){
        GetComponent<TapGesture>().Tapped += pinTapped;
    }

    private void OnDisable(){
        GetComponent<TapGesture>().Tapped -= pinTapped;
    }

    void Start()
    {
        SceneManager.sceneLoaded += openNewScene;
    }

    private void openNewScene(Scene arg0, LoadSceneMode arg1)
    {
        if (this.enabled)
        {
            GameObject boardObject = GameObject.FindGameObjectWithTag("board");
            BoardManager board = boardObject.GetComponent<BoardManager>();

            board.loadLevel(levelToLoad);
        }
    }
    private void pinTapped(object sender, EventArgs e)
    {
        if (enabled)
        {
            SceneManager.LoadScene("mainScene");
        }    
    }

    private void Update()
    {
        if (enabled)
        {
            GetComponent<Light>().color = enabledColor;
        }
        else {
            GetComponent<Light>().color = disabledColor;
        }
    }


}
