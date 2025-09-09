package com.alex.dao;

import com.alex.excecao.AlunoNaoEncontradoException;
import com.alex.modelo.Aluno;

import java.util.List;


public interface AlunoDAO
{
	long inclui(Aluno umAluno);
	void altera(Aluno umAluno) throws AlunoNaoEncontradoException;
	void exclui(long id) throws AlunoNaoEncontradoException;
	Aluno recuperaUmAluno(long numero) throws AlunoNaoEncontradoException;
	List<Aluno> recuperaAlunos();
}
