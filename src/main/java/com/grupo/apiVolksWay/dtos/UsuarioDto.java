package com.grupo.apiVolksWay.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public record UsuarioDto(

        String id_empresa,
        @NotBlank String nome,

        @NotBlank String telefone,

        @NotBlank @Email(message = "O email deve estar no formato válido") String email,

        @NotBlank String senha,

        Date data_nascimento,
        String cidade,
        String cpf,

        String tipo_usuario

) {
}