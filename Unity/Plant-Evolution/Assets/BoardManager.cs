using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using System;
using System.Text;
using System.IO;  

public class BoardManager : MonoBehaviour {

	public string stageName = "stage1";
	protected string actualStage;

	private  GameObject bottomCollider;
	// Use this for initialization
	void Start () {
		Load(stageName);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	private void InstantiateBlock(string type,Vector3 position,float size)
	{
		GameObject prefab = Resources.Load ("Blocks/block_" + type) as GameObject;
		prefab.transform.position = position * size;
		Instantiate(prefab, this.transform);	
	}

	public void loadNextLevel()
	{
		int nextLevel = int.Parse(actualStage.Replace("stage",string.Empty)) +1;
		Load("stage" + nextLevel);
	}

	private void buildBoundries(float length)
	{
		GameObject[] stageCollider = GameObject.FindGameObjectsWithTag("stage_collider");
	
		stageCollider[0].transform.localScale = new Vector3(length,length*2,1);		
		stageCollider[0].transform.position = new Vector3(0,0,(length+1)/2);	

		stageCollider[1].transform.localScale = new Vector3(length,length*2,1);		
		stageCollider[1].transform.position = new Vector3(0,0,-(length+1)/2);	

		stageCollider[2].transform.localScale = new Vector3(1,length*2,length);		
		stageCollider[2].transform.position = new Vector3((length+1)/2,0,0);	

		stageCollider[3].transform.localScale = new Vector3(1,length*2,length);		
		stageCollider[3].transform.position = new Vector3(-(length+1)/2,0,0);	

		stageCollider[4].transform.localScale = new Vector3(length,1,length);		
		stageCollider[4].transform.position = new Vector3(0,length,0);	

		stageCollider[5].transform.localScale = new Vector3(length,1,length);		
		stageCollider[5].transform.position = new Vector3(0,-1,0);
		bottomCollider = stageCollider[5];
		bottomCollider.AddComponent<PlayerReload>();
	}
	
	private void cleanBoard()
	{
		var children = new List<GameObject>();
		foreach (Transform child in transform) children.Add(child.gameObject);
		children.ForEach(child => Destroy(child));
	}

	public bool Load(string fileName)
	{
		// Handle any problems that might arise when reading the text
		try
		{
			cleanBoard();
			string line;
			
			TextAsset file = Resources.Load("Levels\\" + fileName) as TextAsset;

		//	StreamReader theReader = new StreamReader(fileName, Encoding.Default);

			string[] matrix = file.text.Split('\n');

				int Cy = 0;
				float size = 1;
				float delta = 1f;
				int length =  matrix.Length;

				buildBoundries(length* 1.1f);
					

				float boardSize = length;
				CameraHandler mainCamera = Camera.main.GetComponent<CameraHandler>();

				mainCamera.setCameraDistance( boardSize * 1.5f);
				float deltaX = -(boardSize/2 - 0.5f);
				float deltaZ = deltaX = (deltaX * size);

				for(int matrixIndex=0; matrixIndex<length;matrixIndex++)
				{
					line = matrix[matrixIndex];
					line = line.Replace("\r", string.Empty);
						
					if (line != null)
					{
						// Do whatever you need to do with the text line, it's a string now
						// In this example, I split it into arguments based on comma
						// deliniators, then send that array to DoStuff()
						string[] entries = line.Split(',');
						

						if(entries.Length > 0)
						{
							for (int Cx = 0; Cx < entries.Length; Cx++)
							{
								if (entries[Cx] == "1"){
									
									InstantiateBlock("grass",new Vector3(Cx + deltaX,0, Cy+ deltaZ), size);
								}
								else if(entries[Cx] != "0"){
									
									InstantiateBlock("grass",new Vector3(Cx + deltaX,0, Cy+ deltaZ), size);
									
									string entry = entries[Cx].Replace('(', ' ').Replace(')', ' ');
									string[] block = entry.Split(';');
									
									int blockY = Int32.Parse(block[1]);
									int amount = Int32.Parse(block[2]);
									
									
									for (int i = 0; i < amount; i++)
									{
										if(block[0].Contains("S")){
											InstantiateBlock("stone",new Vector3(Cx + deltaX, blockY + i + size/2 + delta, Cy + deltaZ), size);	
										}
										if(block[0].Contains("G")){
											InstantiateBlock("cristal",new Vector3(Cx + deltaX,blockY + i + size/2 + delta, Cy + deltaZ), size);	
										}
										if(block[0].Contains("M")){
											InstantiateBlock("grass_moveable",new Vector3(Cx + deltaX,blockY + i + size/2 + delta, Cy + deltaZ), size);	
										}
									}
									
								}
								
							}
							
						}
					}
					Cy++;
				}

				actualStage = fileName;
				return true;
			}
			// If anything broke in the try block, we throw an exception with information
			// on what didn't work
			catch (Exception e)
			{
				Console.WriteLine("{0}\n", e.Message);
				return false;
			}
	}
	
}

