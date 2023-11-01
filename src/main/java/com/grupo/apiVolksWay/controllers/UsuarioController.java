package com.grupo.apiVolksWay.controllers;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.grupo.apiVolksWay.dtos.UsuarioDto;
import com.grupo.apiVolksWay.models.UsuarioModel;
import com.grupo.apiVolksWay.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
@RestController

@RequestMapping(value = "/usuarios" , produces ={"application/json"})
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios(){

        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }

    @GetMapping("/{idUsuaruio}")
    public ResponseEntity<Object> buscarUsuario(@PathVariable(value = "idUsuaruio") UUID id){

        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);
        if (usuarioBuscado.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());
    }
    @PostMapping
    public ResponseEntity<Object> criarUsuario(@RequestBody @Valid UsuarioDto usuarioDto){

        if(usuarioRepository.findByEmail(usuarioDto.email()) !=null){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail ja cadastrado");
        }
        UsuarioModel novoUsuario = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(novoUsuario));
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Object> editarUsuario(@PathVariable(value = "idUsuario") UUID id, @RequestBody @Valid UsuarioDto usuarioDto){

        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

        if (usuarioBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        UsuarioModel usuarioBd = usuarioBuscado.get();
        BeanUtils.copyProperties(usuarioDto, usuarioBd);

        return  ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuarioBd));
    }

    @DeleteMapping("/{idUsuaruio}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "idUsuaruio") UUID id){

        Optional<UsuarioModel> usuarioBuscado= usuarioRepository.findById(id);
        if (usuarioBuscado.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        usuarioRepository.delete(usuarioBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso!");
    }
}
