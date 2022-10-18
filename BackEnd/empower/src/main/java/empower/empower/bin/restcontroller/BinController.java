package empower.empower.bin.restcontroller;

import empower.empower.bin.entity.Bin;
import empower.empower.bin.repository.BinRepository;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addBin")
    public Bin addBin(@Valid @RequestBody Bin bin){
        Bin savedBin = binService.saveBin(bin);
        if (savedBin ==  null) throw new BinExistsException(bin.getPostalCode());
        return savedBin;
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
