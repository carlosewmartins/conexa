package br.pucpr.conexa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull(message = "Usuário é obrigatório")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "senha"})
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    @NotNull(message = "Evento é obrigatório")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Evento evento;

    @Min(value = 1, message = "Nota mínima é 1")
    @Max(value = 5, message = "Nota máxima é 5")
    @NotNull(message = "Nota é obrigatória")
    private Integer nota;

    @Column(length = 2000)
    private String comentario;

    @Column(nullable = false)
    private LocalDateTime dataAvaliacao;

    @PrePersist
    protected void onCreate() {
        if (dataAvaliacao == null) {
            dataAvaliacao = LocalDateTime.now();
        }
    }
}
