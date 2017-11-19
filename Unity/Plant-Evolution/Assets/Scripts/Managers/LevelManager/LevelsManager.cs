using UnityEngine;
using System.Collections;

public class LevelsManager : MonoBehaviour
{
    LevelPosition levelPosition = new LevelPosition();
    // Use this for initialization
    void Start()
    {
        levelPosition.loadLevels();

    }

    // Update is called once per frame
    void Update()
    {

    }
}
