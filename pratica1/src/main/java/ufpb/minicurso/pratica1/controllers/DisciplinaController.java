package ufpb.minicurso.pratica1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ufpb.minicurso.pratica1.entities.Disciplina;
import ufpb.minicurso.pratica1.services.DisciplinaService;

@RestController
@RequestMapping("/")
public class DisciplinaController {

	@Autowired
	private DisciplinaService service;
	
	@GetMapping()
	public String index() {
		return "Welcome to API! ( ͡❛ ͜ʖ ͡❛)";
	}

	@PostMapping("/v1/api/disciplinas")
	public ResponseEntity<Disciplina> addDisciplina(@RequestBody Disciplina disc) {
		return new ResponseEntity<Disciplina>(service.addDisciplina(disc), HttpStatus.OK);
	}
	
	@GetMapping("/v1/api/disciplinas")
	public ResponseEntity<List<Disciplina>> getDisciplinas() {
		return new ResponseEntity<List<Disciplina>>(service.getDisciplinas(), HttpStatus.OK);
	}
	
	@GetMapping("/v1/api/disciplinas/{id}")
	public ResponseEntity<Disciplina> getDisciplina(@PathVariable int id) {
		try {
			return new ResponseEntity<Disciplina>(service.getDisciplina(id), HttpStatus.OK);
		}
		catch (IndexOutOfBoundsException ex) {
			return new ResponseEntity<Disciplina>(new Disciplina(0, null, null), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/v1/api/disciplinas/{id}/nome")
	public ResponseEntity<Disciplina> editNome(@PathVariable int id, @RequestBody Disciplina disciplina) {
		try {
			return new ResponseEntity<Disciplina>(service.editNome(id, disciplina), HttpStatus.OK);
		}
		catch (IndexOutOfBoundsException ex) {
			return new ResponseEntity<Disciplina>(new Disciplina(0, null, null), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/v1/api/disciplinas/{id}/nota")
	public ResponseEntity<Disciplina> editNota(@PathVariable int id, @RequestBody Disciplina disciplina) {
		try {
			return new ResponseEntity<Disciplina>(service.editNota(id, disciplina), HttpStatus.OK);
		}
		catch (IndexOutOfBoundsException ex) {
			return new ResponseEntity<Disciplina>(new Disciplina(0, null, null), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/v1/api/disciplinas/{id}")
	public ResponseEntity<Disciplina> delDisciplina(@PathVariable int id) {
		try {
			return new ResponseEntity<Disciplina>(service.delDisciplina(id), HttpStatus.OK);
		}
		catch (IndexOutOfBoundsException ex) {
			return new ResponseEntity<Disciplina>(new Disciplina(0, null, null), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/v1/api/disciplinas/ranking")
	public ResponseEntity<List<Disciplina>> getRanking() {
		return new ResponseEntity<List<Disciplina>>(service.getRanking(), HttpStatus.OK);
	}
}