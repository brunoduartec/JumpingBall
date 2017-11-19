using UnityEngine;
using System.Collections;
using TouchScript.Gestures;
using System;

public class WorldMovimentHandler : MonoBehaviour
{
    // Use this for initialization

    private TransformGesture gesture;

    private float rotationSpeed = 10;
    
    void Start()
    {
        
    }

    void OnEnable()
    {
        gesture = GetComponent<TransformGesture>();
        gesture.TransformStarted += transformStartedHandler;
        gesture.Transformed += transformHandler;
    }

    private void transformHandler(object sender, EventArgs e)
    {
        float deltaPositionX = gesture.DeltaPosition.x * rotationSpeed;//(gesture.ScreenPosition - gesture.PreviousScreenPosition); // 
        this.transform.Rotate(0, -deltaPositionX, 0);
    }

    void OnDisable()
    {
        gesture.TransformStarted -= transformStartedHandler;
    }


    private void transformStartedHandler(object sender, EventArgs e)
    {
       // Vector3 deltaPosition = gesture.DeltaPosition;//(gesture.ScreenPosition - gesture.PreviousScreenPosition); // Screen.width * rotationSpeed;
       // this.transform.Rotate(0, -deltaPosition.x, 0);
    }


    // Update is called once per frame
    void Update()
    {

    }
}
