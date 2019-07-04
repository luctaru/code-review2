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
import br.edu.utfpr.dto.CursoDTO;
import io.micrometer.core.ipc.http.HttpSender.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ServicoCurso {
    
    private List<CursoDTO> cursos;
    
    public ServicoCurso(){
        cursos = Stream.of(
            CursoDTO.builder().id(1).nome("Engenharia de Software")
                    .departamento(DepartamentoDTO
                            .builder()
                            .id(1)
                            .nome("DACOM")
                            .chefe(ProfessorDTO
                                    .builder()
                                    .ra(4)
                                    .nome("Lucas")
                                    .build())
                            .build())
                    .coordenador(ProfessorDTO
                            .builder()
                            .ra(1)
                            .nome("Gabriel")
                            .build())
            .build(),
           CursoDTO.builder().id(2).nome("Engenharia de Eletrica")
                    .departamento(DepartamentoDTO
                            .builder()
                            .id(2)
                            .nome("DAELT")
                            .chefe(ProfessorDTO
                                    .builder()
                                    .ra(5)
                                    .nome("Cleiton")
                                    .build())
                            .build())
                    .coordenador(ProfessorDTO
                            .builder()
                            .ra(2)
                            .nome("Fabricio")
                            .build())
            .build(),
           CursoDTO.builder().id(3).nome("Engenharia de Mecanica")
                    .departamento(DepartamentoDTO
                            .builder()
                            .id(3)
                            .nome("DAMEC")
                            .chefe(ProfessorDTO
                                    .builder()
                                    .ra(6)
                                    .nome("Giovani")
                                    .build())
                            .build())
                    .coordenador(ProfessorDTO
                            .builder()
                            .ra(3)
                            .nome("Jose Antonio")
                            .build())
            .build()
        ).collect(Collectors.toList());
    }
    
    @GetMapping ("/servico/curso")
    public ResponseEntity<List<CursoDTO>> listar() {
        return ResponseEntity.ok(cursos);
    }

    @GetMapping ("/servico/curso/{id}")
    public ResponseEntity<CursoDTO> listarPorId(@PathVariable int id) {
        Optional<CursoDTO> cursoEncontrado = cursos.stream().filter(c -> c.getId() == id).findAny();

        return ResponseEntity.of(cursoEncontrado);
    }

    @PostMapping ("/servico/curso")
    public ResponseEntity<CursoDTO> criar (@RequestBody CursoDTO curso) {

        curso.setId(cursos.size() + 1);
        cursos.add(curso);

        return ResponseEntity.status(201).body(curso);
    }

    @DeleteMapping ("/servico/curso/{id}")
    public ResponseEntity excluir (@PathVariable int id) {
        
        if (cursos.removeIf(curso -> curso.getId() == id))
            return ResponseEntity.noContent().build();

        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping ("/servico/curso/{id}")
    public ResponseEntity<CursoDTO> alterar (@PathVariable int id, @RequestBody CursoDTO curso) {
        Optional<CursoDTO> cursoExistente = cursos.stream().filter(c -> c.getId() == id).findAny();

        cursoExistente.ifPresent(c -> {
            c.setNome(curso.getNome());
            c.setDepartamento(curso.getDepartamento());
            c.setCoordenador(curso.getCoordenador());
        });

        return ResponseEntity.of(cursoExistente);
    }
}
