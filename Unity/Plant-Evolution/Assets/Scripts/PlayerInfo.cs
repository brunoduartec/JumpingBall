using UnityEngine;
using System.Collections;
using System;
using System.Collections.Generic;

/// <summary>
/// This will be used in the future to persistency
/// </summary>
/// 
public class PlayerInfo : MonoBehaviour
{
    public int energy;
    public int speed;
    public int jumpSpeed;

    public List<String> currentBehaviors = new List<String>();

    public List<String> getCurrentBehaviors()
    {
        return currentBehaviors;
    }

    private void initValues()
    {
        this.speed = 5;
        this.energy = 3;
        this.jumpSpeed = 5;
    }

    private void initBehaviors()
    {
        currentBehaviors.Add("Walk");
        currentBehaviors.Add("Jump");
    }

    public PlayerInfo()
    {
        initBehaviors();
        initValues();
    }

}
