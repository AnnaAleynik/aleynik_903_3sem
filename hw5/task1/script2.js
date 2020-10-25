document.addEventListener('DOMContentLoaded', () => {
    
    g = 10;
    time = 0;

    ball = document.getElementById("ball");
    speed = 1;
        
    height = parseInt(window.getComputedStyle(document.getElementById("box")).height);
    border = parseInt(window.getComputedStyle(document.getElementById("box")).border);
    ballSize = parseInt(window.getComputedStyle(ball).height); 

    bottomLine = height - border - ballSize;

    heightLine = height - border - ballSize;

    
    move = 0; //!!!
    jump = 0;
    ball.addEventListener("click", () =>  {
        falling = setInterval( () => {
            time = time + 0.001;
            speed = speed + g*time;
            topBall = parseInt(window.getComputedStyle(ball).top);
                // topBall = parseInt(ball.style.top);
                
            move = topBall + speed*time + g*time*time/2;
                // move = topBall + speed;
                console.log(topBall);

            if (move < 0 || move > bottomLine) { //448
                speed *= -1;
                jump += 8;
            }

            ball.style.top = move + 'px';
                    
        },
        1000/60
    );});

});
