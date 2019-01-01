//Script


var playing = false;
var sequence = [];


function playGame() {
    //plays the game
    sequence = makeSequence();
    playing = true;
}




function makeSequence() {
    //Creates the sequence of directions to be created
    var array = [];
    document.getElementById("sequence").innerHTML = "";
    var how_many = prompt("How many terms would you like?", "10");
    for (var i = 1; i <= how_many; i++) {
        var number = Math.random();
        if (number < 0.25) {
            array.push(" Up");
        } else if (number < 0.50) {
            array.push(" Down");
        } else if (number < 0.75) {
            array.push(" Left");
        } else {
            array.push(" Right");
        }
    }
    document.getElementById("sequence").innerHTML = array;
    return array;
}

function checkKey(dir) {
    //Checks the key pressed and changes the innerHTML of #direct
    document.getElementById("direct").innerHTML = dir;
    checkSequence(" "+dir, sequence);
}

function checkSequence(dir) {
    //Checks if the direction is the next one in the sequence
    if (playing) {
        var lost = false;
        if (dir === sequence[0] & playing) {
            sequence.splice(0,1);
            document.getElementById("sequence").innerHTML = sequence;
        }
        else {
            window.alert("Try Again.");
            lost = true;
            playing = false;
            document.getElementById("sequence").innerHTML = "";
            sequence = [];
        }
        
        if (lost === false & sequence.length === 0) {
            window.alert("Congrats fam");
            playing = false;
        }
    }
}




document.onkeydown = checkKey;

function checkKey(e) {

    e = e || window.event;

    if (e.keyCode == '38') {
        // up arrow
        document.getElementById("direct").innerHTML = "Up";
        checkSequence(" Up", sequence);
    }
    else if (e.keyCode == '40') {
        // down arrow
        document.getElementById("direct").innerHTML = "Down";
        checkSequence(" Down", sequence);
    }
    else if (e.keyCode == '37') {
        // left arrow
        document.getElementById("direct").innerHTML = "Left";
        checkSequence(" Left", sequence);
    }
    else if (e.keyCode == '39') {
        // right arrow
        document.getElementById("direct").innerHTML = "Right";
        checkSequence(" Right", sequence);
    }

}