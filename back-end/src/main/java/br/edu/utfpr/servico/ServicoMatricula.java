package br.edu.utfpr.servico;

import br.edu.utfpr.dto.AlunoDTO;
import br.edu.utfpr.dto.DisciplinaDTO;
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

import br.edu.utfpr.dto.MatriculaDTO;
import br.edu.utfpr.dto.ProfessorDTO;
import io.micrometer.core.ipc.http.HttpSender.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ServicoMatricula {
    
    private List<MatriculaDTO> matriculas;
    
    public ServicoMatricula(){
        matriculas = Stream.of(
            MatriculaDTO.builder()
                    .id(1)
                    .aluno(AlunoDTO
                            .builder()
                            .ra(1829793)
                            .nome("Lucas")
                    .build())
                    .disciplina(DisciplinaDTO
                            .builder()
                            .id(1)
                            .nome("APOO")
                            .qtdAula(2)
                            .professor(ProfessorDTO
                                    .builder()
                                    .ra(3333)
                                    .nome("Jose Antonio")
                            .build())
                    .build())
            .build(),
            MatriculaDTO.builder()
                    .id(2)
                    .aluno(AlunoDTO
                            .builder()
                            .ra(1829769)
                            .nome("Isabelle")
                    .build())
                    .disciplina(DisciplinaDTO
                            .builder()
                            .id(1)
                            .nome("APOO")
                            .qtdAula(2)
                            .professor(ProfessorDTO
                                    .builder()
                                    .ra(3333)
                                    .nome("Jose Antonio")
                            .build())
                    .build())
            .build(),
            MatriculaDTO.builder()
                    .id(3)
                    .aluno(AlunoDTO
                            .builder()
                            .ra(1829742)
                            .nome("Felipe")
                    .build())
                    .disciplina(DisciplinaDTO
                            .builder()
                            .id(2)
                            .nome("Desktop")
                            .qtdAula(4)
                            .professor(ProfessorDTO
                                    .builder()
                                    .ra(2222)
                                    .nome("Fabricio")
                            .build())
                    .build())
            .build()
        ).collect(Collectors.toList());
    }
    
    @GetMapping ("/servico/matricula")
    public ResponseEntity<List<MatriculaDTO>> listar() {
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping ("/servico/matricula/{id}")
    public ResponseEntity<MatriculaDTO> listarPorId(@PathVariable int id) {
        Optional<MatriculaDTO> matriculaEncontrada = matriculas.stream().filter(m -> m.getId() == id).findAny();

        return ResponseEntity.of(matriculaEncontrada);
    }

    @PostMapping ("/servico/matricula")
    public ResponseEntity<MatriculaDTO> criar (@RequestBody MatriculaDTO matricula) {

        matricula.setId(matriculas.size() + 1);
        matriculas.add(matricula);

        return ResponseEntity.status(201).body(matricula);
    }

    @DeleteMapping ("/servico/matricula/{id}")
    public ResponseEntity excluir (@PathVariable int id) {
        
        if (matriculas.removeIf(matricula -> matricula.getId() == id))
            return ResponseEntity.noContent().build();

        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping ("/servico/matricula/{id}")
    public ResponseEntity<MatriculaDTO> alterar (@PathVariable int id, @RequestBody MatriculaDTO matricula) {
        Optional<MatriculaDTO> matriculaExistente = matriculas.stream().filter(m -> m.getId() == id).findAny();

        matriculaExistente.ifPresent(m -> {
            m.setAluno(matricula.getAluno());
            m.setDisciplina(matricula.getDisciplina());
        });

        return ResponseEntity.of(matriculaExistente);
    }
}
