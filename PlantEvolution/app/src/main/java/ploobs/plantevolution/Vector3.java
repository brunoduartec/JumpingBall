package ploobs.plantevolution;


import static java.lang.StrictMath.sqrt;

public class Vector3
{
	private float magnitude;
	private float[] normalized;

	private float x;
	private float y;
	private float z;

	public final static Vector3 X = new Vector3(1, 0, 0);
	public final static Vector3 Y = new Vector3(0, 1, 0);
	public final static Vector3 Z = new Vector3(0, 0, 1);
	public final static Vector3 Zero = new Vector3(0, 0, 0);


	public Vector3(){}

	public Vector3(float[] p)
	{
		this.setX(p[0]);
		this.setY(p[1]);
		this.setZ(p[2]);
		normalized = new float[3];
	}


	public  Vector3(float x, float y, float z)
	{
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		normalized = new float[3];
	}
	
	
	public Vector3 set(float x, float y, float z)
	{
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		return this;
	}
	
	public float[] get()
	{
		float[] ret = new float[3];
		
		ret[0] = getX();
		ret[1] = getY();
		ret[2] = getZ();
		
		return ret;
	
	
	
	}
	
	public float getMagnitude()
	{
		magnitude = (float) sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
		return magnitude;
	}
	
	public float getMagnitudeSquared()
	{
	
		return magnitude * magnitude;
	
	}
	
	
	
	public float[] getNormalized()
	{
		normalized[0] = getX() /magnitude;
		return normalized;
	
	}

	
	
    public Vector3 add(Vector3 other) {
        return new Vector3(getX() + other.getX(), getY() + other.getY(), getZ() + other.getZ());
    }



    public Vector3 sub(Vector3 other) {
        return new Vector3(getX() - other.getX(), getY() - other.getY(), getZ() - other.getZ());
    }

    public Vector3 mul(Vector3 other) {
        return new Vector3(getX() * other.getX(), getY() * other.getY(), getZ() * other.getZ());
    }

	public Vector3 mul(float scalar)
	{
		return new Vector3(getX()*scalar,getY()*scalar,getZ()*scalar);

	}


	public boolean equals(Object object)
	{
		if (object == null) return false;
		if (this == object) return true;
		if (!(object instanceof Vector3)) return false;
		Vector3 other = (Vector3)object;
		return this.x == other.x && this.y == other.y && this.z == other.z;
	}
	
	// When the vectors are interpreted as points, 
	// you'll often need these:
	// (Updated based on the comments: This is 
	// basically the length of the difference of
	// the two vectors)
	public float distanceSquared(Vector3 other) {
		float dx = this.getX() - other.getX();
		float dy = this.getY() - other.getY();
		float dz = this.getZ() - other.getZ();
		return dx*dx+dy*dy+dz*dz;
	}
	public float distance(Vector3 other) {
		return (float) sqrt(distanceSquared(other));
	}

	/** @return The dot product between the two vectors */
	public static float dot (float x1, float y1, float z1, float x2, float y2, float z2) {
		return x1 * x2 + y1 * y2 + z1 * z2;
	}


	public float dot (final Vector3 vector) {
		return x * vector.x + y * vector.y + z * vector.z;
	}

	/** Returns the dot product between this and the given vector.
	 * @param x The x-component of the other vector
	 * @param y The y-component of the other vector
	 * @param z The z-component of the other vector
	 * @return The dot product */
	public float dot (float x, float y, float z) {
		return this.x * x + this.y * y + this.z * z;
	}

	/** Sets this vector to the cross product between it and the other vector.
	 * @param vector The other vector
	 * @return This vector for chaining */
	public Vector3 cross (final Vector3 vector) {
		return this.set(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x * vector.y - y * vector.x);
	}

	/** Sets this vector to the cross product between it and the other vector.
	 * @param x The x-component of the other vector
	 * @param y The y-component of the other vector
	 * @param z The z-component of the other vector
	 * @return This vector for chaining */
	public Vector3 cross (float x, float y, float z) {
		return this.set(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
	}

	public Vector3 lerp (final Vector3 target, float alpha) {
		x += alpha * (target.x - x);
		y += alpha * (target.y - y);
		z += alpha * (target.z - z);
		return this;
	}

	/** Converts this {@code Vector3} to a string in the format {@code (x,y,z)}.
	 * @return a string representation of this object. */
	@Override
	public String toString () {
		return "(" + x + "," + y + "," + z + ")";
	}


	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}


	public void rotateX(float angle)
	{
		Vector3 _temp = new Vector3(this.x, this.y, this.z);
		float cosRY = (float) Math.cos(angle);
		float sinRY = (float) Math.sin(angle);



		this.y = (_temp.y*cosRY)-(_temp.z*sinRY);
		this.z = (_temp.y*sinRY)+(_temp.z*cosRY);
	}

	public void rotateY(float angle)
	{
		Vector3 _temp = new Vector3(this.x, this.y, this.z);
		float cosRY = (float) Math.cos(angle);
		float sinRY = (float) Math.sin(angle);



		this.x = (_temp.x*cosRY)+(_temp.z*sinRY);
		this.z = (_temp.x*-sinRY)+(_temp.z*cosRY);
	}

	public void rotateZ(float angle)
	{
		Vector3 _temp = new Vector3(this.x, this.y, this.z);
		float cosRY = (float) Math.cos(angle);
		float sinRY = (float) Math.sin(angle);



		this.x = (_temp.x*cosRY)-(_temp.y*sinRY);
		this.y = (_temp.x*sinRY)+(_temp.y*cosRY);
	}
}