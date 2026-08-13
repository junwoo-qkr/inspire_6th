import features.blogs.domain.dto.BlogResponseDTO;
import features.blogs.service.BlogService;

public class ArrApp {
    public static void main(String[] args) {
        int[] arr = new int[10];
        arr[0] = 10;
        arr[1] = 'A';

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();

        for (int data : arr) {
            System.out.printf("%d ", data);
        }
        System.out.println();


        String[] arr2 = new String[10];
        arr2[0] = "A";

        for (String data : arr2) {
            System.out.printf("%s ", data);
        }
        System.out.println();


        boolean[] arr3 = new boolean[10];
        arr3[3] = true;

        for (boolean data : arr3) {
            System.out.printf("%b ", data);
        }
        System.out.println();

        ///////////////////////////////
        
        BlogResponseDTO[] blogsArr = new BlogResponseDTO[10];
        BlogResponseDTO response = BlogResponseDTO.builder()
                                            .status(200)
                                            .message("OK")
                                            .build();
        blogsArr[0] = response;
        blogsArr[1] = response;
        blogsArr[2] = response;

        for (int i = 0; i < blogsArr.length; i++) {
            BlogResponseDTO data = blogsArr[i];
            if (data == null) {
                break;
            }
            System.out.printf("%s ", data.getMessage());
        }
        System.out.println();


        BlogResponseDTO[] resultArr = BlogService.builder().build().blogs();
    }
}
