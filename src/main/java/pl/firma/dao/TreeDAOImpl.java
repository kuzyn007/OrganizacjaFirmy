package pl.firma.dao;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.firma.model.Tree;

//Helper class to sort Tree objects by name
class TreeNameComparator implements Comparator<Tree> {
    @Override
    public int compare(Tree o1, Tree o2) {
    	if (o1.getNumberid() != o2.getNumberid()) {
    		return o1.getNumberid().compareTo(o2.getNumberid());
    	}
    	else {
    		return o2.getName().compareTo(o1.getName());
    	}
    }
}

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
		treeToUpdate.setName(tree.getName());
		treeToUpdate.setNumberid(tree.getNumberid());
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
	
	public boolean checkIfHaveChildren(int id)
	{
		boolean isListEmpty = getCurrentSession().createQuery("from Tree where numberid=" + id).list().isEmpty();
		return !isListEmpty;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tree> getTrees() {
		List<Tree> lista = getCurrentSession().createQuery("from Tree").list();
		Collections.sort(lista, new TreeNameComparator());
		
		
		List<Tree> helpList = new LinkedList<Tree>();
		
		for(int i=0; i<lista.size(); i++) {
			if(lista.get(i).getNumberid() == 0)
			{
				helpList.add(lista.get(i));
				continue;
			}
			
			int parentNumberId = lista.get(i).getNumberid();
			for (int j = 0 ; j < helpList.size(); j++)
			{
				if (helpList.get(j).getId() == parentNumberId)
				{
					helpList.add(j + 1, lista.get(i));
					break;
				}
			}
		}
	
		
		
		return helpList;
	}
}