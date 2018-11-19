package br.ufrgs.inf.data.domain;

public enum MusicVolume {

    HIGH("Alto"),

    MEDIUM("Medio"),

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