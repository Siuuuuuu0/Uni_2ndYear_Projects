PShader shader;
PShape terrain;
PImage textureImage;
PImage metalTextureImage;
PShape pylone;
String terrain_path = "HYPERSIMPLE/hypersimple.obj";
String terrain_texture = "HYPERSIMPLE/StAuban_texture.jpg";
String metal_texture = "metal.png";

boolean moveForward = false, moveBackward = false,
moveLeft = false, moveRight = false, moveUp = false, moveDown = false;

float camPosX = 0, camPosY = 0, camPosZ = 0;
float camAngleX = 0, camAngleY = 0, camAngleZ = 0;

//float zoom = 1.0;


public void setup()
{ 
  //-10, 70, -265 yzx
  size(1920, 1050, P3D);
  
  smooth();
  
  terrain = loadShape(terrain_path);
  
  shader = loadShader("fragmentShader.glsl", "vertexShader.glsl");
  
  textureImage = loadImage(terrain_texture);
  metalTextureImage = loadImage(metal_texture);
  
  createPylone(45);
  
//  print(terrain.getWidth() + "\n");
//  print(terrain.getHeight() + "\n");
//  print(terrain.getDepth() + "\n");
}

public void draw()
{
  background(255);
  shader(shader);
  shader.set("texture", textureImage);
  //shader.set("interpolatedZ", z);
  lights();

  camera(camPosX, camPosY, camPosZ, 
         camPosX + cos(camAngleY) * cos(camAngleX), 
         camPosY + sin(camAngleX), 
         camPosZ + sin(camAngleY) * cos(camAngleX), 
         0.0, 1.0, 0.0);
    
  
  scale(3.0);

  updCamPos();
  translate(5,-140,-110);
  rotateX(-30);
  rotateZ(30);
  
  shape(terrain);
  
  resetShader();
  
  createPyloneLine(18, 15, 45, -100,-100);
  randomEoliennes();

}

// // // // // // // // // // // // //
// FUNCTIONS TO SPECTATE TERRAIN // //
// // // // // // // // // // // // //

void keyPressed() {
  if (key == 'w' || key == 'W') {
    moveForward = true;
  } else if (key == 's' || key == 'S') {
    moveBackward = true;
  } else if (key == 'a' || key == 'A') {
    moveLeft = true;
  } else if (key == 'd' || key == 'D') {
    moveRight = true;
  }
   else if (key == '8') {
    moveUp = true;
  }
   else if (key == '5') {
    moveDown = true;
  }
}

void keyReleased() {
  if (key == 'w' || key == 'W') {
    moveForward = false;
  } else if (key == 's' || key == 'S') {
    moveBackward = false;
  } else if (key == 'a' || key == 'A') {
    moveLeft = false;
  } else if (key == 'd' || key == 'D') {
    moveRight = false;
  }
   else if (key == '8') {
    moveUp = false;
  }
   else if (key == '5') {
    moveDown = false;
  }
}

void updCamPos() {
  float moveSpeed = 5;
  
  if (moveForward) {
    camPosX += cos(camAngleY) * moveSpeed;
    camPosZ += sin(camAngleY) * moveSpeed;
  } 
  if (moveBackward) {
    camPosX -= cos(camAngleY) * moveSpeed;
    camPosZ -= sin(camAngleY) * moveSpeed;
  } 
  if (moveLeft) {
    camPosX += sin(camAngleY) * moveSpeed;
    camPosZ -= cos(camAngleY) * moveSpeed;
  } 
  if (moveRight) {
    camPosX -= sin(camAngleY) * moveSpeed;
    camPosZ += cos(camAngleY) * moveSpeed;
  }
   if (moveUp) {
    camPosY -= 2;
  }
   if (moveDown) {
    camPosY += 2;
  }
}
  
void mouseMoved() 
{
  float sensitivity = 0.01;
  camAngleY += sensitivity * (mouseX - pmouseX);
  camAngleX += sensitivity * (mouseY - pmouseY);
  
  camAngleX = constrain(camAngleX, -HALF_PI + 0.1, HALF_PI - 0.1);
}

//void mouseWheel(MouseEvent event) 
//{
//  float reaction = event.getCount(); 
//  if (reaction > 0) {
//    zoom -= 0.1;
//  } else {
//    zoom += 0.1;
//  }
//  zoom = constrain(zoom, 1.0, 3.0);
//}
  
// // // // // // // // // // // // // // //
// // CODE BELOW IS MADE FOR PYLONES  // //
// // // // // // // // // // // // // // //  
  
void createPylone(float rot){
  
  pylone = createShape(GROUP); 
  pylone.addChild(createSide());
  PShape side2 = createSide(); 
  pushMatrix();
  side2.rotateY(PI/2);
  side2.translate(0, 0, 30);
  popMatrix();
  pylone.addChild(side2);
  PShape side3 = createSide(); 
  pushMatrix();
  side3.translate(0, 0, 30);
  popMatrix();
  pylone.addChild(side3);
  PShape side4 = createSide(); 
  pushMatrix(); 
  side4.rotateY(PI/2);
  side4.translate(30, 0, 30);
  popMatrix(); 
  pylone.addChild(side4);
  
  PShape p = createPyramide(); 
  pushMatrix(); 
  p.translate(30, -210, 15);
  popMatrix(); 
  pylone.addChild(p); 
  PShape p2= createPyramide(); 
  pushMatrix(); 
   
  p2.rotateY(PI); 
  p2.translate(0, -210,   15);
  popMatrix();
  pylone.addChild(p2); 
  
  PShape h = createHead(); 
  pushMatrix();
  h.translate(15, -315, 15); 
  popMatrix(); 
  pylone.addChild(h); 
  PShape h2 = createHead(); 
  pushMatrix(); 
  h2.rotateY(PI); 
  h2.translate(15, -315, 15); 
  popMatrix();
   pylone.addChild(h2); 
   pushMatrix();
  pylone.rotateX(-PI/2);
  pylone.rotateZ(PI/2);
  pylone.scale(1.0/50.0);
  popMatrix();
  pylone.rotateZ(radians(rot));
}
PShape createSide(){
  PShape side ; 
  side = createShape(); 
  side.beginShape(LINES);
  //side.noFill(); 
  side.stroke(255, 0, 0);
  side.strokeWeight(3);
  
  
  for(int i =0; i<10; i++){
      //d
      side.vertex(0, -i*30);
      side.vertex(30, -i*30);
      //r
      side.vertex(30, -i*30);
      side.vertex(30, -(i+1)*30);
      //u
      side.vertex(30, -(i+1)*30);
      side.vertex(0, -(i+1)*30);
      //l
      side.vertex(0, -(i+1)*30);
      side.vertex(0, -i*30);
      
      side.vertex(0, -i*30);
      side.vertex(30, -(i+1)*30);
      side.vertex(30, -i*30);
      side.vertex(0, -(i+1)*30);
      
  }
  side.endShape(); 
  return side;

}
PShape createPyramide(){
  PShape pyramide; 
  pyramide = createShape(); 
  pyramide.beginShape(TRIANGLE_FAN);
  pyramide.fill(0, 0, 0);
  pyramide.vertex(120, 0, 0); 
  pyramide.vertex(0, 15, -15);
  pyramide.vertex(0, 15, 15); 
  pyramide.vertex(0, -15, 15); 
  pyramide.vertex(0, -15, -15); 
  pyramide.vertex(0, 15, -15);
  pyramide.endShape();
  return pyramide;
}

PShape createHead(){
  PShape head; 
  head = createShape(); 
  head.beginShape(TRIANGLE_FAN); 
  head.fill(0, 0, 0); 
  head.vertex(70, 20, 0); 
  head.vertex(0, 20, -15);
  head.vertex(0, 20, 15); 
  head.vertex(0, 0, 15); 
  head.vertex(0, 0, -15); 
  head.vertex(0, 20, -15);
  head.endShape();
  return head; 
}
void createPylone(float x, float y, float z){
  pushMatrix(); 
  translate(0,0, z);
  
  shape(pylone, x, y);  
  popMatrix();

}

float getTerrainHeight(float x, float y)
{
  float minDist = Float.MAX_VALUE;
  float nearestHeight = Float.POSITIVE_INFINITY;
  for(int i = 0; i < terrain.getChildCount(); i++)
  {
   PShape child = terrain.getChild(i);
   if(child.getVertexCount() > 0)
   {
      for(int j = 0; j < child.getVertexCount(); j++)
      {
          PVector vertex = child.getVertex(j);
          float d = dist(x, y, vertex.x, vertex.y);
          if(d < minDist)
          {
             minDist = d; 
             nearestHeight = vertex.z;
          }
      }
   }
  }
  return nearestHeight; 
}

float cosh(float x)
{
  return (exp(x) + exp(-x)) / 2.0;
}

class electroLine {
  
  PVector p1, p2;
  PVector translatedP1, translatedP2;
  
  float angle;
  int precisionPerUnit;
  int precision;
  float alpha;
  
  float b;
  float c;
  
  public float resB(PVector p1, PVector p2, float alpha) {
    float scaled_diffz = (p2.z - p1.z) / alpha;
    float scaled_difft = (p2.x - p1.x) / alpha;
    return log(scaled_diffz + sqrt(sq(scaled_diffz) + 2.0 * (cosh(scaled_difft) - 1.0))) - log(exp(p2.x / alpha) - exp(p1.x / alpha));
  }
  
  public float resC(PVector p, float b, float alpha) {
   return p.z - alpha * cosh(p.x / alpha + b);
  }
  
  public float catenaryFun(float t) {
    return this.alpha * cosh(t / this.alpha + this.b) + this.c;
  }
  
  
  public void init() {
    pushMatrix();
    translate(this.p1.x, this.p1.y, 0.0);
    rotate(this.angle);

    float t = 0.;
    beginShape(LINES);
    for(int i = 0; i < this.precision; i++) {
      t = float(i) / this.precisionPerUnit;
      vertex(t, 0.0, this.catenaryFun(t));

    }
    vertex(this.translatedP2.x, 0.0, this.translatedP2.z);
    endShape();
    popMatrix();
  }
  
  electroLine(int precisionPerUnit, float alpha, PVector p1, PVector p2) {
    this.p1 = p1;
    this.p2 = p2;
    this.alpha = alpha;
    
    this.angle = atan2(p2.x - p1.x, p2.y - p1.y);
    
    this.translatedP1 = new PVector(0.0, 0.0, p1.z);
    this.translatedP2 = new PVector(sqrt(sq(this.p2.x - this.p1.x) +sq(this.p2.y - this.p1.y)), 0.0, this.p2.z);
    
    this.precisionPerUnit = precisionPerUnit;
    this.precision = int(precisionPerUnit * this.translatedP2.x);
    
    this.b = resB(translatedP1, translatedP2, alpha);
    this.c = resC(translatedP1, this.b, alpha);
  }
}

//Higher precision -> more work for function to do. Recommended precision 10 < n < 100
//Recommended alpha 20 < n < 100
electroLine createElectroLine(int precisionPerUnit, float alpha, float x1, float y1, float z1, float x2, float y2, float z2)
{
  return new electroLine(precisionPerUnit, alpha, new PVector(x1,y1,z1), new PVector(x2,y2,z2));
}


void createPyloneLine(int n, float distBetweenPylones, float rot, float startX, float startY)
{
  
  float terrainMaxX = 127;
  float terrainMinX = -135;
  float terrainMaxY = 159;
  float terrainMinY = -158;
   
  float radOfAngle = radians(rot);
  
  float distX = cos(radOfAngle) * distBetweenPylones;
  float distY = sin(radOfAngle) * distBetweenPylones;
  
  float lineEndPointX = startX + distX * (n - 1);
  float lineEndPointY = startY + distY * (n - 1);
  
  if
  (
    lineEndPointX > terrainMaxX
    || lineEndPointX < terrainMinX
    || lineEndPointY > terrainMaxY
    || lineEndPointY < terrainMinY
  )
  {
      float maxXDistance = max(abs(terrainMaxX - startX), abs(terrainMinX - startX));
      float maxYDistance = max(abs(terrainMaxY - startY), abs(terrainMinY - startY));
      
      int maxXCount = floor(maxXDistance / abs(distX));
      int maxYCount = floor(maxYDistance / abs(distY));
      
      n = min(maxXCount, maxYCount);
      n = max(n, 1);
  }
  
  for(int i = 0; i < n; i++)
  {
    float x = startX + i * distX;
    float y = startY + i * distY;
    float z = getTerrainHeight(x, y);
    
    createPylone(x, y, z);
    if(i < n - 1)
    {
      electroLine lineDownRight = createElectroLine(10, 20.0,
      x - 1.5 - 0.8, y + 2.8 - 0.8, z * 0.979,
      x + distX - 1.5 - 0.8, y + distY + 2.8 - 0.8, getTerrainHeight(x + distX, y + distY) * 0.979);
      electroLine lineDownLeft = createElectroLine(10, 15.0,
      x + 1.5, y - 1.8, z * 0.979,
      x + distX + 1.5, y + distY - 1.8, getTerrainHeight(x + distX, y + distY) * 0.979);
      
      electroLine lineUpRight = createElectroLine(10, 20.0,
      x - 1.6, y + 0.9, z * 0.969,
      x + distX - 1.6, y + distY + 0.9, getTerrainHeight(x + distX, y + distY) * 0.969);
      electroLine lineUpLeft = createElectroLine(10, 15.0,
      x + 0.5, y - 1.0, z * 0.969,
      x + distX + 0.5, y + distY - 1.0, getTerrainHeight(x + distX, y + distY) * 0.969);
      
      lineDownRight.init();
      lineDownLeft.init();
      
      lineUpRight.init();
      lineUpLeft.init();      
    }
    else continue;
  }
  
}

// // // // // // // // // // // // // // //
// // CODE BELOW IS MADE FOR EOLIENNES // //
// // // // // // // // // // // // // // //


PShape createBase(){
  PShape base = createShape(); 
  base.beginShape(QUAD_STRIP);
  base.strokeWeight(0.1);
  base.fill(255, 255, 255);
  //base.strokeWeight(0.1);
  //base.fill(255, 255, 255);
  float a; 
  for(int i = -10; i<10; i++){
    a = i/10.0*PI; 
    base.vertex(cos(a)*5, 0, sin(a)*5); 
    base.vertex(cos(a)*10, 200, sin(a)*10); 
  }
  base.vertex(cos(-PI)*5, 0, sin(-PI)*5); 
  base.vertex(cos(-PI)*10, 200, sin(-PI)*10); 
  base.endShape(); 
  base.scale(3);
  
  return base; 
}

PShape createLopast(){
  PShape l = createShape(); 
  l.beginShape(); 
  l.strokeWeight(0.1);
  l.fill(255, 255, 255);
  l.strokeWeight(0.2);
  l.vertex(-175, -10, -10);
  l.bezierVertex(-60, -10, -30, -20, 20, -30, 10, 0, 0);
  l.vertex(10, -10, 0);
  l.bezierVertex(-10, -10, -30, -60, -20, -30,  -175, -10, -10);
  l.endShape();
  l.scale(3);
  return l; 
}

PShape createEolEngineForm(float baseRadius, float coneTopRadius, float coneHeight) {
  
  PShape form = createShape(GROUP);
  
  int halfSphereRings = 12;
  int halfSphereSegments = 24;
  int coneSegments = 36;
  float truncatedConeTranslateZ = -baseRadius + (coneHeight/ 2);
  

  //Creating half sphere
  PShape halfSphere = createShape();
  halfSphere.beginShape(TRIANGLE_STRIP);
  halfSphere.strokeWeight(0.1);
  halfSphere.fill(255, 255, 255);
  for (int i = 0; i <= halfSphereRings / 2; i++) {
    float lat0 = PI * (-0.5f + (float)i / halfSphereRings);
    float z0  = sin(lat0) * baseRadius;
    float r0  = cos(lat0) * baseRadius;
    float z1  = sin(lat0 + PI / halfSphereRings) * baseRadius;
    float r1  = cos(lat0 + PI / halfSphereRings) * baseRadius;
    
    for (int j = 0; j <= halfSphereSegments; j++) {
      float lon = 2 * PI * (float)j / halfSphereSegments;
      float x = cos(lon);
      float y = sin(lon);
      
      halfSphere.vertex(x * r0, y * r0, z0);
      halfSphere.vertex(x * r1, y * r1, z1);
    }
  }
  halfSphere.translate(0,0,-baseRadius);
  halfSphere.endShape();
  

  //Creating truncated (cut) cone
  PShape truncatedCone = createShape();
  truncatedCone.beginShape(QUAD_STRIP);
  truncatedCone.strokeWeight(0.1);
  truncatedCone.fill(255, 255, 255);
  for (int i = 0; i <= coneSegments; i++) {
    float angle = TWO_PI * i / coneSegments;
    float x = cos(angle);
    float z = sin(angle);
    

    float r1 = baseRadius;
    float r2 = coneTopRadius;
    

    float y1 = -coneHeight / 2;
    float y2 = coneHeight / 2;
    
    truncatedCone.vertex(r1 * x, y1, r1 * z);
    truncatedCone.vertex(r2 * x, y2, r2 * z);
  }
  truncatedCone.rotateX(PI/2);
  truncatedCone.translate(0,0,truncatedConeTranslateZ);
  truncatedCone.endShape();
 

  form.addChild(halfSphere);
  form.addChild(truncatedCone);
  return form;
}

PShape createEolienne(){
  PShape eolienne = createShape(GROUP);
  PShape base = createBase();
  base.setTexture(metalTextureImage);
  eolienne.addChild(base);
  //eolienne.addChild(createFan());
  PShape e0 = createLopast();
  e0.setTexture(metalTextureImage);
  e0.rotateZ(frameCount/30.0);
  eolienne.addChild(e0);
  PShape e1 = createLopast(); 
  e1.setTexture(metalTextureImage);
  e1.rotateZ(frameCount/30.0 + PI/3*2);
  eolienne.addChild(e1);
  PShape e2 = createLopast(); 
  e2.setTexture(metalTextureImage);
  e2.rotateZ(frameCount/30.0 -PI/3*2);
  eolienne.addChild(e2);
  PShape engineForm = createEolEngineForm(50, 10, 200);
  engineForm.setTexture(metalTextureImage);
  eolienne.addChild(engineForm);
  eolienne.translate(0, -200, 0);
  //eolienne.rotateY(PI/2);
  eolienne.rotateX(-PI/2);
  eolienne.rotateZ(-PI/2);
  eolienne.scale(1.0/120.0);
  //translate(0, 0, z) z = z coords of the vertex
  return eolienne;
}

void randomEoliennes()
{
  int count = 0; 
  for(int i =0; i<terrain.getChildCount(); i++){
    PShape child = terrain.getChild(i); 
    for(int j =0; j<child.getVertexCount(); j++){
       
       if(child.getVertex(j).z >-195&&count<=5)
       {
         
         pushMatrix(); 
         translate(child.getVertex(j).x, child.getVertex(j).y, child.getVertex(j).z+3) ; 
         PShape eolienne = createEolienne(); 
         shape(eolienne);
         popMatrix(); 
         count++; 
       }
    }
  }
}
