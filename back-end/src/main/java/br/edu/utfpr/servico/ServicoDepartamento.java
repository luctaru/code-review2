package br.edu.utfpr.servico;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.dto.DepartamentoDTO;
import br.edu.utfpr.dto.ProfessorDTO;
import io.micrometer.core.ipc.http.HttpSender.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ServicoDepartamento {
    
    private List<DepartamentoDTO> departamentos;
    
    public ServicoDepartamento(){
        departamentos = Stream.of(
            DepartamentoDTO.builder().id(1).nome("DACOM")
                    .chefe(ProfessorDTO.builder().ra(1111).nome("Gabriel").build())
            .build(),
            DepartamentoDTO.builder().id(2).nome("DAELT")
                    .chefe(ProfessorDTO.builder().ra(2222).nome("Fabricio").build())
            .build(),DepartamentoDTO.builder().id(3).nome("DAMEC")
                    .chefe(ProfessorDTO.builder().ra(3333).nome("Jose Antonio").build())
            .build()
        ).collect(Collectors.toList());
    }
    
    @GetMapping ("/servico/departamento")
    public ResponseEntity<List<DepartamentoDTO>> listar() {
        return ResponseEntity.ok(departamentos);
    }

    @GetMapping ("/servico/departamento/{id}")
    public ResponseEntity<DepartamentoDTO> listarPorId(@PathVariable int id) {
        Optional<DepartamentoDTO> departamentoEncontrado = departamentos.stream().filter(d -> d.getId() == id).findAny();

        return ResponseEntity.of(departamentoEncontrado);
    }

    @PostMapping ("/servico/departamento")
    public ResponseEntity<DepartamentoDTO> criar (@RequestBody DepartamentoDTO departamento) {

        departamento.setId(departamentos.size() + 1);
        departamentos.add(departamento);

        return ResponseEntity.status(201).body(departamento);
    }

    @DeleteMapping ("/servico/departamento/{id}")
    public ResponseEntity excluir (@PathVariable int id) {
        
        if (departamentos.removeIf(departamento -> departamento.getId() == id))
            return ResponseEntity.noContent().build();

        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping ("/servico/departamento/{id}")
    public ResponseEntity<DepartamentoDTO> alterar (@PathVariable int id, @RequestBody DepartamentoDTO departamento) {
        Optional<DepartamentoDTO> departamentoExistente = departamentos.stream().filter(d -> d.getId() == id).findAny();

        departamentoExistente.ifPresent(d -> {
            d.setNome(departamento.getNome());
            d.setChefe(departamento.getChefe());
        });

        return ResponseEntity.of(departamentoExistente);
    }
}
