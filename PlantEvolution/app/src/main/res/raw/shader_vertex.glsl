 // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
#version 300 es


uniform mat4 uMVPMatrix;
in vec4 a_Position;
in vec4 a_Color;        		// Per-vertex color information we will pass in.

in vec2 a_TexCoordinate;
out vec2 v_TexCoordinate;


out vec4 v_Color;          		// This will be passed into the fragment shader.


void main() {
// The matrix must be included as a modifier of gl_Position.
// Note that the uMVPMatrix factor *must be first* in order
// for the matrix multiplication product to be correct.

  
  v_Color = a_Color;
  gl_Position = uMVPMatrix * a_Position;
  v_TexCoordinate = a_TexCoordinate;


}