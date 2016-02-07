package pl.firma.service;

import java.util.List;

import pl.firma.model.Tree;

public interface TreeService {
	
	public void addTree(Tree tree);
	public void updateTree(Tree tree);
	public Tree getTree(int id);
	public void deleteTree(int id);
	public List<Tree> getTrees();
	public boolean checkIfHaveChildren(int id);
}
