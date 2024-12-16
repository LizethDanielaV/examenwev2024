package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.FacturaException;
import com.example.demo.dtos.ConsultaFacturaResDto;
import com.example.demo.dtos.ConsultarFacturarReqDto;
import com.example.demo.dtos.CrearFacturaDto;
import com.example.demo.entities.Compra;
import com.example.demo.services.CompraService;
import com.example.demo.services.ConsultarFacturaService;

@RestController
@RequestMapping("/api")
public class FacturaController {

	@Autowired
    private CompraService compraService;
	
	@Autowired
	ConsultarFacturaService consultarFacturaService;
	
	 @PostMapping("/crear/{tiendaUuid}")
	    public ResponseEntity<?> crearFactura(@PathVariable String tiendaUuid, @RequestBody CrearFacturaDto facturaRequest) {
		try {
			 Compra compra = compraService.procesarFactura(tiendaUuid, facturaRequest);
			 Map<String, Object> response = new HashMap<>();
	            response.put("status", "success");
	            response.put("message", "La factura se ha creado correctamente con el número: " + compra.getId());
	            Map<String, String> data = new HashMap<>();
	            data.put("numero", compra.getId().toString());
	            data.put("total", compra.getTotal().toString());
	            data.put("fecha", compra.getFecha().toString());
	            
	            response.put("data", data);
	            
	            return ResponseEntity.ok(response);
		}catch (FacturaException e) {
			Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            errorResponse.put("data", null);
            return ResponseEntity.status(e.getStatus()).body(errorResponse);
	 }catch (Exception e) {
		 Map<String, Object> errorResponse = new HashMap<>();
         e.printStackTrace();
         errorResponse.put("status", "error");
         errorResponse.put("message", "Error interno del servidor");
         errorResponse.put("data", null);
  
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	 }
	}
	 
	 @PostMapping("consultar/{uuidTienda}")
		public ResponseEntity<?> consultarFactura(@PathVariable String uuidTienda, @RequestBody ConsultarFacturarReqDto consultaDto){
		 Compra compra =consultarFacturaService.consultar(uuidTienda, consultaDto);
		 ConsultaFacturaResDto dto = new ConsultaFacturaResDto();
		 dto.fromEntity(compra);
		 return ResponseEntity.ok(dto);
	 }
		
}
