using UnityEngine;
using System.Collections;
using System;
using System.Collections.Generic;

/// <summary>
/// This will be used in the future to persistency
/// </summary>
public class PlayerInfo
{
    public int energy
    {
        get;
        set;
    }

    public int speed
    {
        get;
        set;
    }

    public int jumpSpeed
    {
        get;
        set;
    }

    private List<String> currentBehaviors = new List<String>();

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
