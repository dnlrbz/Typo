package at.ac.univie.hci.typo.Controller.ActivityManagement;

public interface ConstantsForActivities {
    public static final String BROADCAST_DETECTED_ACTIVITY = "activity_intent";

    static final long DETECTION_INTERVAL_IN_MILLISECONDS = 3 * 1000;

    public static final int CONFIDENCE = 70;

    public String IN_VEHICLE_ACTIVITY = "IN VEHICLE";
    public String IN_BUS = "IN BUS";
    public String IN_UNDERGROUND = "IN UNDERGROUND";
    public String IN_TRAM = "IN TRAM";
    public String IN_CAR = "CAR PASSENGER";
    public String IN_TRAIN = "IN TRAIN";
    public String WALKING_ACTIVITY = "WALKING";
    public String RUNNING_ACTIVITY = "RUNNING";
    public String TILTING_ACTIVITY = "TILTING";
    public String STILL_ACTIVITY = "STILL";
    public String ON_BICYCLE_ACTIVITY = "ON BICYCLE";

}
