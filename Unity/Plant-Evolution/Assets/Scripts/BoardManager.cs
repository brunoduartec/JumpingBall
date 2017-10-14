﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using System;
using System.Text;
using System.IO;
using System.Text.RegularExpressions;

public class BoardManager : MonoBehaviour {

	public string stageName = "stage1";
	private Stage currentStage;

	public bool startLevel = false;

	public float quoteDelayTimeInSeconds = 2.0f;

	private GameObject bottomCollider;
	private GameObject quoteOverlay;
	private GameObject player;

	private float boardBiggestDimension;

	// Use this for initialization
	void Start () {
		if (startLevel)
		{
			loadLevel(stageName);
		}

	}
	
	// Update is called once per frame
	void Update () {
		
	}

	private GameObject InstantiateEntity(string type,Vector3 position,float size)
	{
		if (type.Length == 0)
		{
			type = "stone";
		}
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

		if (quoteObject != null)
		{
			QuoteManager quoteManager = quoteObject.GetComponent<QuoteManager>();

			quoteManager.showQuoteOverlay(currentStage.quote, currentStage.levelName);

			Timer.RunWithDelay(this,quoteDelayTimeInSeconds, () =>
			{
				quoteManager.hideQuoteOverlay();
				playStage(currentStage);
			});
		}
		else
		{
			playStage(currentStage);
		}
		
	}
	public void loadNextLevel()
	{
		int nextLevel = int.Parse(currentStage.levelName.Replace("Level ",string.Empty)) +1;
		loadLevel("stage" + nextLevel);
	}

	private GameObject createBoundaryBox()
	{
		GameObject cube = GameObject.CreatePrimitive(PrimitiveType.Cube);
		cube.GetComponent<MeshRenderer>().enabled = false;

		cube.transform.parent = this.transform;
		return cube;
	}

	private void buildBoundries(Stage level)
	{
		float minX, maxX, minY, maxY, minZ, maxZ;

		minX = maxX = level.board[0].position.x; //initializing
		minY = maxY = level.board[0].position.y; //initializing
		minZ = maxZ = level.board[0].position.z; //initializing

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

		float maxHeight = maxY;

		float xyComparison    = Math.Max(maxX, maxY);
		boardBiggestDimension = Math.Abs(Math.Max(xyComparison,maxZ));

		//Z Boundaries
		GameObject leftBoundary = createBoundaryBox();

		leftBoundary.name = "left";

		float centerX = maxX - minX;
		float centerY = maxY - minY;
		float centerZ = maxZ - minZ;

		float levelXDimension = Math.Abs( ( (maxX) + 1) - ( (minX) - 1) );
		float levelYDimension = Math.Abs( ( (maxY) + 1) - ( (minY) - 1));
		float levelZDimension = Math.Abs( ( (maxZ) + 1) - ( (minZ) - 1));

		leftBoundary.transform.localScale = new Vector3(levelXDimension, levelYDimension *  2, 1);		
		leftBoundary.transform.position   = new Vector3(minX + centerX, minY + centerY, minZ -1);

		GameObject rightBoundary = createBoundaryBox();
		rightBoundary.name = "right";
		rightBoundary.transform.localScale = leftBoundary.transform.localScale;
		rightBoundary.transform.position   = new Vector3(minX + centerX, minY + centerY, maxZ + 1);

		// X Boundaries

		GameObject frontBoundary = createBoundaryBox();
		frontBoundary.name = "front";
		frontBoundary.transform.localScale = new Vector3(1,levelYDimension * 2,levelZDimension);	
		frontBoundary.transform.position = new Vector3(maxX + 1,  minY + centerY, centerZ + minZ);

		GameObject backBoundary = createBoundaryBox();
		backBoundary.name = "back";
		backBoundary.transform.localScale = frontBoundary.transform.localScale;		
		backBoundary.transform.position = new Vector3(minX - 1,  minY + centerY, centerZ + minZ);

		// Y Boundaries
		GameObject topBoundary = createBoundaryBox();
		topBoundary.name = "top";
		topBoundary.transform.localScale = new Vector3(levelXDimension, 1, levelZDimension);		
		topBoundary.transform.position = new Vector3(minX + centerX, (minY + centerY)*2, minZ + centerZ);	

		GameObject bottomBoundary = createBoundaryBox();
		bottomBoundary.name = "bottom";
		bottomBoundary.transform.localScale = topBoundary.transform.localScale;		
		bottomBoundary.transform.position = new Vector3(minX + centerX, minY - 1, minZ + centerZ);

		PlayerReload reload = player.GetComponent<PlayerReload>();
		reload.setPlayerInitialPos(currentStage.playerPosition);
		reload.setCollider(bottomBoundary);
	}
	
	private void cleanBoard()
	{
		var children = new List<GameObject>();
		foreach (Transform child in transform) children.Add(child.gameObject);
		children.ForEach(child => Destroy(child));
	}

	public void SaveLevelToFile(String raw){
		string path = null;
		#if UNITY_EDITOR
		path = "Assets/Resources/Levels/Level_.json";
		#endif
		#if UNITY_STANDALONE
			// You cannot add a subfolder, at least it does not work for me
			path = "MyGame_Data/Resources/Level_.json"
		#endif
	
		using (FileStream fs = new FileStream(path, FileMode.Create)){
			using (StreamWriter writer = new StreamWriter(fs)){
				writer.Write(raw);
			}
		}
		#if UNITY_EDITOR
		UnityEditor.AssetDatabase.Refresh ();
		#endif
	}

	private Stage loadJSON(string fileName)
	{
		TextAsset file = Resources.Load("Levels\\" + fileName) as TextAsset;
		return JsonUtility.FromJson<Stage>(file.text);
	}

	private void serializeLevelToJson()
	{
		GameObject sceneObjects = GameObject.FindGameObjectWithTag("board");

		int size = sceneObjects.transform.GetChildCount();
		
		Stage stage = new Stage();

		GameObject player = GameObject.FindGameObjectWithTag("Player");
		stage.playerPosition = player.transform.position;
		stage.playerType     = player.name;

		stage.board = new StageCell[size];

		for (int i=0; i< sceneObjects.transform.GetChildCount(); i++)
		{
			Transform item = sceneObjects.transform.GetChild(i);
			
			StageCell cell = new StageCell();
			
			cell.position = item.position;
			cell.rotation = item.rotation.eulerAngles;
			cell.scale 	  = item.localScale;
			cell.type     = item.name.Replace("block_","");
			
			Regex regex = new Regex(@"[0-9|(,)]+");
			Match match = regex.Match(cell.type);

			if (match.Success)
			{
				cell.type = cell.type.Replace(" " + match.Value,"");
			}

			stage.board[i] = cell;	
		}
		string json = JsonUtility.ToJson(stage);
		SaveLevelToFile(json);
	}

	public void exportLevel()
	{
		serializeLevelToJson();
	}

	public bool playStage(Stage stage)
	{
		cleanBoard();

		float size = 1;
		
		for(int cellId=0; cellId<stage.board.Length;cellId++)
		{
			StageCell cell = stage.board[cellId];
			InstantiateEntity(cell.type,cell.position, size);
		}
		player = InstantiateEntity(stage.playerType,stage.playerPosition,size);
		player.tag = "Player";

		buildBoundries(stage);		

		CameraHandler mainCamera = Camera.main.GetComponent<CameraHandler>();
		mainCamera.setCameraDistance( boardBiggestDimension);

		return true;
	}
	
}

