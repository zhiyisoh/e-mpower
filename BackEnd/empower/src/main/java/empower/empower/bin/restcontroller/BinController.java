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

    //returns a list of all the bins
    @GetMapping("")
    public List<Bin> getBins(){
        return binService.listBins();
    }

    //returns a specific bin by the bin's id
    //throws a BinNotFoundException if the bin is null
    @GetMapping("/{id}")
    public Bin getBin(@PathVariable Long id){
        Bin bin = binService.getBin(id);
        if(bin==null) throw new BinNotFoundException(id);
        return binService.getBin(id);
    }

    //returns a list of bins by postal code
    //if the list is null then a BinNotFoundException is thrown
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

    //Finds the nearest bin by taking in coordinates
    //Gets recycle type of the bin closest to the coordinates
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
        Bin closestBin = binService.compareCoordinates(binlist, longitude, latitude);
        return closestBin.getId();
    }

    

    //updates information about a bin through the bin's id
    //if the bin cannot be found by the id entered then a BinNotFoundException is thrown
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

    //deletes a bin by its id
    @DeleteMapping("deleteBin/{id}")
    public void deleteBin(@PathVariable Long id){
        try{
            binService.deleteBin(id);
        } catch(EmptyResultDataAccessException e){
            throw new BinNotFoundException(id);
        }
    }
}
