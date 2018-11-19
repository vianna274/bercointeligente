package br.ufrgs.inf.data.domain;

public enum MobileSpeed {

    SLOW("Lento"),

    MEDIUM("Moderado"),

    FAST("Rápido");

    private final String label;

    MobileSpeed(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
