package ufpb.minicurso.pratica2.dto;

import org.modelmapper.ModelMapper;

import lombok.Data;
import ufpb.minicurso.pratica2.entities.Disciplina;

/**
 * Essa classe deve ser usada no método editComentarios()
 */
@Data
public class DisciplinaDTO3 {
	
	private Long id;
	private String nome;
	private String comentarios;

	/**@return Retorna um JSON com campos: id e nome
	 */
	public static DisciplinaDTO3 create(Disciplina disciplina) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(disciplina, DisciplinaDTO3.class);
	}
}
