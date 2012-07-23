package com.vejagol.repositorio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.vejagol.model.Jogo;
import com.vejagol.util.HibernateUtil;


public class RepositorioJogoDAO implements RepositorioJogo {

	private Session session;
	
	@Override
	public void atualizar(Jogo jogo) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.merge(jogo);
		session.getTransaction().commit();
		session.close(); 
	}

	@Override
	public Jogo getJogo(Integer idJogo) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Jogo jogo = (Jogo)session.load(Jogo.class, idJogo);
		session.getTransaction().commit();
		session.close();
		return jogo;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Jogo getJogo(Calendar data, String timeCasa, String timeVisitante) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria c = session.createCriteria(Jogo.class);
		c.add(Restrictions.eq("data", data));
		c.add(Restrictions.eq("timeCasa", timeCasa));
		c.add(Restrictions.eq("timeVisitante", timeVisitante));
		ArrayList<Jogo> listaJogos = (ArrayList<Jogo>)c.list();
		session.close();
		if (listaJogos.size() > 0) { 
			return listaJogos.get(0);
		}
		return null;
	}
	
	@Override
	public void inserir(Jogo jogo) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.save(jogo);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Jogo> listar(int de, int ate, String ordem, String filtros, boolean ascending) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(Jogo.class);
		Disjunction ou = Restrictions.disjunction();

		boolean temFiltro = false;
		StringTokenizer tokenizer = new StringTokenizer(filtros, ";");
		while (tokenizer.hasMoreTokens()) {
			ou.add(Restrictions.eq("liga", tokenizer.nextToken().trim()));
			temFiltro = true;
		}	
		if (temFiltro) {
			c.add(ou);
		}

		tokenizer = new StringTokenizer(ordem, ";");
		while (tokenizer.hasMoreTokens()) {
			if (ascending) {
				c.addOrder(Order.asc(tokenizer.nextToken().trim()));
			} else {
				c.addOrder(Order.desc(tokenizer.nextToken().trim()));
			}
		}
		List<Jogo> listaJogo = c.list();
		session.close();
		if (ate <= listaJogo.size()) {
			return new ArrayList<Jogo>(listaJogo.subList(de, ate));
		} else {
			return new ArrayList<Jogo>(listaJogo.subList(de, listaJogo.size()));
		}
	}

	@Override
	public void remover(Jogo jogo) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Jogo jogoRemover = (Jogo)session.load(Jogo.class, jogo.getId());
		session.delete(jogoRemover);
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Jogo> buscar(int de, int ate, String ordem, String chaves, boolean ascending) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(Jogo.class);
		Disjunction ou = Restrictions.disjunction();

		boolean temChave = false;
		String chaveToken;
		StringTokenizer tokenizer = new StringTokenizer(chaves, " ");
		while (tokenizer.hasMoreTokens()) {
			chaveToken = tokenizer.nextToken().trim();
			//ou.add(Restrictions.ilike("data", chaveToken));
			ou.add(Restrictions.like("timeCasa", "%" + chaveToken + "%"));
			ou.add(Restrictions.like("timeVisitante", "%" + chaveToken + "%"));
			ou.add(Restrictions.like("campeonato", "%" + chaveToken + "%"));
			ou.add(Restrictions.like("liga", "%" + chaveToken + "%"));
			temChave = true;
		}	
		if (temChave) {
			c.add(ou);
		}

		tokenizer = new StringTokenizer(ordem, ";");
		while (tokenizer.hasMoreTokens()) {
			if (ascending) {
				c.addOrder(Order.asc(tokenizer.nextToken().trim()));
			} else {
				c.addOrder(Order.desc(tokenizer.nextToken().trim()));
			}
		}
		List<Jogo> listaJogo = c.list();
		session.close();
		if (ate <= listaJogo.size()) {
			return new ArrayList<Jogo>(listaJogo.subList(de, ate));
		} else {
			return new ArrayList<Jogo>(listaJogo.subList(de, listaJogo.size()));
		}
	}

}
