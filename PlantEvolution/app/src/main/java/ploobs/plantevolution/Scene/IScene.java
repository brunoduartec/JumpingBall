package ploobs.plantevolution.Scene;

import ploobs.plantevolution.World.IWorld;

public interface IScene {

	void Draw();
	void Update();
	void Initialize();
	IWorld getWorld();

}
