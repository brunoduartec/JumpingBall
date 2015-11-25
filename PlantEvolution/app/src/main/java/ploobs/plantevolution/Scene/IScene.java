package ploobs.plantevolution.Scene;

import ploobs.plantevolution.IWorld;

public interface IScene {

	void Draw();
	void Update();
	void Initialize();
	IWorld getWorld();

}
