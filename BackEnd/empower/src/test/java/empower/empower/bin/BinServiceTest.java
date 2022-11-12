package empower.empower.bin;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import empower.empower.bin.entity.Bin;
import empower.empower.bin.repository.BinRepository;
import empower.empower.bin.restcontroller.BinController;
import empower.empower.bin.service.BinService;

@ExtendWith(MockitoExtension.class)
public class BinServiceTest {
    
    @Mock
    private BinRepository bins;

    @InjectMocks
    private BinService binService;

    @InjectMocks
    private BinController binController;

    @Test
    void addBin_ReturnSavedBin(){
        Bin newBin = new Bin(123456);

        when(bins.save(any(Bin.class))).thenReturn(newBin);

        //act
        Bin savedBin = binService.saveBin(newBin);

        //assert
        assertNotNull(savedBin);
        
        verify(bins).save(newBin);
    }

    @Test
    void addBin_samePostalCode_ReturnNull(){
        int postal=100000;
        Bin bin = new Bin(postal);
        List<Bin> samePostalCodes = new ArrayList<Bin>();
        samePostalCodes.add(new Bin(postal));
        //when(bins.findByPostalCode(bin.getPostalCode())).thenReturn(samePostalCodes);
        Bin savedBin = binService.saveBin(bin);
        assertNull(savedBin);
        //verify(bins).findByPostalCode(postal);
    }

    @Test
    void compareCoordinate_SCIS1ICT_ReturnCorrectNearest(){

        //arrange
        List<Bin> binList = new ArrayList<Bin>();
        binList.add(new Bin(229233, "2 HANDY RD, THE CATHAY, LOADING BAY", true, true, true, 1.2996983528137207, 103.84794616699219));
        binList.add(new Bin(238843, "176 ORCHARD ROAD, LEVEL 3", true, true, true, 1.3014954328536987, 103.83966064453125));
        binList.add(new Bin(287994, "200 TURF CLUB ROAD DBS THE GRANDSTAND, THE GRANDSTAND (TURF CITY), MAIN LOBBY CONCEIRGE, LEVEL 3", true, true, true, 1.3382333517074585, 103.79347229003906));

        //act
        Bin returnedBin = binService.compareCoordinates(binList, 103.84958354932, 1.29732560407976);
        Bin correctBin = binList.get(0);

        //assert
        assertEquals(returnedBin, correctBin);

    }

}
