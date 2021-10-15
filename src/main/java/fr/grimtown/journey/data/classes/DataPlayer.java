package fr.grimtown.journey.data.classes;

import dev.morphia.annotations.Entity;

import java.util.UUID;

@Entity(value = "dataPlayer")
public class DataPlayer {
    private UUID uuid;
    private Universe universe;

    public DataPlayer() {}

    public enum Universe {
        SKY_LAND(1),
        MOON(2),
        MATRIX(3),
        APOCALYPSE(4);

        public final int level;
        Universe(int level) { this.level = level; }
    }

    public UUID getUuid() {
        return uuid;
    }

    public Universe getUniverse() {
        return universe;
    }
}
