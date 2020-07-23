package ufpb.minicurso.pratica2.dto;

import lombok.Data;
import ufpb.minicurso.pratica2.entities.Disciplina;

import org.modelmapper.ModelMapper;


/**
 * Essa classe deve ser usada no método getDisciplina()
 */
@Data
public class DisciplinaDTODefault {
	
	private Long id;
	private String nome;
	private Double nota;
	private String comentarios;
	private int likes;
	
	/**@return Retorna um JSON com campos: id, nome, nota, comentarios e likes
	 */
	public static DisciplinaDTODefault create(Disciplina disciplina) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(disciplina, DisciplinaDTODefault.class);
	}
	
}
