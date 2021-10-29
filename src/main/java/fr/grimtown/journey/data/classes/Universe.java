package fr.grimtown.journey.data.classes;

public enum Universe {
    SKY_LAND(1),
    MOON(2),
    MATRIX(3),
    APOCALYPSE(4);

    public final int level;
    Universe(int level) { this.level = level; }
}
