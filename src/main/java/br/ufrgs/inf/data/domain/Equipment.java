package br.ufrgs.inf.data.domain;

import java.util.stream.Stream;

public enum Equipment {

    CAMERA("Camera"),

    HEATER("Aquecedor"),

    SOUND("Som"),

    LIGHT("Luz"),

    MOBILE("Mobile");

    private final String label;

    Equipment(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Equipment of(final String value) {
        return Stream.of(values())
                     .filter(e -> e.label.equals(value))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Unable to find enum of type " + value));
    }

    @Override
    public String toString() {
        return this.label;
    }
}
