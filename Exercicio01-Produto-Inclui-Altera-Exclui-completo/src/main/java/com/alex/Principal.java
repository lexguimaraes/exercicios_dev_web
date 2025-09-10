package com.alex;

import corejava.Console;
import org.slf4j.Logger;


public class Principal
{	public static void main (String[] args) 
	{
//		Logger logger = LoggerFactory.getLogger(Principal.class);
//		logger.error("Mensagem de log emitida utilizando o LOG4J");
		// fatal - error - warning - info - debug
        System.out.println("Deseja testar o que \n 1. Aluno \n 2. Produto");
        int option = Console.readInt("");
        switch(option){
            case 1:
                TestaAluno.Testar();
                break;
            case 2:
                TestaProduto.Testar();
                break;
            default:
                break;
        }
	}
}
