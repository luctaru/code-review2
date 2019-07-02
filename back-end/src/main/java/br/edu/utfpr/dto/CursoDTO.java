package br.edu.utfpr.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoDTO {
    private int id;
    private String nome;
    private DepartamentoDTO departamento;
    private ProfessorDTO coordenador;
}
