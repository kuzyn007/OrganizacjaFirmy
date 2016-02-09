package pl.firma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.firma.model.Tree;
import pl.firma.service.TreeService;

@Controller
@RequestMapping(value="/tree")
public class TreeController {
	
	@Autowired
	private TreeService treeService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addTreePage(Model model) {
		List<Tree> trees = treeService.getTrees();
		model.addAttribute("trees", trees);
		model.addAttribute("tree", new Tree());
		return "jsonTemplate";
	//public ModelAndView addTreePage() {
		//ModelAndView modelAndView = new ModelAndView("add-tree-form");
		//List<Tree> trees = treeService.getTrees();
		//modelAndView.addObject("trees", trees);
		//modelAndView.addObject("tree", new Tree());
		//return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST, headers = {"Content-type=application/json"})
	public String addingTree(@RequestBody Tree tree, Model model) {
		if (tree == null || tree.getId() != null || tree.getLevel() != null || tree.getName()== null || tree.getNumberid() == null )
        {
            
            String message = "Wrong JSON.";
            model.addAttribute("message", message);
            return "jsonTemplate";
        }
		int idDoSprawdzenia = tree.getNumberid();
		Tree treeParent = treeService.getTree(idDoSprawdzenia);
		if (treeParent == null) {
			String message = "Podany ID rodzica nie istnieje.";
			model.addAttribute("message", message);
			
			return "jsonTemplate";
		}
		tree.setLevel(treeParent.getLevel() + 1);
		treeService.addTree(tree);
		
		String message = "Dzia³ zosta³ poprawnie dodany.";
		model.addAttribute("message", message);
		
		return "jsonTemplate";
	}
	
	@RequestMapping(value="/list")
	public String listOfTrees(Model model) {	
		model.addAttribute("WszystkieDzialy:", treeService.getTrees());
		return "jsonTemplate";
	//public ModelAndView listOfTrees() {
		//ModelAndView modelAndView = new ModelAndView("list-of-trees");
		//List<Tree> trees = treeService.getTrees();
		//modelAndView.addObject("trees", trees);
		//return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editTreePage(@PathVariable Integer id, Model model) {
		Tree tree = treeService.getTree(id);
		model.addAttribute("tree",tree);		
		return "jsonTemplate";
	//public ModelAndView editTreePage(@PathVariable Integer id) {
		//ModelAndView modelAndView = new ModelAndView("edit-tree-form");
		//Tree tree = treeService.getTree(id);
		//modelAndView.addObject("tree",tree);		
		//return modelAndView;
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST, headers = {"Content-type=application/json"})
    public String edditingTree(@RequestBody Tree tree, @PathVariable Integer id, Model model) {
		if (tree == null || tree.getId() == null || tree.getLevel() == null || tree.getName()== null || tree.getNumberid() == null )
        {
            String message = "Wrong JSON.";
            model.addAttribute("message", message);
            return "jsonTemplate";
        }
		treeService.updateTree(tree);
        String message = "Dzia³ zosta³ poprawnie zedytowany.";
        model.addAttribute("message", message);
        return "jsonTemplate";
    }
	/*@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingTree(@ModelAttribute Tree tree, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		treeService.updateTree(tree);
		
		String message = "Dzia³ zosta³ poprawnie zedytowany.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}*/
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteTree(@PathVariable Integer id, Model model) {
	//public ModelAndView deleteTree(@PathVariable Integer id) {
		//ModelAndView modelAndView = new ModelAndView("home");
		Tree treeToDelete = treeService.getTree(id);
		if (treeToDelete == null) {
			String message = "Dzia³ o id " + id + " nie istnieje";
			model.addAttribute("message", message);
			return "jsonTemplate";
			//modelAndView.addObject("message", message);
			//return modelAndView;
		}
		if (treeService.checkIfHaveChildren(id)) {
			String message = "Dzial " + treeToDelete.getName() + " nie mo¿e zostaæ usuniêty, poniewa¿ ma poddzia³y.";
			model.addAttribute("message", message);
			return "jsonTemplate";
			//modelAndView.addObject("message", message);
			//return modelAndView;
		}
		treeService.deleteTree(id);
		String message = "Dzia³ zosta³ poprawnie usuniêty.";
		model.addAttribute("message", message);
		return "jsonTemplate";
		//modelAndView.addObject("message", message);
		//return modelAndView;
	}
}