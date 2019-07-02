package br.edu.utfpr.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatriculaDTO {
    private int id;
    private AlunoDTO aluno;
    private DisciplinaDTO disciplina;
}
