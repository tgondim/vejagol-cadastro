package com.vejagol.controller;

import java.util.ArrayList;

import com.vejagol.model.MelhorDaSemana;
import com.vejagol.repositorio.RepositorioMelhorDaSemana;
import com.vejagol.repositorio.RepositorioMelhorDaSemanaDAO;


public class CadastroMelhorDaSemana {

	private RepositorioMelhorDaSemana repositorioMelhorDaSemana;
	
	public CadastroMelhorDaSemana() {
		this.repositorioMelhorDaSemana = new RepositorioMelhorDaSemanaDAO();		
	}
	
	private boolean contains(MelhorDaSemana melhorDaSemana) {
		MelhorDaSemana auxMelhorDaSemana = repositorioMelhorDaSemana.getMelhorDaSemana(melhorDaSemana.getMes(), melhorDaSemana.getSemana(), melhorDaSemana.getAno());
		if (auxMelhorDaSemana != null) {
			return true;
		}
		return false;
	}
	
	public boolean adicionar(MelhorDaSemana melhorDaSemana) {
		if (!contains(melhorDaSemana)) {
			repositorioMelhorDaSemana.inserir(melhorDaSemana);
			return true;
		}
		return false;
	}
	
	public MelhorDaSemana getMelhorDaSemana(int mes, int semana, int ano) {
		return repositorioMelhorDaSemana.getMelhorDaSemana(mes, semana, ano);
	}
	
	public ArrayList<MelhorDaSemana> listarMelhorDaSemana(int de, int ate, String ordem, String filtros, boolean ascending) {
		return (ArrayList<MelhorDaSemana>)repositorioMelhorDaSemana.listar(de, ate, ordem, filtros, ascending);
	}
}
