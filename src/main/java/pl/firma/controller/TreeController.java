package pl.firma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.firma.model.Tree;
import pl.firma.service.TreeService;

@Controller
@RequestMapping(value="/tree")
public class TreeController {
	
	@Autowired
	private TreeService treeService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addTreePage() {
		ModelAndView modelAndView = new ModelAndView("add-tree-form");
		List<Tree> trees = treeService.getTrees();
		modelAndView.addObject("trees", trees);
		modelAndView.addObject("tree", new Tree());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingTree(@ModelAttribute Tree tree) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		int idDoSprawdzenia = tree.getNumberid();
		Tree treeParent = treeService.getTree(idDoSprawdzenia);
		if (treeParent == null) {
			String message = "Podany ID rodzica nie istnieje.";
			modelAndView.addObject("message", message);
			
			return modelAndView;
		}
		tree.setLevel(treeParent.getLevel() + 1);
		treeService.addTree(tree);
		
		String message = "Dzia³ zosta³ poprawnie dodany.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/list")
	public ModelAndView listOfTrees() {
		ModelAndView modelAndView = new ModelAndView("list-of-trees");
		
		List<Tree> trees = treeService.getTrees();
		modelAndView.addObject("trees", trees);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editTreePage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-tree-form");
		//List<Tree> trees = treeService.getTrees();
		Tree tree = treeService.getTree(id);
		modelAndView.addObject("tree",tree);
		//modelAndView.addObject("trees", trees);
		
		
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingTree(@ModelAttribute Tree tree, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		treeService.updateTree(tree);
		
		String message = "Dzia³ zosta³ poprawnie zedytowany.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteTree(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		Tree treeToDelete = treeService.getTree(id);
		if (treeToDelete == null) {
			String message = "Dzia³ o id " + id + " nie istnieje";
			modelAndView.addObject("message", message);
			return modelAndView;
		}
		if (treeService.checkIfHaveChildren(id)) {
			String message = "Dzial " + treeToDelete.getName() + " nie mo¿e zostaæ usuniêty, poniewa¿ ma poddzia³y.";
			modelAndView.addObject("message", message);
			return modelAndView;
		}
		treeService.deleteTree(id);
		String message = "Dzia³ zosta³ poprawnie usuniêty.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}