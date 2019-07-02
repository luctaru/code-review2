package br.edu.utfpr.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartamentoDTO {
    private int id;
    private String nome;
    private ProfessorDTO chefe;
}
