package br.edu.utfpr.pb.controleveiculo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.utfpr.pb.controleveiculo.model.Despesas;
import br.edu.utfpr.pb.controleveiculo.model.GraficoDesp;

public interface DespesasRepository extends JpaRepository<Despesas, Long>{

	@Query("select new br.edu.utfpr.pb.controleveiculo.model.GraficoDesp( d.tipo.descricao, sum(d.valor), sum(d.valor) ) "
			+ "from Despesas d where id_veiculo = ? group by d.tipo.descricao ")
	public List<GraficoDesp> findAllByTipo(Long id);
	
	
	 
	@Query(value = "select * from despesas where id_abastecimento = ?", nativeQuery = true)	
	public Despesas findByAbastecimento(Long id);
	
	@Query(value = "select * from Despesas where id_multas = ?", nativeQuery = true)
	public Despesas findByMulta(Long id);
	
	@Query(value = "select sum(valor) from despesas where id_veiculo = ?", nativeQuery = true)
	public Double findValorTotal(Long id);
	
	@Query(value = "select * from despesas where despesas.id_usuario = ?", nativeQuery = true)
	public List<Despesas> findByUsuario(long id);
}
