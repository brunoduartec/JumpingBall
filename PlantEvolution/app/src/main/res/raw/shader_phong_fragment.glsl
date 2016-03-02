#version 300 es

precision mediump float;

in vec3 normalInterp;
in vec3 vertPos;

uniform int mode;

uniform vec3 u_LightPos;
uniform vec3 u_AmbientColor;
uniform vec3 u_DiffuseColor;
uniform vec3 u_SpecColor;

out vec4 fragColor;

void main() {

  vec3 normal = normalize(normalInterp);
  vec3 lightDir = normalize(u_LightPos - vertPos);

  float lambertian = max(dot(lightDir,normal), 0.0);
  float specular = 0.0;

  if(lambertian > 0.0) {

    vec3 viewDir = normalize(-vertPos);

    // this is blinn phong
    vec3 halfDir = normalize(lightDir + viewDir);
    float specAngle = max(dot(halfDir, normal), 0.0);
    specular = pow(specAngle, 16.0);

    // this is phong (for comparison)
    if(mode == 2) {
      vec3 reflectDir = reflect(-lightDir, normal);
      specAngle = max(dot(reflectDir, viewDir), 0.0);
      // note that the exponent is different here
      specular = pow(specAngle, 4.0);
    }
  }

  fragColor = vec4(u_AmbientColor +
                      lambertian * u_DiffuseColor +
                      specular * u_SpecColor, 1.0);
}
