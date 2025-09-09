package com.alex.dao.impl;

import com.alex.dao.AlunoDAO;
import com.alex.excecao.AlunoNaoEncontradoException;
import com.alex.modelo.Aluno;
import com.alex.util.FabricaDeEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import java.util.List;

public class JPAAlunoDAO implements AlunoDAO
{	
	public long inclui(Aluno umAluno)
	{
		EntityManager em = null;
		EntityTransaction tx = null;

		try
		{	// transiente - quando o id é null
			// persistente - quando o objeto está sendo monitorado pelo EntityManager
			// destacado - quando o id é diferente de null e o objeto não está sendo
            //             monitorado pelo entity manager.
            em = FabricaDeEntityManager.criarEntityManager();

            tx = em.getTransaction();

            tx.begin();
            em.persist(umAluno);
            // 1. Inclui o objeto umAluno na lista de objetos monitorados do EntityManager.
            // 2. Executa a inserção do comando insert
            // 3. Atribui o valor do id em umAluno
            System.out.println("id = " + umAluno.getId());
            umAluno.setNome("abc");
            // 4. Agenda a execução de um comando update
            tx.commit();
            // 5. Executa o comando update e commita
			return umAluno.getId();
		}
		catch(RuntimeException e)
		{	if (tx != null)
			{
                tx.rollback();
			}
			throw e;
		}
		finally
		{
            em.close();
		}
	}

	public Aluno recuperaUmAluno(long numero) throws AlunoNaoEncontradoException
	{
		EntityManager em = null;

		try
		{
			em = FabricaDeEntityManager.criarEntityManager();
            Aluno umAluno = em.find(Aluno.class, numero);

			// Características no método find():
			// 1. É genérico: não requer um cast.
			// 2. Retorna null caso a linha não seja encontrada no banco.

			if(umAluno == null)
			{	throw new AlunoNaoEncontradoException("Aluno não encontrado");
			}
			return umAluno;
		}
		finally
		{   em.close();
		}
	}

	public void altera(Aluno umAluno) throws AlunoNaoEncontradoException
	{
		EntityManager em = null;
		EntityTransaction tx = null;
		Aluno aluno = null;
		try
		{
			em = FabricaDeEntityManager.criarEntityManager();
			tx = em.getTransaction();
			tx.begin();

            aluno = em.find(Aluno.class, umAluno.getId(), LockModeType.PESSIMISTIC_WRITE);

			if (aluno == null) {
                tx.rollback();
                throw new AlunoNaoEncontradoException(
                    "Aluno com id = " + umAluno.getId() + " não encontrado.");
			}
			// O merge entre nada e tudo é tudo. Ao tentar alterar um aluno deletado ele será re-inserido
			// no banco de dados.
            em.merge(umAluno);

            tx.commit();
		}
		catch(RuntimeException e)
		{
			if (tx != null)
		    {   tx.rollback();
		    }
		    throw e;
		}
		finally
		{   em.close();
		}
	}

	public void exclui(long numero) throws AlunoNaoEncontradoException
	{
		EntityManager em = null;
		EntityTransaction tx = null;

		try
		{
			em = FabricaDeEntityManager.criarEntityManager();
			tx = em.getTransaction();
			tx.begin();

            Aluno aluno = em.find(Aluno.class, numero);

			if(aluno == null)
			{	tx.rollback();
				throw new AlunoNaoEncontradoException("Aluno não encontrado");
			}

            em.remove(aluno);
			tx.commit();
		}
		catch(RuntimeException e)
		{
			if (tx != null)
		    {   tx.rollback();
		    }
		    throw e;
		}
		finally
		{   em.close();
		}
	}

	public List<Aluno> recuperaAlunos()
	{
		EntityManager em = null;

		try
		{	em = FabricaDeEntityManager.criarEntityManager();

			List alunos = em
                .createQuery("select p from Aluno p order by p.id")
                .getResultList();

			// Retorna um List vazio caso a tabela correspondente esteja vazia.

			return alunos;
		}
		finally
		{   em.close();
		}
	}
}
