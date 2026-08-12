import features.blogs.domain.dto.BlogRequestDTO;
import features.blogs.domain.dto.BlogResponseDTO;
import features.operator.OperatorDemo;

public class OperatorApp {
    public static void main(String[] args) {
        OperatorDemo instance = new OperatorDemo();
        instance.operator();

        BlogResponseDTO response = instance.register("title1", "content1", "AAA");
        System.out.println(response.getStatus());
        System.out.println(response.getMessage());

        BlogRequestDTO request = new BlogRequestDTO(1, "", "", "");
        BlogResponseDTO response2 = instance.register2(request);
        System.out.println(response2.getStatus());
        System.out.println(response2.getMessage());

        String result = instance.WoodMan(3);
        System.out.println(result);

        int result2 = instance.sumNumber(1, 100);
        System.out.println(result2);

        System.out.println(OperatorDemo.sumRandom());

        instance.printGuGuDan(3);

        instance.printGuGuDan2();

        instance.printGuGuDan3(5);

        instance.printGuGuDan4();

        instance.popStr("ABCDEFG");
    }
}
