package br.ufrgs.inf.data.domain;

public enum Temperature {

    AMBIENT("Ambiente"),

    HOT("Quente"),

    COLD("Gelado");

    private final String label;

    Temperature(final String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
