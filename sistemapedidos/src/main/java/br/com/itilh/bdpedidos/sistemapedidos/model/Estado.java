package br.com.itilh.bdpedidos.sistemapedidos.model;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Anotações
@Data // Cria Getters e Setters
@EqualsAndHashCode // Cria HashCod e Equals
@NoArgsConstructor // Cria Construtor vazio
@AllArgsConstructor // Cria Construtor Padrao
@ToString // Cria ToString

// JPA - Anotações

@Entity
@Table(name = "tb_estados")

public class Estado {

    @Id
    @SequenceGenerator(name = "estado_id_seq", sequenceName = "tb_estados_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_id_seq")
    private BigInteger id;

    @Column(name = "tx_nome")
    private String nome;

    @Column(name = "tx_uf")
    private String uf;

}
