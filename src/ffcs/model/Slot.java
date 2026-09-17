package ffcs.model;

public class Slot {

    private String slotCode;
    private String day;
    private int startHour;
    private int endHour;

    public Slot(String slotCode, String day, int startHour, int endHour) {
        this.slotCode = slotCode;
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public boolean clashesWith(Slot other) {
        if (!this.day.equals(other.day)) {
            return false;
        }
        return this.startHour < other.endHour && other.startHour < this.endHour;
    }

    public String getSlotCode() { return slotCode; }
    public String getDay() { return day; }
    public int getStartHour() { return startHour; }
    public int getEndHour() { return endHour; }

    @Override
    public String toString() {
        return slotCode + " [" + day + " " + startHour + ":00-" + endHour + ":00]";
    }
}
