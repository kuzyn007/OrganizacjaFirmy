package pl.firma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.firma.service.TreeService;

import pl.firma.dao.TreeDAO;
import pl.firma.model.Tree;

@Service
@Transactional
public class TreeServiceImpl implements TreeService {
	
	@Autowired
	private TreeDAO treeDAO;

	public void addTree(Tree tree) {
		treeDAO.addTree(tree);		
	}

	public void updateTree(Tree tree) {
		treeDAO.updateTree(tree);
	}

	public Tree getTree(int id) {
		return treeDAO.getTree(id);
	}

	public void deleteTree(int id) {
		treeDAO.deleteTree(id);
	}

	public List<Tree> getTrees() {
		return treeDAO.getTrees();
	}

}
