package br.edu.utfpr.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubstitutivaDTO {
    private int id;
    private AulaDTO aula;
    private ProfessorDTO profOriginal;
    private ProfessorDTO profSubstituto;
    
}
