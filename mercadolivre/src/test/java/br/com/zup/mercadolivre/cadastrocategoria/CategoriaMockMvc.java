package br.com.zup.mercadolivre.cadastrocategoria;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaMockMvc {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EntityManager manager;

    @Test
    @DisplayName("teste cadastro categoria")
    public void cadastroCategoria() throws Exception {

        // cenario

        // ação

        // verificar
        URI uri = new URI("/categoria/cadastrar");

        CategoriaFormRequest formRequest = new CategoriaFormRequest("teste");
        mockMvc.perform(post(uri).content(objectMapper.writeValueAsString(formRequest)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(200));

        Mockito.verify(null);

    }
}
