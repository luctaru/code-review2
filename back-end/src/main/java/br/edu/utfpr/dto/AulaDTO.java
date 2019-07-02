package br.edu.utfpr.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AulaDTO {
    private int id;
    private String conteudo;
    private Date data;
    private String observacao;
    private boolean substituicao;
}
