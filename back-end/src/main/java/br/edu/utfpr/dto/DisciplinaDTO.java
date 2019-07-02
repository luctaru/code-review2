package br.edu.utfpr.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DisciplinaDTO {
    private int id;
    private String nome;
    private int qtdAula;
    private ProfessorDTO professor;
}
