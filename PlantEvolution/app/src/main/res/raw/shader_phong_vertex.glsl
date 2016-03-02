#version 300 es

in vec3 a_Position;
in vec2 a_TextCoord;
in vec3 a_Normal;
in vec4 a_Color;        		// Per-vertex color information we will pass in.


uniform mat4 u_MVPMatrix;      		// A constant representing the combined model/view/projection matrix.
uniform mat4 u_MVMatrix;       		// A constant representing the combined model/view matrix.
uniform mat4 u_MatNorm;

out vec3 normalInterp;
out vec3 vertPos;

void main(){
    gl_Position = u_MVPMatrix * vec4(a_Position, 1.0);
    vec4 vertPos4 = u_MVMatrix * vec4(a_Position, 1.0);
    vertPos = vec3(vertPos4) / vertPos4.w;
    normalInterp = vec3(u_MatNorm * vec4(a_Normal, 0.0));
}
