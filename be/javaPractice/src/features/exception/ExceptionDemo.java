package features.exception;

import features.exception.util.CustomException;

public class ExceptionDemo {
    public void sample(int x) throws CustomException {
        System.out.println("sample 시작");
        try {
            if(x < 0) {
                throw new CustomException("양의 정수를 입력하세요.");
            }
        } finally {
            System.out.println("sample 끝");
        }
    }
}
