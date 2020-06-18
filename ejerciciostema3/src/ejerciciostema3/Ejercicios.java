<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="js/pixi.min.js"></script>
    <script>
            var hexagonRadius = 50;
            var hexagonHeight = hexagonRadius * Math.sqrt(3);

            
        //CONTAINER
        window.onload = function(){
            app = new PIXI.Application({

                width: 1200,
                height: 600,
                backgroundColor: 0xAAE4
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

            let hex1 = {'x' : 626, 'y' : -289};
            let hex2 = {'x' : 733, 'y' : -224};
            let hex3 = {'x' : 626, 'y' : -153};

            var hexagons = [];

            hexagons.push(hex1);
            hexagons.push(hex2);
            hexagons.push(hex3);

            console.log(hexagons);
            console.log(hexagons[0].x);
            for (var i = 0 ; i < 19; i++) {
           /* var hexaP = toHexagonPosition({
                x: hexagons[i].x,
                y: hexagons[i].y
            })*/

            var hexaP = toHexagonPosition({
                x: 599,
                y: 256
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
            punto.x = 100;
            punto.y = 100;
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


        }


function createBunny(x, y) {

var bunny = new PIXI.Graphics();
bunny.beginFill(0xFF0000);

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
