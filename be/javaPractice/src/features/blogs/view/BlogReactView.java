package features.blogs.view;

import java.util.List;
import java.util.Scanner;

import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.facade.BlogFrontController;
import features.blogs.util.ResponseEntity;

public class BlogReactView {
    private Scanner scanner;
    private BlogFrontController front;

    public BlogReactView() {
        scanner = new Scanner(System.in);
        front = new BlogFrontController();
    }

    public void landingPage() {
        boolean isLoad = front.file("file", "load");
        System.out.println(isLoad ? "로드 성공" : "로드 실패");
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
                        update();
                        break;

                    case 5:
                        delete();
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
        System.out.print("종료 전 저장하시겠습니까? (y/n): ");
        String yn = scanner.nextLine();
        if (yn.equalsIgnoreCase("y")) {
            String endPoint = "file";
            System.out.println(front.file(endPoint, "save") ? "데이터 저장 완료" : "데이터 저장 실패");
        }
        System.out.println("Shutting down");
        System.exit(1);
    }

    public void list() {
        System.out.println(">>>> 전체 게시글 출력 <<<<");
        String endPoint = "list";
        ResponseEntity<List<BlogResponseDTO>> response = front.list(endPoint);

        if (response.getCode() == 200) {
            response.getData().stream().forEach(System.out::println);
        }
    }

    public void read() {
        System.out.print("게시글 번호를 입력하세요: ");
        int postId = Integer.parseInt(scanner.nextLine());
        String endPoint = "read";
        ResponseEntity<BlogResponseDTO> response = front.read(endPoint, postId);
        System.out.println((response.getCode() == 200) ? response.getData() : "No post found : postId = " + postId);

    }

    public void search() {
        System.out.print("검색어를 입력하세요: ");
        String searchParam = scanner.nextLine();
        String endPoint = "search";
        ResponseEntity<List<BlogResponseDTO>> response = front.search(endPoint, searchParam);
        if (response.getCode() == 200 && response.getData().isEmpty()) {
            System.out.println("No Post Contains " + searchParam);
        }
        response.getData().stream().forEach(System.out::println);
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

    public void update() {
        System.out.print("게시글 번호를 입력하세요: ");
        int postId = Integer.parseInt(scanner.nextLine());
        System.out.print("제목 입력: ");
        String title = scanner.nextLine();
        System.out.print("내용 입력: ");
        String content = scanner.nextLine();
        String endPoint = "update";

        int flag = front.update(endPoint, postId, title, content);
        System.out.println((flag == 1) ? "수정 성공" : "수정 실패");
    }

    public void delete() {
        System.out.print("게시글 번호를 입력하세요: ");
        int postId = Integer.parseInt(scanner.nextLine());
        String endPoint = "delete";
        int response = front.delete(endPoint, postId);
        System.out.print((response == 1) ? "Post deleted" : "No post found");
        System.out.println(" : postId = " + postId);
    }
}
