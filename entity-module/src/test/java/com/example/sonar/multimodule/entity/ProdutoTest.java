package com.example.sonar.multimodule.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProdutoTest {

    @Test
    void testProdutoGettersAndSetters() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto A");
        produto.setPreco(100.0);

        assertEquals(1L, produto.getId());
        assertEquals("Produto A", produto.getNome());
        assertEquals(100.0, produto.getPreco());
    }

    @Test
    void testProdutoEqualsAndHashCode() {
        Produto produto1 = new Produto();
        produto1.setId(1L);
        produto1.setNome("Produto A");
        produto1.setPreco(100.0);

        Produto produto2 = new Produto();
        produto2.setId(1L);
        produto2.setNome("Produto A");
        produto2.setPreco(100.0);

        Produto produto3 = new Produto();
        produto3.setId(2L);
        produto3.setNome("Produto B");
        produto3.setPreco(200.0);

        assertEquals(produto1, produto2);
        assertNotEquals(produto1, produto3);
        assertEquals(produto1.hashCode(), produto2.hashCode());
        assertNotEquals(produto1.hashCode(), produto3.hashCode());
    }

    @Test
    void testProdutoToString() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto A");
        produto.setPreco(100.0);

        String expectedToString = "Produto(id=1, nome=Produto A, preco=100.0)";
        assertEquals(expectedToString, produto.toString());
    }
}
