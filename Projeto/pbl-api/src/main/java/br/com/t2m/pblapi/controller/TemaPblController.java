package br.com.t2m.pblapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.t2m.pblapi.domain.model.TemaPbl;
import br.com.t2m.pblapi.domain.service.TemaPblService;

@Controller
@CrossOrigin
@RequestMapping("/temaPbl")
public class TemaPblController {
	
	@Autowired
	TemaPblService temaPblService;
	
	@GetMapping
	public ResponseEntity<List<TemaPbl>> listarTodos(){
		return ResponseEntity.ok().body(temaPblService.getAll());
	}

}
