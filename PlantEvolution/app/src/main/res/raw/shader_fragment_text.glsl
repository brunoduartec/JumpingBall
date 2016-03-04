
precision mediump float;            // Set the default precision to medium. We don't need as high of a
// precision in the fragment shader.
varying vec4 u_Color;
varying vec2 v_TexCoordinate;   // Interpolated texture coordinate per fragment.
uniform sampler2D u_Texture;           // The input texture.


void main()                         // The entry point for our fragment shader.
{
   gl_FragColor = texture2D(u_Texture, v_TexCoordinate).w * u_Color; // texture is grayscale so take only grayscale value from
																					   // it when computing color output (otherwise font is always black)
}