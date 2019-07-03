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

import br.edu.utfpr.dto.DisciplinaDTO;
import br.edu.utfpr.dto.ProfessorDTO;
import io.micrometer.core.ipc.http.HttpSender.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ServicoDisciplina {
    
     private List<DisciplinaDTO> disciplinas;
    
    public ServicoDisciplina(){
        disciplinas = Stream.of(
            DisciplinaDTO.builder().id(1).nome("APOO").qtdAula(2)
                    .professor(ProfessorDTO.builder().ra(3333).nome("Jose Antonio").build())
            .build(),
            DisciplinaDTO.builder().id(2).nome("Desktop").qtdAula(4)
                    .professor(ProfessorDTO.builder().ra(2222).nome("Fabricio").build())
            .build(),
            DisciplinaDTO.builder().id(3).nome("Arquitetura").qtdAula(4)
                    .professor(ProfessorDTO.builder().ra(1111).nome("Gabriel").build())
            .build()
        ).collect(Collectors.toList());
    }
    
    @GetMapping ("/servico/disciplina")
    public ResponseEntity<List<DisciplinaDTO>> listar() {
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping ("/servico/disciplina/{id}")
    public ResponseEntity<DisciplinaDTO> listarPorId(@PathVariable int id) {
        Optional<DisciplinaDTO> disciplinaEncontrada = disciplinas.stream().filter(d -> d.getId() == id).findAny();

        return ResponseEntity.of(disciplinaEncontrada);
    }

    @PostMapping ("/servico/disciplina")
    public ResponseEntity<DisciplinaDTO> criar (@RequestBody DisciplinaDTO disciplina) {

        disciplina.setId(disciplinas.size() + 1);
        disciplinas.add(disciplina);

        return ResponseEntity.status(201).body(disciplina);
    }

    @DeleteMapping ("/servico/disciplina/{id}")
    public ResponseEntity excluir (@PathVariable int id) {
        
        if (disciplinas.removeIf(disciplina -> disciplina.getId() == id))
            return ResponseEntity.noContent().build();

        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping ("/servico/disciplina/{id}")
    public ResponseEntity<DisciplinaDTO> alterar (@PathVariable int id, @RequestBody DisciplinaDTO disciplina) {
        Optional<DisciplinaDTO> disciplinaExistente = disciplinas.stream().filter(d -> d.getId() == id).findAny();

        disciplinaExistente.ifPresent(d -> {
            d.setNome(disciplina.getNome());
            d.setQtdAula(disciplina.getQtdAula());
            d.setProfessor(disciplina.getProfessor());
        });

        return ResponseEntity.of(disciplinaExistente);
    }
}
