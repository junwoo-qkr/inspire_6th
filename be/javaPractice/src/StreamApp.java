import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import features.lambda.InspireFunction;

public class StreamApp {
    public static void main(String[] args) {
        // 함수형 인터페이스 실습
        InspireFunction func01 = (x, y) -> x > y ? x : y;
        System.out.println("func01 = " + func01.max(100, 200));
        System.out.println();

        InspireFunction func02 = (x, y) -> x + y;
        System.out.println("func02 = " + func02.max(100, 200));
        System.out.println();

        /////////////////////////////////
        
        // Supplier: 매개변수X , 반환타입O / 실행 메서드: get()
        Supplier<String> supplier = () -> "Hello!";
        System.out.println(supplier.get());
        System.out.println();

        // Consumer: 매개변수O , 반환타입X / 실행 메서드: accept()
        Consumer<String> consumer = (str) -> System.out.println(str.split(" ")[0]);
        consumer.accept("consumer test");
        consumer.andThen(System.out::println).accept("Hi There");
        System.out.println();

        // Function: 매개변수O , 반환타입O / 실행 메서드: apply()
        Function<String, Integer> function = (str) -> {
            return str.length();
        };
        int len = function.apply("10 fingers tearing out my hair");
        System.out.println("len = " + len);
        System.out.println();

        // Predicate: 매개변수O , Boolean 반환 / 실행 메서드: test()
        Predicate<String> predicate = (str) -> str.equals("Coca Cola");
        Boolean isFlag = predicate.test("Pepsi");
        System.out.println("Pepsi == Coca Cola? " + isFlag);
        Boolean isFlag2 = predicate.test("Coca Cola");
        System.out.println("Coca Cola == Coca Cola? " + isFlag2);
        System.out.println();

        /////////////////////////////////
        
        // Stream 실습
        List<String> brands = Arrays.asList("LG", "SamSung", "Panasonic", "Sony");
        
        System.out.println("forEach 사용");
        brands.forEach((brand) -> {
            System.out.println(brand);
        });
        System.out.println();

        System.out.println("Stream 사용1");
        Stream<String> stream = brands.stream();
        stream.forEach((brand) -> {
            System.out.println(brand.toUpperCase());
        });
        System.out.println();

        System.out.println("Stream 사용2: 메서드 참조 방식");
        stream = brands.stream();
        stream.map(String::toUpperCase).forEach(System.out::println);
    }
}
