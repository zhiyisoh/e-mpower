package empower.empower.bin.requests;

public class Coordinate {
    private double longitude;
    private double latitude;
    private String recycleType;

    public Coordinate(){

    }
    
    public Coordinate(double longitude, double latitude, String recycleType) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.recycleType = recycleType;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public String getRecycleType() {
        return recycleType;
    }
    public void setRecycleType(String recycleType) {
        this.recycleType = recycleType;
    }

    
}
