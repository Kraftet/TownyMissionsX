package pl.kraftet.townymissions.database.models;

public class TownyMissionsDB {

    private String nation;
    private long current_ammount;
    private long required_ammount;
    private String mission_type;
    private String uuid;
    private long stored;

    private int completed;

    public TownyMissionsDB(String nation, long current_ammount, long required_ammount, String mission_type, String uuid, long stored, int completed) {
        this.nation = nation;
        this.current_ammount = current_ammount;
        this.required_ammount = required_ammount;
        this.mission_type = mission_type;
        this.uuid = uuid;
        this.stored = stored;
        this.completed = completed;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public long getCurrent_ammount() {
        return current_ammount;
    }

    public void setCurrent_ammount(long current_ammount) {
        this.current_ammount = current_ammount;
    }

    public long getRequired_ammount() {
        return required_ammount;
    }

    public void setRequired_ammount(long required_ammount) {
        this.required_ammount = required_ammount;
    }

    public String getMission_type() {
        return mission_type;
    }

    public void setMission_type(String mission_type) {
        this.mission_type = mission_type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getStored() {
        return stored;
    }

    public void setStored(long stored) {
        this.stored = stored;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
}