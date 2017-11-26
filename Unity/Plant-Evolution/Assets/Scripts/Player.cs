using Scripts.Behaviours;
using System;
using System.Collections.Generic;
using TouchScript.Gestures;
using UnityEngine;
public class Player : MonoBehaviour {


    public PlayerInfo playerInfo;
    private BehaviourLibrary behaviorLibrary = new BehaviourLibrary();

    public ScreenTransformGesture transformGesture;
    public TapGesture tapGesture;
    public TapGesture doubleTapGesture;

    private static Rigidbody playerBody;

    public static Rigidbody getPlayer()
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

        return playerBody;
    }


    void Start()
    {
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
                if (doubleTapGesture != null)
                {
                    doubleTapGesture.Tapped += behavior.handler;
                }
            }
            else if (gestureType == IPlayerBehaviour.GESTURETYPE.TAP)
            {
                if (tapGesture != null)
                {
                    tapGesture.Tapped += behavior.handler;
                }
            }
            else if (gestureType == IPlayerBehaviour.GESTURETYPE.TRANSFORM)
            {
                if (transformGesture != null)
                {
                    transformGesture.TransformCompleted += behavior.handler;
                }
            }
        }
    }


}   