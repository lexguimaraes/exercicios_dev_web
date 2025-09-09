
package com.alex.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
// @Table(name="aluno")
public class Aluno
{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;

    // ********* Construtores *********

    public Aluno()
    {
    }

    public Aluno(String nome,
                   String cpf, String email
                   )
    {	this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    // ********* Métodos do Tipo Get *********

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    public Long getId()
    {	return id;
    }

    public String getNome()
    {	return nome;
    }

    public String getCpf()
    {	return cpf;
    }

    public String getEmail(){
        return email;
    }
    // ********* Métodos do Tipo Set *********

    private void setId(Long id)
    {	this.id = id;
    }

    public void setNome(String nome)
    {	this.nome = nome;
    }

    public void setCpf(String cpf)
    {	this.cpf = cpf;
    }

    public void setEmail(String email){
        this.email = email;
    }
}


