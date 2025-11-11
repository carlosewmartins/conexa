package br.pucpr.conexa.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Data é obrigatória")
    private LocalDateTime data;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "local_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Local local;

    @ManyToOne
    @JoinColumn(name = "organizador_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "senha"})
    private Usuario organizador;
}