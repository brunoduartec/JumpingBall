package ploobs.plantevolution.Camera;


import ploobs.plantevolution.ISerializable;
import ploobs.plantevolution.Math.Vector3;

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

Vector3 getPosition();
void setPosition(Vector3 p);
Vector3 getTarget();
void setTarget(Vector3 t);
String getName();
void setName(String name);

float[] getViewMatrix();
float[] getProjectionMatrix();
float[] getViewProjectionMatrix();







}