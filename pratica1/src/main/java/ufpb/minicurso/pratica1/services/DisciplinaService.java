package ufpb.minicurso.pratica1.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ufpb.minicurso.pratica1.entities.Disciplina;

@Service
public class DisciplinaService {

	List<Disciplina> discList = new ArrayList<Disciplina>();
	
	public Disciplina addDisciplina(Disciplina disc) {
		int id = discList.size() + 1;
		String nome = disc.getNome();
		Double nota = disc.getNota();
		
		Disciplina disciplina = new Disciplina(id, nome, nota);
		discList.add(disciplina);
		
		return disciplina;
	}

	public List<Disciplina> getDisciplinas() {
		return discList;
	}

	public Disciplina getDisciplina(int id) {
		return discList.stream().filter(d -> d.getId() == id).collect(Collectors.toList()).get(0);
	}
	
	public Disciplina editNome(int id, Disciplina disciplina) {
		Disciplina disc = discList.stream().filter(d -> d.getId() == id).collect(Collectors.toList()).get(0);
		disc.setNome(disciplina.getNome());
		return disc;
	}

	public Disciplina editNota(int id, Disciplina disciplina) {
		Disciplina disc = discList.stream().filter(d -> d.getId() == id).collect(Collectors.toList()).get(0);
		disc.setNota(disciplina.getNota());
		return disc;
	}

	public Disciplina delDisciplina(int id) {
		Disciplina disc = this.getDisciplina(id);
		discList.remove(disc);
		return disc;
	}

	public List<Disciplina> getRanking() {
		List<Disciplina> listaOrdenada = new ArrayList<Disciplina>(discList);
		Collections.sort(listaOrdenada, Collections.reverseOrder());
		return listaOrdenada;
	}
	
	
	
}
