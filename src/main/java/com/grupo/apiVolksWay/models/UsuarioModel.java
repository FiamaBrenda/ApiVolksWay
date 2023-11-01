package com.grupo.apiVolksWay.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.io.Serial;
import java.util.UUID;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "tb_usuarios")
public class UsuarioModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario", nullable = false)
    private UUID id;
    private String  id_empresa;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private Date data_nascimento;
    private String cidade;
    private String cpf;
    private String tipo_usuario;

}