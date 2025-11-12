package com.example.umascota.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.umascota.model.adopcion.Adopcion;
import com.example.umascota.repository.AdopcionRepository;
import com.example.umascota.repository.MascotaRepository;

@Service
public class AdopcionService {

    @Autowired
    private AdopcionRepository adopcionRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    //Mostrar Adopcion
    public Optional<Adopcion> mostrarAdopcion(Long idAdopcion){
        return adopcionRepository.findByIdAdopcion(idAdopcion);
    }

    //Lista de Adopciones

    public List<Adopcion> mostrarAdopciones(){
        return adopcionRepository.findAll();
    }

    //Borrar Adopcion

    public void borrarAdopcion(Long idAdopcion){

        if(mascotaRepository.existsByIdMascota(idAdopcion)){
            mascotaRepository.deleteById(idAdopcion);
        }else{
            throw new IllegalArgumentException("La adopcion no esta registrada");
        }

    }
    //Actualizar datos de Adopcion

    public Optional<Adopcion> actualizarAdopcion(long idAdopcion, Adopcion nuevosDatos){
        return adopcionRepository.findByIdAdopcion(idAdopcion).map(adopcionDatos -> {
            adopcionDatos.setNotas(nuevosDatos.getNotas());
            return adopcionRepository.save(adopcionDatos);
        });
    }
    
}
