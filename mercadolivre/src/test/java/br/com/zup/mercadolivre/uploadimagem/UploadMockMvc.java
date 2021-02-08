package br.com.zup.mercadolivre.uploadimagem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
public class UploadMockMvc {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testeMock() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.post("")).andExpect(null);


    }

}
