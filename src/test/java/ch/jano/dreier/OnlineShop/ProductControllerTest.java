package ch.jano.dreier.OnlineShop;

import ch.jano.dreier.OnlineShop.controller.ProductController;
import ch.jano.dreier.OnlineShop.entity.ProductEntity;
import ch.jano.dreier.OnlineShop.repository.ProductRepository;
import ch.jano.dreier.OnlineShop.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class ProductControllerTest {

    @Autowired
    private MockMvc api;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testGetAllProducts() throws Exception {
        String accessToken = obtainAccessToken("user", "user");

        ProductEntity product = new ProductEntity(null, "Produkt A", "Beschreibung A", 10.0, null);
        this.productRepository.save(product);

        api.perform(
                        get("/products")
                                .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Produkt A")));
    }

    @Test
    public void testGetProductById() throws Exception {
        String accessToken = obtainAccessToken("user", "user");

        ProductEntity product = new ProductEntity(null, "Produkt XY", "Beschreibung XY", 15.0, null);
        this.productRepository.save(product);

        String url = String.format("/products/%s", product.getId());

        api.perform(
                        get(url)
                                .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Produkt XY")));
    }

    @Test
    public void testCreateProduct() throws Exception {
        String accessToken = obtainAccessToken("admin", "admin");

        ProductEntity newProduct = new ProductEntity(null, "Neues Produkt", "Neue Beschreibung", 99.99, null);

        api.perform(
                        post("/products")
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(newProduct))
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Neues Produkt")));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        String accessToken = obtainAccessToken("admin", "admin");

        ProductEntity product = new ProductEntity(null, "Produkt Alt", "Beschreibung Alt", 10.0, null);
        this.productRepository.save(product);

        String url = String.format("/products/%s", product.getId());
        product.setName("Produkt Neu");

        api.perform(
                        put(url)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(product))
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Produkt Neu")));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        String accessToken = obtainAccessToken("admin", "admin");

        ProductEntity product = new ProductEntity(null, "Produkt Zum Löschen", "Beschreibung Löschen", 5.0, null);
        this.productRepository.save(product);

        String url = String.format("/products/%s", product.getId());

        api.perform(
                        delete(url)
                                .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(status().isOk());
    }

    private String obtainAccessToken(String username, String password) {
        RestTemplate rest = new RestTemplate();

        String body = "client_id=shop-client&" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                String.format("username=%s&", username) +
                String.format("password=%s", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> resp = rest.postForEntity(
                "http://localhost:8080/realms/OnlineShopModul295/protocol/openid-connect/token",
                entity,
                String.class
        );

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
    }
}
