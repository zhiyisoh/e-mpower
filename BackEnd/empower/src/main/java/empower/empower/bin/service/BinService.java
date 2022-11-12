package empower.empower.bin.service;

import empower.empower.bin.entity.Bin;
import empower.empower.bin.repository.BinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import javax.transaction.Transactional;

@Service
@Transactional
public class BinService {
    @Autowired
    private BinRepository binRepository;

    public List<Bin> listBins(){
        return binRepository.findAll();
    }

    public List<Bin> listIctBins(){
        return binRepository.findByIct(true);
    }
    public List<Bin> listBatteryBins(){
        return binRepository.findByBattery(true);
    }
    public List<Bin> listLampBins(){
        return binRepository.findByLamp(true);
    }

    public Bin saveBin(Bin bin){
        return binRepository.save(bin);
    }

    public Bin getBin(Long id){
        return binRepository.findById(id).get();
    }

    public void deleteBin(Long id){
        binRepository.deleteById(id);
    }

    //Returns the nearest bin by comparing all the bins in the list with the inputted bin's longtitude and latitude
    public Bin compareCoordinates(List<Bin> list, double longitude, double latitude){
        Bin nearest = list.get(0);

        double nearestDistance = Double.MAX_VALUE;
        //int count = 0;
        for (int i = 0; i < list.size(); i++) {
            Bin currBin = list.get(i);
            double currBinLon = currBin.getLongitude();
            double currBinLat = currBin.getLatitude();
            double distance = distance(currBinLat, latitude, currBinLon, longitude);

            if(nearestDistance > distance){
                nearestDistance = distance;
                nearest = currBin;
            }
        }
        return nearest;
    }

    // Haversine formula to determine the closest bin in it's surroundings
    public static double distance(double lat1, double lat2, double lon1, double lon2) {
 
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                 + Math.cos(lat1) * Math.cos(lat2)
                 * Math.pow(Math.sin(dlon / 2),2);
             
        double c = 2 * Math.asin(Math.sqrt(a));
 
        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;
 
        // calculate the result
        return(c * r);
    }

    public Bin updateBin(Long id, Bin newBin){
        return binRepository.findById(id).map(bin ->{
            bin.setPostalCode(newBin.getPostalCode());
            bin.setAddress(newBin.getAddress());
            bin.setIct(newBin.isIct());
            bin.setBattery(newBin.isBattery());
            bin.setLamp(newBin.isLamp());
            bin.setLatitude(newBin.getLatitude());
            bin.setLongitude(newBin.getLongitude());
            return binRepository.save(bin);
        }).orElse(null);
    }



}
