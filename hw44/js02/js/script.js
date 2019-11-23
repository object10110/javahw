//Задание 1
// 	- объявить глобальную переменную color
// 	- объявить функцию setColor()
// 	- в функции setColor() объявить локальную переменную color
// 	- с вероятностью 50% задать значение локальной переменной или глобальной
// 	- для глобальной переменной задается цвет красный, для локальной - черный
// 	- вывести значение локальной и глобальной переменной в консоль через console.log()
// 	- передать значение измененной переменной в функцию drawBox()
// 	- объявить функцию drawBox() с одним параметром color
// 	- вывести через document.writeln() в html блочный элемент (div) закрашенный цветом color
var color;
function setColor() {
    let color;
    if((Math.random()>0.5)){
        this.color = "red";
        drawBox(color)
    }else {
        color = "black";
    }
    console.log(color, this.color);

    drawBox(this.color ? this.color : color);
}

function drawBox(color) {
    document.writeln(`<div style='background-color: ${color}; height: 20px'></div>`);
}
setColor();

// Задание 2
//
// Составить тест: творческая ли вы натура?
// Вопросы задаются через функцию prompt.
// Вывести сообщение с результатами теста через функцию alert.
// Все вопросы храняться в виде массива строк, которые передаются в функцию.

const questions = [
    "Обнаружив, что любимые джинсы порвались, вы\n" +
    "выкидываете их — 0\n" +
    "делаете еще несколько художественных разрезов и продолжаете носить — 1",
    "Подружка попросила вас побыть с ее ребенком-непоседой. Вы\n" +
    "включите ему телевизор или видеоигру  — 0\n" +
    "окунетесь в детство и вместе придумаете веселую увлекательную игру — 1",
    "Часто ли вам снятся сны?\n" +
    "Да, вы обычно помните сновидения, они яркие и интересные — 1\n" +
    "Нет, вы редко запоминаете сны — 0",
    "Какую работу вы предпочтете — ту, где известно, что и как делать, или ту, в которой необходимо что-то придумывать?\n" +
    "Интереснее самостоятельно искать решения проблем, даже если это требует временных затрат — 1 \n" +
    "Вам проще работать, когда существует четкий алгоритм — 0",
    "Подружка выходит замуж, и вам предстоит организовать выкуп невесты, вы\n" +
    "изучите литературу и подберете конкурсы  — 0 \n" +
    "постараетесь придумать конкурсы сами, чтобы они были оригинальными — 1",
    "Изучая меню в ресторане, вы, скорее всего, остановитесь на\n" +
    "знакомом блюде — 0\n" +
    "блюде с самым экстравагантным, названием — 1",
    "Если в процессе создания стенгазеты потребуется нарисовать какое-то животное, вы\n" +
    "постараетесь найти фотографию или другое изображение и срисуете — 0\n" +
    "нарисуете животное сами, даже если не очень хорошо имеете — 1",
    "Для новогоднего маскарада в детском саду необходимо найти малышу костюм. Вы \n" +
    "сошьете его сами — 1 \n" +
    "купите в магазине — 0",
    "Любили ли вы в детстве читать сказки?\n" +
    "Вам интереснее было смотреть мультики — 0\n" +
    "Да, вы любили читать не меньше, чем смотреть телевизор — 1",
    "Ваши фотографии в альбоме —\n" +
    "чаше традиционные — 0 \n" +
    "живые позы в необычных ракурсах — 1",
];



function test() {
    const answers = new Int8Array(questions.length);
    f: for (let i = 0; i < questions.length; i++){
        let answer;
        do {
            answer = prompt(questions[i], 0);
            if(answer === null){
                break f;
            }
            answer = +answer;
        } while (!isFinite(answer) || answer < 0 || answer > 1);
        answers[i] = answer;
    }
    const units = answers.filter(a => a === 1).length;
    if (units < 4) {
        alert("Вы придерживаетесь традиционных взглядов на решение проблемных ситуаций.");
    } else if (units < 7) {
        alert("Вы достаточно креативны по натуре, но не всегда считаете нужным пользоваться этими способностями.");
    } else {
        alert("Вы очень творческий человек. Вы умеете видеть необычные решения, которые незаметны для окружающих.");
    }
}
test();



// 3. Напишите JavaScript программу для поиска наиболее часто встречающегося элемента в массиве
// Пример массива : var arr1=[3, 'a', 'a', 'a', 2, 3, 'a', 3, 'a', 2, 4, 9, 3];
// Результат : a ( 5 times )
let arr = [3, 'a', 'a', 'a', 2, 3, 'a', 3, 'a', 2, 4, 9, 3];
function numberOfRepetitions(arr) {
    const map = new Map()
    let maxKey, maxVal = 0, next;
    for (let i = 0; i < arr.length; i++) {
        if (map.has(arr[i])) {
            next = map.get(arr[i]) + 1;
        } else {
            next = 1;
        }
        map.set(arr[i], next);
        if (next > maxVal) {
            maxKey = arr[i];
            maxVal = next;
        }
    }
    return {maxKey, maxVal, map};
}
let result = numberOfRepetitions(arr);
console.log(`Repetitions: ${result.maxKey} - ${result.maxVal} times`);

// 4. Напишите JavaScript функию для клонирования массива.
// Пример:
// console.log(array_Clone([1, 2, 4, 0]));
// console.log(array_Clone([1, 2, [4, 0]]));
function createCloneArr(src) {
    return Object.assign({}, src);
}
let arrForClone = [1,2,[4,0]];
console.log(`Clone arr:`, createCloneArr(arrForClone));

//5. Напишите JavaScript функция для получения первого элемента массива.
// При получении аргумента 'n' - возвращает 'n' элементов массива
function firstItemsFromArray(array, n = 1) {
    if(n > 0) {
        return array.slice(0, n);
    }
    return [];
}
console.log("firstItemsFromArray:", firstItemsFromArray([7, 9, 0, -2],6));

//6. Напишите JavaScript функцию, которая принимает числои вставляет тире (-) послекаждого четного числа.
// Например: входное число 25468 на выходе должно быть 254-6-8
function everyEvenSetSymbol(number, symbol) {
    if(isFinite(+number)) {
        let numberStr = number.toString();
        let result = "";
        for (let i = 0; i < numberStr.length; i++){
            result += numberStr[i];
            if(+numberStr[i] % 2 === 0) {
                result += symbol;
            }
        }
        return result;
    }
}
console.log("everyEvenSetSymbol:", everyEvenSetSymbol(25468, "-" ));

//7.Написать функцию для нахождения суммы квадратов чисел массива.
function sqrtSum(array) {
    return array.reduce((a, b) => Math.sqrt(a) + Math.sqrt(b));
}
console.log("sqrt:", sqrtSum([1,2,3,4,5]));

//8. Написать функцию для перемешивания элементов в массиве
function rand(max) {
    let  rand = Math.random() * (max);
    return Math.floor(rand);
}

function arrForRandomSort(array) {
    for(let i = 0; i < array.length; i++) {
        let r = rand(array.length);
        let temp = array[i];
        array[i] = array[r];
        array[r] = temp;
    }
    return array;
}
console.log("arrForRandomSort:", arrForRandomSort([1,2,3,4,5,6,7,8,9,0]));

//9.  Написать функцию, которая возвращает уникальные в массиве.
// Например
// console.log(unique([7, 9, 0, -2]));
// console.log(unique([7, 7, 0, -2]));
// console.log(unique([7, 9, 9, -2]));
// console.log(unique([1, 1, 1, 1]));
//
// Ожидаемые результаты:
// [7, 9, 0, -2]
// [9, 0, -2]
// [7, -2]
// []
function unique(array) {
    const map = numberOfRepetitions(array).map;
    const result = [];
    map.forEach((val, key) => {
        if(val === 1){
            result.push(key);
        }
    });
    return result;
}
console.log("unique:", unique([7, 9, 0, -2]),
    unique([7, 7, 0, -2]),
    unique([7, 9, 9, -2]),
    unique([1, 1, 1, 1]));