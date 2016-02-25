package ploobs.plantevolution.World.Animation;

import org.w3c.dom.Node;

import ploobs.plantevolution.Material.TextureList;
import ploobs.plantevolution.Model.Model3D.FacesBufferList;
import ploobs.plantevolution.Model.Model3D.Vertices;
import ploobs.plantevolution.World.IObject;
import ploobs.plantevolution.World.IWorld;

/**
 * Created by Bruno on 23/02/2016.
 */

public class AnimationObject extends IObject {
    private int numFrames;
    private KeyFrame[] frames;
    private int currentFrameIndex;
    private long startTime;
    private long currentTime;
    private boolean isPlaying;
    private float interpolation;
    private float fps = 70;
    private boolean updateVertices = true;
    private String currentFrameName;
    private int loopStartIndex;
    private boolean loop = false;


    private boolean _animationEnabled=false;


    public AnimationObject(int maxVertices, int maxFaces)
    {
        Vertices  _vertices = new Vertices(maxVertices,true,true,true);
        FacesBufferList _faces = new FacesBufferList(maxFaces);

        this.getModel().setVertices(_vertices);
        this.getModel().setFaceBufferList(_faces);


    }


    public AnimationObject(int maxVertices, int maxFaces, int $numFrames) {

      Vertices  _vertices = new Vertices(maxVertices,true,true,true);
       FacesBufferList _faces = new FacesBufferList(maxFaces);

        this.getModel().setVertices(_vertices);
        this.getModel().setFaceBufferList(_faces);




        this.numFrames = $numFrames;
        this.frames = new KeyFrame[numFrames];
        this.currentFrameIndex = 0;
        this.isPlaying = false;
        this.interpolation = 0;
        this.set_animationEnabled(true);
    }

    public AnimationObject(Vertices vertices, FacesBufferList faces, TextureList $textures, KeyFrame[] $frames)
    {


        this.getModel().setVertices(vertices);
        this.getModel().setFaceBufferList(faces);


        numFrames = $frames.length;
        frames = $frames;
    }

    public int getCurrentFrame() {
        return currentFrameIndex;
    }

    public void addFrame(KeyFrame frame) {
        frames[currentFrameIndex++] = frame;
    }

    public void setFrames(KeyFrame[] frames) {
        this.frames = frames;
    }

    public void play() {
        startTime = System.currentTimeMillis();
        isPlaying = true;
        currentFrameName = null;
        loop = false;
    }

    public void play(String name) {
        currentFrameIndex = 0;
        currentFrameName = name;

        for (int i = 0; i < numFrames; i++) {
            if (frames[i].getName().equals(name))
            {
                loopStartIndex = currentFrameIndex = i;
                break;
            }
        }

        startTime = System.currentTimeMillis();
        isPlaying = true;
    }

    public void play(String name, boolean loop) {
        this.loop = loop;
        play(name);
    }

    public void stop() {
        isPlaying = false;
        currentFrameIndex = 0;
    }

    public void pause() {
        isPlaying = false;
    }

    public void update() {
        if (!isPlaying || !updateVertices)
            return;
        currentTime = System.currentTimeMillis();
        KeyFrame currentFrame = frames[currentFrameIndex];
        KeyFrame nextFrame = frames[(currentFrameIndex + 1) % numFrames];

        if(currentFrameName != null && !currentFrameName.equals(currentFrame.getName()))
        {
            if(!loop)
                stop();
            else
                currentFrameIndex = loopStartIndex;
            return;
        }

        float[] currentVerts = currentFrame.getVertices();
        float[] nextVerts = nextFrame.getVertices();
        float[] currentNormals = currentFrame.getNormals();
        float[] nextNormals = nextFrame.getNormals();
        int numVerts = currentVerts.length;

        float[] interPolatedVerts = new float[numVerts];
        float[] interPolatedNormals = new float[numVerts];

        for (int i = 0; i < numVerts; i += 3) {
            interPolatedVerts[i] = currentVerts[i] + interpolation * (nextVerts[i] - currentVerts[i]);
            interPolatedVerts[i + 1] = currentVerts[i + 1] + interpolation * (nextVerts[i + 1] - currentVerts[i + 1]);
            interPolatedVerts[i + 2] = currentVerts[i + 2] + interpolation 	* (nextVerts[i + 2] - currentVerts[i + 2]);
            interPolatedNormals[i] = currentNormals[i] + interpolation * (nextNormals[i] - currentNormals[i]);
            interPolatedNormals[i + 1] = currentNormals[i + 1] + interpolation * (nextNormals[i + 1] - currentNormals[i + 1]);
            interPolatedNormals[i + 2] = currentNormals[i + 2] + interpolation * (nextNormals[i + 2] - currentNormals[i + 2]);
        }

        interpolation += fps * (currentTime - startTime) / 1000;

       this.getModel().getVertices().overwriteNormals(interPolatedNormals);
        this.getModel().getVertices().overwriteVerts(interPolatedVerts);

        if (interpolation > 1) {
            interpolation = 0;
            currentFrameIndex++;

            if (currentFrameIndex >= numFrames)
                currentFrameIndex = 0;
        }

        startTime = System.currentTimeMillis();
    }

    public float getFps() {
        return fps;
    }

    public void setFps(float fps) {
        this.fps = fps;
    }

    public IObject clone(boolean cloneData)
    {
        Vertices v = cloneData ? this.getModel().getVertices().clone() :this.getModel().getVertices();
        FacesBufferList f = cloneData ?this.getModel().getFaces().clone() : this.getModel().getFaces();
        //KeyFrame[] fr = cloneData ? getClonedFrames() : frames;

        AnimationObject clone = new AnimationObject(v, f, this.getMaterial().getTextures(), frames);
        clone.getPosition().setX(this.getPosition().getX());
        clone.getPosition().setY(this.getPosition().getY());
        clone.getPosition().setZ(this.getPosition().getZ());

        clone.getRotation().setX(this.getRotation().getX());
        clone.getRotation().setY(this.getRotation().getY());
        clone.getRotation().setZ(this.getRotation().getZ());

        clone.getScale().setX(this.getScale().getX());
        clone.getScale().setY(this.getScale().getY());
        clone.getScale().setZ(this.getScale().getZ());

        clone.setFps(fps);
        clone.set_animationEnabled(this.is_animationEnabled());
        return clone;
    }

    public KeyFrame[] getClonedFrames()
    {
        int len = frames.length;
        KeyFrame[] cl = new KeyFrame[len];

        for(int i=0; i<len; i++)
        {
            cl[i] = frames[i].clone();
        }

        return cl;
    }

    public boolean getUpdateVertices() {
        return updateVertices;
    }

    public void setUpdateVertices(boolean updateVertices) {
        this.updateVertices = updateVertices;
    }

    @Override
    public void Draw(IWorld world) {

    }

    @Override
    public void Update() {

    }

    @Override
    public Object Parse(Node childnode) {
        return null;
    }

    public boolean is_animationEnabled() {
        return _animationEnabled;
    }

    public void set_animationEnabled(boolean _animationEnabled) {
        this._animationEnabled = _animationEnabled;
    }
}