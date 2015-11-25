package ploobs.plantevolution.Camera;


import ploobs.plantevolution.ISerializable;

public interface ICamera extends ISerializable
{

void setRatio(float ratio);

float getRatio();

float getFOV();
void setFOV(float f);
void Update();
float getNearPlane();
void setNearPlane(float n);
float getFarPlane();
void setFarPlane(float f);

float[] getPosition();
void setPosition(float[] p);
float[] getTarget();
void setTarget(float[] t);
String getName();
void setName(String name);

float[] getViewMatrix();
float[] getProjectionMatrix();
float[] getViewProjectionMatrix();







}