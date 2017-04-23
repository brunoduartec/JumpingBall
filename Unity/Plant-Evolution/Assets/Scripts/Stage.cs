using System;
using UnityEngine;

[Serializable]
public class Stage {
    // "player":{
    //     "position":{
    //         "x":0,
    //         "y":0,
    //         "z":0
    //     }
    // },
    // "level":{
    //     "name": "Stage 01",
    //     "quote": "Push Me"
    // }
    public string levelName;
    public string quote;

    public string playerType;
    public Vector3 playerPosition;
    public int boardLength;

    public StageCell[] board;
   
    
}