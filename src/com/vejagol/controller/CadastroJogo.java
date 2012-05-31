package com.vejagol.controller;

import java.util.ArrayList;

import com.vejagol.model.Jogo;
import com.vejagol.repositorio.RepositorioJogo;
import com.vejagol.repositorio.RepositorioJogoDAO;


public class CadastroJogo {

	private RepositorioJogo repositorioJogo;
	
	public CadastroJogo() {
		this.repositorioJogo = new RepositorioJogoDAO();		
	}
	
	private boolean contains(Jogo jogo) {
		Jogo auxJogo = repositorioJogo.getJogo(jogo.getData(), jogo.getTimeCasa(), jogo.getTimeVisitante());
		if (auxJogo != null) {
			return true;
		}
		return false;
	}
	
	public boolean adicionar(Jogo jogo) {
		if (!contains(jogo)) {
			repositorioJogo.inserir(jogo);
			return true;
		}
		return false;
	}
	
	public ArrayList<Jogo> listarJogos(int de, int ate, String ordem, String filtros, boolean ascending) {
		return (ArrayList<Jogo>)repositorioJogo.listar(de, ate, ordem, filtros, ascending);
	}
}
