package br.ufrgs.inf.data.domain;

import java.util.stream.Stream;

public enum EquipmentStatus {

    ON("Ligado"),

    OFF("Desligado");

    private final String label;

    EquipmentStatus(String label) {
        this.label = label;
    }

    public static EquipmentStatus of(final String value) {
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
