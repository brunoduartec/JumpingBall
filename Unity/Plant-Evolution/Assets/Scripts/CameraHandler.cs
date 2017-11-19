using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TouchScript.Gestures;

public class CameraHandler : MonoBehaviour {

	// Use this for initialization
	private float distance = 5;

	private bool wasInitialized = false;

	public float timeToMoveCamera = 0.5f;

	private float initialCameraSize;

	private Camera cam;

	void Start () {
		cam = GetComponent<Camera>();
		initialCameraSize = cam.orthographicSize;
	}

	public void setDeltaCameraDistance(float delta)
	{
		setCameraDistance(distance + delta);
	}

	public void setCameraDistance(float d)
	{
		distance = d;
		wasInitialized = false;
	}

	public void zoomToGameObject(GameObject objectToZoom)
	{
		StartCoroutine(zoomTo(objectToZoom));
	}

	public void initializeZoom()
	{
		StartCoroutine(zoomOut());
	}

	IEnumerator zoomOut()
	{
		yield return null;
	}

	IEnumerator zoomTo(GameObject objectToZoom)
	{
        float t = 0;
		float endSize = 2;

		transform.LookAt(objectToZoom.transform.position, Vector3.up);
		transform.position = objectToZoom.transform.position += new Vector3(1,0,1);

        while (t < timeToMoveCamera)
        {
            float fraction = t / timeToMoveCamera;

            cam.orthographicSize = Mathf.Lerp(initialCameraSize, endSize, fraction);
            t += Time.deltaTime;
            yield return null;
        }
        cam.orthographicSize = endSize;
	}
	
	// Update is called once per frame
	void Update () {

		if (!wasInitialized)
		{
			wasInitialized = true;

			// MOCK - aqui tambem colocar a posicao inicial
			Vector3 position = new Vector3(distance, distance + 3, -distance);
			transform.position = position;

			// MOCK - colocar o centro da fase aqui
			Vector3 lookPosition = new Vector3(0,3,0);
			transform.LookAt(lookPosition,Vector3.up);
		}
	}
}
