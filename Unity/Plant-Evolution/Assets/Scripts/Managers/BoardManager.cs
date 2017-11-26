using System.Collections;
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
    public float levelLoadDelayTimeInSeconds = .001f;

	public float destroyObjectDelay = .01f;

	private GameObject bottomCollider;
	private GameObject quoteOverlay;
	private GameObject player;

    private GameObject boundariesGroup;

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
		string prefabToInstantiateName;
		
		Transform parentTransform = this.transform;

		if (type.Contains("extra"))
		{
			prefabToInstantiateName =  "Extras/" + type;
		}
		else if (type.Contains("player"))
		{
			prefabToInstantiateName =  "Player/" + type;
		}
		else
		{
			prefabToInstantiateName = "Blocks/block_" + type;
			
		}

		if (!type.Contains("player"))
		{
			parentTransform = GameObject.FindGameObjectWithTag("blocks").transform;
		}
		
		GameObject prefab = Resources.Load (prefabToInstantiateName) as GameObject;
		prefab.transform.position = position;// * size;

		return Instantiate(prefab, parentTransform);
	}

	public void loadLevel(string levelName)
	{
		currentStage = loadJSON(levelName);

		GameObject quoteObject = GameObject.FindGameObjectWithTag("quote");

        boundariesGroup = GameObject.FindGameObjectWithTag("boundaries");

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

	private GameObject createBoundaryBox()
	{
		GameObject cube = GameObject.CreatePrimitive(PrimitiveType.Cube);
		cube.GetComponent<MeshRenderer>().enabled = false;

		cube.transform.parent = boundariesGroup.transform;
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

		float centerX = minX + (maxX - minX)/2;
		float centerY = minY + (maxY - minY)/2;
		float centerZ = minZ + (maxZ - minZ)/2;

		float levelXDimension = Math.Abs( ( (maxX) + 1) - ( (minX) - 1) );
		float levelYDimension = Math.Abs( ( (maxY) + 1) - ( (minY) - 1));
		float levelZDimension = Math.Abs( ( (maxZ) + 1) - ( (minZ) - 1));

		leftBoundary.transform.localScale = new Vector3(levelXDimension, levelYDimension *  2, 1);		
		leftBoundary.transform.position   = new Vector3(centerX, centerY, minZ -1);

		GameObject rightBoundary = createBoundaryBox();
		rightBoundary.name = "right";
		rightBoundary.transform.localScale = leftBoundary.transform.localScale;
		rightBoundary.transform.position   = new Vector3(centerX, centerY, maxZ + 1);

		// X Boundaries

		GameObject frontBoundary = createBoundaryBox();
		frontBoundary.name = "front";
		frontBoundary.transform.localScale = new Vector3(1,levelYDimension * 2,levelZDimension);	
		frontBoundary.transform.position = new Vector3(maxX + 1,  centerY, centerZ);

		GameObject backBoundary = createBoundaryBox();
		backBoundary.name = "back";
		backBoundary.transform.localScale = frontBoundary.transform.localScale;		
		backBoundary.transform.position = new Vector3(minX - 1,  centerY, centerZ);

		// Y Boundaries
		GameObject topBoundary = createBoundaryBox();
		topBoundary.name = "top";
		topBoundary.transform.localScale = new Vector3(levelXDimension, 1, levelZDimension);		
		topBoundary.transform.position = new Vector3(centerX, centerY + levelYDimension, centerZ);	

		GameObject bottomBoundary = createBoundaryBox();
		bottomBoundary.name = "bottom";
		bottomBoundary.transform.localScale = topBoundary.transform.localScale;		
		bottomBoundary.transform.position = new Vector3(centerX, minY - 1, centerZ);

		PlayerReload reload = player.GetComponent<PlayerReload>();
		reload.setPlayerInitialPos(currentStage.playerPosition);
		reload.setCollider(bottomBoundary);
	}
	
	public void loadNextLevel()
	{
		var children = new List<GameObject>();

		GameObject blocks = GameObject.FindGameObjectWithTag("blocks");

        foreach (Transform child in boundariesGroup.transform)
            Destroy(child.gameObject);
        
        foreach (Transform child in blocks.transform) 
			children.Add(child.gameObject);

		StartCoroutine(destroyObjectList(children, 0));
	}


	IEnumerator destroyObjectList(List<GameObject> objectsToDestroy, int index)
	{
		GameObject objectToDestroy = objectsToDestroy[index];

		Vector3 initialPosition = objectToDestroy.transform.position;
		Vector3 endPosition = objectToDestroy.transform.position += new Vector3(0,10,0);

		float t=0;
        while (t < destroyObjectDelay)
        {
            float fraction = t / destroyObjectDelay;

            objectToDestroy.transform.position = Vector3.Lerp(initialPosition, endPosition, fraction);
            t += Time.deltaTime;
            yield return null;
        }
        objectToDestroy.transform.position = endPosition;

		Destroy(objectToDestroy);

		if (index < objectsToDestroy.Count - 1)
		{
			StartCoroutine(destroyObjectList(objectsToDestroy, index + 1));
		}
		else
		{
			Destroy(player);

            Timer.RunWithDelay(this, levelLoadDelayTimeInSeconds, () =>
            {
                int nextLevel = int.Parse(currentStage.levelName.Replace("Level ", string.Empty)) + 1;
                loadLevel("stage" + nextLevel);
            });
		}
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
		// cleanBoard();

		#region Configuring HUD Objects
		
		GameObject UI = GameObject.FindGameObjectWithTag("hud");
		InGameUI inGameUI = UI.GetComponent<InGameUI>();
		inGameUI.levelName.text = stage.levelName;
		inGameUI.levelHint.text = stage.quote;

		#endregion

		

		float size = 1;
		
		for(int cellId=0; cellId<stage.board.Length;cellId++)
		{
			StageCell cell = stage.board[cellId];
			InstantiateEntity(cell.type,cell.position, size);
		}
		player = InstantiateEntity(stage.playerType,stage.playerPosition,size);
		player.tag = "Player";

		buildBoundries(stage);		

		CameraHandler cameraHandler = Camera.main.GetComponent<CameraHandler>();
		cameraHandler.setCameraDistance( boardBiggestDimension);

		// cameraHandler.zoomToGameObject(player);

		return true;
	}
	
}

