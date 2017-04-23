using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using System;
using System.Text;
using System.IO;  



public class BoardManager : MonoBehaviour {

	public string stageName = "stage8";
	private string actualStageName;

	private Stage actualStage;

	public float quoteDelayTimeInSeconds = 2.0f;

	private  GameObject bottomCollider;
	private GameObject quoteOverlay;

	// Use this for initialization
	void Start () {
		loadLevel(stageName);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	private GameObject InstantiateEntity(string type,Vector3 position,float size)
	{
		string prefabToInstantiateName = "Blocks/block_" + type;
		if (type.Contains("extra"))
		{
			prefabToInstantiateName =  "Extras/" + type;
		}
		if (type.Contains("player"))
		{
			prefabToInstantiateName =  "Player/" + type;
		}
		
		GameObject prefab = Resources.Load (prefabToInstantiateName) as GameObject;
		prefab.transform.position = position;// * size;
		return Instantiate(prefab, this.transform);
	}

	public void loadLevel(string levelName)
	{
		actualStageName = levelName;

		print("-----------------------" + levelName + "--------" + actualStageName);
		actualStage = loadJSON(levelName);
		
		GameObject quoteObject = GameObject.FindGameObjectWithTag("quote");
		QuoteManager quoteManager = quoteObject.GetComponent<QuoteManager>();
		quoteManager.showQuoteOverlay(actualStage.quote, actualStage.levelName);

		Timer.RunWithDelay(this,quoteDelayTimeInSeconds, () =>
        {
			quoteManager.hideQuoteOverlay();
			playStage(actualStage);
        });
	}
	public void loadNextLevel()
	{
		int nextLevel = int.Parse(actualStageName.Replace("stage",string.Empty)) +1;
		loadLevel("stage" + nextLevel);
	}

	private void buildBoundries(float length)
	{
		GameObject[] stageCollider = GameObject.FindGameObjectsWithTag("stage_collider");
	
		stageCollider[0].transform.localScale = new Vector3(length,length*2,1);		
		stageCollider[0].transform.position = new Vector3(0,0,(length + 1)/2);	

		stageCollider[1].transform.localScale = new Vector3(length,length*2,1);		
		stageCollider[1].transform.position = new Vector3(0,0,-(length + 1)/2);	

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

		PlayerReload reload = bottomCollider.GetComponent<PlayerReload>();
		reload.setPlayerInitialPos(actualStage.playerPosition);
		
	}
	
	private void cleanBoard()
	{
		var children = new List<GameObject>();
		foreach (Transform child in transform) children.Add(child.gameObject);
		children.ForEach(child => Destroy(child));
	}

	private Stage loadJSON(string fileName)
	{
		TextAsset file = Resources.Load("Levels\\" + fileName) as TextAsset;
		return JsonUtility.FromJson<Stage>(file.text);
	}

	public bool playStage(Stage stage)
	{
		cleanBoard();

		int length =   stage.boardLength;
		buildBoundries(length);

		float boardSize = length;
		float size = 1;
		CameraHandler mainCamera = Camera.main.GetComponent<CameraHandler>();
		mainCamera.setCameraDistance( boardSize);

		for(int cellId=0; cellId<stage.board.Length;cellId++)
		{
			StageCell cell = stage.board[cellId];
			InstantiateEntity(cell.type,cell.position, size);
		}
		GameObject player = InstantiateEntity(stage.playerType,stage.playerPosition,size);
		player.tag = "Player";
		return true;
	}

	public bool Load(string fileName)
	{
		// Handle any problems that might arise when reading the text
		try
		{
			cleanBoard();
			string line;
			
			TextAsset file = Resources.Load("Levels\\" + fileName) as TextAsset;

			string[] matrix = file.text.Split('\n');

				int Cy = 0;
				float size = 1;
				int length =  matrix.Length;

				buildBoundries(length);
					

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
						string[] entries = line.Split(',');

						if(entries.Length > 0)
						{
							for (int Cx = 0; Cx < entries.Length; Cx++)
							{
								if (entries[Cx] == "1"){
									
									InstantiateEntity("grass",new Vector3(Cx + deltaX,0, Cy+ deltaZ), size);
								}
								else if(entries[Cx] != "0"){
									
									InstantiateEntity("grass",new Vector3(Cx + deltaX,0, Cy+ deltaZ), size);
									
									string entry = entries[Cx].Replace('(', ' ').Replace(')', ' ');
									string[] block = entry.Split(';');
									
									int blockY = Int32.Parse(block[1]);
									int amount = Int32.Parse(block[2]);
									
									
									for (int i = 0; i < amount; i++)
									{
										float yPos = blockY*size*(1+i);
										float xPos = Cx + deltaX;
										float zPos = Cy + deltaZ;

										string boxType = "grass";
										 
										if(block[0].Contains("S")){
											boxType = "stone";
										}
										if(block[0].Contains("G")){
											boxType = "cristal";
										}
										if(block[0].Contains("M")){
											boxType = "grass_moveable";
										}
										if(block[0].Contains("E")){

											
											boxType = "extra";
											string[] extraConfig = block[0].Split('-');
											boxType = boxType + "_" +  extraConfig[1];

											print("-------------------VLAUS-----------------------" + boxType);
										}
										

										InstantiateEntity(boxType,new Vector3(xPos, yPos,zPos ), size);	
									}
									
								}
								
							}
							
						}
					}
					Cy++;
				}

				actualStageName = fileName;
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

