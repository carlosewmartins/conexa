package br.pucpr.conexa.model;
import jakarta.persistence.*;
import lombok.Data;
@Data @Entity
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
}