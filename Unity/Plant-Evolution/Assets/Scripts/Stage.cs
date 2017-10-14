using System;
using UnityEngine;

[Serializable]
public class Stage {
    public string levelName;
    public string quote;
    public string playerType;
    public Vector3 playerPosition;
    public StageCell[] board;
}