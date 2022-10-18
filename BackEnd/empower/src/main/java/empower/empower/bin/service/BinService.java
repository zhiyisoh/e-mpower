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

    public Bin saveBin(Bin bin){
        return binRepository.save(bin);
    }

    public Bin getBin(Long id){
        return binRepository.findById(id).get();
    }

    public void deleteBin(Long id){
        binRepository.deleteById(id);
    }
}
