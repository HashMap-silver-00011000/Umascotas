package com.example.umascota.controller;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.umascota.model.adopcion.SolicitudAdopcion;
import com.example.umascota.service.SolicitudService;
import com.example.umascota.util.UserDetailsImpl;

@Controller
@RestController("/Solicitud")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController (SolicitudService solicitudService){this.solicitudService = solicitudService;}
    
    //Crear solicitud "Usuario_adoptante"
    @PostMapping("/solicitud-mascota/{idMascota}")
    public ResponseEntity<SolicitudAdopcion> crearSolicitud(@PathVariable Long idMascota, @RequestBody SolicitudAdopcion datosSolicitud,
    @AuthenticationPrincipal UserDetailsImpl usuarioAutenticado ){

        Long usuarioAutenticadoId = usuarioAutenticado.getId();
        SolicitudAdopcion  solicitudAdopcion = solicitudService.crearSolicitudAdopcion(idMascota, usuarioAutenticadoId, datosSolicitud);

        return new ResponseEntity<>(solicitudAdopcion, HttpStatus.CREATED);
    }

    //Aceptar Solicitud "Usuario_Resuelve"
    @PutMapping("/decision-mascota/{idSolicitud}")
    public ResponseEntity<SolicitudAdopcion> decisionSolicitud(@PathVariable Long idSolicitud, @RequestBody SolicitudAdopcion datosSolicitud,
    @AuthenticationPrincipal UserDetailsImpl usuarioAutenticado){

        Long usuarioAutenticadoId = usuarioAutenticado.getId();
        SolicitudAdopcion  solicitudAdopcion = solicitudService.crearSolicitudAdopcion(idSolicitud, usuarioAutenticadoId, datosSolicitud);

        return ResponseEntity.ok(solicitudAdopcion);
    }

    //Mostrar Solicitudes
    @GetMapping("/solicitudes")
    public ResponseEntity<List<SolicitudAdopcion>> mostrarSolicitudes(){
        
        List<SolicitudAdopcion> solicitud_adopcion = solicitudService.mostrarSolicitudes();
        return ResponseEntity.ok(solicitud_adopcion);
    }

    /*MostrarSolicitud
    @GetMapping("/solicitud/{id}")
    public ResponseEntity<SolicitudAdopcion> mostrarSolicitud(@PathVariable Long id){
        
        Optional<SolicitudAdopcion> solicitudAdopcion = solicitudService.mostrarSolicitud(id);

        if(solicitudAdopcion.isPresent()){
            return ResponseEntity.ok(solicitudAdopcion.get());
        }else
            return ResponseEntity.notFound().build();
        
    }
    */

}
