package com.ecommerce.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.models.Account.User;
import com.ecommerce.models.Items.Category;
import com.ecommerce.models.Items.Item;
import com.ecommerce.models.Items.Order;
import com.ecommerce.models.Items.OrderItem;
import com.ecommerce.models.Items.SubCategory;
import com.ecommerce.repositories.Items.CategoryRepository;
import com.ecommerce.repositories.Items.ItemRepository;
import com.ecommerce.repositories.Items.OrderItemRepository;
import com.ecommerce.repositories.Items.OrderRepository;
import com.ecommerce.repositories.Items.SubCategoryRepository;
import com.ecommerce.repositories.user.UserRepository;
import com.ecommerce.service.admin.Utilities;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private Utilities utils;

	private String[] objects = new String[] { "Category", "Item", "Order", "OrderItem", "SubCategory" };

	@GetMapping(name = "admin_home", value = "/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("entities", objects);
		mv.addObject("title", "Home");
		mv.setViewName("adminHome");
		return mv;
	}

	@GetMapping(name = "admin_entity", value = "/{entity}")
	public ModelAndView entity(@PathVariable(name = "entity") String entity) {
		ModelAndView mv = new ModelAndView();
		if (entity.equals(objects[0]))
			mv.addObject("itr", categoryRepository.findAll().iterator());
		else if (entity.equals(objects[1]))
			mv.addObject("itr", itemRepository.findAll().iterator());
		else if (entity.equals(objects[2]))
			mv.addObject("itr", orderRepository.findAll().iterator());
		else if (entity.equals(objects[3]))
			mv.addObject("itr", orderItemRepository.findAll().iterator());
		else if (entity.equals(objects[4]))
			mv.addObject("itr", subCategoryRepository.findAll().iterator());
		else
			return mv;
		mv.addObject("title", entity);
		mv.setViewName("adminEntity");
		return mv;
	}

	@PostMapping(name = "admin_entity", value = "/{entity}")
	public ModelAndView entityDeleteSelect(@PathVariable(name = "entity") String entity, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		String[] ids = req.getParameterValues("_selected_action");
		if (entity.equals(objects[0]))
			for (String id : ids)
				categoryRepository.deleteById(Integer.parseInt(id));
		else if (entity.equals(objects[1]))
			for (String id : ids)
				itemRepository.deleteById(Integer.parseInt(id));
		else if (entity.equals(objects[2]))
			for (String id : ids)
				orderRepository.deleteById(Integer.parseInt(id));
		else if (entity.equals(objects[3]))
			for (String id : ids)
				orderItemRepository.deleteById(Integer.parseInt(id));
		else if (entity.equals(objects[4]))
			for (String id : ids)
				subCategoryRepository.deleteById(Integer.parseInt(id));
		else
			return mv;
		mv.setViewName("redirect:/admin/" + entity);
		return mv;
	}

	@GetMapping(name = "admin_entity", value = "/{entity}/add")
	public ModelAndView entityAddItem(@PathVariable(name = "entity") String entity) {
		ModelAndView mv = new ModelAndView();
		if (entity.equals(objects[0]))
			mv.addObject("dataTypes", utils.getTypes(Category.class.getDeclaredFields()));
		else if (entity.equals(objects[1])) {
			mv.addObject("dataTypes", utils.getTypes(Item.class.getDeclaredFields()));
			mv.addObject("subCategory", subCategoryRepository.findAll().iterator());
		} else if (entity.equals(objects[2])) {
			mv.addObject("dataTypes", utils.getTypes(Order.class.getDeclaredFields()));
			mv.addObject("user", userRepository.findAll().iterator());
		} else if (entity.equals(objects[3])) {
			mv.addObject("dataTypes", utils.getTypes(OrderItem.class.getDeclaredFields()));
			mv.addObject("item", itemRepository.findAll().iterator());
			mv.addObject("user", userRepository.findAll().iterator());
			mv.addObject("order", orderRepository.findAll().iterator());
		} else if (entity.equals(objects[4])) {
			mv.addObject("dataTypes", utils.getTypes(SubCategory.class.getDeclaredFields()));
			mv.addObject("category", categoryRepository.findAll().iterator());
		} else
			return mv;
		mv.addObject("title", "Add " + entity);
		mv.addObject("entity", entity);
		mv.setViewName("adminAddItem");
		return mv;
	}

	@PostMapping(name = "admin_entity", value = "/{entity}/add")
	public ModelAndView postAddItem(@PathVariable(name = "entity") String entity, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		if (entity.equals(objects[0])) {
			String name = req.getParameter("Category");
			Category category = new Category(name);
			categoryRepository.save(category);
			categoryRepository.save(category); // Don't remove this cause imageurl is named after it's Id value which
												// will be generated after the object is persisted.
			try {
				Part img = req.getPart("ImageUrl");
				utils.saveImage(img, category.getImageUrl());
			} catch (Exception e) {
				categoryRepository.delete(category);
				e.printStackTrace();
			}
		} else if (entity.equals(objects[1])) {
			String name = req.getParameter("Title");
			String desc = req.getParameter("Descrition");
			Double price = Double.parseDouble(req.getParameter("Price"));
			Double dPrice = Double.parseDouble(req.getParameter("DiscountPrice"));
			SubCategory subCategory = subCategoryRepository.findById(Integer.parseInt(req.getParameter("subCategory")))
					.get();
			Item item = new Item(name, desc, subCategory, price, dPrice);
			itemRepository.save(item);
			itemRepository.save(item);// Don't remove this cause imageurl is named after it's Id value which will be
										// generated after the object is persisted.
			try {
				Part img = req.getPart("ImageUrl");
				utils.saveImage(img, item.getImageUrl());
			} catch (Exception e) {
				itemRepository.delete(item);
				e.printStackTrace();
			}
		} else if (entity.equals(objects[3])) {
			Double dPrice = Double.parseDouble(req.getParameter("DiscountedPrice"));
			Double price = Double.parseDouble(req.getParameter("Price"));
			Integer quantity = new Double(req.getParameter("Quantity")).intValue();
			boolean ordered = !(req.getParameter("Ordered") == null);
			User user = userRepository.findById(Integer.parseInt(req.getParameter("user"))).get();
			Order order = orderRepository.findById(Integer.parseInt(req.getParameter("order"))).get();
			Item item = itemRepository.findById(Integer.parseInt(req.getParameter("item"))).get();
			OrderItem orderItem = new OrderItem(item, user, ordered, quantity, price, dPrice, order);
			orderItemRepository.save(orderItem);
		} else if (entity.equals(objects[2])) {
			User user = userRepository.findById(Integer.parseInt(req.getParameter("user"))).get();
			boolean ordered = !(req.getParameter("Ordered") == null);
			Double totalAmount = Double.parseDouble(req.getParameter("TotalAmount"));
			Double amountSaved = Double.parseDouble(req.getParameter("AmountSaved"));
			Order order = new Order(user, ordered, totalAmount, amountSaved);
			orderRepository.save(order);
		} else if (entity.equals(objects[4])) {
			String name = req.getParameter("SubCategory");
			Category category = categoryRepository.findById(Integer.parseInt(req.getParameter("category"))).get();
			SubCategory subCategory = new SubCategory(name, category);
			subCategoryRepository.save(subCategory);
		} else
			return mv;
		mv.setViewName("redirect:/admin/" + entity);
		return mv;
	}

	@GetMapping(name = "Change_Entity", value = "/{entity}/{id}/change")
	public ModelAndView changeEntity(@PathVariable(name = "entity") String entity, @PathVariable(name = "id") int id) {
		ModelAndView mv = new ModelAndView();
		if (entity.equals(objects[0])) {
			mv.addObject("dataTypes", utils.getTypes(Category.class.getDeclaredFields()));
			mv.addObject("dataValues", utils.getValues(categoryRepository.findById(id).get()));
		} else if (entity.equals(objects[1])) {
			mv.addObject("dataTypes", utils.getTypes(Item.class.getDeclaredFields()));
			mv.addObject("subCategory", subCategoryRepository.findAll().iterator());
			mv.addObject("dataValues", utils.getValues(itemRepository.findById(id).get()));
		} else if (entity.equals(objects[2])) {
			mv.addObject("dataTypes", utils.getTypes(Order.class.getDeclaredFields()));
			mv.addObject("user", userRepository.findAll().iterator());
			mv.addObject("dataValues", utils.getValues(orderRepository.findById(id).get()));
		} else if (entity.equals(objects[3])) {
			mv.addObject("dataTypes", utils.getTypes(OrderItem.class.getDeclaredFields()));
			mv.addObject("item", itemRepository.findAll().iterator());
			mv.addObject("user", userRepository.findAll().iterator());
			mv.addObject("order", orderRepository.findAll().iterator());
			mv.addObject("dataValues", utils.getValues(orderItemRepository.findById(id).get()));
		} else if (entity.equals(objects[4])) {
			mv.addObject("dataTypes", utils.getTypes(SubCategory.class.getDeclaredFields()));
			mv.addObject("category", categoryRepository.findAll().iterator());
			mv.addObject("dataValues", utils.getValues(subCategoryRepository.findById(id).get()));
		} else
			return mv;
		mv.addObject("entity", entity);
		mv.addObject("id", id);
		mv.addObject("title", "Change " + entity);
		mv.setViewName("adminChangeEntity");
		return mv;
	}

	@PostMapping(name = "admin_change_entity", value = "/{entity}/{id}/change")
	public ModelAndView postChangeItem(@PathVariable(name = "entity") String entity, @PathVariable(name = "id") int id,
			HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		if (entity.equals(objects[0])) {
			String name = req.getParameter("Category");
			Category category = categoryRepository.findById(id).get();
			category.setCategory(name);
			categoryRepository.save(category);
			try {
				Part img = req.getPart("ImageUrl");
				if (img != null)
					utils.saveImage(img, category.getImageUrl());
			} catch (Exception e) {
				categoryRepository.delete(category);
				e.printStackTrace();
			}
		} else if (entity.equals(objects[1])) {
			String name = req.getParameter("Title");
			String desc = req.getParameter("Descrition");
			Double price = Double.parseDouble(req.getParameter("Price"));
			Double dPrice = Double.parseDouble(req.getParameter("DiscountPrice"));
			SubCategory subCategory = subCategoryRepository.findById(Integer.parseInt(req.getParameter("subCategory")))
					.get();
			Item item = itemRepository.findById(id).get();
			item.setTitle(name);
			item.setDescrition(desc);
			item.setPrice(price);
			item.setDiscountPrice(dPrice);
			itemRepository.save(item);
			try {
				Part img = req.getPart("ImageUrl");
				if (img != null)
					utils.saveImage(img, item.getImageUrl());
			} catch (Exception e) {
				itemRepository.delete(item);
				e.printStackTrace();
			}
		} else if (entity.equals(objects[3])) {
			Double dPrice = Double.parseDouble(req.getParameter("DiscountedPrice"));
			Double price = Double.parseDouble(req.getParameter("Price"));
			Integer quantity = new Double(req.getParameter("Quantity")).intValue();
			boolean ordered = !(req.getParameter("Ordered") == null);
			User user = userRepository.findById(Integer.parseInt(req.getParameter("user"))).get();
			Order order = orderRepository.findById(Integer.parseInt(req.getParameter("order"))).get();
			Item item = itemRepository.findById(Integer.parseInt(req.getParameter("item"))).get();
			OrderItem orderItem = orderItemRepository.findById(id).get();
			orderItem.setDiscountedPrice(dPrice);
			orderItem.setItem(item);
			orderItem.setOrder(order);
			orderItem.setOrdered(ordered);
			orderItem.setPrice(price);
			orderItem.setQuantity(quantity);
			orderItem.setUser(user);
			orderItemRepository.save(orderItem);
		} else if (entity.equals(objects[2])) {
			User user = userRepository.findById(Integer.parseInt(req.getParameter("user"))).get();
			boolean ordered = !(req.getParameter("Ordered") == null);
			Double totalAmount = Double.parseDouble(req.getParameter("TotalAmount"));
			Double amountSaved = Double.parseDouble(req.getParameter("AmountSaved"));
			Order order = orderRepository.findById(id).get();
			order.setAmountSaved(amountSaved);
			order.setOrdered(ordered);
			order.setTotalAmount(totalAmount);
			order.setUser(user);
			orderRepository.save(order);
		} else if (entity.equals(objects[4])) {
			String name = req.getParameter("SubCategory");
			Category category = categoryRepository.findById(Integer.parseInt(req.getParameter("category"))).get();
			SubCategory subCategory = subCategoryRepository.findById(id).get();
			subCategory.setSubCategory(name);
			subCategory.setCategory(category);
			subCategoryRepository.save(subCategory);
		} else
			return mv;
		mv.setViewName("redirect:/admin/" + entity);
		return mv;
	}
	@PostMapping(name = "admin_delete_entity", value = "/{entity}/{id}/delete")
	public ModelAndView postDeleteItem(@PathVariable(name = "entity") String entity, @PathVariable(name = "id") int id) {
		
		ModelAndView mv = new ModelAndView();
		if (entity.equals(objects[0]))
			categoryRepository.deleteById(id);
		else if (entity.equals(objects[1]))
			itemRepository.deleteById(id);
		else if (entity.equals(objects[2]))
			orderRepository.deleteById(id);
		else if (entity.equals(objects[3]))
			orderItemRepository.deleteById(id);
		else if (entity.equals(objects[4]))
			subCategoryRepository.deleteById(id);
		else
			return mv;
		mv.setViewName("redirect:/admin/" + entity);
		return mv;
	}
}