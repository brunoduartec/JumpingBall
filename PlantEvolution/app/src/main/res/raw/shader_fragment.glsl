precision mediump float;


//varying vec4 v_Color;
uniform sampler2D u_Texture;    // The input texture.

varying vec4 v_Color;



varying vec2 v_TexCoordinate;

void main() {
 gl_FragColor  = v_Color;/// * texture2D(u_Texture, v_TexCoordinate);
}