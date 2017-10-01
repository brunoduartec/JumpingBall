using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using System;
using System.Text;
using System.IO;  



public class BoardManager : MonoBehaviour {

	public string stageName = "stage1";
	private Stage currentStage;

	public float quoteDelayTimeInSeconds = 2.0f;

	private  GameObject bottomCollider;
	private GameObject quoteOverlay;
	private GameObject player;

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
		currentStage = loadJSON(levelName);

		GameObject quoteObject = GameObject.FindGameObjectWithTag("quote");
		QuoteManager quoteManager = quoteObject.GetComponent<QuoteManager>();
		quoteManager.showQuoteOverlay(currentStage.quote, currentStage.levelName);

		Timer.RunWithDelay(this,quoteDelayTimeInSeconds, () =>
        {
			quoteManager.hideQuoteOverlay();
			playStage(currentStage);
        });
	}
	public void loadNextLevel()
	{
		int nextLevel = int.Parse(currentStage.levelName.Replace("stage",string.Empty)) +1;
		loadLevel("stage" + nextLevel);
	}

	// MOCK
	private void buildBoundries(Stage level)
	{
		float minX, maxX, minY, maxY, minZ, maxZ;
		minX = maxX = minY = maxY = minZ = maxZ = 0;

		//discovering the min and max boxes
		foreach (var item in level.board)
		{
			if (item.type.Contains("extra"))
			{
				continue;
			}

			if(item.position.x < minX)
			{
				minX = item.position.x;
			}
			if(item.position.x > maxX)
			{
				maxX = item.position.x;
			}

			if(item.position.y < minY)
			{
				minY = item.position.y;
			}
			if(item.position.y > maxY)
			{
				maxY = item.position.y;
			}

			if(item.position.z < minZ)
			{
				minZ = item.position.z;
			}
			if(item.position.z > maxZ)
			{
				maxZ = item.position.z;
			}


		}

		GameObject[] stageCollider = GameObject.FindGameObjectsWithTag("stage_collider");
	
		float maxHeight = maxY;

		//Z Boundaries
		stageCollider[0].name = "left";
		stageCollider[0].transform.localScale = new Vector3((maxX + 1) - (minX - 1), maxHeight * 2, 1);		
		stageCollider[0].transform.position = new Vector3(0,0,minZ -1);

		stageCollider[1].name = "right";
		stageCollider[1].transform.localScale = stageCollider[0].transform.localScale;
		stageCollider[1].transform.position = new Vector3(0,0,maxZ + 1);//new Vector3(0,0,-(length + 1)/2);	

		// X Boundaries

		stageCollider[2].name = "front";
		stageCollider[2].transform.localScale = new Vector3(1,maxHeight * 2,(maxZ + 1)- (minZ - 1));	
		stageCollider[2].transform.position = new Vector3(maxX + 1, 0, 0);//new Vector3((length+1)/2,0,0);	

		stageCollider[3].name = "back";
		stageCollider[3].transform.localScale = stageCollider[2].transform.localScale;		
		stageCollider[3].transform.position = new Vector3(minX - 1, 0, 0);//new Vector3(-(length+1)/2,0,0);	

		// Y Boundaries

		stageCollider[4].name = "top";
		stageCollider[4].transform.localScale = new Vector3(maxHeight,1,maxHeight);		
		stageCollider[4].transform.position = new Vector3(0, maxHeight * 2, 0);	

		stageCollider[5].transform.localScale = stageCollider[4].transform.localScale;		
		stageCollider[5].transform.position = new Vector3(0,-1,0);

		stageCollider[5].name = "bottom";

		PlayerReload reload = player.GetComponent<PlayerReload>();
		reload.setPlayerInitialPos(currentStage.playerPosition);
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
		
		float boardSize = length;
		float size = 1;
		CameraHandler mainCamera = Camera.main.GetComponent<CameraHandler>();
		mainCamera.setCameraDistance( boardSize);

		for(int cellId=0; cellId<stage.board.Length;cellId++)
		{
			StageCell cell = stage.board[cellId];
			InstantiateEntity(cell.type,cell.position, size);
		}
		player = InstantiateEntity(stage.playerType,stage.playerPosition,size);
		player.tag = "Player";

		buildBoundries(stage);		

		return true;
	}
	
}

