package com.example.sonar.multimodule.repository;

import com.example.sonar.multimodule.entity.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProdutoRepositoryTest {

    @Mock
    private JpaProdutoRepository jpaProdutoRepository;

    @InjectMocks
    private ProdutoRepository produtoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Produto produto = new Produto();
        produto.setNome("Produto A");
        produto.setPreco(100.0);

        when(jpaProdutoRepository.save(produto)).thenReturn(produto);

        Produto savedProduto = produtoRepository.save(produto);

        assertThat(savedProduto).isNotNull();
        assertThat(savedProduto.getNome()).isEqualTo("Produto A");
        assertThat(savedProduto.getPreco()).isEqualTo(100.0);
        verify(jpaProdutoRepository, times(1)).save(produto);
    }

    @Test
    void testFindAll() {
        Produto produto1 = new Produto();
        produto1.setNome("Produto A");
        produto1.setPreco(100.0);

        Produto produto2 = new Produto();
        produto2.setNome("Produto B");
        produto2.setPreco(200.0);

        List<Produto> produtos = Arrays.asList(produto1, produto2);

        when(jpaProdutoRepository.findAll()).thenReturn(produtos);

        List<Produto> result = produtoRepository.findAll();

        assertThat(result).hasSize(2).contains(produto1, produto2);
        verify(jpaProdutoRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Produto produto = new Produto();
        produto.setNome("Produto A");
        produto.setPreco(100.0);

        when(jpaProdutoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Optional<Produto> result = produtoRepository.findById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNome()).isEqualTo("Produto A");
        verify(jpaProdutoRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteById() {
        doNothing().when(jpaProdutoRepository).deleteById(1L);

        produtoRepository.deleteById(1L);

        verify(jpaProdutoRepository, times(1)).deleteById(1L);
    }
}
