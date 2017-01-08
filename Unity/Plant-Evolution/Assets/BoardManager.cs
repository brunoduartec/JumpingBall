using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using System;
using System.Text;
using System.IO;  

public class BoardManager : MonoBehaviour {

	// Use this for initialization
	void Start () {
		Load("Assets/Levels/stage1.txt");
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	private void InstantiateBlock(string type,Vector3 position,float size)
	{
		GameObject prefab = Resources.Load ("Blocks/block_" + type) as GameObject;
		prefab.transform.position = position * size;
		Instantiate(prefab);
	}


	private bool Load(string fileName)
	{
		// Handle any problems that might arise when reading the text
		try
		{
			string line;
			// Create a new StreamReader, tell it which file to read and what encoding the file
			// was saved as
			StreamReader theReader = new StreamReader(fileName, Encoding.Default);
			// Immediately clean up the reader after this block of code is done.
			// You generally use the "using" statement for potentially memory-intensive objects
			// instead of relying on garbage collection.
			// (Do not confuse this with the using directive for namespace at the 
			// beginning of a class!)
			using (theReader)
			{
				// While there's lines left in the text file, do this:
				int Cy = 0;
				float size = 1;
				do
				{
					line = theReader.ReadLine();
						
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
									InstantiateBlock("grass",new Vector3(Cx,0, Cy), size);
								}
								else if(entries[Cx] != "0"){
									
									InstantiateBlock("grass",new Vector3(Cx,0, Cy), size);
									
									string entry = entries[Cx].Replace('(', ' ').Replace(')', ' ');
									string[] block = entry.Split(';');
									
									int blockY = Int32.Parse(block[1]);
									int amount = Int32.Parse(block[2]);
									
									
									for (int i = 0; i < amount; i++)
									{
										if(block[0].Contains("S")){
											InstantiateBlock("stone",new Vector3(Cx,blockY + i, Cy), size);	
										}
										if(block[0].Contains("G")){
											InstantiateBlock("gem",new Vector3(Cx,blockY + i, Cy), size);	
										}
									}
									
								}
								
							}
							
						}
					}
					Cy++;
				}
				while (line != null);
				// Done reading, close the reader and return true to broadcast success    
				theReader.Close();
				return true;
				}
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

