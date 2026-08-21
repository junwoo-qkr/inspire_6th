import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import features.blogs.domain.dto.BlogResponseDTO;

public class IOStreamApp {
    public static void main(String[] args) {
        // System.in.read() 실습
        try {
            int input = System.in.read();
            System.out.println((char)input);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 한글 문자열을 받을 수 있게 감싸기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = br.readLine();
            System.out.println(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) { br.close(); }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        // AutoClosable 구현
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(br.readLine());
        } catch(Exception e) {
            e.printStackTrace();
        }

        // 파일 내용 읽어서 출력하기
        try (BufferedReader br = new BufferedReader(new FileReader(new File("./text.txt"), StandardCharsets.UTF_8))) {
            String input = null;
            while ((input = br.readLine()) != null) {
                System.out.println(input);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        // 파일 내용 수정하기
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./text.txt"), StandardCharsets.UTF_8))) {
            bw.write("hey\n");
        } catch(Exception e) {
            e.printStackTrace();
        }

        // 직렬화
        String path = "./object.txt";
        List<BlogResponseDTO> posts = new ArrayList<>(List.of(
            BlogResponseDTO.builder()
            .postId(1).title("title1").content("Hi").email("AAA@gmail.com").viewCnt(3).build(),
            BlogResponseDTO.builder()
            .postId(2).title("title2").content("Monster Energy Drink").email("BBB@gmail.com").viewCnt(4).build(),
            BlogResponseDTO.builder()
            .postId(3).title("title3").content("Bench Press").email("CCC@gmail.com").viewCnt(1).build(),
            BlogResponseDTO.builder()
            .postId(4).title("title4").content("Fan").email("DDD@gmail.com").viewCnt(12).build()
        ));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)))) {
            oos.writeObject(posts);
        } catch(Exception e) {
            e.printStackTrace();
        }

        // 직렬화했던 객체 읽어들이기
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)))) {
            List<BlogResponseDTO> list = (List<BlogResponseDTO>)ois.readObject();
            list.forEach(System.out::println);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
