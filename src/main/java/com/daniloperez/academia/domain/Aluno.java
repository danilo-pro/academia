package com.daniloperez.academia.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import com.daniloperez.academia.domain.enums.BioTipo;
import com.daniloperez.academia.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Aluno extends Usuario{
	private static final long serialVersionUID = 1L;

	private double peso;
	private double altura;
	private String cpf;
	@Enumerated(EnumType.STRING)
	private BioTipo biotipo;
	private double imc;
	
	@JsonIgnore
	@OneToMany(mappedBy = "aluno", cascade=CascadeType.ALL)// cascade=CascadeType.ALL serve para indicar que se for apagar o aluno do banco deve ser apagado as avaliações dele também
	private List<AvaliacaoAluno> avaliacoes = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "aluno", cascade=CascadeType.ALL)// cascade=CascadeType.ALL serve para indicar que se for apagar o aluno do banco deve ser apagado os scripts treinos dele também)
	private List<ScriptTreino> scripts = new ArrayList<>();
	
	
	public Aluno() {
		
	}
	
	public Aluno(Integer integer, Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11) {
		
	}

	public Aluno(Integer id,BioTipo biotipo, String nome, String email, String cpf, Date data_nasc, Date data_cad, char sexo, double peso, double altura, double imc, String senha) {
		super(id, nome, email, data_nasc, data_cad, sexo, senha);
		this.biotipo = (biotipo==null) ? null : biotipo;
		this.peso = peso;
		this.altura = altura;
		this.imc = imc;
		this.cpf = cpf;
		addPerfil(Perfil.ALUNO);
	}

	public double getPeso() {

		return peso;
	}

	public void setPeso(double peso) {

		this.peso = peso;
	}

	public double getAltura() {

		return altura;
	}

	public void setAltura(double altura) {

		this.altura = altura;
	}

	public BioTipo getBiotipo() {

		return biotipo;
	}

	public void setBiotipo(BioTipo biotipo) {
		this.biotipo = biotipo;
	}

	public double getImc() {
		return imc;
	}

	public void setImc(double imc) {
		this.imc = imc;
	}

	public List<AvaliacaoAluno> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<AvaliacaoAluno> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}


	public List<ScriptTreino> getScripts() {
		return scripts;
	}

	public void setScripts(List<ScriptTreino> scripts) {
		this.scripts = scripts;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Aluno [peso=" + peso + ", altura=" + altura + ", cpf=" + cpf + ", biotipo=" + biotipo + ", imc=" + imc
				+ ", avaliacoes=" + avaliacoes + ", scripts=" + scripts + "]";
	}
	
	
	
}
