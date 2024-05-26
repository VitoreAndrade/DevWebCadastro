package DevWeb.DevWeb.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosCadastrosRacao (

        @NotBlank
                String nome,
        @NotNull
        int kgQuantidade,
        @NotNull
        BigDecimal valorPago,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataCompra



){
}
