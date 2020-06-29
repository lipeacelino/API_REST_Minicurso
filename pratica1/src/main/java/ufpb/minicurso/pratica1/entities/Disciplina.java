package ufpb.minicurso.pratica1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Disciplina implements Comparable<Disciplina> {

	private int id;
	private String nome;
	private Double nota;
	
	@Override
	public int compareTo(Disciplina o) {
		return this.getNota().compareTo(o.getNota());
	}
	
}
