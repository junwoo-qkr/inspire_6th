package features.blogs.view;

import java.util.List;
import java.util.Scanner;

import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.facade.BlogFrontController;

public class BlogReactView {
    private Scanner scanner;
    private int mainMenuInput;
    private BlogFrontController front;

    public BlogReactView() {
        scanner = new Scanner(System.in);
        front = new BlogFrontController();
    }

    public void landingPage() {
        while(true) {
            System.out.println();
            System.out.println(">>>> Blog <<<<");
            System.out.println("1. 전체 검색");
            System.out.println("2: 게시글 상세보기");
            System.out.println("3: 게시글 입력");
            System.out.println("4: 게시글 수정");
            System.out.println("5: 게시글 삭제");
            System.out.println("6: 게시글 검색");
            System.out.println("...");
            System.out.println("99: 프로그램 종료");
            System.out.println();

            System.out.print("메뉴 번호 입력: ");

            try {
                mainMenuInput = Integer.parseInt(scanner.nextLine());
                System.out.println();
                System.out.println();
                switch (mainMenuInput) {
                    case 1:
                        list();
                        break;

                    case 2:
                        System.out.println("You chose 2");
                        break;

                    case 3:
                        System.out.println("You chose 3");
                        break;

                    case 4:
                        System.out.println("You chose 4");
                        break;

                    case 5:
                        System.out.println("You chose 5");
                        break;

                    case 6:
                        System.out.println("You chose 6");
                        break;

                    case 99:
                        exit();
                        break;

                    default:
                        System.out.println("올바른 숫자를 입력해주세요.");
                        break;
                }
            } catch(NumberFormatException e) {
                System.out.println();
                System.out.println();
                System.out.println("올바른 숫자를 입력해주세요.");
            }
            
        }
    }

    public void exit() {
        System.out.println("Shutting down");
        System.exit(1);
    }

    public void list() {
        System.out.println(">>>> 데이터 출력 <<<<");
        String endPoint = "list.posts";
        List<BlogResponseDTO> response = front.list(endPoint);
        // TODO : Stream API로 출력

        response.stream().forEach(System.out::println);
    }
}
