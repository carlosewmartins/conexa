package br.pucpr.conexa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Inscricao {
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

    @Column(nullable = false)
    private LocalDateTime dataInscricao;

    @Enumerated(EnumType.STRING)
    private StatusInscricao status;

    private String observacoes;

    @PrePersist
    protected void onCreate() {
        if (dataInscricao == null) {
            dataInscricao = LocalDateTime.now();
        }
        if (status == null) {
            status = StatusInscricao.CONFIRMADA;
        }
    }

    public enum StatusInscricao {
        CONFIRMADA, CANCELADA, AGUARDANDO, COMPARECEU
    }
}
