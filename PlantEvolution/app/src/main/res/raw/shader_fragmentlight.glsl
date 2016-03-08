precision mediump float;


uniform float u_LightIntensity;
uniform float u_AmbientLightIntensity;
uniform float u_SpecularLightIntensity;

uniform sampler2D u_Texture;    // The input texture.
//uniform float u_matShininess = 64; // = 64;



varying vec3 v_Position;
varying vec4 v_Color;
varying vec3 v_Normal;

varying vec3 o_LightPos;
varying vec3 o_CameraPos;
varying vec2 v_TexCoordinate;



//varying vec4 fragColor;

float ambientLighting()
{

    return  u_AmbientLightIntensity;
}

float diffuseLighting(vec3 N, vec3 L)
{
   // calculation as for Lambertian reflection

   // float distance = length(L);
    float diffuse = clamp(dot(N, L), 0.0, 1.0);//max(dot(L,N), 0.1);
 //    diffuse = diffuse * (1.0 / (1.0 + (0.25 * distance * distance)));

   return u_LightIntensity * diffuse;

}

// returns intensity of specular reflection
float specularLighting(vec3 N, vec3 L, vec3 V)
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

// gl_FragColor  = texture2D(u_Texture, v_TexCoordinate)  * ( Iamb + Idif + Ispe);
gl_FragColor  = texture2D(u_Texture, v_TexCoordinate);
}