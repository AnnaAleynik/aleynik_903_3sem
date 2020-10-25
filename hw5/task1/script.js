document.addEventListener('DOMContentLoaded', () => {
    
    G = 9.8;

    ball = document.getElementById("ball");
    speed = -1;
        
    height = parseInt(window.getComputedStyle(document.getElementById("box")).height);
    border = parseInt(window.getComputedStyle(document.getElementById("box")).border);
    ballSize = parseInt(window.getComputedStyle(ball).height); 

    heightLine = height - border - ballSize;

    ball.addEventListener("click", () =>  {
        setInterval( () => {

                bottom = parseInt(window.getComputedStyle(ball).bottom);
                move = bottom + speed;

                if (move < 0 || move > heightLine ) { //448
                    speed *= -1;
                }

                ball.style.bottom = move + 'px';
                    
        },
        1000/60
        );
    });
});
