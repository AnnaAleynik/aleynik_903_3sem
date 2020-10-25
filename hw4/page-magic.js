// document.onreadystatechange = function(){
window.addEventListener('DOMContentLoaded', () => {    
    function handler1() {
        // alert('hello!');
        elem = document.getElementById("first");
        color = window.getComputedStyle(elem).backgroundColor;
        document.getElementById("square").style.backgroundColor = color;
        // alert(color);
    }

    function handler2() {
        // alert('hello!');
        elem = document.getElementById("second");
        color = window.getComputedStyle(elem).backgroundColor;
        document.getElementById("square").style.backgroundColor = color;
        // alert(color);
    }

    function handler3() {
        // alert('hello!');
        elem = document.getElementById("third");
        color = window.getComputedStyle(elem).backgroundColor;
        document.getElementById("square").style.backgroundColor = color;
        // alert(color);
    }

    function handler4() {
        
        square = document.getElementById("square");
        
        color = window.getComputedStyle(square).backgroundColor;
        color1 = window.getComputedStyle(document.getElementById("first")).backgroundColor;
        color2 = window.getComputedStyle(document.getElementById("second")).backgroundColor;
        color3 = window.getComputedStyle(document.getElementById("third")).backgroundColor;


        switch (color) {
            case color1:
                square.style.backgroundColor = color2;
                break;
            case color2: 
                square.style.backgroundColor = color3;
                break;
            default:
                square.style.backgroundColor = color1;
                break;
        }
    }


    first = document.getElementById("first");
    first.addEventListener("click", handler1);
    // first.removeEventListener("click", handler1("first"));
    document.getElementById("second").addEventListener("click", handler2);
    document.getElementById("third").addEventListener("click", handler3);

    document.getElementById("fourth").addEventListener("click", handler4);

});    
// };



