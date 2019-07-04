package br.edu.utfpr.apresentacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoModel {
    private int id;
    private String nome;
    private DepartamentoModel departamento;
    private ProfessorModel coordenador;
}
