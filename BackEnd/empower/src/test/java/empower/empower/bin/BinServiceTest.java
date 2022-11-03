package empower.empower.bin;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ExceptionCollector;

import empower.empower.bin.entity.Bin;
import empower.empower.bin.repository.BinRepository;
import empower.empower.bin.service.BinService;

@ExtendWith(MockitoExtension.class)
public class BinServiceTest {
    
    @Mock
    private BinRepository bins;

    @InjectMocks
    private BinService binService;

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

}
