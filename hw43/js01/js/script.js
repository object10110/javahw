//1. Подсчитать сумму всех чисел в заданном пользователем диапазоне
function sumFromAtoB(a,b) {
    a = parseInt(a);
    b = parseInt(b);
    let sum = 0;
    for (let i = a;i<=b; i++){
        sum+=i;
    }
    return sum;
}
console.log( sumFromAtoB(2,5));

//2. Запросить 2 числа и найти только наибольший общий делитель.
//greatest common divisor
function gcd(a,b) {
    a = Math.abs(a);
    b = Math.abs(b);
    if (b > a) {let temp = a; a = b; b = temp;}
    while (true) {
        if (b === 0) return a;
        a %= b;
        if (a === 0) return b;
        b %= a;
    }
}
console.log(gcd(12, 9));

//3. Запросить у пользователя число и вывести все делители этого числа.
function allDivisors(a) {
    a = Math.abs(a);
    let listDivisors = [];
    let divisor = 1;
    while (divisor <= a) {
        if(a%divisor === 0) listDivisors.push(divisor);
        divisor+=1;
    }
    return listDivisors;
}
console.log(allDivisors(8));

//4. Определить количество цифр в введенном числе.
function digitAmount(number) {
    number = number.toString();
    let count = 0;
    for (let i = 0; i < number.length; i++) {
        let digit = parseInt(number[i])
        if(!isNaN(digit)) count+=1;
    }
    return count;
}
console.log(digitAmount(-214.12));

//5. Запросить у пользователя 10 чисел и подсчитать, сколько он ввел положительных,
// отрицательных и нулей. При этом также посчитать, сколько четных и нечетных.
// Вывести статистику на экран. Учтите, что достаточно одной переменной (не 10)
// для ввода чисел пользователем.
function arrayInfo(array) {
    let countPositive = 0;
    let countNegative = 0;
    let countZero = 0;
    let countEven = 0;//четные
    let countOdd = 0;//нечетные
    array.forEach(function (elem) {
        let number = parseInt(elem)
        if(!isNaN(number)){
            if(number%2 ===0)countEven+=1;
            else countOdd+=1;
            if(number===0) countZero+=1;
            else if(number>=0) countPositive+=1;
            else countNegative+=1;
        }
    });
    console.log(`positive:\t${countPositive}\n`
                +`negative:\t${countNegative}\n`
                +`zero:\t${countZero}\n`
                +`even:\t${countEven}\n`
                +`odd:\t${countOdd}`);
}
arrayInfo([0, -24, 3, 2,4,-3,0,9,31,-2]);

//6. Зациклить калькулятор. Запросить у пользователя 2 числа и знак, решить пример,
// вывести результат и спросить, хочет ли он решить еще один пример. И так до тех пор,
// пока пользователь не откажется.
function calc(a,b,symbol) {
    a = parseInt(a);
    b = parseInt(b);
    if(!(isNaN(a) || isNaN(b))){
        switch (symbol) {
            case "+": {
                return a + b;
            }
            case  "-":{
                return a-b;
            }
            case "*": {
                return a*b;
            }
            case "/": {
                return a/b;
            }
        }
    }
}
console.log(calc(2,5, '+'));

//7. Запросить у пользователя число и на сколько цифр его сдвинуть. Сдвинуть цифры числа 
// и вывести результат (если число 123456 сдвинуть на 2 цифры, то получится 345612).
function numberShift(number, offset) {
    number = parseInt(number)
    if(!isNaN(number)) {
        number = number.toString();
        for (let i = 0; i < number.length && i < offset; i++) {
            number = number.substr(1) + number[0];
        }
    }
    return number;
}
console.log(numberShift(12345, 2));

//8. Зациклить вывод дней недели таким образом: «День недели. Хотите увидеть следующий день?»
// и так до тех пор, пока пользователь нажимает OK
function showNextDayOfTheWeek() {
    let days = ["пн","вт","ср","чт","пт","сб","вс"];
    let idx = 0;
    while(confirm("Хотите увидеть следующий день?")){
        alert(days[idx]);
        idx+=1;
        if(idx===days.length) idx = 0;
    }
}
//showNextDayOfTheWeek();

//9. Вывести таблицу умножения для всех чисел от 2 до 9. Каждое число необходимо умножить
// на числа от 1 до 10.
function tables(from, to) {
    let str = "";
    for (let i = from; i <= to; i++) {
        str+=table(i)+"\n";
    }
    return str;
}
function table(number) {
    let str ="";
    for (let i = 1; i <= 10; i++) {
        str+=`${number}*${i}=${number*i}\n`
    }
    return str;
}
console.log(tables(2, 9));

//10. Игра «Угадай число». Предложить пользователю загадать число от 0 до 100 и отгадать его
// следующим способом: каждую итерацию цикла делите диапазон чисел пополам, записываете результат
// в N и спрашиваете у пользователя «Ваше число > N, < N или == N?». В зависимости от того что
// указал пользователь, уменьшаете диапазон. Начальный диапазон от 0 до 100, поделили пополам и
// получили 50. Если пользователь указал, что его число > 50, то изменили диапазон на от 51 до 100.
// И так до тех пор, пока пользователь не выберет == N
function game() {
    let min = 0,max = 100;
    let count = 0;
    alert(`Загадайте число от ${min} до ${max}`);
    while (true){
        let n = Math.floor((max+min)/2);
        if(!confirm(`Ваше число = ${n}?`)){
            count++;
            if(confirm(`Ваше число > ${n}?`)){
                min = n+1;
            }
            else {
                max = n;
            }
        }
        else{
            break;
        }
    }
}
game();