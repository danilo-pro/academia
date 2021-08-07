package com.daniloperez.academia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.domain.Cidade;
import com.daniloperez.academia.domain.Endereco;
import com.daniloperez.academia.dto.AlunoNewDTO;
import com.daniloperez.academia.repositories.AlunoRepository;
import com.daniloperez.academia.repositories.EnderecoRepository;
import com.daniloperez.academia.services.exceptions.DataIntegrityException;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

//@Service é a anotação para indicar que esse é um service
@Service
public class AlunoService {
	@Autowired
	private AlunoRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	//Buscar Aluno por ID
	public Aluno find(Integer id) {
		Optional<Aluno> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()));
	}
	
	//Buscar todos Alunos
	public List<Aluno> findAll(){
		return repo.findAll();
	}
	
	//Incluir aluno
	@Transactional
	public Aluno insert(Aluno obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	//Incluir aluno por DTO
	public Aluno fromDTO(AlunoNewDTO objDto) {
		Aluno al1 = new Aluno(null, objDto.getBiotipo(), objDto.getNome(), objDto.getEmail(), objDto.getCpf(), objDto.getData_nasc(), objDto.getData_cad(), objDto.getSexo(), objDto.getPeso(), objDto.getAltura(), objDto.getImc());
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), al1, cid);
		al1.getEnderecos().add(end);
		al1.getTelefones().add(objDto.getTelefone1());
		
		if(objDto.getTelefone2()!=null) {
			al1.getTelefones().add(objDto.getTelefone2());
		}
		
		if(objDto.getTelefone3()!=null) {
			al1.getTelefones().add(objDto.getTelefone3());
		}
		
		return al1;
	}
	
	
	
	//Excluir Aluno
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
}