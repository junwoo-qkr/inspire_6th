package features.blogs.view;

import java.util.List;
import java.util.Scanner;

import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.facade.BlogFrontController;

public class BlogReactView {
    private Scanner scanner;
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
                int mainMenuInput = Integer.parseInt(scanner.nextLine());
                System.out.println();
                System.out.println();
                switch (mainMenuInput) {
                    case 1:
                        list();
                        break;

                    case 2:
                        read();
                        break;

                    case 3:
                        insert();
                        break;

                    case 4:
                        System.out.println("You chose 4");
                        break;

                    case 5:
                        System.out.println("You chose 5");
                        break;

                    case 6:
                        search();
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
        System.out.println(">>>> 전체 게시글 출력 <<<<");
        String endPoint = "list";
        List<BlogResponseDTO> response = front.list(endPoint);
        response.stream().forEach(System.out::println);
    }

    public void read() {
        System.out.print("게시글 번호를 입력하세요: ");
        int postId = Integer.parseInt(scanner.nextLine());
        String endPoint = "read";
        BlogResponseDTO response = front.read(endPoint, postId);
        System.out.println((response != null) ? response : "No post found : postId = " + postId);
    }

    public void search() {
        System.out.print("검색어를 입력하세요: ");
        String searchParam = scanner.nextLine();
        String endPoint = "search";
        List<BlogResponseDTO> response = front.search(endPoint, searchParam);
        if (response.isEmpty()) {
            System.out.println("No Post Contains " + searchParam);
        }
        response.stream().forEach(System.out::println);
    }

    private void insert() {
        System.out.print("제목 입력: ");
        String title = scanner.nextLine();
        System.out.print("내용 입력: ");
        String content = scanner.nextLine();
        System.out.print("이메일 입력: ");
        String email = scanner.nextLine();
        String endPoint = "insert";

        int flag = front.insert(endPoint, title, content, email);
        System.out.println((flag == 1) ? "입력 성공" : "입력 실패");
    }
}
