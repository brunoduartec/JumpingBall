#version 300 es

precision mediump float;


uniform float u_LightIntensity;
uniform float u_AmbientLightIntensity;
uniform float u_SpecularLightIntensity;


//uniform float u_matShininess = 64; // = 64;



in vec3 v_Position;
in vec4 v_Color;
in vec3 v_Normal;

in vec3 o_LightPos;
in vec3 o_CameraPos;


out vec4 fragColor;

float ambientLighting()
{

    return  u_AmbientLightIntensity;
}

float diffuseLighting( in vec3 N, in vec3 L)
{
   // calculation as for Lambertian reflection

   // float distance = length(L);
    float diffuse = clamp(dot(N, L), 0.0, 1.0);//max(dot(L,N), 0.1);
 //    diffuse = diffuse * (1.0 / (1.0 + (0.25 * distance * distance)));

   return u_LightIntensity * diffuse;

}

// returns intensity of specular reflection
float specularLighting(in vec3 N, in vec3 L, in vec3 V)
{

   float specularTerm = 0.0;
    float u_matShininess = 8.0;
    // calculate specular reflection only if
      // the surface is oriented to the light source
      if( dot(N, L) > 0.0)
      {

       // half vector
           vec3 H = normalize(L + V);
           specularTerm = pow(dot(N, H), u_matShininess);
      }

    return u_SpecularLightIntensity*specularTerm ;
}


void main() {



vec3 L = normalize(o_LightPos);
vec3 V = normalize(o_CameraPos);
vec3 N = normalize(v_Normal);




    float Iamb = ambientLighting();
    float Idif = diffuseLighting(N,L);
    float Ispe = specularLighting(N, L, V);

 fragColor  = v_Color * ( Iamb + Idif + Ispe);

}