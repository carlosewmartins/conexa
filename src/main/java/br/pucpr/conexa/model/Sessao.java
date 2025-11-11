package br.pucpr.conexa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @Column(length = 2000)
    private String descricao;

    @NotNull(message = "Horário de início é obrigatório")
    private LocalDateTime horaInicio;

    @NotNull(message = "Horário de término é obrigatório")
    private LocalDateTime horaFim;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    @NotNull(message = "Evento é obrigatório")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "palestrante_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Palestrante palestrante;

    @ManyToOne
    @JoinColumn(name = "local_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Local local;

    private Integer capacidade;

    @Enumerated(EnumType.STRING)
    private TipoSessao tipo;

    public enum TipoSessao {
        PALESTRA, WORKSHOP, PAINEL, KEYNOTE, DEBATE, NETWORKING
    }
}
