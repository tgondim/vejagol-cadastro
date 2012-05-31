package com.vejagol.repositorio;

import java.util.List;

import com.vejagol.model.MelhorDaSemana;


public interface RepositorioMelhorDaSemana {

	public void inserir(MelhorDaSemana melhorDaSemana);

	public void atualizar(MelhorDaSemana melhorDaSemana);

	public MelhorDaSemana getMelhorDaSemana(Integer idMelhorDaSemana);

	public MelhorDaSemana getMelhorDaSemana(int mes, int semana, int ano);

	public List<MelhorDaSemana> listar(int de, int ate, String ordem, String filtros, boolean ascending);
	
	public void remover(MelhorDaSemana melhorDaSemana);


}
