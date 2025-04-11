/*package ch.jano.dreier.OnlineShop;

import ch.jano.dreier.OnlineShop.controller.ProductController;
import ch.jano.dreier.OnlineShop.entity.ProductEntity;
import ch.jano.dreier.OnlineShop.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "USER")
    void testGetAllProducts() throws Exception {
        List<ProductEntity> mockProducts = Arrays.asList(
                new ProductEntity(1L, "Produkt A", "Beschreibung A", 10.0, null),
                new ProductEntity(2L, "Produkt B", "Beschreibung B", 20.0, null)
        );

        when(service.findAll()).thenReturn(mockProducts);

        mockMvc.perform(get("/products/read"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateProduct() throws Exception {
        ProductEntity input = new ProductEntity(null, "Neu", "Testbeschreibung", 99.99, null);
        ProductEntity saved = new ProductEntity(1L, "Neu", "Testbeschreibung", 99.99, null);

        when(service.save(any(ProductEntity.class))).thenReturn(saved);

        mockMvc.perform(post("/products/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateProduct() throws Exception {
        Long id = 1L;
        ProductEntity oldProduct = new ProductEntity(id, "Alt", "AltDesc", 10.0, null);
        ProductEntity updated = new ProductEntity(id, "Neu", "NeuDesc", 99.99, null);

        when(service.findById(id)).thenReturn(oldProduct);
        when(service.save(any(ProductEntity.class))).thenReturn(updated);

        mockMvc.perform(put("/products/update/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Neu"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteProduct() throws Exception {
        Long id = 1L;
        doNothing().when(service).deleteById(id);

        mockMvc.perform(delete("/products/delete/" + id))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteById(id);
    }
}
*/