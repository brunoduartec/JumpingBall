using System;
using UnityEngine;

[Serializable]
public class StageCell{
    public Vector3 position;
    public Vector3 scale;
    public Vector3 rotation;

    public string type;

    public  string ToString(){
        return "position:" + position.ToString() + "  type:" + type;
    }
    
}