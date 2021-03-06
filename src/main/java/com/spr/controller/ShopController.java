package com.spr.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spr.exception.ShopNotFound;
import com.spr.model.Shop;
import com.spr.service.ShopService;

@Controller
@RequestMapping(value="/shop")
public class ShopController {
	
	private static Logger LOGGER = Logger.getLogger(ShopController.class);
	
	
	@Autowired
	private ShopService shopService;

	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView newShopPage() {
		
		ModelAndView mav = new ModelAndView("shop-new", "shop", new Shop());
		
		LOGGER.info("/create, GET");
		return mav;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createNewShop(@ModelAttribute Shop shop, 
			final RedirectAttributes redirectAttributes) {
		
		ModelAndView mav = new ModelAndView();
		String message = "New shop "+shop.getName()+" was successfully created.";
		
		shopService.create(shop);
		mav.setViewName("redirect:/index.html");
				
		redirectAttributes.addFlashAttribute("message", message);
		
		LOGGER.info("/create, POST");
		return mav;		
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView shopListPage() {
		
		ModelAndView mav = new ModelAndView("shop-list");
		List<Shop> shopList = shopService.findAll();
		mav.addObject("shopList", shopList);
		
		LOGGER.info("/list, GET");
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editShopPage(@PathVariable Integer id) {
		
		ModelAndView mav = new ModelAndView("shop-edit");
		Shop shop = shopService.findById(id);
		mav.addObject("shop", shop);
		
		LOGGER.info("/edit/{id}, GET");
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView editShop(@ModelAttribute Shop shop,
			@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws ShopNotFound {
		
		ModelAndView mav = new ModelAndView("redirect:/index.html");
		String message = "Shop was successfully updated.";

		shopService.update(shop);
		
		redirectAttributes.addFlashAttribute("message", message);	
		
		LOGGER.info("/edit/{id}, POST");
		return mav;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteShop(@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws ShopNotFound {
		
		ModelAndView mav = new ModelAndView("redirect:/index.html");		
		
		Shop shop = shopService.delete(id);
		String message = "The shop "+shop.getName()+" was successfully deleted.";
		
		redirectAttributes.addFlashAttribute("message", message);
		
		LOGGER.info("/delete/{id}, GET");
		return mav;
	}
	
	@RequestMapping(value="/find", method = RequestMethod.POST)
    public ModelAndView findShop(@RequestParam("newfield") int id) {
		
		ModelAndView mav = new ModelAndView("shop-find");
		Shop shop = shopService.findById(id);
		mav.addObject("shop", shop);
		
		LOGGER.info("/find/{id}, GET");
		return mav;
    }
}
