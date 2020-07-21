package ufpb.minicurso.pratica2.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ufpb.minicurso.pratica2.entities.Disciplina;
import ufpb.minicurso.pratica2.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository discRep;

	@PostConstruct
	public void initDisciplina() {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Disciplina>> typeReference = new TypeReference<List<Disciplina>>() {
		};
		InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/disciplinas.json");

		try {
			List<Disciplina> disciplinas = mapper.readValue(inputStream, typeReference);
			this.discRep.saveAll(disciplinas);
		} catch (IOException e) {
			System.out.println("Erro ao carregar disciplinas: " + e.getMessage());
		}

	}

	public List<Disciplina> getDisciplinas() {
		return discRep.findAll();
	}
	
	public List<Disciplina> getDisciplinasByNotaDesc() {
		return discRep.findAll(Sort.by(Sort.Direction.DESC, "nota"));
	}
	
	public List<Disciplina> getDisciplinasByLikesDesc() {
		return discRep.findAll(Sort.by(Sort.Direction.DESC, "likes"));
	}
	
	public Optional<Disciplina> getById(Long id) {
		return discRep.findById(id);
	}
	
	public Optional<Disciplina> editLike(Long id, int qtdLike) {
		Optional<Disciplina> disc = this.discRep.findById(id);
		disc.get().setLikes(disc.get().getLikes() + qtdLike);
		this.discRep.save(disc.get());
		return disc;
	}

	public Optional<Disciplina> editNota(Long id, Double novaNota) {

		Optional<Disciplina> disc = this.discRep.findById(id);

		if (disc.get().getNota() == null) {
			disc.get().setNota(novaNota);
		} else {
			disc.get().setNota((disc.get().getNota() + novaNota) /2);
		} 
		
		this.discRep.save(disc.get());
		
		return disc;
	}

	public Optional<Disciplina> editComent(Long id, String comentario) {
		
		Optional<Disciplina> disc = this.discRep.findById(id);
		
		if (disc.get().getComentarios() == null) {
			disc.get().setComentarios(comentario);
		} else {
			disc.get().setComentarios(disc.get().getComentarios() + " - "+comentario);
		}
		
		discRep.save(disc.get());
		
		return disc;
	}

	
}
