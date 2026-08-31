console.log("Hello World!");

console.log("Value: " + isPalindrome("abbA"))
console.log("Value: " + isPalindrome("abbA"))
console.log("Value: " + isPalindrome("abbA"))

// function squareDigits(num){
//     result = "";
//     num = num.toString();
//     for (let index = 0; index < num.length; index++) {
//         const element = parseInt(num[index]);
//         result += element*element;
//     }
//     return parseInt(result);
// }

var number = function(busStops){
    counter = 0;
    busStops.forEach(element => {
        counter += element[0] - element[1];
    });
    return counter;
}

function isPalindrome(x) {
    const str = x.toLowerCase();
    return str === str.split('').reverse().join('');
}

function addLength(str) {
    var words = str.split(" ");
    for (i = 0; i < words.length; i++) {
      words[i] = words[i] + " " + words[i].length();
    }
    return words;

    // str.split(" ").map(word => word+" "+word.lenght)
}

function cakes(recipe, available) {
    let values = []
    for (const key in recipe) {
        if (!Object.hasOwn(available, key)) return 0;
        values.push(Math.floor(available[key]/recipe[key]) );
    }
    return Math.min(...values);
}

function disemvowel(str) {
    return str.replace("[aeiouAEIOU]", "");
}


