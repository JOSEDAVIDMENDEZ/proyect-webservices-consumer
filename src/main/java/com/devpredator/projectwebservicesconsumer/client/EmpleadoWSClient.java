/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devpredator.projectwebservicesconsumer.client;

import com.devpredator.projectwebservicesconsumer.client.dto.EmpleadoDTO;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;
import org.glassfish.jersey.client.ClientConfig;

/**
 *  ESTA CLASE AL FINAL DEPENDE DEL PROYECTO DE WEB SERVICES QUE AL FINAL NO SE ME LEVANTA NO SE POR QUE.
 * @author David
 */
public class EmpleadoWSClient {
    
    public static void main(String[] args) {
        
        //:::::::::::::::::::::::::::::::::::::::::GET:::::::::::::::::::::::::::::::::::::::;
        //Client client = ClientBuilder.newClient(new ClientConfig().register( LoggingFilter.class ) );
        Client client = ClientBuilder.newClient();
        //client es la clase principal de jersey para conectar a un servicio remoto o local
        
        WebTarget webTarget = client.target("http://localhost:8080/proyect-webservices/devpredator/empleadoWS")
                .path("consultarEmpleadosPorID").path("1");
        // endpoint(url) a la que estaremos conectados, los path son para cada metodo que usemos dentro de la clase de servicios (@Path)
        
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        //indica lo que la peticion me va a traer(tipo json)
        
        Response response = invocationBuilder.get();// obtiene la respuesta(response), se puede poner un if para tener los difernetes tipos de respuestas
        
        EmpleadoDTO empleado = response.readEntity(EmpleadoDTO.class);//literalmente mappeando lo que se trae de respuesta con el entity(clase)
        
        
        //:::::::::::::::::::::::::::::::::POST:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        //recordar que para verificar los post hay que utilizar postman
        Client client1 = ClientBuilder.newClient();
        WebTarget webTarget1 = client1.target("http://localhost:8080/proyect-webservices/devpredator/empleadoWS")
                .path("guardarEmpleado");//este es el path del metodo post
        EmpleadoDTO emp = new EmpleadoDTO();
        emp.setEdad(25);
        emp.setFechaCreacion(LocalDateTime.now());
        emp.setNombre("Manolo");
        emp.setPrimerApellido("Garcia");
        emp.setSegundoApellido("Martinez");
        emp.setNumeroEmpleado(003);
        
         Invocation.Builder invocationBuilder1 = webTarget1.request(MediaType.APPLICATION_JSON);
         Response response1 = invocationBuilder1.post(Entity.entity(emp, MediaType.APPLICATION_JSON));
         
         System.out.println(" "+response1.getStatus());//para imprimir la respuesta
    }
    
}
