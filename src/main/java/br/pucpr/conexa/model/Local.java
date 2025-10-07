package br.pucpr.conexa.model;
import jakarta.persistence.*;
import lombok.Data;
@Data @Entity
public class Local {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
}