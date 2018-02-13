package kov.develop.mvc.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import kov.develop.mvc.controller.data.TestData;
import kov.develop.mvc.model.Good;
import kov.develop.mvc.model.PurchasingDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractControllerTest{

    @Test
    public void findAllGoodsTest() throws Exception {
        goodService.save(TestData.GOOD_ZERO);
        MvcResult result = mockMvc.perform(get("/user/goods"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        String resultAsString = result.getResponse().getContentAsString();
        List<Good> list = mapper.readValue(resultAsString, new TypeReference<List<Good>>(){});
        Assert.assertArrayEquals(TestData.GOODS_WITHOUT_ONE, list.stream().sorted((a, b) -> a.getId().compareTo(b.getId())).collect(Collectors.toList()).toArray());
    }


    @Test
    public void purchaseTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/user/purchase/Zlatan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(TestData.GOOD_2)))
                .andExpect(status().isOk());
        Assert.assertArrayEquals(TestData.GOODS_WITHOUT_ONE, goodService.findAllNotZeroQuantity().toArray());
        List<PurchasingDto> purchasings = purchasingService.findAllDto();
        Assert.assertEquals(purchasings.size(), 2);
        Assert.assertEquals(purchasings.get(1).getUsername(), "Zlatan");
        Assert.assertEquals(purchasings.get(1).getGoodname(), "Meet1");
    }
}
