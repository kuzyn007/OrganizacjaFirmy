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
    	if (o1.getNumerid() != o2.getNumerid()) {
    		return o1.getNumerid().compareTo(o2.getNumerid());
    	}
    	else {
    		return o2.getNazwa().compareTo(o1.getNazwa());
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
	
	public boolean checkIfHaveChildren(int id)
	{
		boolean isListEmpty = getCurrentSession().createQuery("from Tree where numerid=" + id).list().isEmpty();
		return !isListEmpty;
	}
	
	//private final String QUERY = "SELECT t1.nazwa AS lev1, t2.nazwa as lev2, t3.nazwa as lev3, t4.nazwa as lev4 FROM trees AS t1 LEFT JOIN trees AS t2 ON t2.numerid = t1.id LEFT JOIN trees AS t3 ON t3.numerid = t2.id LEFT JOIN trees AS t4 ON t4.numerid = t3.id WHERE t1.nazwa = 'Firma' ORDER BY t1.nazwa,t2.nazwa,t3.nazwa,t4.nazwa";
	@SuppressWarnings("unchecked")
	public List<Tree> getTrees() {
		List<Tree> lista = getCurrentSession().createQuery("from Tree").list();
		Collections.sort(lista, new TreeNameComparator());
		
		
		List<Tree> listaPomocnicza = new LinkedList<Tree>();
		
		for(int i=0; i<lista.size(); i++) {
			if(lista.get(i).getNumerid() == 0)
			{
				listaPomocnicza.add(lista.get(i));
				continue;
			}
			
			int parentNumberId = lista.get(i).getNumerid();
			for (int j = 0 ; j < listaPomocnicza.size(); j++)
			{
				if (listaPomocnicza.get(j).getId() == parentNumberId)
				{
					listaPomocnicza.add(j + 1, lista.get(i));
					break;
				}
			}
		}
	
		
		
		return listaPomocnicza;
	}
}