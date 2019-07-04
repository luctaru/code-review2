package br.edu.utfpr.apresentacao;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AulaModel {
    private int id;
    private String conteudo;
    private Date data;
    private String observacao;
    private boolean substituicao;
}
