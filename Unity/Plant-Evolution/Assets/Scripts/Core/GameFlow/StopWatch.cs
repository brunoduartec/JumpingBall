using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class StopWatch : IWait
{
	private float totalTime;

	public float remainingTime
	{
		get;
		private set;
	}
	public MonoBehaviour monoBehaviour
	{
		get;
		set;
	}

	protected override IEnumerator WaitAndDo()
	{
		while (remainingTime > 0 && !paused)
		{
			remainingTime -= Time.deltaTime;
			yield return new WaitForSeconds(Time.deltaTime);
		}

		if(!paused)
		{
			try
			{
				Action();
			}
			catch (System.Exception)
			{
				Debug.LogWarning("[StopWatch]: Tryed to run invalid action, probably trying to access destroyed object");
			}
		}
		else
		{
			Debug.Log("[StopWatch]: clock paused at "+remainingTime);
		}
	}

	public StopWatch(MonoBehaviour mb, float time, WaitAndDoCallback callback)
	{
		totalTime = time;
		remainingTime = totalTime;
		monoBehaviour = mb;
		Action = callback;
		mb.StartCoroutine(WaitAndDo());
	}

	public override void Pause()
	{
		paused = true;
	}

	public override void Resume()
	{
		try
		{
			Debug.Log("resuming clock "+remainingTime);
			paused = false;
			monoBehaviour.StartCoroutine(WaitAndDo());
		}
		catch (System.Exception)
		{
			throw;
		}
	}
}
