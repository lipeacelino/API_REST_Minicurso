package ufpb.minicurso.pratica2.controllers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ufpb.minicurso.pratica2.entities.Disciplina;
import ufpb.minicurso.pratica2.services.DisciplinaService;

@RestController
@RequestMapping("v1/api/disciplinas")
public class DisciplinaController {

	@Autowired
	private DisciplinaService discServ;
	
	//GET /api/disciplinas Retorna um JSON (com campos id, nome) com todas as disciplinas inseridas no sistema e código 200.
	@GetMapping
	public ResponseEntity<List<Disciplina>> getDisciplinas() {
		return new ResponseEntity<List<Disciplina>>(this.discServ.getDisciplinas(), HttpStatus.OK); 
	}

	//GET /api/disciplinas/{id} Retorna um JSON que representa a disciplina completa (id, nome, nota, likes e comentarios)
	//cujo identificador único é id e código 200. Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado.
	@GetMapping("/{id}")
	public ResponseEntity<Disciplina> getDisciplina(@PathVariable Long id) {
		try {
			return new ResponseEntity<Disciplina>(this.discServ.getById(id), HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			return new ResponseEntity<Disciplina>(new Disciplina(), HttpStatus.NOT_FOUND);
		}
	}
	
	//PUT /api/disciplinas/likes/{id} Incrementa em um o número de likes da disciplina cujo identificador é id. 
	//Retorna a disciplina que foi atualizada (incluindo o id, nome e likes) e código 200. 
	//Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado.
//	@PutMapping("likes/{id}")
//	public ResponseEntity<Optional<Disciplina>> editLike(@PathVariable Long id, @RequestBody Map<String, Integer> qtdLikes) {
//		try {
//			return new ResponseEntity<Optional<Disciplina>>(this.discServ.editLike(id, qtdLikes.get("likes")), HttpStatus.OK);
//		} catch (Exception ex) {
//			return new ResponseEntity<Optional<Disciplina>> 
//		}
//	}
	
	//PUT /api/disciplinas/nota/{id} Atualiza a nota da disciplina de identificador id no sistema. 
	//No corpo da requisição HTTP deve estar um JSON com uma nova nota atribuída à disciplina. 
	//A nova nota da disciplina é a média aritmética da nota anterior da disciplina e da nova nota passada nesta chamada. 
	//Se for a primeira nota sendo adicionada então esta nota é a que vai valer para a disciplina. 
	//Retorna a disciplina que foi atualizada (incluindo o id, nome e nota) e código 200. Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado.
	@PutMapping("nota/{id}")
	public ResponseEntity<Disciplina> editNota(@PathVariable Long id, @RequestBody Map<String, Double> nota) {
		try {
			return new ResponseEntity<Disciplina>(this.discServ.editNota(id, nota.get("nota")), HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			return new ResponseEntity<Disciplina>(new Disciplina(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	//PUT /api/disciplinas/comentarios/{id} Insere um novo comentário na disciplina de identificador id. 
	//No corpo da requisição HTTP deve estar um JSON com o novo comentário (chave “comentario”) a ser adicionado na disciplina a ser atualizada no sistema. 
	//Retorna a disciplina que foi atualizada (incluindo o id, nome e os comentarios atualizados) e código 200. 	
	//Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado.
	@PutMapping("comentarios/{id}")
	public ResponseEntity<Disciplina> editComent(@PathVariable Long id, @RequestBody Map<String, String> comentario) {
		try {
			return new ResponseEntity<Disciplina>(this.discServ.editComent(id, comentario.get("comentarios")), HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			return new ResponseEntity<Disciplina>(new Disciplina(), HttpStatus.NOT_FOUND);
		}
	
	}
	
	

	
	//GET /api/disciplinas/ranking/notas Retorna todas as disciplinas inseridas no sistema ordenadas pela nota (da maior para a menor) e código 200.
	
	@GetMapping("/ranking/notas")
	public List<Disciplina> getDiscByNotaDesc() {
		return discServ.getDisciplinasByNotaDesc();
	}
	
	//GET /api/disciplinas/ranking/likes Retorna todas as disciplinas inseridas no sistema ordenadas pelo número de likes (da que tem mais likes para a que tem menos likes) e código 200.
	
	@GetMapping("/ranking/likes")
	public List<Disciplina> getDiscByLikesDesc() {
		return discServ.getDisciplinasByLikesDesc();
	}
	
}
