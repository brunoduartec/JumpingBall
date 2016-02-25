package ploobs.plantevolution.World;

import ploobs.plantevolution.Material.IMaterial;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.Model.Model3D.BoxModel;
import ploobs.plantevolution.Model.IModel;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class SimpleObject extends IObject
{
	// used to xmlparse
	public SimpleObject()
	{
		localID = ID++;
	}

	public SimpleObject(IMaterial mat, IModel mod, String nm)
	{
		this.name = nm;
		//this.material = mat;
		//this.model = mod;
		setMaterial(mat);
		setModel(mod);
		localID = ID++;		
		
		 
		
	}

	@Override
	public void Draw(IWorld world) {
		// TODO Auto-generated method stub

		getMaterial().Draw(this, world);
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	//	 Matrix.setIdentityM(localTransformation, 0); // initialize to identity matrix
	//	Matrix.scaleM(localTransformation,0,scale[0],scale[1],scale[2]);
	//	 Matrix.translateM(localTransformation, 0, position[0], position[1], position[2]); // Multiply by translation to the position

	}

	@Override
	public Vector3 getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	
	/*
	@Override
	public float[] getLocalTransformation() {
		// TODO Auto-generated method stub
		return localTransformation;
	}
*/
	@Override
	public boolean getEnabled() {
		// TODO Auto-generated method stub
		return Enabled;
	}

	@Override
	public boolean getVisible() {
		// TODO Auto-generated method stub
		return Visible;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return localID;
	}


	@Override
	public void setLocalTransformation(float[] trans) {
		// TODO Auto-generated method stub
		this.localTransformation = trans;
	}

	@Override
	public void setPosition(Vector3 pos) {
		// TODO Auto-generated method stub
	this.position = pos;	
	}

	@Override
	public Vector3 getRotation() {
		// TODO Auto-generated method stub
		return rotation;
	}

	@Override
	public void setRotation(Vector3 rot) {
		// TODO Auto-generated method stub
		rotation = rot;
	}

	@Override
	public Vector3 getScale() {
		// TODO Auto-generated method stub
		return scale;
	}

	@Override
	public void setScale(Vector3 scale) {
		// TODO Auto-generated method stub
		this.scale = scale;
	}


	@Override
	public Object Parse(Node childnode) {




                                NodeList collisionchildnodeList = childnode.getChildNodes();

                                for (int w = 0; w < collisionchildnodeList.getLength(); w++) {
                                    Node collisionchildnode = collisionchildnodeList.item(w);
                                    String collisionchildnodename = collisionchildnode.getNodeName();



                                switch(collisionchildnodename) {
                                    case "position": {
                                       // Node positionnode = collisionchildnode.getChildNodes().item(1);
                                        NodeList posnodes = collisionchildnode.getChildNodes();
                                        Vector3 pp = new Vector3();

                                        for (int k = 0; k < posnodes.getLength(); k++) {

                                            if (posnodes.item(k) instanceof org.w3c.dom.Element) {

                                                switch (posnodes.item(k).getNodeName()) {
                                                    case "x":


                                                        pp.setX(Float.parseFloat(posnodes.item(k).getLastChild().getTextContent().trim()));
                                                        break;
                                                    case "y":
                                                        pp.setY(Float.parseFloat(posnodes.item(k).getLastChild().getTextContent().trim()));
                                                        break;
                                                    case "z":
                                                        pp.setZ(Float.parseFloat(posnodes.item(k).getLastChild().getTextContent().trim()));
                                                        break;

                                                }
                                            }
                                        }
                                        this.setPosition(pp);
                                    }
                                    break;

                                    case "scale": {
                                        // Node positionnode = collisionchildnode.getChildNodes().item(1);
                                        NodeList scalenodes = collisionchildnode.getChildNodes();
                                        Vector3 pp = new Vector3();

                                        for (int k = 0; k < scalenodes.getLength(); k++) {

                                            if (scalenodes.item(k) instanceof org.w3c.dom.Element) {

                                                switch (scalenodes.item(k).getNodeName()) {
                                                    case "x":
                                                        pp.setX(Float.parseFloat(scalenodes.item(k).getLastChild().getTextContent().trim()));
                                                        break;
                                                    case "y":
                                                        pp.setY(Float.parseFloat(scalenodes.item(k).getLastChild().getTextContent().trim()));
                                                        break;
                                                    case "z":
                                                        pp.setZ(Float.parseFloat(scalenodes.item(k).getLastChild().getTextContent().trim()));
                                                        break;

                                                }
                                            }
                                        }
                                        this.setScale(pp);

                                        IModel m1 = new BoxModel(1);
                                        this.setModel(m1);

                                    }
                                    break;


                                }
                        }





		return this;
	}
}