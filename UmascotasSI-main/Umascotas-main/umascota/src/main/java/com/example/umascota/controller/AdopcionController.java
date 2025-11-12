package com.example.umascota.controller;


import java.util.*;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.umascota.model.adopcion.Adopcion;
import com.example.umascota.repository.AdopcionRepository;
import com.example.umascota.service.AdopcionService;


@RestController
@RequestMapping("/api/adopciones")
public class AdopcionController {

    private final AdopcionRepository adopcionRepository;
    
    private final AdopcionService adopcionService;

    public AdopcionController(AdopcionService adopcionService, AdopcionRepository adopcionRepository){this.adopcionService = adopcionService;this.adopcionRepository = adopcionRepository;}

    //GET por id
    @GetMapping("/adopcion/{idAdopcion}")
    public ResponseEntity<Adopcion> obtenerAdopcion(@PathVariable Long idAdopcion){

        Optional<Adopcion> adopcion = adopcionService.mostrarAdopcion(idAdopcion);

        if(adopcion.isPresent()){
            return ResponseEntity.ok(adopcion.get());
        }else{
            return ResponseEntity.notFound().build();
        }         
    }
    //Get en Lista
    @GetMapping("/adopciones")
    public ResponseEntity<List<Adopcion>> obtenerAdopciones(){
        List<Adopcion> adopciones = adopcionService.mostrarAdopciones();
        return ResponseEntity.ok(adopciones);
    }

    //PUT ACTUALIZAR datos de adopcion
    @PutMapping("actualizar-datos/{id}")
    public ResponseEntity<Adopcion> actualizarAdopcion(@PathVariable Long id, @RequestBody Adopcion nueAdopcion){
        Optional<Adopcion> adopcion =  adopcionService.actualizarAdopcion(id, nueAdopcion);
        if(adopcion.isPresent()){
            return ResponseEntity.ok(adopcion.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    //Eliminar Adopcion por ID
    @DeleteMapping("borrar-adopcion/{id}")
    public ResponseEntity<Void> borrarAdopcion(@PathVariable Long id){
        adopcionService.borrarAdopcion(id);
        return ResponseEntity.noContent().build();
    }

}
