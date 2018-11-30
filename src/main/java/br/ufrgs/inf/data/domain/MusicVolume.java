package br.ufrgs.inf.data.domain;

public enum MusicVolume {

    MEDIUM("Medio"),

    HIGH("Alto"),

    LOW("Baixo");

    private final String label;

    MusicVolume(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}