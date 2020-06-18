<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="js/pixi.min.js"></script>
    <script>
            var hexagonRadius = 52;
            var hexagonHeight = hexagonRadius * Math.sqrt(3);

            
        //CONTAINER
        window.onload = function(){
            app = new PIXI.Application({

                width: 900,
                height: 600,
                backgroundColor: 0xAAE4,
                
            }
            );

            document.body.appendChild(app.view);

            const container = new PIXI.Container();

        app.stage.addChild(container);
            //POSICIONES

            const $count = document.createElement('div');
            $count.style = 'position: fixed; right: 10px; bottom: 10px; background: deeppink; color: white; padding: 5px; font-family: sans-serif';
            document.body.appendChild($count);


            const $view = document.createElement('canvas');
            document.body.appendChild($view);

            debugger;
            const renderer = new PIXI.autoDetectRenderer({width: window.innerWidth, height: window.innerHeight,
            transparent: true,
            autoResize: true,
            pixelRatio: window.devicePixelRatio,
            view: $view,
            });

            const ticker = PIXI.Ticker.shared;

            ticker.add(() => {
            const { x, y } = renderer.plugins.interaction.mouse.global;
            $count.textContent = x + ',' + y;
            })
 
            //NARANJAS
            // Create a new texture
            const texture = PIXI.Texture.from('images/naranja.jpg');

            // Create a 5x5 grid of bunnies
          /*  for (let i = 0; i < 25; i++) {*/
                const bunny = new PIXI.Sprite(texture);
                bunny.anchor.set(0.5);
                bunny.x = (1 % 5) * 40;
                bunny.y = Math.floor(1 / 5) * 40;
                container.addChild(bunny);
            /*}*/

            // Move container to the center
            container.x = app.screen.width / 2;
            container.y = app.screen.height / 2;

            // Center bunny sprite in local container coordinates
            container.pivot.x = container.width / 2;
            container.pivot.y = container.height / 2;

            // Listen for animate update
            app.ticker.add((delta) => {
                // rotate the container!
                // use delta to create frame-independent transform
                container.rotation -= 0.01 * delta;
            });
            
            //HEXAGONO
 

            var texture1 = PIXI.Texture.fromImage('images/hexRed.jpg');

            // Scale mode for pixelation
            texture1.baseTexture.scaleMode = PIXI.SCALE_MODES.NEAREST;

            let hex1 = {'x' : 450, 'y' : 259};
            let hex2 = {'x' : 525, 'y' : 303};
            let hex3 = {'x' : 525, 'y' : 216};
            let hex4 = {'x' : 450, 'y' : 173};
            let hex5 = {'x' : 375, 'y' : 216};
            let hex6 = {'x' : 375, 'y' : 303};
            let hex7 = {'x' : 450, 'y' : 346};
            let hex8 = {'x' : 600, 'y' : 259};
            let hex9 = {'x' : 600, 'y' : 173};
            let hex10 = {'x' : 525, 'y' : 129};
            let hex11 = {'x' : 450, 'y' : 86};
            let hex12 = {'x' : 375, 'y' : 129};
            let hex13 = {'x' : 300, 'y' : 173};
            let hex14 = {'x' : 300, 'y' : 259};
            let hex15 = {'x' : 300, 'y' : 346};
            let hex16 = {'x' : 375, 'y' : 389};
            let hex17 = {'x' : 450, 'y' : 433};
            let hex18 = {'x' : 525, 'y' : 389};
            let hex19 = {'x' : 600, 'y' : 346};


            const hexagons = [];

            hexagons.push(hex1);
            hexagons.push(hex2);
            hexagons.push(hex3);
            hexagons.push(hex4);
            hexagons.push(hex5);
            hexagons.push(hex6);
            hexagons.push(hex7);
            hexagons.push(hex8);
            hexagons.push(hex9);
            hexagons.push(hex10);
            hexagons.push(hex11);
            hexagons.push(hex12);
            hexagons.push(hex13);
            hexagons.push(hex14);
            hexagons.push(hex15);
            hexagons.push(hex16);
            hexagons.push(hex17);
            hexagons.push(hex18);
            hexagons.push(hex19);
         


            console.log(hexagons);
            console.log(hexagons[0].x);
            for (var i = 0 ; i < hexagons.length; i++) {
           /* var hexaP = toHexagonPosition({
                x: hexagons[i].x,
                y: hexagons[i].y
            })*/

            var hexaP = toHexagonPosition({
                x: hexagons[i].x,
                y: hexagons[i].y
            })
            console.log('hexagono ' + i);
            console.log(hexaP.x);
            console.log(hexaP.y);
            createBunny(
                hexaP.x,
                hexaP.y
            );
            }

            //PUNTO
             //const texture2 = PIXI.Texture.from(images/pui)
             const punto =  PIXI.Sprite.from('images/punto.jpg');
            punto.interactive = true;
            punto.buttonMode = true;
            punto.x = 526;
            punto.y = 322;
            app.stage.addChild(punto);

            punto.on('pointerdown', ()=>{
                console.log("prueba");
            } );
            var style = {
            font: '18px Courier, monospace',
            fill: '#ffffff'
             };

            const onDragStart = event => {
                punto.data = event.data;
                punto.dragging = true;
            };
            const onDragEnd = event => {
                delete punto.data;
                punto.dragging = false;
            };
            const onDragMove = event => {
                if(punto.dragging === true){
                    const newPosition = punto.data.getLocalPosition(punto.parent);
                    punto.x = newPosition.x;
                    punto.y = newPosition.y;  
                    console.log('posiciones punto');
                    console.log('x: ' + newPosition.x);
                    console.log('y: ' + newPosition.y);      
                }
            };

            punto.on('pointerdown', onDragStart)
            .on('pointerup', onDragEnd)
            .on('pointerupoutside', onDragEnd)
            .on('pointermove', onDragMove);


            //PUNTOS BUENOS

            const semicircle = new PIXI.Graphics();
            semicircle.beginFill(0x000000);
            semicircle.lineStyle(0.5, 0xffffff);
            semicircle.arc(25 ,25 , 12, 25, Math.PI); // cx, cy, radius, startAngle, endAngle
            semicircle.position = {x: 526, y: 322};
            app.stage.addChild(semicircle);

        }


function createBunny(x, y) {

var bunny = new PIXI.Graphics();
var colors = [0xFF0000, 0x9C9C9C, 0xFFFF00, 0x008F39, 0x00FF00, 0xEED09D];
var count = [0,0,0,0,0,0];
var rnd = Math.floor(Math.random() * (5 - 0)) + 0;

if(count[rnd] < 5 ){
count[rnd] = count[rnd] + 1;
bunny.beginFill(colors[rnd]);
bunny.lineStyle(3, 0xffffff);
}else if(count[5] == 1){
bunny.beginFill(colors[5]);
count[5] = count[5] + 1; 
}else{
bunny.beginFill(colors[rnd]);
}
/*if(count = 0){
bunny.beginFill(0xEED09D);
}else{
bunny.beginFill(0x9C9C9C);
count++;
}*/
bunny.drawPolygon([
  -hexagonRadius, 0,
  -hexagonRadius/2, hexagonHeight/2,
  hexagonRadius/2, hexagonHeight/2,
  hexagonRadius, 0,
  hexagonRadius/2, -hexagonHeight/2,
  -hexagonRadius/2, -hexagonHeight/2,

])

bunny.endFill();
bunny.x = x;
bunny.y = y;


bunny.interactive = true;


bunny.buttonMode = true;

bunny
    .on('pointerdown', onDragStart)
    .on('pointerup', onDragEnd)
    .on('pointerupoutside', onDragEnd)
    .on('pointermove', onDragMove);


bunny.x = x;
bunny.y = y;

// add it to the stage
app.stage.addChild(bunny);
// app.stage.addChild(hexagon);
}

function onDragStart(event) {

this.data = event.data;
this.alpha = 0.5;
this.dragging = true;
}

function onDragEnd() {
this.alpha = 1;
this.dragging = false;
// set the interaction data to null
this.data = null;
}

function onDragMove() {
if (this.dragging) {
var newPosition = this.data.getLocalPosition(this.parent);
var hexaP = toHexagonPosition(newPosition);
this.x = hexaP.x;
this.y = hexaP.y;
console.log('posiciones hexagono');
console.log(hexaP.x);
console.log(hexaP.y);
// this.mask.x = this.x;
// this.mask.y = this.y;
}
}

function toHexagonPosition(p) {
var newP = {};
var xIdx = Math.round(p.x / (hexagonRadius * (3 / 2)));
newP.x = xIdx * (hexagonRadius * (3 / 2));
if (xIdx % 2) {
newP.y = Math.floor(p.y / (hexagonHeight)) * hexagonHeight + hexagonHeight / 2; 
} else {
newP.y = Math.round(p.y / (hexagonHeight)) * hexagonHeight;
}

return newP;
}
    </script>
</head>
<body>
</body>
</html>
