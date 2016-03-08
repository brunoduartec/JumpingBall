uniform mat4 uMVPMatrix;
attribute vec4 a_Position;
attribute vec4 a_Color;        		// Per-vertex color information we will pass in.

attribute vec2 a_TexCoordinate;
varying vec2 v_TexCoordinate;
varying vec4 v_Color;          		// This will be passed into the fragment shader.


void main() {
// The matrix must be included as a modifier of gl_Position.
// Note that the uMVPMatrix factor *must be first* in order
// for the matrix multiplication product to be correct.

  
  v_Color = a_Color;
  gl_Position = uMVPMatrix * a_Position;
  v_TexCoordinate = a_TexCoordinate;


}