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

import br.edu.utfpr.dto.AulaDTO;
import io.micrometer.core.ipc.http.HttpSender.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ServicoAula {
    
    private List<AulaDTO> aulas;
    
    public ServicoAula(){
        aulas = Stream.of(
            AulaDTO.builder()
                    .id(1)
                    .conteudo("POO")
                    .data(2019/06/02)
                    .observacao("observacao 1")
                    .substituicao(false)
                    .build(),
            AulaDTO.builder().
                    id(1)
                    .conteudo("Desktop")
                    .data(2019/06/02)
                    .observacao("observacao 2")
                    .substituicao(true)
                    .build(),
            AulaDTO.builder()
                    .id(1)
                    .conteudo("Arquitetura")
                    .data(2019/06/02)
                    .observacao("observacao 3")
                    .substituicao(false).build()
        ).collect(Collectors.toList());
    }
    
    @GetMapping ("/servico/aula")
    public ResponseEntity<List<AulaDTO>> listar() {
        return ResponseEntity.ok(aulas);
    }

    @GetMapping ("/servico/aula/{id}")
    public ResponseEntity<AulaDTO> listarPorId(@PathVariable int id) {
        Optional<AulaDTO> aulaEncontrada = aulas.stream().filter(a -> a.getId() == id).findAny();

        return ResponseEntity.of(aulaEncontrada);
    }

    @PostMapping ("/servico/aula")
    public ResponseEntity<AulaDTO> criar (@RequestBody AulaDTO aula) {

        aula.setId(aulas.size() + 1);
        aulas.add(aula);

        return ResponseEntity.status(201).body(aula);
    }

    @DeleteMapping ("/servico/aula/{id}")
    public ResponseEntity excluir (@PathVariable int id) {
        
        if (aulas.removeIf(aula -> aula.getId() == id))
            return ResponseEntity.noContent().build();

        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping ("/servico/aula/{id}")
    public ResponseEntity<AulaDTO> alterar (@PathVariable int id, @RequestBody AulaDTO aula) {
        Optional<AulaDTO> aulaExistente = aulas.stream().filter(a -> a.getId() == id).findAny();

        aulaExistente.ifPresent(a -> {
            a.setConteudo(aula.getConteudo());
            a.setData(aula.getData());
            a.setObservacao(aula.getObservacao());
            a.setSubstituicao(aula.isSubstituicao());
        });

        return ResponseEntity.of(aulaExistente);
    }
}
