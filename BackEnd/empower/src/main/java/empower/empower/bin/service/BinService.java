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

    public Bin compareCoordinates(List<Bin> list, double longitude, double latitude){
        Bin nearest = list.get(0);

        System.out.println("list size is " + list.size());
        double nearestDistance = Double.MAX_VALUE;
        //int count = 0;
        for (int i = 0; i < list.size(); i++) {
            Bin currBin = list.get(i);
            double currBinLon = currBin.getLongitude();
            double currBinLat = currBin.getLatitude();
            double distance = distance(currBinLat, latitude, currBinLon, longitude);
            //System.out.println("distance calculated is " + distance);
            if(nearestDistance > distance){
                nearestDistance = distance;
                nearest = currBin;
            }
            //System.out.println("nearest distance is " + nearestDistance);
            //System.out.println(++count);
        }
        return nearest;
    }

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
}
