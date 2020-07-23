package ufpb.minicurso.pratica2.dto;

import org.modelmapper.ModelMapper;

import lombok.Data;
import ufpb.minicurso.pratica2.entities.Disciplina;

/**
 * Essa classe deve ser usada no método getDisciplinas()
 */
@Data
public class DisciplinaDTO1 {

	private Long id;
	private String nome;
	
	/**@return Retorna um JSON com campos: id e nome
	 */
	public static DisciplinaDTO1 create(Disciplina disciplina) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(disciplina, DisciplinaDTO1.class);
	}
	
}
