package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Cliente;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ClienteRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ClienteController {

    private final ClienteRepository repositorio;

    public ClienteController(ClienteRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/clientes")
    public List<Cliente> getTodosClientes() {
        return (List<Cliente>) repositorio.findAll();
    }

    @GetMapping("/cliente/{id}")
    public Cliente getClientePorID(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(
                () -> new Exception("ID não encontrado!"));

    }

    @PostMapping("/cliente")
    public Cliente criarCliente(@RequestBody Cliente entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception ex) {

            throw new Exception("Não foi possivel criar o cliente." + ex.getMessage());
        }

    }

    @PutMapping("/cliente/{id}")
    public Cliente alterarCliente(@PathVariable BigInteger id, @RequestBody Cliente novosDados) throws Exception {
        try {
            Optional<Cliente> clienteArmazenado = repositorio.findById(id);
            if (clienteArmazenado.isPresent()) {
                Cliente cliente = clienteArmazenado.get();
                cliente.setNome_razao_social(novosDados.getNome_razao_social());
                cliente.setMunicipio(novosDados.getMunicipio());
                cliente.setCep(novosDados.getCep());
                cliente.setBairro(novosDados.getBairro());
                cliente.setEndereco(novosDados.getEndereco());
                cliente.setTelefone(novosDados.getTelefone());
                return repositorio.save(cliente);
            } else {
                throw new Exception("Cliente não encontrado");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao alterar o cliente: " + ex.getMessage());
        }
    }

    @DeleteMapping("/cliente/{id}")
    public String deletePorId(@PathVariable BigInteger id) throws Exception {

        Optional<Cliente> clienteArmazenado = repositorio.findById(id);
        if (clienteArmazenado.isPresent()) {
            repositorio.delete(clienteArmazenado.get());
            return "Excluido";
        }
        throw new Exception("ID não encontrado para exclusão");
    }

}
