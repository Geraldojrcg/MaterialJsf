package br.ufrn.imd.material.repositorios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrn.imd.material.dominio.Material;

@Stateless
public class MaterialRepositorio {
	
	@PersistenceContext
	private EntityManager em;
	
	public Material adicionar(Material material) {
		if(material.getId() == 0) {
			em.persist(material);
		} else {
			em.merge(material);
		}
		return material;
	}
	
	public void remover(Material material) {
		material = em.find(Material.class, material.getId());
		em.remove(material);
	}
	
	@SuppressWarnings("unchecked")
	public List<Material> listarMateriais(){
		return (List<Material>) em.createQuery("select m from Material m")
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Material> buscarMaterialPorUsuario(String login) {
		String jpaql = "select m from Material m where m.usuarioCadastro.login = :login";
		Query q = em.createQuery(jpaql);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Material> buscarMaterial(String codigo) {
		String jpaql = "select m from Material m where m.codigo = :codigo";
		Query q = em.createQuery(jpaql);
		return q.getResultList();
	}
}
