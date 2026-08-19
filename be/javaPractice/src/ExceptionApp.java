import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import features.exception.ExceptionDemo;
import features.exception.util.CustomException;

public class ExceptionApp {
    public static void main(String[] args) {
        // try - catch - finally (Runtime 시점의 예외)
        System.out.println("ArrayIndexOutOfBoundsException 발생");
        String[] strArr = {"Oasis", "Maroon 5", "YOASOBI"};
        try {
            for (int i = 0; i <= strArr.length; i++) {
                System.out.println(strArr[i]);
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } finally {
            System.out.println("예외 발생 여부와 상관없이 실행");
        }

        //////////////////////////////////////
        
        // try - catch - finally (Compile 시점의 예외)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            System.out.print("메시지를 입력하세요: ");
            line = br.readLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println(line);

        //////////////////////////////////////

        // 사용자 정의 예외 실습
        System.out.println("사용자 정의 예외");
        ExceptionDemo demo = new ExceptionDemo();
        try {
            demo.sample(-1);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
}
