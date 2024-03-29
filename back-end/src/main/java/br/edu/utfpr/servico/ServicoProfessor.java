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

import br.edu.utfpr.dto.ProfessorDTO;
import io.micrometer.core.ipc.http.HttpSender.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ServicoProfessor {
    
    private List<ProfessorDTO> professores;
    
    public ServicoProfessor(){
        professores = Stream.of(
            ProfessorDTO.builder().ra(1).nome("Gabriel").build(),
            ProfessorDTO.builder().ra(2).nome("Fabricio").build(),
            ProfessorDTO.builder().ra(3).nome("Jose Antonio").build(),
            ProfessorDTO.builder().ra(4).nome("Lucas").build(),
            ProfessorDTO.builder().ra(5).nome("Cleiton").build(),
            ProfessorDTO.builder().ra(6).nome("Giovani").build(),
            ProfessorDTO.builder().ra(7).nome("William").build(),
            ProfessorDTO.builder().ra(8).nome("Silvio").build(),
            ProfessorDTO.builder().ra(9).nome("Cleber").build()
        ).collect(Collectors.toList());
    }
    
    @GetMapping ("/servico/professor")
    public ResponseEntity<List<ProfessorDTO>> listar() {
        return ResponseEntity.ok(professores);
    }

    @GetMapping ("/servico/professor/{ra}")
    public ResponseEntity<ProfessorDTO> listarPorRa(@PathVariable int ra) {
        Optional<ProfessorDTO> professorEncontrado = professores.stream().filter(p -> p.getRa() == ra).findAny();

        return ResponseEntity.of(professorEncontrado);
    }

    @PostMapping ("/servico/professor")
    public ResponseEntity<ProfessorDTO> criar (@RequestBody ProfessorDTO professor) {

        professor.setRa(professores.size() + 1);
        professores.add(professor);

        return ResponseEntity.status(201).body(professor);
    }

    @DeleteMapping ("/servico/professor/{ra}")
    public ResponseEntity excluir (@PathVariable int ra) {
        
        if (professores.removeIf(professor -> professor.getRa() == ra))
            return ResponseEntity.noContent().build();

        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping ("/servico/professor/{ra}")
    public ResponseEntity<ProfessorDTO> alterar (@PathVariable int ra, @RequestBody ProfessorDTO professor) {
        Optional<ProfessorDTO> professorExistente = professores.stream().filter(p -> p.getRa() == ra).findAny();

        professorExistente.ifPresent(p -> {
            p.setNome(professor.getNome());
        });

        return ResponseEntity.of(professorExistente);
    }
}
