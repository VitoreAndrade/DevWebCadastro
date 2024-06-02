package DevWeb.DevWeb.Dto;

import DevWeb.DevWeb.Model.Racao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
public record DadosListagemRacao(
        Long id,
        @NotBlank
        String nome,
        @NotNull
        int kgQuantidade,
        @NotNull
        BigDecimal valorPago,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataCompra
) {
    public DadosListagemRacao (Racao racao){
        this(   racao.getId(),
                racao.getNome(),
                racao.getKgQuantidade(),
                racao.getValorPago(),
                racao.getDataCompra());
    }
}
