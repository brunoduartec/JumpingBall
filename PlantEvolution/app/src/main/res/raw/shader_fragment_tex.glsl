#version 300 es

precision mediump float;


//varying vec4 v_Color;
uniform sampler2D u_Texture;    // The input texture.

in vec4 v_Color;
out vec4 fragColor;


in vec2 v_TexCoordinate;

void main() {
  //gl_FragColor = v_Color;
 // gl_FragColor = vec4(0,1.0,0,1);
 //fragColor = vec4(0,1.0,0,1);
 fragColor  = ( texture(u_Texture, v_TexCoordinate));
}