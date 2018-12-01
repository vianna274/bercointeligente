package br.ufrgs.inf.data.domain;

public enum BabyStatus {

    AWAKE("Acordado"),
    AWAKING ("Cansado"),
    SLEEPING("Dormindo");

    private String label;

    BabyStatus(final String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
