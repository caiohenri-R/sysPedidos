package br.com.itilh.bdpedidos.sistemapedidos.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Produto;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ProdutoRepository;

@RestController
public class ProdutoController {

    private final ProdutoRepository repositorio;

    public ProdutoController(ProdutoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/produtos")
    public List<Produto> getTodosProdutos() {
        return (List<Produto>) repositorio.findAll();
    }

    @GetMapping("/produto/{id}")
    public Produto getProdutoPorID(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(
                () -> new Exception("ID não encontrado!"));

    }

    @PostMapping("/produto")
    public Produto criarProduto(@RequestBody Produto entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception ex) {

            throw new Exception("Não foi possivel criar o produto." + ex.getMessage());
        }

    }

    @PutMapping("/produto/{id}")
    public Produto alterarProduto(@PathVariable BigInteger id, @RequestBody Produto novosDados) throws Exception {
        try {
            Optional<Produto> produtoArmazenado = repositorio.findById(id);
            if (produtoArmazenado.isPresent()) {
                Produto produto = produtoArmazenado.get();
                produto.setDescricao(novosDados.getDescricao());
                produto.setAtivo(false);
                return repositorio.save(produto);
            } else {
                throw new Exception("Produto não encontrado");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao alterar o produto: " + ex.getMessage());
        }
    }

    @DeleteMapping("/produto/{id}")
    public String deletePorId(@PathVariable BigInteger id) throws Exception {

        Optional<Produto> produtoArmazenado = repositorio.findById(id);
        if (produtoArmazenado.isPresent()) {
            repositorio.delete(produtoArmazenado.get());
            return "Excluido";
        }
        throw new Exception("ID não encontrado para exclusão");
    }

}
