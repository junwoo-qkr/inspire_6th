package com.example.jpapractice.features.openai.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.jpapractice.features.openai.domain.QuizResponseDTO;
import com.example.jpapractice.features.openai.domain.RecommendResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
@RequiredArgsConstructor 
public class OpenAIService {
    
    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;
    private final OkHttpClient okHttpClient;

    @Value("${spring.ai.openai.api-key}")
    private String key;

    @Value("${spring.ai.openai.chat.options.model}")
    private String model;

    // 모델이 다르면 endpoint도 다름
    // endpoint1: https://api.openai.com/v1
    // endpoint2: https://api.openai.com/v1/chat/completions
    private String endPoint = "https://api.openai.com/v1/chat/completions";

    // chatClient를 사용하지 않을 때
    public RecommendResponseDTO recommend(String weather, String location) {
        System.out.println("OpenAI Service recommend");
        System.out.println("params : " + weather + "\t" + location);

        String prompt = """
            너는 지역 맛집을 훤히 꿰고 있는 맛집 블로거야.
            현재 날씨에 맞는 음식을 판매하는 지역 맛집을 추천해줘.
            추천된 음식점의 음식점 이름, 음식점 분류, 추천 이유를 출력예시의 형식을 지켜 출력해줘.
            <조건>
                날씨: "%s"
                지역: "%s"
            </조건>
            <출력예시>
                {
                    "weather" : "날씨",
                    "location" : "위치",
                    "restaurants" : [
                        {"name" : "음식점 이름", "category" : "음식점 분류", "reason" : "추천 이유"}
                    ]
                }
            </출력예시>
        """.formatted(weather, location);

        Map<String, Object> messages = new HashMap<>();
        messages.put("model", model);

        Map<String, Object> user = new HashMap<>();
        user.put("role", "user");
        user.put("content", prompt);

        Map<String, Object> system = new HashMap<>();
        system.put("role", "system");
        system.put("content", "전처리된 JSON 형태로만 반환할 것.");

        messages.put("messages", List.of(user, system));

        // Object(Map) -> JSON
        String requestJSON = null;
        try {
            requestJSON = objectMapper.writeValueAsString(messages);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("requestJSON :\n" + requestJSON);

        Request request = new Request.Builder()
            .url(endPoint)
            .header("Authorization", "Bearer " + key)
            .header("Content-Type", "application/json")
            .post(RequestBody.create(requestJSON, MediaType.parse("application/json")))
            .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            System.out.println("response :\n" + response);

            // 응답의 body만 가져와서 문자열로 저장
            String responseJSON = response.body().string();
            System.out.println("responseJSON :\n" + responseJSON);

            // 문자열의 responseJSON을 트리형태로 읽기
            JsonNode node = objectMapper.readTree(responseJSON);
            String exr = node.at("/choices/0/message/content").asText();
            System.out.println("exr :\n" + exr);

            // objectMapper로 exr을 RecommendResponseDTO로 매핑
            RecommendResponseDTO result = objectMapper.readValue(exr, RecommendResponseDTO.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // chatClient를 사용할 때
    public RecommendResponseDTO recommend2(String weather, String location) {
        System.out.println("OpenAI Service recommend2");
        System.out.println("params : " + weather + "\t" + location); 

        RecommendResponseDTO result = chatClient
            .prompt()
            .system("전처리된 JSON 형태로만 반환할 것.")
            .user(
                """
                    너는 지역 맛집을 훤히 꿰고 있는 맛집 블로거야.
                    현재 날씨에 맞는 음식을 판매하는 지역 맛집을 추천해줘.
                    추천된 음식점의 음식점 이름, 음식점 분류, 추천 이유를 출력예시의 형식을 지켜 출력해줘.
                    <조건>
                        날씨: "%s"
                        지역: "%s"
                    </조건>
                    <출력예시>
                        {
                            "weather" : "날씨",
                            "location" : "위치",
                            "restaurants" : [
                                {"name" : "음식점 이름", "category" : "음식점 분류", "reason" : "추천 이유"}
                            ]
                        }
                    </출력예시>
                """.formatted(weather, location))
            .call()
            .entity(RecommendResponseDTO.class);

        return result;
    }

    public QuizResponseDTO quiz(String subject) {
        System.out.println("OpenAI Service quiz");
        System.out.println("params : " + subject); 

        QuizResponseDTO result = chatClient
            .prompt()
            .system(
                """
                    너는 문제 출제 전문가야.
                    반드시 JSON 형태로만 출력해줘.
                """)
            .user(
                """
                    너는 문제 출제를 전문으로 하는 위원이고, %s 전공의 박사학위 수준의 문제를 출제할거야.
                    <조건>
                        - 총 10개의 문제를 출력해줘.
                        - 백틱(`)은 쓰지마.
                        - 반드시 JSON 형태로 출력해줘.
                    </조건>
                    <출력예시>
                        {
                            "quizes" : [
                                {
                                    "question" : <문제>,
                                    "options" : [보기1, 보기2, 보기3, 보기4],
                                    "answer" : <정답>,
                                    "desc" : <해설>
                                }
                            ]
                        }
                    </출력예시>
                """.formatted(subject))
            .call()
            .entity(QuizResponseDTO.class);

        return result;
    }
}
