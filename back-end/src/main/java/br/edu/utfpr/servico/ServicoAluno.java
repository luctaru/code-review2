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

import br.edu.utfpr.dto.AlunoDTO;
import io.micrometer.core.ipc.http.HttpSender.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ServicoAluno {
    
    private List<AlunoDTO> alunos;
    
    public ServicoAluno(){
        alunos = Stream.of(
            AlunoDTO.builder().ra(1).nome("Lucas").build(),
            AlunoDTO.builder().ra(2).nome("Felipe").build(),
            AlunoDTO.builder().ra(3).nome("Isabelle").build()
        ).collect(Collectors.toList());
    }
    
    @GetMapping ("/servico/aluno")
    public ResponseEntity<List<AlunoDTO>> listar() {
        return ResponseEntity.ok(alunos);
    }

    @GetMapping ("/servico/aluno/{ra}")
    public ResponseEntity<AlunoDTO> listarPorRa(@PathVariable int ra) {
        Optional<AlunoDTO> alunoEncontrado = alunos.stream().filter(a -> a.getRa() == ra).findAny();

        return ResponseEntity.of(alunoEncontrado);
    }

    @PostMapping ("/servico/aluno")
    public ResponseEntity<AlunoDTO> criar (@RequestBody AlunoDTO aluno) {

        aluno.setRa(alunos.size() + 1);
        alunos.add(aluno);

        return ResponseEntity.status(201).body(aluno);
    }

    @DeleteMapping ("/servico/aluno/{ra}")
    public ResponseEntity excluir (@PathVariable int ra) {
        
        if (alunos.removeIf(aluno -> aluno.getRa() == ra))
            return ResponseEntity.noContent().build();

        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping ("/servico/aluno/{ra}")
    public ResponseEntity<AlunoDTO> alterar (@PathVariable int ra, @RequestBody AlunoDTO aluno) {
        Optional<AlunoDTO> alunoExistente = alunos.stream().filter(a -> a.getRa() == ra).findAny();

        alunoExistente.ifPresent(a -> {
            a.setNome(aluno.getNome());
        });

        return ResponseEntity.of(alunoExistente);
    }
}
