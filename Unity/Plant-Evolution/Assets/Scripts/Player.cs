using Scripts.Behaviours;
using System;
using System.Collections.Generic;
using TouchScript.Gestures;
using UnityEngine;
public class Player : MonoBehaviour {


    private PlayerInfo playerInfo = new PlayerInfo();
    private BehaviourLibrary behaviorLibrary = new BehaviourLibrary();

    public ScreenTransformGesture transformGesture;
    public TapGesture tapGesture;
    public TapGesture doubleTapGesture;

    public static Rigidbody playerBody = null;

    
    private static void setPlayer()
    {
        if (playerBody == null)
        {
            GameObject[] pList = GameObject.FindGameObjectsWithTag("Player");

            if (pList.Length > 0)
            {
                GameObject player = pList[0];
                playerBody = player.GetComponent<Rigidbody>();
            }
        }
    }


    void Start()
    {
        setPlayer();
        initBehaviors();
    }

    private void initBehaviors()
    {
        foreach (String behaviorName in playerInfo.getCurrentBehaviors())
        {
            learnBehavior(behaviorName);
        }
    }

    public void learnBehavior(String behaviorName)
    {
        IPlayerBehaviour behavior = behaviorLibrary.getBehaviorByName(behaviorName);
        if (behavior != null)
        {
            behavior.playerInfo = playerInfo;
            Scripts.Behaviours.IPlayerBehaviour.GESTURETYPE gestureType = behavior.behaviorGestureType;

            if (gestureType == IPlayerBehaviour.GESTURETYPE.DOUBLETAP)
            {
                doubleTapGesture.Tapped += behavior.handler;
            }
            else if (gestureType == IPlayerBehaviour.GESTURETYPE.TAP)
            {
                tapGesture.Tapped += behavior.handler;
            }
            else if (gestureType == IPlayerBehaviour.GESTURETYPE.TRANSFORM)
            {
                //transformGesture.Transformed += behavior.handler;
                transformGesture.TransformCompleted += behavior.handler;
            }
        }
    }


}   