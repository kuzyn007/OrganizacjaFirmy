package pl.firma.dao;

import java.util.List;

import pl.firma.model.Tree;

public interface TreeDAO {
	
	public void addTree(Tree tree);
	public void updateTree(Tree tree);
	public Tree getTree(int id);
	public void deleteTree(int id);
	public List<Tree> getTrees();

}
