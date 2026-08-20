import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import features.blogs.domain.dto.BlogResponseDTO;

public class BlogStreamApp {
    public static void main(String[] args) {
        ArrayList<BlogResponseDTO> posts = new ArrayList<>(List.of(
            BlogResponseDTO.builder()
            .postId(1).title("title1").content("Hi").email("AAA@gmail.com").viewCnt(33).build(),
            BlogResponseDTO.builder()
            .postId(2).title("title2").content("Monster Energy Drink").email("BBB@gmail.com").viewCnt(14).build(),
            BlogResponseDTO.builder()
            .postId(3).title("title3").content("Bench Press").email("CCC@gmail.com").viewCnt(71).build(),
            BlogResponseDTO.builder()
            .postId(4).title("title4").content("Fan").email("DDD@gmail.com").viewCnt(12).build(),
            BlogResponseDTO.builder()
            .postId(5).title("title5").content("Iced Coffee").email("BBB@gmail.com").viewCnt(74).build()
        ));

        // stream.filter(): 조건 검색
        System.out.println("stream.filter(): 조건 검색");
        posts.stream().filter(p -> p.getViewCnt() >= 30).forEach(System.out::println);
        System.out.println();

        // stream.map(): 요소의 값 또는 타입 변환
        System.out.println("stream.map(): 요소의 값 또는 타입 변환");
        posts.stream().filter(p -> p.getViewCnt() >= 30).map(BlogResponseDTO::getEmail).forEach(System.out::println);
        System.out.println();

        // stream.collect(): Collection으로 반환
        System.out.println("stream.collect(): Collection으로 반환");
        List<BlogResponseDTO> result = posts.stream().filter(p -> p.getEmail().equals("BBB@gmail.com")).collect(Collectors.toList());
        result.forEach(System.out::println);
        System.out.println();

        // Collectors.groupingBy: 그룹 지정
        System.out.println("Collectors.groupingBy: 그룹 지정");
        Map<String, List<BlogResponseDTO>> result2 = posts.stream().collect(Collectors.groupingBy(BlogResponseDTO::getEmail));
        result2.get("BBB@gmail.com").stream().forEach(System.out::println);
        System.out.println();

        // stream.average(): 평균 구하기
        System.out.println("stream.average(): 평균 구하기");
        double average = posts.stream().mapToInt(BlogResponseDTO::getViewCnt).average().orElse(0);
        System.out.println(average);
        System.out.println();

        // stream.distinct(): 중복 제거
        System.out.println("stream.distinct(): 중복 제거");
        posts.stream().map(BlogResponseDTO::getEmail).distinct().forEach(System.out::println);
        System.out.println();

        // stream.sorted(): 요소 정렬
        System.out.println("stream.sorted(): 요소 정렬");
        posts.stream().sorted(Comparator.comparing(BlogResponseDTO::getViewCnt).reversed()).forEach(System.out::println);
        System.out.println();

        // stream.anyMatch(): match하는 요소가 있는지 판단
        System.out.println("stream.anyMatch(): match하는 요소가 있는지 판단");
        boolean isAAA = posts.stream().anyMatch(p -> p.getEmail().equals("AAA@gmail.com"));
        System.out.println(isAAA);
        System.out.println();

        // stream.allMatch(): 전체에 대해 match하는지 판단
        System.out.println("stream.allMatch(): 전체에 대해 match하는지 판단");
        boolean isViewCnt = posts.stream().allMatch(p -> p.getViewCnt() >= 13);
        System.out.println(isViewCnt);
        System.out.println();

        // stream.noneMatch(): match하는 요소가 없는지 판단
        System.out.println("stream.noneMatch(): match하는 요소가 없는지 판단");
        boolean isUnder10 = posts.stream().noneMatch(p -> p.getViewCnt() < 10);
        System.out.println(isUnder10);
        System.out.println();

        ///////////////////////////////////////////
        
        // Optional.ifPresent() 실습
        System.out.println("Optional.ifPresent() 실습");
        Optional<String> optional = Optional.of("Youtube");
        // if(optional.isPresent()) {
        //     System.out.println(optional.get());
        // }
        optional.ifPresent(value -> System.out.println(value));
        System.out.println();

        // Optional.ifPresentOrElse() 실습
        System.out.println("Optional.ifPresentOrElse() 실습");
        optional.ifPresentOrElse(value -> System.out.println(value), () -> System.out.println("Nothing to see here."));
        System.out.println();

        // Optional.orElseThrow() 실습
        System.out.println("Optional.orElseThrow() 실습");
        optional = Optional.empty();
        String errMessage = optional.orElseThrow(() -> new RuntimeException("It's Empty in here!"));
        System.out.println(errMessage);
        System.out.println();
    }
}
