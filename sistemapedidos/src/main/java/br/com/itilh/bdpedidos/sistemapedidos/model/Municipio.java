package br.com.itilh.bdpedidos.sistemapedidos.model;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tb_municipios")
public class Municipio {
    @Id
    @SequenceGenerator(name = "municipio_id_seq", sequenceName = "tb_municipios_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipio_id_seq")
    private BigInteger id;

    @Column(name = "tx_nome")
    private String nome;

    @Column(name = "bo_entrega")
    private Boolean entrega;

    @ManyToOne // Indica que o relacionamento é muitos-para-um.
    @JoinColumn(name = "id_estado") // Nome da coluna na tabela que referencia.
    private Estado estado; // Referência para o Estado ao qual o Município pertence
}
