package ploobs.plantevolution.Light;

import ploobs.plantevolution.IWorld;
import ploobs.plantevolution.Material.IMaterial;
import ploobs.plantevolution.Material.PointLightMaterial;
import ploobs.plantevolution.Vector3;

import org.w3c.dom.Node;

public class AmbientLight extends ILight 
{


		
   private IMaterial pLightMaterial = new PointLightMaterial();
	

   
   public AmbientLight(float[] color, float intens, Vector3 position)
   {
	   
	   this.setColor(color);
	   this.setIntensity(intens);
	   this.setPosition(position.get());
	   
   }
	

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Draw(IWorld world) {
		
		pLightMaterial.Draw(this, world);
		
	}




	@Override
	public Object Parse(Node childnode) {
		return null;
	}
}
