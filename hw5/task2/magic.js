document.addEventListener('DOMContentLoaded', () => {
    
    ball = document.getElementById("ball");
    dx = 1.5;
    dy = 2.5;
    c = 0.05;
    d = 0.05;
        
    height = parseInt(window.getComputedStyle(document.getElementById("box")).height);
    border = parseInt(window.getComputedStyle(document.getElementById("box")).border);
    ballSize = parseInt(window.getComputedStyle(ball).height); 

    borderLine = height - border - ballSize;

    ball.addEventListener("click", () =>  {
        setInterval( () => {

            topBall = parseInt(window.getComputedStyle(ball).top);
            leftBall = parseInt(window.getComputedStyle(ball).left);

            moveUp = topBall + dy;
            moveLeft = leftBall + dx;

            if (moveUp < 0 || moveUp > borderLine ) { //448
                // c = -c;
                dy = -dy;// + c;
            }

            if (moveLeft < 0 || moveLeft > borderLine ) { //448
                // d = -d;
                dx = -dx; // + d;
            }

            ball.style.top = moveUp + 'px';
            ball.style.left = moveLeft + 'px';
                    
        },
        1000/60
        );
    });
});
