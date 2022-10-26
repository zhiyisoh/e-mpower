package empower.empower.bin.restcontroller;

import empower.empower.bin.entity.Bin;
import empower.empower.bin.repository.BinRepository;
import empower.empower.bin.requests.Coordinate;
import empower.empower.bin.service.BinService;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bins")
public class BinController {
    @Autowired
    private BinService binService;

    private BinRepository binRepository;

    public BinController(BinRepository binRepository, BinService binService){
        this.binRepository=binRepository;
        this.binService=binService;
    }

    @GetMapping("")
    public List<Bin> getBins(){
        return binService.listBins();
    }

    @GetMapping("/{id}")
    public Bin getBin(@PathVariable Long id){
        Bin bin = binService.getBin(id);
        if(bin==null) throw new BinNotFoundException(id);
        return binService.getBin(id);
    }

    @GetMapping("/getBin/{postalCode}")
    public List<Bin> getBin(@PathVariable int postalCode){
        List<Bin> list = binRepository.findByPostalCode(postalCode);
        if(list == null){
            throw new BinNotFoundException();
        }
        return list;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addBin")
    public Bin addBin(@Valid @RequestBody Bin bin){
        Bin savedBin = binService.saveBin(bin);
        if (savedBin ==  null) throw new BinExistsException(bin.getPostalCode());
        return savedBin;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/findNearestBin")
    public Long getNearestBin(@RequestBody Coordinate coordinate){
        List<Bin> binlist;

        String recycleType = coordinate.getRecycleType();
        double longitude = coordinate.getLongitude();
        double latitude = coordinate.getLatitude();

        switch(recycleType){
            case "ICT":
                binlist = binService.listIctBins();
                break;
            case "Bulb":
                binlist = binService.listLampBins();
                break;
            case "Batteries":
                binlist = binService.listBatteryBins();
                break;
            default:
                binlist = binService.listBins();
        }
        //System.out.println(binlist.size() + "Longitude and Latitude: " + longitude + latitude);
        Bin closestBin = compareCoordinates(binlist, longitude, latitude);
        return closestBin.getId();
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

    @PutMapping("/updateBin/{id}")
    public ResponseEntity<?> updateBin(@PathVariable Long id, @Valid @RequestBody Bin bin){
        if(!binRepository.existsById(id)) throw new BinNotFoundException(id);

        return binRepository.findById(id).map(oldBin ->{
            oldBin.setPostalCode(bin.getPostalCode());
            oldBin.setAddress(bin.getAddress());
            oldBin.setIct(bin.isIct());
            oldBin.setBattery(bin.isBattery());
            oldBin.setLamp(bin.isLamp());
            oldBin.setLatitude(bin.getLatitude());
            oldBin.setLongitude(bin.getLongitude());
            binService.saveBin(oldBin);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new BinNotFoundException(id));
    }

    @DeleteMapping("deleteBin/{id}")
    public void deleteBin(@PathVariable Long id){
        try{
            binService.deleteBin(id);
        } catch(EmptyResultDataAccessException e){
            throw new BinNotFoundException(id);
        }
    }
}
