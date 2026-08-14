package features.operator;

import features.blogs.domain.dto.BlogRequestDTO;
import features.blogs.domain.dto.BlogResponseDTO;
import java.lang.Math;

public class OperatorDemo {
    public OperatorDemo() {
    }

    public void operator() {
        System.out.println("산술연산자: +, -, *, /, %, +=, -=, *=, /=, ...");
        System.out.println("증감연산자: ++ --");
        System.out.println("삼항연산자: (조건식) ? true : false");
        System.out.println("논리연산자: &, |, !, &&, ||");
        System.out.println("관계연산자: >, >=, <, <=, ==, !=");
    }

    public BlogResponseDTO register(String title, String content, String email) {
        if (title == "title1") {
            return new BlogResponseDTO(201, "OK");
        } else {
            return new BlogResponseDTO(400, "FAIL");
        }
    }

    public BlogResponseDTO register2(BlogRequestDTO request) {
        if (request.getTitle() != "") {
            return new BlogResponseDTO(201, "OK");
        } else {
            return new BlogResponseDTO(400, "FAIL");
        }
    }

    public String WoodMan(int number) {
        switch (number) {
            case 1:
                return "거짓말 ㄴㄴ";

            case 2:
                return "또 거짓말 ㄴㄴ";

            case 3:
                return "착하네";
        
            default:
                return null;
        }
    }

    // 람다식 사용
    public String WoodMan2(int number) {
        String result = null;
        switch (number) {
            case 1 -> result = "거짓말 ㄴㄴ";
            case 2 -> result = "또 거짓말 ㄴㄴ";
            case 3 -> result = "착하네";
            default -> result = null;
        }
        return result;
    }

    // 람다식 사용 2
    public String WoodMan3(int number) {
        return switch (number) {
            case 1 -> "거짓말 ㄴㄴ";
            case 2 -> "또 거짓말 ㄴㄴ";
            case 3 -> "착하네";
            default -> null;
        };
    }

    public int sumNumber(int start, int end) {
        int result = 0;
        int temp = 0;
        if (start > end) {
            temp = start;
            start = end;
            end = temp;
        }

        for (int i = start; i <= end; i++) {
            result += i;
        }
        
        return result;
    }

    // for 문
    public static int sumRandom() {
        int nan = (int)(Math.random() * 100) + 1;
        int result = 0;

        for (int i = 1; i <= nan; i++) {
            result += i;
        }

        return result;
    }

    // while 문
    public static int sumRandom2() {
        int nan = (int)(Math.random() * 100) + 1;
        int result = 0;
        int i = 1;
        
        while (i <= nan) {
            result += i;
            i++;
        }

        return result;
    }

    // do ~ while 문
    public static int sumRandom3() {
        int nan = (int)(Math.random() * 100) + 1;
        int result = 0;
        int i = 1;
        
        do {
            result += i;
            i++;
        } while (i <= nan);

        return result;
    }

    // printf 사용
    public void printGuGuDan(int dan) {
        for (int i = 1; i <= 9; i++) {
            System.out.printf("%d * %d = %d\n", dan, i, dan*i );
        }
    }

    // 중첩 for 문
    public void printGuGuDan2() {
        for (int i = 2; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.printf("%d * %d = %d\t", i, j, i*j);
            }
        }
    }

    // break 사용
    public void printGuGuDan3(int breakpoint) {
        outer:
        for (int i = 2; i <= 9; i++) {
            inner:
            for (int j = 1; j <= 9; j++) {
                System.out.printf("%d * %d = %d\t", i, j, i*j);
            }
        System.out.println();
        if (i == breakpoint) { break; }
        }
    }

    // 구구단 세로로 출력
    public void printGuGuDan4() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 2; j <= 9; j++) {
                System.out.printf("%d * %d = %d\t", j, i, i*j);
            }
            System.out.println();
        }
    }

    public void popStr(String str) {
            System.out.println(str);
            System.out.printf("str length = %d\n", str.length());
            for (int i = str.length() - 1; i >= 0; i--) {
                System.out.print(str.charAt(i) + " ");
            }
            System.out.println();
        }
}
