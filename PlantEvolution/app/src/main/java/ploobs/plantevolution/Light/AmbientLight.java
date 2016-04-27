package ploobs.plantevolution.Light;

import ploobs.plantevolution.Material.Color4;
import ploobs.plantevolution.World.IWorld;
import ploobs.plantevolution.Material.IMaterial;
import ploobs.plantevolution.Material.PointLightMaterial;
import ploobs.plantevolution.Math.Vector3;

import org.w3c.dom.Node;

public class AmbientLight extends ILight 
{


		
   private IMaterial pLightMaterial = new PointLightMaterial();
	

   
   public AmbientLight(Color4 color, float diffuseIntensity, float ambientIntensity, float specularIntensity, Vector3 position)
   {
	   
	   this.setColor(color);
	   this.setDiffuseIntensity(diffuseIntensity);
	   this.setAmbientIntensity(ambientIntensity);
	   this.setSpecularIntensity(specularIntensity);

	   this.setPosition(position);
	   
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
