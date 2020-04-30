package com.stevemu.giflib.controller;

import com.stevemu.giflib.data.CategoryRepository;
import com.stevemu.giflib.data.GifRepository;
import com.stevemu.giflib.model.Category;
import com.stevemu.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GifRepository gifRepository;

    @GetMapping("/categories")
    public String listCategories(ModelMap modelMap) {
        List<Category> categories = categoryRepository.getAllCategories();
        modelMap.put("categories", categories);
        return "categories";
    }

    @GetMapping("/categories/{id}")
    public String category(@PathVariable int id, ModelMap modelMap) {
        Category category = categoryRepository.findById(id);
        modelMap.put("category", category);

        List<Gif> gifs = gifRepository.findByCategoryId(id);
        modelMap.put("gifs", gifs);

        return "category";
    }


}
