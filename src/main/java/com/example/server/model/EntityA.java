package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class EntityA {
    @JsonProperty("field.int")
    public int fieldInt;
}
