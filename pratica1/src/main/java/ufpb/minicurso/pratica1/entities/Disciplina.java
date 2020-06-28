package ufpb.minicurso.pratica1.entities;

public class Disciplina implements Comparable<Disciplina> {

	private int id;
	private String nome;
	private Double nota;

	public Disciplina(int id, String nome, Double nota) {
		this.id = id;
		this.nome = nome;
		this.nota = nota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	@Override
	public int compareTo(Disciplina o) {
		return this.getNota().compareTo(o.getNota());
	}
	
	
}
