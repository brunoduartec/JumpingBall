package ploobs.plantevolution.Math;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Bruno on 16/02/2016.
 */
public class Vector3BufferList {

    public static final int PROPERTIES_PER_ELEMENT = 3;
    public static final int BYTES_PER_PROPERTY = 4;

    private FloatBuffer _b;
    private int _numElements = 0;

    public Vector3BufferList(FloatBuffer b, int size)
    {
        ByteBuffer bb = ByteBuffer.allocateDirect(b.limit() * BYTES_PER_PROPERTY);
        bb.order(ByteOrder.nativeOrder());
        _b = bb.asFloatBuffer();
        _b.put(b);
        _numElements = size;
    }

    public Vector3BufferList(int maxElements)
    {
        int numBytes = maxElements * PROPERTIES_PER_ELEMENT * BYTES_PER_PROPERTY;
        ByteBuffer bb = ByteBuffer.allocateDirect(numBytes);
        bb.order(ByteOrder.nativeOrder());

        _b  = bb.asFloatBuffer();
    }

    /**
     * The number of items in the list.
     */
    public int size()
    {
        return _numElements;
    }

    /**
     * The _maximum_ number of items that the list can hold, as defined on instantiation.
     * (Not to be confused with the Buffer's capacity)
     */
    public int capacity()
    {
        return _b.capacity() / PROPERTIES_PER_ELEMENT;
    }

    /**
     * Clear object in preparation for garbage collection
     */
    public void clear()
    {
        _b.clear();
    }

    //

    public Vector3 getAsNumber3d(int index)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT);
        return new Vector3( _b.get(), _b.get(), _b.get() );
    }

    public void putInVector3(int index, Vector3 number3d)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT);
        number3d.setX(_b.get());
        number3d.setY(_b.get());
        number3d.setZ(_b.get());
    }

    public float getPropertyX(int index)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT);
        return _b.get();
    }
    public float getPropertyY(int index)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT + 1);
        return _b.get();
    }
    public float getPropertyZ(int index)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT + 2);
        return _b.get();
    }

    //

    public void add(Vector3 n)
    {
        set( _numElements, n );
        _numElements++;
    }

    public void add(float x, float y, float z)
    {
        set( _numElements, x,y,z );
        _numElements++;
    }

    public void set(int index, Vector3 n)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT);
        _b.put(n.getX());
        _b.put(n.getY());
        _b.put(n.getZ());
    }

    public void set(int index, float x, float y, float z)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT);
        _b.put(x);
        _b.put(y);
        _b.put(z);
    }

    public void setPropertyX(int index, float x)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT);
        _b.put(x);
    }
    public void setPropertyY(int index, float y)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT + 1);
        _b.put(y);
    }
    public void setPropertyZ(int index, float z)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT + 2);
        _b.put(z);
    }

    //

    public FloatBuffer buffer()
    {
        return _b;
    }

    public void overwrite(float[] newVals)
    {
        _b.position(0);
        _b.put(newVals);
    }

    public Vector3BufferList clone()
    {
        _b.position(0);
        Vector3BufferList c = new Vector3BufferList(_b, size());
        return c;
    }
}