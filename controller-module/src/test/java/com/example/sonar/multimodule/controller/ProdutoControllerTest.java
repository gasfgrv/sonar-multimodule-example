package com.example.sonar.multimodule.controller;

import com.example.sonar.multimodule.entity.Produto;
import com.example.sonar.multimodule.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProdutoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateProduto() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto A");
        produto.setPreco(100.0);

        given(produtoService.saveProduto(any(Produto.class))).willReturn(produto);

        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Produto A"))
                .andExpect(jsonPath("$.preco").value(100.0))
                .andDo(print());
    }

    @Test
    void testGetAllProdutos() throws Exception {
        Produto produto1 = new Produto();
        produto1.setId(1L);
        produto1.setNome("Produto A");
        produto1.setPreco(100.0);

        Produto produto2 = new Produto();
        produto2.setId(2L);
        produto2.setNome("Produto B");
        produto2.setPreco(200.0);

        List<Produto> produtos = Arrays.asList(produto1, produto2);

        given(produtoService.getAllProdutos()).willReturn(produtos);

        mockMvc.perform(get("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Produto A"))
                .andExpect(jsonPath("$[0].preco").value(100.0))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nome").value("Produto B"))
                .andExpect(jsonPath("$[1].preco").value(200.0))
                .andDo(print());
    }

    @Test
    void testGetProdutoById() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto A");
        produto.setPreco(100.0);

        given(produtoService.getProdutoById(anyLong())).willReturn(Optional.of(produto));

        mockMvc.perform(get("/api/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Produto A"))
                .andExpect(jsonPath("$.preco").value(100.0))
                .andDo(print());
    }

    @Test
    void testGetProdutoByIdNotFound() throws Exception {
        given(produtoService.getProdutoById(anyLong())).willReturn(Optional.empty());

        mockMvc.perform(get("/api/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testUpdateProduto() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto A");
        produto.setPreco(100.0);

        Produto updatedProduto = new Produto();
        updatedProduto.setId(1L);
        updatedProduto.setNome("Produto B");
        updatedProduto.setPreco(150.0);

        given(produtoService.getProdutoById(anyLong())).willReturn(Optional.of(produto));
        given(produtoService.updateProduto(any(Produto.class))).willReturn(updatedProduto);

        mockMvc.perform(put("/api/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Produto B"))
                .andExpect(jsonPath("$.preco").value(150.0))
                .andDo(print());
    }

    @Test
    void testUpdateProdutoNotFound() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto B");
        produto.setPreco(150.0);

        given(produtoService.getProdutoById(anyLong())).willReturn(Optional.empty());

        mockMvc.perform(put("/api/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testDeleteProduto() throws Exception {
        mockMvc.perform(delete("/api/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
