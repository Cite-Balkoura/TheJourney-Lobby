package fr.grimtown.journey.data.classes;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

import java.util.UUID;

@Entity(value = "dataPlayer")
public class DataPlayer {
    @Id
    private ObjectId id;
    @Indexed(options = @IndexOptions(unique = true))
    private UUID uuid;
    @Indexed(options = @IndexOptions(unique = true))
    private String lastNameSeen;
    private Universe universe;

    public DataPlayer() {}

    public DataPlayer(UUID uuid, String lastNameSeen) {
        this.uuid = uuid;
        this.lastNameSeen = lastNameSeen;
        universe = Universe.SKY_LAND;
    }

    public void setLastNameSeen(String lastNameSeen) {
        this.lastNameSeen = lastNameSeen;
    }

    public Universe getUniverse() {
        return universe;
    }
}
