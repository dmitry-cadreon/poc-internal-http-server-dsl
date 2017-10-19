package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CampaignSynchronizationResult {
    @JsonProperty("campaign")
    private Campaign campaign;
    @JsonProperty("status")
    private CampaignSynchronizationStatus status;
    @JsonProperty("error.message")
    private String message;
}
