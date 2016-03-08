uniform mat4 u_MVPMatrix;      		// A constant representing the combined model/view/projection matrix.
uniform mat4 u_MVMatrix;       		// A constant representing the combined model/view matrix.	


uniform vec3 u_LightPos;       	    // The position of the light in eye space.
uniform vec3 u_CameraPos;       	    // The position of the light in eye space.




varying vec2 v_TexCoordinate;

attribute vec4 a_Position;     		// Per-vertex position information we will pass in.
attribute vec4 a_Color;        		// Per-vertex color information we will pass in.
attribute vec3 a_Normal;       		// Per-vertex normal information we will pass in.
attribute vec2 a_TexCoordinate;



varying vec3 v_Position;              // This will be passed into the fragment shader.
varying vec4 v_Color;                // This will be passed into the fragment shader.
varying vec3 v_Normal;                // This will be passed into the fragment shader.
varying vec3 o_LightPos;
varying vec3 o_CameraPos;

void main()                     	// The entry point for our vertex shader.
{                              		

   //Transform the vertex into world space
   v_Position = vec3(u_MVMatrix * a_Position);

   // Pass through the color
   v_Color = a_Color;

   //Transform the normal's orientation into world space
   v_Normal = vec3(u_MVMatrix * vec4(a_Normal,0.0));

// get a ligthning direction vector from light to vertex
   // o_LightPos = normalize(  vec3(u_MVMatrix * vec4(u_LightPos,0.0) )   - v_Position);
    o_LightPos = normalize ( vec3(u_MVMatrix * vec4(u_LightPos,0.0) )   -     v_Position);
    o_CameraPos = normalize ( vec3(u_MVMatrix * vec4(u_CameraPos,0.0) ) -     v_Position);
   // o_CameraPos = normalize(  vec3(u_MVMatrix * vec4(u_CameraPos,0.0) )   - v_Position);


    v_TexCoordinate = a_TexCoordinate;

   // Transform to screen coordinates
   gl_Position = u_MVPMatrix * a_Position;




}    