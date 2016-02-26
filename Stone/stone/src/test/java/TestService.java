import com.alibaba.fastjson.JSONObject;
import com.zhenhappy.ems.dto.ContactVisaDto;
import com.zhenhappy.ems.dto.ProductQueryDto;
import com.zhenhappy.ems.entity.TExhibitorInfo;
import com.zhenhappy.ems.service.ExhibitorService;
import com.zhenhappy.ems.service.ProductService;
import com.zhenhappy.ems.service.VisaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by lianghaijian on 2014-04-12.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class TestService {

    @Autowired
    private ExhibitorService exhibitorService;

    @Autowired
    private ProductService productService;

    @Autowired
    private VisaService visaService;

    @Test
    public void testGetAllTypes() {
        System.out.println(JSONObject.toJSONString(exhibitorService.loadAllProductTypes()));
    }

    @Test
    public void testGetExhibitorChecked() {
        System.out.println(JSONObject.toJSONString(exhibitorService.loadAllProductTypesWithCheck(4)));
    }

    @Test
    public void testGetProduts() {
        System.out.printf(JSONObject.toJSONString(productService.loadAllProducts(4)));
    }

    @Test
    public void testQueryProducts() {
        ProductQueryDto p = new ProductQueryDto();
        p.setProductModel("小号");
        System.out.println(JSONObject.toJSONString(productService.queryProducts(p, 4)));
    }

    @Test
    public void testVisas() {
        System.out.printf("size:" + visaService.queryVisasByEid(2).size());
    }

    @Test
    public void testTypeProducts(){
        System.out.printf(JSONObject.toJSONString(productService.previewProducts(4)));
    }

    @Test
    public void testQueryContactVisas(){
        List<ContactVisaDto> contactVisaDtos = visaService.queryContactsWithVisaByEid(2);
        System.out.printf(contactVisaDtos.size()+"-- size");
    }

	@Test
	public void testLoadExhibitorInfoByEid(){
		TExhibitorInfo exhibitorInfo = exhibitorService.loadExhibitorInfoByEid(1544);
		System.out.printf(exhibitorInfo.toString());
	}
}
