package com.alex;

import com.alex.dao.AlunoDAO;
import com.alex.excecao.AlunoNaoEncontradoException;
import com.alex.modelo.Aluno;
import com.alex.util.FabricaDeDAOs;
import corejava.Console;
import org.slf4j.Logger;
import java.util.List;

public class TestaAluno {

    
    
    public static void Testar(){
        String nome;
		String cpf;
		String email;
		Aluno umAluno;

		AlunoDAO alunoDAO = FabricaDeDAOs.getDAO(AlunoDAO.class);

		boolean continua = true;
		while (continua)
		{	System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um aluno");
			System.out.println("2. Alterar um aluno");
			System.out.println("3. Remover um aluno");
			System.out.println("4. Listar todos os alunos");
			System.out.println("5. Sair");
						
			int opcao = Console.readInt('\n' + 
							"Digite um número entre 1 e 5:");
					
			switch (opcao)
			{	case 1:
				{
					nome = Console.readLine('\n' + 
						"Informe o nome do aluno: ");
					cpf = Console.readLine(
						"Informe o cpf: ");
					email = Console.readLine(
						"Informe o email: ");
						
					umAluno = new Aluno(nome, cpf, email);
					
					alunoDAO.inclui(umAluno);
					
					System.out.println('\n' + "Aluno número " +
					    umAluno.getId() + " incluído com sucesso!");

					break;
				}

				case 2:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número do aluno que você deseja alterar: ");
										
					try
					{
						umAluno = alunoDAO.recuperaUmAluno(resposta);
					}
					catch(AlunoNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + umAluno.getId() +
						"    Nome = " + umAluno.getNome() +
						"    cpf = " + umAluno.getCpf() + 
                        "    email = " + umAluno.getEmail());
												
					System.out.println('\n' + "O que você deseja alterar?");
					System.out.println('\n' + "1. Nome");
					System.out.println("2. cpf");
					System.out.println("3. email");
					int opcaoAlteracao = Console.readInt('\n' + 
											"Digite um número de 1 a 3:");

					switch (opcaoAlteracao)
					{	case 1:
							String novoNome = Console.
								readLine("Digite o novo nome: ");

							umAluno.setNome(novoNome);

							try
							{
								alunoDAO.altera(umAluno);

								System.out.println('\n' +
									"Alteração de nome efetuada com sucesso!");
							}
							catch(AlunoNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}

							break;

						case 2:
							String novo_cpf = Console.
									readLine("Digite o novo cpf: ");

							umAluno.setCpf(novo_cpf);

							try
							{
								alunoDAO.altera(umAluno);

								System.out.println('\n' +
									"Alteração de cpf efetuada " +
									"com sucesso!");
							}
							catch(AlunoNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}

							break;

                        case 3:
                            String novo_email = Console.readLine("Digite o novo email: ");

                            umAluno.setEmail(novo_email);
                        
                            try
                            {
                                alunoDAO.altera(umAluno);
                                System.out.println("Alteração de email efetuada com sucesso");
                            }
                            catch(AlunoNaoEncontradoException e)
                            {   System.out.println('\n' + e.getMessage());
                            }
                        
                        
						default:
							System.out.println('\n' + "Opção inválida!");
					}

					break;
				}

				case 3:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número do aluno que você deseja remover: ");
									
					try
					{
						umAluno = alunoDAO.recuperaUmAluno(resposta);
					}
					catch(AlunoNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + umAluno.getId() +
						"    Nome = " + umAluno.getNome());
														
					String resp = Console.readLine('\n' + 
						"Confirma a remoção do aluno?");

					if(resp.equals("s"))
					{	try
						{
							alunoDAO.exclui (umAluno.getId());
							System.out.println('\n' + 
								"Aluno removido com sucesso!");
						}
						catch(AlunoNaoEncontradoException e)
						{	System.out.println('\n' + e.getMessage());
						}
					}
					else
					{	System.out.println('\n' + "Aluno não removido.");
					}
					
					break;
				}

				case 4:
				{
					List<Aluno> alunos = alunoDAO.recuperaAlunos();

//                  Utilizando um consumer. Consumer é uma interface funcional. Ela recebe um
//                  argumento e não retorna nada. Para que um valor seja aceito pelo Consumer
//                  deve ser executado o método accept.


//                  Utilizando method reference. Method references são expressões que possuem
//                  o mesmo tratamento de expressões lambda, mas em vez de prover um corpo  à
//                  expressão lambda, eles (os method references) referenciam um método existente
//                  pelo nome.

					for (Aluno aluno : alunos)
					{
						System.out.println('\n' +
							"Id = " + aluno.getId() +
							"  Nome = " + aluno.getNome() +
							"  Cpf = " + aluno.getCpf() +
							"  Email = " + aluno.getEmail() );
					}

					break;
				}

				case 5:
				{	continua = false;
					break;
				}

				default:
					System.out.println('\n' + "Opção inválida!");
			}
		}		
	}
   
}
