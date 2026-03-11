package com.example.live.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.example.live.product.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import java.util.List;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import org.springframework.test.web.servlet.ResultActions;
import java.util.ArrayList;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

@ExtendWith(MockitoExtension.class)
public class ProductTests {

    @InjectMocks
    private ProductController productController;

    @Autowired
    private ProductRepository underTest;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    public MockMvc mockMvc;

    @DisplayName("Test 1:Save Product Test")
    @Order(1)
    public void EmployeeTest() throws Exception{
        
        Product prod = new Product();
        prod.setName("daadd dadad");
        prod.setSku("123");
        prod.setImage("123");
        prod.setPrice(123);
        prod.setHealthcarcat(123);
    
        underTest.save(prod);

        //Verify save data
        System.out.println(prod);
        assertThat(prod.getId()).isGreaterThan(0);

        //Verify update data

        Product existingProduct = underTest.findById(prod.getId()).get();

        existingProduct.setName("adsdsa asdasddassad");
        existingProduct.setSku("123");
        existingProduct.setImage("123");
        existingProduct.setPrice(123);
        existingProduct.setHealthcarcat(123);

        underTest.save(existingProduct);

        ResultActions response = mockMvc.perform(put("/api/products/{id}", prod.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(existingProduct)));

        response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.name", is(existingProduct.getName())));

         // verify get data by id

        ResultActions responseGetDataById = mockMvc.perform(get("/api/products/{id}", prod.getId()));

        responseGetDataById.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(prod.getName())))
                .andExpect(jsonPath("$.image", is(prod.getImage())))
                .andExpect(jsonPath("$.sku", is(prod.getSku())))
                .andExpect(jsonPath("$.healthcarcat", is(prod.getHealthcarcat())))
                .andExpect(jsonPath("$.price", is(prod.getPrice())));

                 //pagination

                ResultActions responsePagi = mockMvc.perform(get("/api/products/pagination/1/0"));
        
                responsePagi.andExpect(status().isOk())
                        .andDo(print());        

        //Verify delete data
        ResultActions responseDel= mockMvc.perform(delete("/api/products/{id}", prod.getId()));

        responseDel.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(2)
    public void getAllProduct() throws Exception{
       // precondition

        // action

        Product prod = new Product();
        prod.setName("daadd dadad");
        prod.setSku("123");
        prod.setImage("123");
        prod.setPrice(123);
        prod.setHealthcarcat(123);

        List<Product> prodList = List.of(prod);


        // verify the output
        System.out.println(prodList);
        assertThat(prodList).isNotNull();
        assertThat(prodList.size()).isGreaterThan(0);
}

// @Test
// @Order(3)
// public void getPagination() throws Exception{
//         ResultActions responsePagi = mockMvc.perform(get("/api/products/pagination/1/0"));

//         //pagination

//         responsePagi.andExpect(status().isOk())
//                 .andDo(print());
// }

// @Test
//     @Order(3)
//     public void updateProduct(){

//         // 
//         Product prod = new Product();
//         prod.setName("daadd dadad");
//         prod.setSku("123");
//         prod.setImage("123");
//         prod.setPrice(123);
//         prod.setHealthcarcat(123);

//         given(underTest.findById(prod.getId())).willReturn(Optional.of(prod));
//         prod.setName("dsadasasd dsadsaasdads");
//         given(underTest.save(prod)).willReturn(prod);

//         // action
//         Product existingProduct = underTest.findById(prod.getId()).get();
//         existingProduct.setName(prod.getName());
//         underTest.save(existingProduct);

//         ResultActions response = mockMvc.perform(put("/api/products/{id}", prod.getId())
//         .contentType(MediaType.APPLICATION_JSON)
//         .content(objectMapper.writeValueAsString(prod)));

//         // verify
//         response.andExpect(status().isOk())
//         .andDo(print())
//         .andExpect(jsonPath("$.name", is(prod.getName())));
//     }

       

}