package br.ufrgs.inf.data.domain;

public enum BabyBottleStatus {

    READY("Está Pronta"),

    NOT_READY("Não está Pronta");

    private final String label;

    BabyBottleStatus(String label) {
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
