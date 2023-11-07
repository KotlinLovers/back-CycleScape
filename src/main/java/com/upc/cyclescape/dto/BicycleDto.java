package com.upc.cyclescape.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.cyclescape.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BicycleDto {
    private Long id;
    private String bicycleName;
    private String bicycleDescription;
    private double bicyclePrice;
    private String bicycleSize;
    private String bicycleModel;
    private String imageData;
    private Double latitude;
    private Double longitude;
    private User user;
}
