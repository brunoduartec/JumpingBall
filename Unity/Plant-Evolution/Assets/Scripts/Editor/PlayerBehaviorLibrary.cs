using UnityEngine;
using UnityEditor;
using NUnit.Framework;

using Scripts.Behaviours;

public class PlayerBehaviorLibrary {

    BehaviourLibrary library = new BehaviourLibrary();
	[Test]
	public void EditorTest() {
		//Arrange
		var gameObject = new GameObject();

		//Act
		//Try to rename the GameObject
		var newGameObjectName = "My game object";
		gameObject.name = newGameObjectName;

		//Assert
		//The object has a new name
		Assert.AreEqual(newGameObjectName, gameObject.name);
	}

    [Test]
    public void getBehabiorByName()
    {
        JumpPlayerBehaviour jump = new JumpPlayerBehaviour();

        IPlayerBehaviour behavior = library.getBehaviorByName("Jump");

        Assert.AreNotSame(jump, null);

        behavior = library.getBehaviorByName("BANANA");
        Assert.AreSame(behavior, null);

    }
}
