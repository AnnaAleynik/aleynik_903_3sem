document.addEventListener('DOMContentLoaded', () => {
    left = document.getElementById("left");
    right = document.getElementById("right");
    pipe = document.getElementById("pipe1");

    leftTop = parseInt(window.getComputedStyle(left).top);
    rightTop = parseInt(window.getComputedStyle(right).top);
    leftLeft = parseInt(window.getComputedStyle(left).left);
    rightLeft = parseInt(window.getComputedStyle(right).left);

    pipeX = parseInt(window.getComputedStyle(pipe).left);
    pipeY = parseInt(window.getComputedStyle(pipe).top);   

    xCenter1 = 50;
    yCenter1 = 50;
    r1 = leftLeft - xCenter1;
        
    xCenter2 = 50 + 20 + 50;
    yCenter2 = 50;
    r2 = rightLeft - xCenter2;
        
    // alert(radius);
    // alert(pipeX - xCenter2);
    // alert(pipeY - yCenter2);

    function move(mouseX, mouseY, r){
        fi = 0;
        if (mouseX != 0) {
            fi = Math.atan(Math.abs(mouseY/mouseX));
        } else {
            fi = Math.PI/2;
        }
        console.log(" fi before: " + fi);

        if (mouseX < 0 && mouseY < 0) {
            fi = Math.PI + fi;
        }

        if (mouseX > 0 && mouseY < 0) {
            fi = -fi;
        }

        if (mouseX < 0 && mouseY > 0) {
            fi = Math.PI - fi;
        }
    6
        console.log(mouseX + " " + mouseY + " " + fi);

        x = r*Math.cos(fi);
        y = r*Math.sin(fi);
        // console.log(x + " " + y);
        res = [x, y];
        return res;
    }

    document.addEventListener('mousemove', e => {
        
        mouseX = e.clientX - xCenter1;
        mouseY = e.clientY - yCenter1;

        mouseX1 = e.clientX - xCenter2;
        mouseY1 = e.clientY - yCenter2;
        
        // catH = Math.abs(mouseX - xCenter1);
        // catV = Math.abs(mouseY - yCenter1);

        
        leftCoord = move(mouseX, mouseY, r1);
        left.style.left = leftCoord[0] + xCenter1 - 1 + "px";
        left.style.top = leftCoord[1] + yCenter1 - 1 + "px";

        leftCoord = move(mouseX1, mouseY1, r2);
        right.style.left = -leftCoord[0] + xCenter1 + "px";
        right.style.top = -leftCoord[1] + yCenter1 + "px";
        
    });
})