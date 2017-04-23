using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Timer
{
	public static StopWatch RunWithDelay (MonoBehaviour mb, float time, WaitAndDoCallback callback)
	{
		return new StopWatch(mb, time, callback);
	}
}