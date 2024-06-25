#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D texture;

//varying vec4 vertColor;
varying float interpolatedZ;
varying vec2 texCoordOut;


void main() {
	vec4 texColor = texture2D(texture, texCoordOut);
    float remainder = mod(interpolatedZ, 2.0);
    
    if ((interpolatedZ > -195) && (remainder >= 0.1 && remainder <= 0.2)) {
		gl_FragColor = texColor * vec4(0.0, 0.0, 0.0, 1.0); // dark
    } else {
        if (interpolatedZ < -195.0) {
            gl_FragColor = texColor * vec4(0.0, 1.0, 0.0, 1.0); // green
        } else {
            gl_FragColor = texColor; // white
        }
    }
}
