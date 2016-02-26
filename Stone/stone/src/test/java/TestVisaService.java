import com.zhenhappy.ems.dto.ContactVisaDto;
import com.zhenhappy.ems.dto.JoinerVisaDto;
import com.zhenhappy.ems.service.ExhibitorService;
import com.zhenhappy.ems.service.ProductService;
import com.zhenhappy.ems.service.VisaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class TestVisaService {

    @Autowired
    private ExhibitorService exhibitorService;

    @Autowired
    private ProductService productService;

    @Autowired
    private VisaService visaService;

    @Test
    public void testQueryContactVisas(){
        List<JoinerVisaDto> joinerVisaDtos = visaService.queryJoinersWithVisaByEid(2200);
        System.out.printf(joinerVisaDtos.size() + "-- size");
    }
}
