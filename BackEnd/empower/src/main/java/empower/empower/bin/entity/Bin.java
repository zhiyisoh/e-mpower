package empower.empower.bin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bins")
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="postalCode")
    private int postalCode;

    @Column(name="address")
    private String address;

    //types are booleans. If the bin can recycle that type of item, then the boolean
    //will be true and vice versa.
    @Column(name="ict", columnDefinition = "TINYINT(1)") //information and communication equipment
    private boolean ict; 

    @Column(name="battery", columnDefinition = "TINYINT(1)")
    private boolean battery;

    @Column(name="lamp", columnDefinition = "TINYINT(1)")
    private boolean lamp;

    @Column(name="latitude")
    private double latitude;

    @Column(name="longitude")
    private double longitude;

    public Bin(){

    }

    public Bin(Long id, int postalCode){
        this.id=id;
        this.postalCode=postalCode;
    }

    public Bin(int postalCode){
        this.postalCode=postalCode;
    }

    public Bin(int postalCode, String address, boolean ict, boolean battery, boolean lamp, double latitude, double longitude) {
        this.postalCode = postalCode;
        this.address = address;
        this.ict = ict;
        this.battery=battery;
        this.lamp=lamp;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Bin(Long id, int postalCode, String address, boolean ict, boolean battery, boolean lamp, double latitude, double longitude) {
        
        this.id = id;
        this.postalCode = postalCode;
        this.address = address;
        this.ict = ict;
        this.battery=battery;
        this.lamp=lamp;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Long getId(){
        return id;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }

    public boolean isIct() {
        return ict;
    }

    public boolean isBattery() {
        return battery;
    }

    public boolean isLamp() {
        return lamp;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }


    public void setId(Long id){
        this.id=id;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIct(boolean ict) {
        this.ict = ict;
    }
    
    public void setBattery(boolean battery) {
        this.battery = battery;
    }

    public void setLamp(boolean lamp) {
        this.lamp = lamp;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    
}
