package ploobs.plantevolution.Material;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Bruno on 16/02/2016.
 */
public class ColorBufferList {

    public static final int PROPERTIES_PER_ELEMENT = 4;
    public static final int BYTES_PER_PROPERTY = 1;

    private ByteBuffer _b;
    private int _numElements;

    public ColorBufferList(ByteBuffer b, int size)
    {
        _b = ByteBuffer.allocate(b.limit() * BYTES_PER_PROPERTY);
        _b.put(b);
        _numElements = size;
    }

    public ColorBufferList(int maxElements)
    {
        int numBytes = maxElements * PROPERTIES_PER_ELEMENT * BYTES_PER_PROPERTY;
        _b = ByteBuffer.allocateDirect(numBytes);
        _b.order(ByteOrder.nativeOrder());
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

    public Color getAsColor(int index)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT);
        return new Color( _b.get(), _b.get(), _b.get(), _b.get() );
    }

    public void putInColor(int index, Color Color)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT);
        Color.r = (short)_b.get();
        Color.g = (short)_b.get();
        Color.b = (short)_b.get();
        Color.a = (short)_b.get();
    }

    public short getPropertyR(int index)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT);
        return (short)_b.get();
    }
    public short getPropertyG(int index)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT + 1);
        return (short)_b.get();
    }
    public float getPropertyB(int index)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT + 2);
        return (short)_b.get();
    }
    public float getPropertyA(int index)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT + 3);
        return (short)_b.get();
    }

    //

    public void add(Color c)
    {
        set( _numElements, c );
        _numElements++;
    }

    public void add(float r, float g, float b, float a)
    {
        set(_numElements, r, g, b, a);
        _numElements++;
    }

    public void set(int index, Color c)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT);
        _b.put((byte)c.r);
        _b.put((byte)c.g);
        _b.put((byte)c.b);
        _b.put((byte)c.a);

        // Rem, OpenGL takes in color in this order: r,g,b,a -- _not_ a,r,g,b
    }

    public void set(int index, float r, float g, float b, float a)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT);
        _b.put((byte)r);
        _b.put((byte)g);
        _b.put((byte)b);
        _b.put((byte)a);
    }

    public void setPropertyR(int index, short r)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT);
        _b.put((byte)r);
    }
    public void setPropertyG(int index, short g)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT + 1);
        _b.put((byte)g);
    }
    public void setPropertyB(int index, short b)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT + 2);
        _b.put((byte)b);
    }
    public void setPropertyA(int index, short a)
    {
        _b.position(index * PROPERTIES_PER_ELEMENT + 3);
        _b.put((byte)a);
    }

    //

    public ByteBuffer buffer()
    {
        return _b;
    }

    public ColorBufferList clone()
    {
        _b.position(0);
        ColorBufferList c = new ColorBufferList(_b, size());
        return c;
    }
}