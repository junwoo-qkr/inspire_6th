let username: string = "Park";
let age: number = 27;
let isMarried: boolean = false;

console.log(`typeof username = ${typeof(username)}`);
console.log(`typeof age = ${typeof(age)}`);
console.log(`typeof isMarried = ${typeof(isMarried)}`);

// 배열의 타입 지정
let arr1: string[] = ["A", "B"];
let arr2: number[] = [1, 2, 3, 4, 5];

// 선택적 프로퍼티, 객체의 타입 지정
interface User {
    id: string;
    password: string;
    address?: string;
}

const user: User = {
    id: "p40212@gmail.com",
    password: "1234"
}

console.log(`typeof user = ${typeof(user)}`);
console.log(`user.id = ${user.id}, user.password = ${user.password}`);

let userArr: User[] = [
    {id: "a@gmail.com", password: "1234"},
    {id: "b@gmail.com", password: "234"},
    {id: "c@gmail.com", password: "164"}
];

console.log(`userArr =`, userArr);

// 함수 선언 시 매개변수와 반환값의 타입 설정
function showMessage(name: string) : string {
    return `Welcome ${name}`;
}

console.log(showMessage("Kim"));

// union 타입
let status: string | number;
status = "ok";
status = 32;