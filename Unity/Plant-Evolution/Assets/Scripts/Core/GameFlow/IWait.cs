using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public delegate void WaitAndDoCallback ();

public abstract class IWait
{
	protected WaitAndDoCallback Action;

	MonoBehaviour monoBehaviour
	{
		get;
		set;
	}
	public bool paused
	{
		get;
		protected set;
	}
	protected abstract IEnumerator WaitAndDo();

	public abstract void Pause();

	public abstract void Resume();

}
