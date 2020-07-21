package ufpb.minicurso.pratica2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Disciplina {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private double nota;
	private String comentarios;
	private int likes;
	
	/*
	@Override
	public int compareTo(Disciplina o) {
		return this.getNota().compareTo(o.getNota());
	}
	*/
	
}
