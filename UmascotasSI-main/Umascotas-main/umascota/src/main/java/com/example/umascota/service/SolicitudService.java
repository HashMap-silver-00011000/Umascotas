package com.example.umascota.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.umascota.model.adopcion.SolicitudAdopcion;
import com.example.umascota.model.mascota.Mascota;
import com.example.umascota.model.usuario.Usuario;
import com.example.umascota.repository.MascotaRepository;
import com.example.umascota.repository.SolicitudRepository;
import com.example.umascota.repository.UsuarioRepository;


@Service
public class SolicitudService {

    @Autowired
    public MascotaRepository mascotaRepository;

    @Autowired
    public SolicitudRepository solicitudRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;



    //Crear Solicitud, por defecto le llega al admin en PENDIENTE
    public SolicitudAdopcion crearSolicitudAdopcion(Long idMascota, Long id_usuario_adoptante, SolicitudAdopcion solicitudAdopcion){

        Optional<Usuario> usuario = usuarioRepository.findByIdUsuario(id_usuario_adoptante);
        Optional<Mascota> mascota = mascotaRepository.findByIdMascota(idMascota);

        if (usuario.isEmpty() || mascota.isEmpty()) {
            throw new RuntimeException("Usuario o Mascota no encontrados");
        }

        solicitudAdopcion.setUsuarioAdoptante(usuario.get());
        solicitudAdopcion.setMascotaSolicitada(mascota.get());

        return solicitudRepository.save(solicitudAdopcion);

    }

    //Listar Solicitudes
    public List<SolicitudAdopcion> mostrarSolicitudes(){
        return solicitudRepository.findAll();
    }

    //Aceptar Solicitud, Enviado desde LA PUERTA ADMIN
    // Archivo: SolicitudAdopcionService.java - Versión estándar
    public Optional<SolicitudAdopcion> aceptarSolicitud(Long idSolicitud, Long usuarioResolvio, SolicitudAdopcion datoRespuesta){
    
        Optional<Usuario> usuario = usuarioRepository.findByIdUsuario(usuarioResolvio);
        // Usamos el método ESTÁNDAR findById()
        return solicitudRepository.findById(idSolicitud)
            .map(solicitudAdopcion -> {
            
                solicitudAdopcion.setEstadoSolicitud(datoRespuesta.getEstadoSolicitud());
                solicitudAdopcion.setUsuarioResolvio(usuario.get());

                //Agregar condicional para guardar en laa tabla de adopcion

                // Persistencia
                return solicitudRepository.save(solicitudAdopcion); 
        });
    }

    //Mostrar Solicitud
    public Optional<SolicitudAdopcion> mostrarSolicitud(Long idSolicitud){
        return solicitudRepository.findByIdSolicitud(idSolicitud);
    }

}




