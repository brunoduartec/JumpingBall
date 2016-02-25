package ploobs.plantevolution.Model.Parser;

import java.util.ArrayList;
import java.util.HashMap;

import ploobs.plantevolution.Material.Color;
import ploobs.plantevolution.Material.IMaterial;
import ploobs.plantevolution.Material.Uv;
import ploobs.plantevolution.Math.Vector3;
import ploobs.plantevolution.Model.Model3D.Face;
import ploobs.plantevolution.World.Animation.AnimationObject;
import ploobs.plantevolution.World.Animation.KeyFrame;
import ploobs.plantevolution.World.IObject;

/**
 * Created by Bruno on 23/02/2016.
 */


public class ParseObjectData {
    protected ArrayList<ParseObjectFace> faces;
    protected int numFaces = 0;
    protected ArrayList<Vector3> vertices;
    protected ArrayList<Uv> texCoords;
    protected ArrayList<Vector3> normals;

    public String name;

    public ParseObjectData()
    {
        this.vertices = new ArrayList<Vector3>();
        this.texCoords = new ArrayList<Uv>();
        this.normals = new ArrayList<Vector3>();
        this.name = "";
        faces = new ArrayList<ParseObjectFace>();
    }

    public ParseObjectData(ArrayList<Vector3> vertices, ArrayList<Uv> texCoords, ArrayList<Vector3> normals)
    {
        this.vertices = vertices;
        this.texCoords = texCoords;
        this.normals = normals;
        this.name = "";
        faces = new ArrayList<ParseObjectFace>();
    }

    public AnimationObject getParsedObject(AParser.TextureAtlas textureAtlas, HashMap<String, IMaterial> materialMap, KeyFrame[] frames)
    {
        AnimationObject obj = new AnimationObject(numFaces * 3, numFaces, frames.length);
        obj.setName(name);
        obj.setFrames(frames);

        parseObject(obj, materialMap, textureAtlas);

        return obj;
    }

    public IObject getParsedObject(HashMap<String, IMaterial> materialMap, AParser.TextureAtlas textureAtlas) {
        AnimationObject obj = new AnimationObject(numFaces * 3, numFaces);
        obj.setName(name);

        parseObject((AnimationObject) obj, materialMap, textureAtlas);

        return obj;
    }

    private void parseObject(AnimationObject obj, HashMap<String, IMaterial> materialMap, AParser.TextureAtlas textureAtlas)
    {
        int numFaces = faces.size();
        int faceIndex = 0;
        boolean hasBitmaps = textureAtlas.hasBitmaps();

        for (int i = 0; i < numFaces; i++) {
            ParseObjectFace face = faces.get(i);
            AParser.BitmapAsset ba = textureAtlas
                    .getBitmapAssetByName(face.materialKey);

            for (int j = 0; j < face.faceLength; j++) {
                Vector3 newVertex = vertices.get(face.v[j]);

                Uv newUv = face.hasuv ? texCoords.get(face.uv[j]).clone()
                        : new Uv();
                Vector3 newNormal = face.hasn ? normals.get(face.n[j])
                        : new Vector3();
                IMaterial material = materialMap.get(face.materialKey);

                Color newColor = new Color(255, 255, 0, 255);
                if(material != null && material.getDiffuseColor() != null)
                {
                    newColor.r = material.getDiffuseColor().r;
                    newColor.g = material.getDiffuseColor().g;
                    newColor.b = material.getDiffuseColor().b;
                    newColor.a = material.getDiffuseColor().a;
                }

                if(hasBitmaps && (ba != null))
                {
                    newUv.u = ba.uOffset + newUv.u * ba.uScale;
                    newUv.v = ba.vOffset + ((newUv.v + 1) * ba.vScale) - 1;
                }
                obj.getModel().getVertices().addVertex(newVertex, newUv, newNormal, newColor);
            }

            if (face.faceLength == 3) {
                obj.getModel().getFaces().add(
                        new Face(faceIndex, faceIndex + 1, faceIndex + 2));
            } else if (face.faceLength == 4) {
                obj.getModel().getFaces().add(
                        new Face(faceIndex, faceIndex + 1, faceIndex + 3));
                obj.getModel().getFaces().add(
                        new Face(faceIndex + 1, faceIndex + 2, faceIndex + 3));
            }

            faceIndex += face.faceLength;
        }

        if (hasBitmaps) {
            obj.getMaterial().getTextures().addById(textureAtlas.getId());
        }

        cleanup();
    }

    public void calculateFaceNormal(ParseObjectFace face)
    {
        Vector3 v1 = vertices.get(face.v[0]);
        Vector3 v2 = vertices.get(face.v[1]);
        Vector3 v3 = vertices.get(face.v[2]);

        Vector3 vector1 = v2.sub( v1);
        Vector3 vector2 = v3.sub( v1);

        Vector3 normal = new Vector3();
        normal.setX((vector1.getY() * vector2.getZ()) - (vector1.getZ() * vector2.getY()));
        normal.setY(-((vector2.getZ() * vector1.getX()) - (vector2.getX() * vector1.getZ())));
        normal.setZ(vector1.getX() * vector2.getY() - (vector1.getY() * vector2.getX()));

        double normFactor = Math.sqrt((normal.getX() * normal.getX()) + (normal.getY() * normal.getY()) + (normal.getZ() * normal.getZ()));

        normal.setX((float) (normal.getX()/normFactor));
        normal.setY((float) (normal.getY() / normFactor));
        normal.setZ((float) (normal.getZ() / normFactor));

        normals.add(normal);

        int index = normals.size() - 1;
        face.n = new int[3];
        face.n[0] = index;
        face.n[1] = index;
        face.n[2] = index;
        face.hasn = true;
    }


    protected void cleanup() {
        faces.clear();
    }
}