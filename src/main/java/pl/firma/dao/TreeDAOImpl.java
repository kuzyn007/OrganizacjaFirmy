package pl.firma.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.firma.model.Tree;

@Repository
public class TreeDAOImpl implements TreeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addTree(Tree tree) {
		getCurrentSession().save(tree);
	}

	public void updateTree(Tree tree) {
		Tree treeToUpdate = getTree(tree.getId());
		treeToUpdate.setNazwa(tree.getNazwa());
		treeToUpdate.setNumerid(tree.getNumerid());
		getCurrentSession().update(treeToUpdate);
		
	}

	public Tree getTree(int id) {
		Tree tree = (Tree) getCurrentSession().get(Tree.class, id);
		return tree;
	}

	public void deleteTree(int id) {
		Tree tree = getTree(id);
		if (tree != null)
			getCurrentSession().delete(tree);
	}

	@SuppressWarnings("unchecked")
	public List<Tree> getTrees() {
		return getCurrentSession().createQuery("from Tree").list();
	}

}