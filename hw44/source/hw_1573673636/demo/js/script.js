'use strict';
let a = 10;

f(1);

function f(a, b = 2) {
    //b = b || 2;
    console.log(`a = ${a}, b = ${b}`);
    for (let i in arguments) {
        console.log(arguments[i]);
    }
    console.log(arguments);
}

console.log(summ(1,2,3));

function summ(...nums) {
    let s = 0;
    for(let num of nums) {
        s += num;
    }
    return s;
}


var var_summ = summ;

console.log(var_summ(1,2,3));

var print = function (text) {
    document.writeln(`<p>${text}</p>`)
};

print("Hello HTML");

function bubble(arr, cmp) {
    print(cmp);
    for(let i=0; i<arr.length; i++) {
        for(let j=0; j<arr.length-1 - i; j++) {
            if(cmp(arr[j], arr[j+1])) {
                let tmp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = tmp;
            }
        }
    }
    return arr;
}

var arr = Array();
for(let i=0; i<10; i++) {
    arr.push(Math.floor(Math.random()*100));
}
print(arr);
print(bubble(arr, function(a, b) {return a > b;}));
print(bubble(arr, (a, b) => a < b));

// замыкание
function counter(start = 0) {
    var value = start;

    function next () {
        return ++value;
    }

    return next;
}

var c = counter(10);
print(c());
print(c());
print(c());
print(c());

arr = Array(10);
console.dir(arr);
arr = Array(1,2,3);
console.dir(arr);
arr = [1,2,3];
console.dir(arr);

console.log(arr.length);

arr.length = 10;
console.dir(arr);

arr.length = 1;
console.dir(arr);

arr[0] = 10;
arr[10] = 0;
console.dir(arr);

// LIFO Stack
arr.length = 0;
arr.push(1,2,3);
console.table(arr);

console.log(arr.pop());
console.table(arr);


// FIFO Queue
arr.unshift(-3, -2, -1, 0);
console.table(arr);

console.log(arr.shift());
console.table(arr);

arr.sort((a, b) => b - a);
console.table(arr);


var half = arr.slice(0, arr.length/2);
console.table(half);

half = arr.splice(arr.length/2, 2, 100, 200);
console.log("arr");
console.table(arr);
console.log("half");
console.table(half);

var str = arr.join(":");
console.log(str);



