package com.vejagol.repositorio;

import java.util.Calendar;
import java.util.List;

import com.vejagol.model.Jogo;


public interface RepositorioJogo {

	public void inserir(Jogo jogo);

	public void atualizar(Jogo jogo);

	public Jogo getJogo(Integer idJogo);

	public Jogo getJogo(Calendar data, String timeCasa, String timeVisitante);

	public List<Jogo> listar(int de, int ate, String ordem, String filtros, boolean ascending);
	
	public List<Jogo> buscar(int de, int ate, String chave, String ordem, boolean ascending);
	
	public void remover(Jogo jogo);


}
