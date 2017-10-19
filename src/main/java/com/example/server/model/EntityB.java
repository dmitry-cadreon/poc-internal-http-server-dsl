package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class EntityB {
    @JsonProperty("field.string")
    public String fieldString;
}
