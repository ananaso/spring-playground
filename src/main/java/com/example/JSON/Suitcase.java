package com.example.JSON;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Suitcase {
    private final int width;
    private final int height;
    private final int depth;
    private final int weight;

    @JsonCreator
    Suitcase(
            @JsonProperty("width") int width,
            @JsonProperty("height") int height,
            @JsonProperty("depth") int depth,
            @JsonProperty("weight") int weight
    ) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.weight = weight;
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public int getDepth() { return depth; }

    public int getWeight() { return weight; }
}

