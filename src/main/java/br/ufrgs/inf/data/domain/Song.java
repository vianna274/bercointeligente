package br.ufrgs.inf.data.domain;

public enum Song {

    FIRST("Primeira Música"),

    SECOND("Segunda Música"),

    THIRD("Terceira Música");

    private final String label;

    Song(String label) {
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
