uniform mat4 transform;

attribute vec4 position;
//attribute vec4 color;
attribute vec2 texCoord;

varying float interpolatedZ;
varying vec4 vertColor;
varying vec2 texCoordOut;

void main() {
	vec4 nposition = vec4(position.x, position.y, position.z, position.w);
	gl_Position   = transform * nposition;
	
	//vertColor     = color;
	
	interpolatedZ = position.z;
	
	texCoordOut = texCoord;
}

