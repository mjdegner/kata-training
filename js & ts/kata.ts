export class Kata {
    static squareDigits (num: number): number{
        var result = "";
        let numb: string = num.toString();
        for (let index = 0; index < numb.length; index++) {
            const element = parseInt(numb[index]);
            result += element*element;
        }
        return parseInt(result);
    }

    
}

export function number(busStops: [number, number][]): number {
  let count = 0;
  busStops.forEach(element => {
    count += element[0] - element[1];
  });
  return count;
}



export function isPalindrome(x: string): boolean {
  const text = x.toLowerCase();
  return text === text.split('').reverse().join('');
}

export function addLength(str: string): string[] {
  var words = str.split(" ");
  for (let i = 0; i < words.length; i++) {
    words[i] = words[i] + " " + words[i].length;
  }
  return words;
}