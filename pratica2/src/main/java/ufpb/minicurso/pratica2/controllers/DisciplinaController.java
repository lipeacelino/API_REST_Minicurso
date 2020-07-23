package ufpb.minicurso.pratica2.controllers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ufpb.minicurso.pratica2.dto.DisciplinaDTO1;
import ufpb.minicurso.pratica2.dto.DisciplinaDTO2;
import ufpb.minicurso.pratica2.dto.DisciplinaDTO3;
import ufpb.minicurso.pratica2.dto.DisciplinaDTODefault;
import ufpb.minicurso.pratica2.services.DisciplinaService;

@RestController
@RequestMapping("v1/api/disciplinas")
public class DisciplinaController {

	@Autowired
	private DisciplinaService discServ;
	
	//GET /api/disciplinas Retorna um JSON (com campos id, nome) com todas as disciplinas inseridas no sistema e código 200.
	@GetMapping
	public ResponseEntity<List<DisciplinaDTO1>> getDisciplinas() {
		return new ResponseEntity<List<DisciplinaDTO1>>(this.discServ.getDisciplinas()
				.stream()
				.map(d -> DisciplinaDTO1.create(d))
				.collect(Collectors.toList()), HttpStatus.OK); 
	}

	//GET /api/disciplinas/{id} Retorna um JSON que representa a disciplina completa (id, nome, nota, likes e comentarios)
	//cujo identificador único é id e código 200. Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado.
	@GetMapping("/{id}")
	public ResponseEntity<DisciplinaDTODefault> getDisciplina(@PathVariable Long id) {
		try {
			return new ResponseEntity<DisciplinaDTODefault>(DisciplinaDTODefault.create(this.discServ.getById(id)), HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			return new ResponseEntity<DisciplinaDTODefault>(new DisciplinaDTODefault(), HttpStatus.NOT_FOUND);
		}
	}
	
	//PUT /api/disciplinas/nota/{id} Atualiza a nota da disciplina de identificador id no sistema. 
	//No corpo da requisição HTTP deve estar um JSON com uma nova nota atribuída à disciplina. 
	//A nova nota da disciplina é a média aritmética da nota anterior da disciplina e da nova nota passada nesta chamada. 
	//Se for a primeira nota sendo adicionada então esta nota é a que vai valer para a disciplina. 
	//Retorna a disciplina que foi atualizada (incluindo o id, nome e nota) e código 200. Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado.
	@PutMapping("nota/{id}")
	public ResponseEntity<DisciplinaDTO2> editNota(@PathVariable Long id, @RequestBody Map<String, Double> nota) {
		try {
			return new ResponseEntity<DisciplinaDTO2>(DisciplinaDTO2.create(this.discServ.editNota(id, nota.get("nota"))), HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			return new ResponseEntity<DisciplinaDTO2>(new DisciplinaDTO2(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	//PUT /api/disciplinas/comentarios/{id} Insere um novo comentário na disciplina de identificador id. 
	//No corpo da requisição HTTP deve estar um JSON com o novo comentário (chave “comentario”) a ser adicionado na disciplina a ser atualizada no sistema. 
	//Retorna a disciplina que foi atualizada (incluindo o id, nome e os comentarios atualizados) e código 200. 	
	//Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado.
	@PutMapping("comentarios/{id}")
	public ResponseEntity<DisciplinaDTO3> editComent(@PathVariable Long id, @RequestBody Map<String, String> comentario) {
		try {
			return new ResponseEntity<DisciplinaDTO3>(DisciplinaDTO3.create(this.discServ.editComent(id, comentario.get("comentarios"))), HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			return new ResponseEntity<DisciplinaDTO3>(new DisciplinaDTO3(), HttpStatus.NOT_FOUND);
		}
	
	}
	
	//GET /api/disciplinas/ranking/notas Retorna todas as disciplinas inseridas no sistema ordenadas pela nota (da maior para a menor) e código 200.
	
	@GetMapping("/ranking/notas")
	public List<DisciplinaDTODefault> getDiscByNotaDesc() {
		return this.discServ.getDisciplinasByNotaDesc()
				.stream()
				.map(d -> DisciplinaDTODefault.create(d))
				.collect(Collectors.toList());
	}
	
	//GET /api/disciplinas/ranking/likes Retorna todas as disciplinas inseridas no sistema ordenadas pelo número de likes (da que tem mais likes para a que tem menos likes) e código 200.
	
	@GetMapping("/ranking/likes")
	public List<DisciplinaDTODefault> getDiscByLikesDesc() {
		return this.discServ.getDisciplinasByLikesDesc()
				.stream()
				.map(d -> DisciplinaDTODefault.create(d))
				.collect(Collectors.toList());
	}
	
}
