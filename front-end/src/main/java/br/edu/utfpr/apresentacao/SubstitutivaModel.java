package br.edu.utfpr.apresentacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubstitutivaModel {
    private int id;
    private AulaModel aula;
    private ProfessorModel profOriginal;
    private ProfessorModel profSubstituto;
}
