package pl.kraftet.townymissions.databases.models;

public class TownyMissionsModel {

    private String town;
    private long current_ammount;
    private long required_ammount;
    private String mission_type;
    private String uuid;
    private long stored;

    private int completed;

    public TownyMissionsModel(String town, long current_ammount, long required_ammount, String mission_type, String uuid, long stored, int completed) {
        this.town = town;
        this.current_ammount = current_ammount;
        this.required_ammount = required_ammount;
        this.mission_type = mission_type;
        this.uuid = uuid;
        this.stored = stored;
        this.completed = completed;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
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