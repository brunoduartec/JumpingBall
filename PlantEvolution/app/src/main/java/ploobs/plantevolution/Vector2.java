package ploobs.plantevolution;


import static java.lang.StrictMath.sqrt;

public class Vector2
{
	private float magnitude;
	private float[] normalized;

	public float x;
	public float y;

	public final static Vector2 X = new Vector2(1, 0);
	public final static Vector2 Y = new Vector2(0, 1);
	public final static Vector2 Zero = new Vector2(0, 0);

	public float getX(){return x;}
	public float getY(){return y;}


	public Vector2(float x, float y)
	{
		this.x=x;
		this.y=y;

		normalized = new float[2];
	}
	
	
	public Vector2 set(float x, float y)
	{
		this.x=x;
		this.y=y;

		return this;

	}
	
	public float[] get()
	{
		float[] ret = new float[2];
		
		ret[0] = x;
		ret[1] = y;

		
		return ret;
	
	
	
	}
	
	public float getMagnitude()
	{
		magnitude = (float) sqrt(x * x + y * y );
		return magnitude;
	}
	
	public float getMagnitudeSquared()
	{
	
		return magnitude * magnitude;
	
	}
	
	
	
	public float[] getNormalized()
	{
		normalized[0] = x/magnitude;
		return normalized;
	
	}

	
	
    public Vector2 add(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }

    public Vector2 sub(Vector2 other) {
        return new Vector2(x - other.x, y - other.y);
    }

    public Vector2 mul(Vector2 other) {
        return new Vector2(x * other.x, y * other.y);
    }

	public Vector2 mul(float v)
	{

		return new Vector2(x*v,y*v);
	}

	
	// When the vectors are interpreted as points, 
	// you'll often need these:
	// (Updated based on the comments: This is 
	// basically the length of the difference of
	// the two vectors)
	public float distanceSquared(Vector2 other) {
		float dx = this.x - other.x;
		float dy = this.y - other.y;

		return dx*dx+dy*dy;
	}
	public float distance(Vector2 other) {
		return (float) sqrt(distanceSquared(other));
	}


	public static float dot (float x1, float y1, float x2, float y2) {
		return x1 * x2 + y1 * y2;
	}


	public float dot (Vector2 v) {
		return x * v.x + y * v.y;
	}

	public float dot (float ox, float oy) {
		return x * ox + y * oy;
	}


	/** Calculates the 2D cross product between this and the given vector.
	 * @param v the other vector
	 * @return the cross product */
	public float crs (Vector2 v) {
		return this.x * v.y - this.y * v.x;
	}
	/** Calculates the 2D cross product between this and the given vector.
	 * @param x the x-coordinate of the other vector
	 * @param y the y-coordinate of the other vector
	 * @return the cross product */
	public float crs (float x, float y) {
		return this.x * y - this.y * x;
	}

	/** @return the angle in degrees of this vector (point) relative to the x-axis. Angles are towards the positive y-axis (typically
	 *         counter-clockwise) and between 0 and 360. */
	public float angle () {
		float angle = (float)Math.atan2(y, x) * MathUtils.radiansToDegrees;
		if (angle < 0) angle += 360;
		return angle;
	}

	/** @return the angle in degrees of this vector (point) relative to the given vector. Angles are towards the positive y-axis
	 *         (typically counter-clockwise.) between -180 and +180 */
	public float angle (Vector2 reference) {
		return (float)Math.atan2(crs(reference), dot(reference)) * MathUtils.radiansToDegrees;
	}

	/** @return the angle in radians of this vector (point) relative to the x-axis. Angles are towards the positive y-axis.
	 *         (typically counter-clockwise) */
	public float angleRad () {
		return (float)Math.atan2(y, x);
	}

	/** @return the angle in radians of this vector (point) relative to the given vector. Angles are towards the positive y-axis.
	 *         (typically counter-clockwise.) */
	public float angleRad (Vector2 reference) {
		return (float)Math.atan2(crs(reference), dot(reference));
	}


}