var oriImg = null;
var grayImg = null;
var redImg = null;
var rainImg = null;
var canvas = document.getElementById("c1");

function loadImage() {
  var input = document.getElementById("fileInput");
  oriImg = new SimpleImage(input);
  grayImg = new SimpleImage(input);
  redImg = new SimpleImage(input);
  rainImg = new SimpleImage(input);
  oriImg.drawTo(canvas);
}

function imageIsLoaded(img) {
  if(img == null || !img.complete())   {
    alert("The image has not been loaded");
    return false;
  }
  return true;
}

function filterGray() {
  for (var pix of grayImg.values()) {
    var avg = (pix.getRed() + pix.getGreen() + pix.getBlue()) / 3;
    pix.setRed(avg);
    pix.setGreen(avg);
    pix.setBlue(avg);
  }
}

function doGrayscale() {
  if (imageIsLoaded(grayImg)) {
    filterGray();
    grayImg.drawTo(canvas);
  }
}

function filterRed() {
  for (var pix of redImg.values()) {
    var average = (pix.getRed() + pix.getGreen() + pix.getBlue())/3;
    if (average < 128) {
      pix.setRed(2*average);
      pix.setGreen(0);
      pix.setBlue(0);
    } else {
      pix.setRed(255);
      pix.setGreen(2*average-255);
      pix.setBlue(2*average-255);
    }
  }
}

function doRed() {
  if (imageIsLoaded(redImg)) {
    filterRed();
    redImg.drawTo(canvas);
  }
}

function setColor (Rc, Gc, Bc, avg, pix) {
  if (avg < 128) {
    var R	= Rc/127.5*avg;
    var G = Gc/127.5*avg
    var B	= Bc/127.5*avg
  } else {
    var R = (2 - Rc/127.5)*avg + 2*Rc - 255;
    var G = (2 - Gc/127.5)*avg + 2*Gc - 255;
    var B = (2 - Bc/127.5)*avg + 2*Bc - 255;
  }
  pix.setRed(R);
  pix.setGreen(G);
  pix.setBlue(B);
}

function filterRainbow() {
  for (var pix of rainImg.values()) {
    var avg = (pix.getRed() + pix.getGreen() + pix.getBlue())/3;
    if (pix.getY() < rainImg.getHeight()/7)     {
      setColor(255, 0, 0, avg, pix);
    } else if (pix.getY() < rainImg.getHeight()*2/7){
      setColor(255, 165, 0, avg, pix);
    } else if (pix.getY() < rainImg.getHeight()*3/7){
      setColor(255, 255, 0, avg, pix);
    } else if (pix.getY() < rainImg.getHeight()*4/7){
      setColor(0, 255, 0, avg, pix);
    } else if (pix.getY() < rainImg.getHeight()*5/7){
      setColor(0, 0, 255, avg, pix);
    } else if (pix.getY() < rainImg.getHeight()*6/7){
      setColor(75, 0, 130, avg, pix);
    } else {
      setColor(143, 0, 255, avg, pix);
    } 
  }
}

function doRainbow() {
  if (imageIsLoaded(rainImg)) {
    filterRainbow();
    rainImg.drawTo(canvas);
  }
}

function getRandomInt() {
  return Math.floor(Math.random() * 21) - 10;
}

function doBlur() {
  if (imageIsLoaded(oriImg)) {
    var output = new SimpleImage(oriImg.getWidth(), oriImg.getHeight());
    for(var pix of output.values()) {
      if(Math.random() < 0.5) {
        output.setPixel(pix.getX(), pix.getY(), oriImg.getPixel(pix.getX(), pix.getY()));
      } else {
        var dx = getRandomInt();
        var dy = getRandomInt();
        var X = pix.getX()+dx;
        var Y = pix.getY()+dy;
        if (X < 0) {
          X = 0;
        } else if (X > output.getWidth()-1) {
          X = output.getWidth()-1;
        }
        if (Y < 0) {
          Y = 0;
        } else if (Y > output.getHeight()-1) {
          Y = output.getHeight()-1;
        }
        output.setPixel(pix.getX(), pix.getY(), oriImg.getPixel(X, Y));
      }
    }
    output.drawTo(canvas);
  }
}

function reset() {
  if (imageIsLoaded(oriImg)) {
    oriImg.drawTo(canvas);
    grayImg = new SimpleImage(oriImg);
    redImg = new SimpleImage(oriImg);
    rainImg = new SimpleImage(oriImg);
  }
}