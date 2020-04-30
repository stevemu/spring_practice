package com.stevemu.giflib.controller;

import com.stevemu.giflib.data.GifRepository;
import com.stevemu.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GifController {

    @Autowired
    private GifRepository gifRepository;

    @GetMapping("/")
    public String listGifs(ModelMap modelMap) {
        List<Gif> allGifs = gifRepository.getAllGifs();
        modelMap.put("gifs", allGifs);
        return "home";
    }

    @GetMapping("/gif/{name}")
    public String gifDetails(@PathVariable String name, ModelMap modelMap) {
        Gif gif = gifRepository.findByName(name);
        modelMap.put("gif", gif);
        return "gif-details";
    }



}
