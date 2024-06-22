package com.example.sonar.multimodule.service;

import com.example.sonar.multimodule.entity.Produto;
import com.example.sonar.multimodule.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduto() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto A");
        produto.setPreco(100.0);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto savedProduto = produtoService.saveProduto(produto);

        assertNotNull(savedProduto);
        assertEquals("Produto A", savedProduto.getNome());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testGetAllProdutos() {
        Produto produto1 = new Produto();
        produto1.setId(1L);
        produto1.setNome("Produto A");
        produto1.setPreco(100.0);

        Produto produto2 = new Produto();
        produto2.setId(2L);
        produto2.setNome("Produto B");
        produto2.setPreco(200.0);

        List<Produto> produtos = Arrays.asList(produto1, produto2);

        when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> result = produtoService.getAllProdutos();

        assertEquals(2, result.size());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    void testGetProdutoById() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto A");
        produto.setPreco(100.0);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Optional<Produto> result = produtoService.getProdutoById(1L);

        assertTrue(result.isPresent());
        assertEquals("Produto A", result.get().getNome());
        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateProduto() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto A");
        produto.setPreco(100.0);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto updatedProduto = produtoService.updateProduto(produto);

        assertNotNull(updatedProduto);
        assertEquals("Produto A", updatedProduto.getNome());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testDeleteProduto() {
        doNothing().when(produtoRepository).deleteById(1L);

        produtoService.deleteProduto(1L);

        verify(produtoRepository, times(1)).deleteById(1L);
    }

}