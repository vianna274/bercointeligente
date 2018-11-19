package br.ufrgs.inf.data.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Recording {

    ON("Ligado"),

    OFF("Desligado");

    private final String label;

    Recording(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static List<String> labels() {
        return Stream.of(values())
                     .map(Recording::getLabel)
                     .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return this.label;
    }
}
