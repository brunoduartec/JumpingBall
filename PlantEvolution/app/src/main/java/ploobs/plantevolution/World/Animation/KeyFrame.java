package ploobs.plantevolution.World.Animation;

import ploobs.plantevolution.Math.Vector3;

/**
 * Created by Bruno on 23/02/2016.
 */

public class KeyFrame {
    private String name;
    private float[] vertices;
    private float[] normals;

    private int[] indices;

    public KeyFrame(String name, float[] vertices)
    {
        this.name = name;
        this.vertices = vertices;
    }

    public KeyFrame(String name, float[] vertices, float[] normals)
    {
        this(name, vertices);
        this.normals = normals;
    }

    public String getName() {
        return name;
    }

    public float[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public float[] getNormals() {
        return normals;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
        float[] compressed = vertices;
        vertices = new float[indices.length*3];
        int len = indices.length;
        int vi = 0;
        int ii = 0;
        int normalIndex = 0;

        for(int i=0; i<len; i++)
        {
            ii = indices[i] * 3;
            vertices[vi++] = compressed[ii];
            vertices[vi++] = compressed[ii + 1];
            vertices[vi++] = compressed[ii + 2];
        }

        normals = new float[vertices.length];
        int vertLen = vertices.length;

        for(int i=0; i<vertLen; i+=9)
        {
            Vector3 normal = calculateFaceNormal(
                    new Vector3(vertices[i], vertices[i+1], vertices[i+2]),
                    new Vector3(vertices[i+3], vertices[i+4], vertices[i+5]),
                    new Vector3(vertices[i+6], vertices[i+7], vertices[i+8])
            );
            normals[normalIndex++] = normal.getX();
            normals[normalIndex++] = normal.getY();
            normals[normalIndex++] = normal.getZ();
            normals[normalIndex++] = normal.getX();
            normals[normalIndex++] = normal.getY();
            normals[normalIndex++] = normal.getZ();
            normals[normalIndex++] = normal.getX();
            normals[normalIndex++] = normal.getY();
            normals[normalIndex++] = normal.getZ();
        }
    }

    public Vector3 calculateFaceNormal(Vector3 v1, Vector3 v2, Vector3 v3)
    {
        Vector3 vector1 = v2.sub(v1);
        Vector3 vector2 = v2.sub(v1);

        Vector3 normal = new Vector3();
        normal.setX((vector1.getY() * vector2.getZ()) - (vector1.getZ() * vector2.getY()));
        normal.setY(-((vector2.getZ() * vector1.getX()) - (vector2.getX() * vector1.getZ())));
        normal.setZ((vector1.getX() * vector2.getY()) - (vector1.getY() * vector2.getX()));

        double normFactor = Math.sqrt((normal.getX() * normal.getX()) + (normal.getY() * normal.getY()) + (normal.getZ() * normal.getZ()));


        normal.setX((float) (normal.getX()/normFactor));
        normal.setY((float) (normal.getY()/normFactor));
        normal.setZ((float) (normal.getZ()/normFactor));




        return normal;
    }

    public KeyFrame clone()
    {
        KeyFrame k = new KeyFrame(name, vertices.clone(), normals.clone());
        return k;
    }
}