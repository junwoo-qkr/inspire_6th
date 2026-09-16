package com.example.jpapractice.features.openai.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder 
@Getter 
@ToString 
@NoArgsConstructor 
@AllArgsConstructor 
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecommendResponseDTO {
    
    private String weather;
    private String location;
    private List<RestaurantDTO> restaurants;

    // inner class
    @Builder 
    @Getter 
    @ToString 
    @NoArgsConstructor 
    @AllArgsConstructor 
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RestaurantDTO {

        private String name;
        private String category;
        private String reason;
    }
}
