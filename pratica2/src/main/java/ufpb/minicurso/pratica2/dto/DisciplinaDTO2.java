package ufpb.minicurso.pratica2.dto;

import lombok.Data;
import ufpb.minicurso.pratica2.entities.Disciplina;

import org.modelmapper.ModelMapper;

/**
 * Essa classe deve ser usada no método editNota()
 */
@Data
public class DisciplinaDTO2 {
	
	private Long id;
	private String nome;
	private Double nota;
	
	/**@return Retorna um JSON com campos: id, nome, nota
	 */
	public static DisciplinaDTO2 create(Disciplina disciplina) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(disciplina, DisciplinaDTO2.class);
	}
}
