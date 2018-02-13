package kov.develop.mvc.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import kov.develop.mvc.controller.data.TestData;
import kov.develop.mvc.model.Good;
import kov.develop.mvc.model.Purchasing;
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

public class AdminControllerTest extends AbstractControllerTest {

    @Test
    public void findAllGoodsTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/goods"));
        List<Good> result = checkAndGetGoods(resultActions);
        Assert.assertArrayEquals(TestData.GOODS, result.toArray());
    }

 /*   @Test
    public void GoodSaveTest() throws Exception {
        System.out.println(mapper.writeValueAsString(TestData.GOOD_NEW) + "********");
        ResultActions resultActions = mockMvc.perform(post("/good/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(TestData.GOOD_NEW)))
                .andExpect(status().isOk());
        Assert.assertArrayEquals(TestData.GOODS_WITH_NEW, goodService.findAll().toArray());
    }*/

    @Test
    public void GetGoodTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/good/2"));
        Good result = checkAndGetGood(resultActions);
        Assert.assertEquals(TestData.GOOD_2, result);
        Assert.assertArrayEquals(TestData.GOODS, goodService.findAll().toArray());
    }

    @Test
    public void DeleteGoodTest() throws Exception {
        mockMvc.perform(delete("/good/1"))
       .andExpect(status().isOk());
        Assert.assertArrayEquals(TestData.GOODS_WITHOUT_ONE, goodService.findAll().toArray());
    }

    @Test
    public void findAllPurchasingsTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/purchasings"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        String resultAsString = result.getResponse().getContentAsString();
        List<PurchasingDto> list = mapper.readValue(resultAsString, new TypeReference<List<PurchasingDto>>(){});
        Assert.assertArrayEquals(TestData.PURCHASINGS, list.stream().sorted((a, b) -> a.getId().compareTo(b.getId())).collect(Collectors.toList()).toArray());
    }




}
