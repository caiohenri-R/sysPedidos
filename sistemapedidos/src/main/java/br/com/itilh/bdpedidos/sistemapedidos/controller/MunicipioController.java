package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Municipio;
import br.com.itilh.bdpedidos.sistemapedidos.repository.MunicipioRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class MunicipioController {

    private final MunicipioRepository repositorio;

    public MunicipioController(MunicipioRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/municipios")
    public List<Municipio> getTodosMunicipios() {
        return (List<Municipio>) repositorio.findAll();
    }

    @GetMapping("/municipios/estado/{id}")
    public List<Municipio> getTodosMunicipiosPorEstadoId(@PathVariable BigInteger id) {
        return (List<Municipio>) repositorio.findByEstadoId(id);
    }

    /*@GetMapping("/municipios/estado/{nome}")
    public List<Municipio> getTodosMunicipiosPorEstadoNome(@PathVariable String nome) {
        return (List<Municipio>) repositorio.findByEstadoNomeIgnoreCase(nome);
    }*/

    @GetMapping("/municipio/{id}")
    public Municipio getMunicipioPorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(
                () -> new Exception("ID não encontrado"));

    }

    @PostMapping("/municipio")
    public Municipio criarMunicipio(@RequestBody Municipio entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception ex) {

            throw new Exception("Não foi possivel criar o municipio." + ex.getMessage());
        }

    }

    @PutMapping("/municipio/{id}")
    public Municipio alterarMunicipio(@PathVariable BigInteger id, @RequestBody Municipio novosDados) throws Exception {
        try {
            Optional<Municipio> municipioArmazenado = repositorio.findById(id);
            if (municipioArmazenado.isPresent()) {
                Municipio municipio = municipioArmazenado.get();
                municipio.setNome(novosDados.getNome());
                municipio.setEstado(novosDados.getEstado());
                return repositorio.save(municipio);
            } else {
                throw new Exception("Município não encontrado");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao alterar o município: " + ex.getMessage());
        }
    }

    @DeleteMapping("/municipio/{id}")
    public String deletePorId(@PathVariable BigInteger id) throws Exception {

        Optional<Municipio> municipioArmazenado = repositorio.findById(id);
        if (municipioArmazenado.isPresent()) {
            repositorio.delete(municipioArmazenado.get());
            return "Excluido";
        }
        throw new Exception("ID não encontrado para exclusão");
    }

}
