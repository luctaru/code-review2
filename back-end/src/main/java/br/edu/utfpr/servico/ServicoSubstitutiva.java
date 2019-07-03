package br.edu.utfpr.servico;

import br.edu.utfpr.dto.AulaDTO;
import br.edu.utfpr.dto.ProfessorDTO;
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

import br.edu.utfpr.dto.SubstitutivaDTO;
import io.micrometer.core.ipc.http.HttpSender.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ServicoSubstitutiva {
    
    private List<SubstitutivaDTO> substitutivas;
    
    public ServicoSubstitutiva() throws ParseException{
        substitutivas = Stream.of(
            SubstitutivaDTO.builder()
                    .id(1)
                    .aula(AulaDTO.builder()
                            .id(2)
                            .conteudo("JAVA")
                            .data(new SimpleDateFormat( "yyyyMMdd" ).parse( "20190603" ))
                            .observacao("observacao 2")
                            .substituicao(true)
                            .build())
                    .profOriginal(ProfessorDTO.builder().ra(3333).nome("Jose Antonio").build())
                    .profSubstituto(ProfessorDTO.builder().ra(9999).nome("Cleber").build())
            .build()
        ).collect(Collectors.toList());
    }
    
    @GetMapping ("/servico/substitutiva")
    public ResponseEntity<List<SubstitutivaDTO>> listar() {
        return ResponseEntity.ok(substitutivas);
    }

    @GetMapping ("/servico/substitutiva/{id}")
    public ResponseEntity<SubstitutivaDTO> listarPorId(@PathVariable int id) {
        Optional<SubstitutivaDTO> substitutivaEncontrada = substitutivas.stream().filter(s -> s.getId() == id).findAny();

        return ResponseEntity.of(substitutivaEncontrada);
    }

    @PostMapping ("/servico/substitutiva")
    public ResponseEntity<SubstitutivaDTO> criar (@RequestBody SubstitutivaDTO substitutiva) {

        substitutiva.setId(substitutivas.size() + 1);
        substitutivas.add(substitutiva);

        return ResponseEntity.status(201).body(substitutiva);
    }

    @DeleteMapping ("/servico/substitutiva/{id}")
    public ResponseEntity excluir (@PathVariable int id) {
        
        if (substitutivas.removeIf(substitutiva -> substitutiva.getId() == id))
            return ResponseEntity.noContent().build();

        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping ("/servico/substitutiva/{id}")
    public ResponseEntity<SubstitutivaDTO> alterar (@PathVariable int id, @RequestBody SubstitutivaDTO substitutiva) {
        Optional<SubstitutivaDTO> substitutivaExistente = substitutivas.stream().filter(s -> s.getId() == id).findAny();

        substitutivaExistente.ifPresent(s -> {
            s.setAula(substitutiva.getAula());
            s.setProfOriginal(substitutiva.getProfOriginal());
            s.setProfSubstituto(substitutiva.getProfSubstituto());
        });

        return ResponseEntity.of(substitutivaExistente);
    }
}
