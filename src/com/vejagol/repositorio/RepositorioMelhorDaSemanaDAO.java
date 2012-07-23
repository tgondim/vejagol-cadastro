package com.vejagol.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.vejagol.model.MelhorDaSemana;
import com.vejagol.util.HibernateUtil;


public class RepositorioMelhorDaSemanaDAO implements RepositorioMelhorDaSemana {

	private Session session;
	
	@Override
	public void atualizar(MelhorDaSemana melhorDaSemana) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.merge(melhorDaSemana);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public MelhorDaSemana getMelhorDaSemana(Integer idMelhorDaSemana) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		MelhorDaSemana melhorDaSemana = (MelhorDaSemana)session.load(MelhorDaSemana.class, idMelhorDaSemana);
		session.getTransaction().commit();
		session.close();
		return melhorDaSemana;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public MelhorDaSemana getMelhorDaSemana(int mes, int semana, int ano) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria c = session.createCriteria(MelhorDaSemana.class);
		c.add(Restrictions.eq("mes", mes));
		c.add(Restrictions.eq("semana", semana));
		c.add(Restrictions.eq("ano", ano));
		ArrayList<MelhorDaSemana> listaMelhorDaSemana = (ArrayList<MelhorDaSemana>)c.list();
		session.close();
		if (listaMelhorDaSemana.size() > 0) { 
			return listaMelhorDaSemana.get(0);
		}
		return null;
	}
	
	@Override
	public void inserir(MelhorDaSemana melhorDaSemana) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.save(melhorDaSemana);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MelhorDaSemana> listar(int de, int ate, String ordem, String filtros, boolean ascending) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(MelhorDaSemana.class);
//		Disjunction ou = Restrictions.disjunction();

//		boolean temFiltro = false;
//		StringTokenizer tokenizer = new StringTokenizer(filtros, ";");
//		while (tokenizer.hasMoreTokens()) {
//			ou.add(Restrictions.eq("liga", tokenizer.nextToken().trim()));
//			temFiltro = true;
//		}	
//		if (temFiltro) {
//			c.add(ou);
//		}

		StringTokenizer tokenizer = new StringTokenizer(ordem, ";");
		while (tokenizer.hasMoreTokens()) {
			if (ascending) {
				c.addOrder(Order.asc(tokenizer.nextToken().trim()));
			} else {
				c.addOrder(Order.desc(tokenizer.nextToken().trim()));
			}
		}	
		List<MelhorDaSemana> listaMelhorDaSemana = c.list();
		session.close();
		if (de <= listaMelhorDaSemana.size()) {
			return new ArrayList<MelhorDaSemana>(listaMelhorDaSemana.subList(de, ate));
		} else {
			return new ArrayList<MelhorDaSemana>(listaMelhorDaSemana.subList(de, listaMelhorDaSemana.size()));
		}
	}

	@Override
	public void remover(MelhorDaSemana melhorDaSemana) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		MelhorDaSemana melhorDaSemanaRemover = (MelhorDaSemana)session.load(MelhorDaSemana.class, melhorDaSemana.getId());
		session.delete(melhorDaSemanaRemover);
		session.getTransaction().commit();
		session.close();
	}

}
