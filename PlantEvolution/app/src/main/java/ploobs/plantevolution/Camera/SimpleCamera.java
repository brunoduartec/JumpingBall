package ploobs.plantevolution.Camera;

import android.opengl.Matrix;

import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Math.Vector3;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class SimpleCamera implements ICamera {



	private float fov;
	private float nearPlane;
	private float farPlane;


	private Vector3 position;
	private Vector3 target;
	private float[] mViewMatrix = new float[16];
	protected float[] mProjectionMatrix = new float[16];
	private float[] mViewProjectionMatrix = new float[16];
	
	private String name;
	private float ratio = 4f / 3f;


	protected float width;
	protected float height;

	public SimpleCamera()
	{}



	public SimpleCamera(String name,float FOV,float NearPlane, float FarPlane, Vector3 position, Vector3 target)
	{
		this.name=name;
		this.fov=FOV;
		this.setNearPlane(NearPlane);
		this.setFarPlane(FarPlane);
		this.position = position;
		this.target = target;
		this.setWidth(GraphicFactory.getInstance().getWidth());
		this.setHeight(GraphicFactory.getInstance().getHeight());
		
		CalcViewMatrix();
		CalcProjectionMatrix();
		CalcViewProjectionMatrix();



	}
	
	void CalcViewProjectionMatrix()
	{
		Matrix.multiplyMM(mViewProjectionMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
		
	}
	
	public void CalcViewMatrix()
	{
		 Matrix.setLookAtM(mViewMatrix, 0 ,position.getX(), position.getY(), position.getZ(), target.getX(),target.getY(),target.getZ(), 0f, 1.0f, 0.0f);
		
	}
	public void CalcProjectionMatrix()
	{





		// Create a new perspective projection matrix. The height will stay the same
		// while the width will vary as per aspect ratio.
		final float ratio = width / height;
		final float left = -ratio;
		final float right = ratio;
		final float bottom = -1.0f;
		final float top = 1.0f;
		final float near = 1.0f;
		final float far = 1000.0f;

		Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
	//	Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, getNearPlane(), getFarPlane());

//		Matrix.orthoM(mProjectionMatrix, 0, 0, width,0, height, -10,100);

		
	}

	@Override
	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

	@Override
	public float getRatio() {
		return ratio;
	}

	@Override
	public float getFOV() {
		// TODO Auto-generated method stub
		return fov;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		CalcViewMatrix();
		CalcProjectionMatrix();
		CalcViewProjectionMatrix();
	}

	@Override
	public float getNearPlane() {
		// TODO Auto-generated method stub
		return nearPlane;
	}

	@Override
	public float getFarPlane() {
		// TODO Auto-generated method stub
		return farPlane;
	}

	@Override
	public Vector3 getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public Vector3 getTarget() {
		// TODO Auto-generated method stub
		return target;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}


	@Override
	public void setFOV(float f) {
		// TODO Auto-generated method stub
		this.fov = f;
	
	}

	@Override
	public void setNearPlane(float n) {
		// TODO Auto-generated method stub
		this.nearPlane = n;
		
	}

	@Override
	public void setFarPlane(float f) {
		// TODO Auto-generated method stub
		this.farPlane = f;
		
	}

	@Override
	public void setPosition(Vector3 p) {
		// TODO Auto-generated method stub
		this.position = p;
	
		
	}

	@Override
	public void setTarget(Vector3 t) {
		// TODO Auto-generated method stub
		this.target = t;
	
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name;
	}

	@Override
	public float[] getViewMatrix() {
		
		return mViewMatrix;
	}

	@Override
	public float[] getProjectionMatrix() {
		// TODO Auto-generated method stub
		
		 
		return mProjectionMatrix;
	}

	@Override
	public float[] getViewProjectionMatrix() {
		// TODO Auto-generated method stub
		return mViewProjectionMatrix;
	}

	@Override
	public Object Parse(Node childnode) {
		NodeList collisionchildnodeList = childnode.getChildNodes();

		for (int w = 0; w < collisionchildnodeList.getLength(); w++) {
			Node collisionchildnode = collisionchildnodeList.item(w);
			String collisionchildnodename = collisionchildnode.getNodeName();


			switch (collisionchildnodename) {


				case "id":
					this.setName(collisionchildnode.getLastChild().getTextContent().trim());
					break;
				case "fov":
					float vlaus = Float.parseFloat(collisionchildnode.getLastChild().getTextContent().trim());
					this.setFOV(vlaus);
					break;
				case "nearplane":
					this.setNearPlane(Float.parseFloat(collisionchildnode.getLastChild().getTextContent().trim()));
					break;
				case "ratio":
					this.setRatio(Float.parseFloat(collisionchildnode.getLastChild().getTextContent().trim()));
					break;
				case "farplane":
					this.setFarPlane(Float.parseFloat(collisionchildnode.getLastChild().getTextContent().trim()));
					break;

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
									pp.setZ( Float.parseFloat(posnodes.item(k).getLastChild().getTextContent().trim()));
									break;

							}
						}
					}
					this.setPosition(pp);
				}

				case "target": {
					// Node positionnode = collisionchildnode.getChildNodes().item(1);
					NodeList posnodes = collisionchildnode.getChildNodes();
					Vector3 pp = new Vector3();

					for (int k = 0; k < posnodes.getLength(); k++) {

						if (posnodes.item(k) instanceof org.w3c.dom.Element) {

							switch (posnodes.item(k).getNodeName()) {
								case "x":
									pp.setX( Float.parseFloat(posnodes.item(k).getLastChild().getTextContent().trim()));
									break;
								case "y":
									pp.setY( Float.parseFloat(posnodes.item(k).getLastChild().getTextContent().trim()));
									break;
								case "z":
									pp.setZ( Float.parseFloat(posnodes.item(k).getLastChild().getTextContent().trim()));
									break;

							}
						}
					}
					this.setTarget(pp);
				}


			}
		}
		return this;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
}
