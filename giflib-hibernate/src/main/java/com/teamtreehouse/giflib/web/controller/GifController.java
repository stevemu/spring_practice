package com.teamtreehouse.giflib.web.controller;

import com.teamtreehouse.giflib.model.Gif;
import com.teamtreehouse.giflib.service.CategoryService;
import com.teamtreehouse.giflib.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GifController {

    @Autowired
    private GifService gifService;

    @Autowired
    private CategoryService categoryService;

    // Home page - index of all GIFs
    @RequestMapping("/")
    public String listGifs(Model model) {
        // TODO: Get all gifs
        List<Gif> gifs = gifService.findAll();

        model.addAttribute("gifs", gifs);
        return "gif/index";
    }

    // Single GIF page
    @RequestMapping("/gifs/{gifId}")
    public String gifDetails(@PathVariable Long gifId, Model model) {
        // TODO: Get gif whose id is gifId
        Gif gif = gifService.findById(gifId);

        model.addAttribute("gif", gif);
        return "gif/details";
    }

    // GIF image data
    @RequestMapping("/gifs/{gifId}.gif")
    @ResponseBody
    public byte[] gifImage(@PathVariable Long gifId) {
        // TODO: Return image data as byte array of the GIF whose id is gifId
        return gifService.findById(gifId).getBytes();
    }

    // Favorites - index of all GIFs marked favorite
    @RequestMapping("/favorites")
    public String favorites(Model model) {
        // TODO: Get list of all GIFs marked as favorite
        List<Gif> faves = new ArrayList<>();

        model.addAttribute("gifs",faves);
        model.addAttribute("username","Chris Ramacciotti"); // Static username
        return "gif/favorites";
    }

    // Upload a new GIF
    @RequestMapping(value = "/gifs", method = RequestMethod.POST)
//    @ResponseBody
    public String addGif(@Valid Gif gif, BindingResult result, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.gif", result);
            redirectAttributes.addFlashAttribute("gif", gif);
            return String.format("redirect:/upload");
        }

        gifService.save(gif, file);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("GIF successfully uploaded", FlashMessage.Status.SUCCESS));

        System.out.println(gif);

        return String.format("redirect:/gifs/%s", gif.getId());
    }

    // Form for uploading a new GIF
    @RequestMapping("/upload")
    public String formNewGif(Model model) {
        if (!model.containsAttribute("gif")) {
            model.addAttribute("gif", new Gif());
        }

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("heading", "Upload a Gif");
        model.addAttribute("submit", "Upload");
        model.addAttribute("action", String.format("/gifs"));

        return "gif/form";
    }

    // Form for editing an existing GIF
    @RequestMapping(value = "/gifs/{gifId}/edit")
    public String formEditGif(@PathVariable Long gifId, Model model) {
        model.addAttribute("gif", gifService.findById(gifId));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("heading", "Edit a Gif");
        model.addAttribute("submit", "Update");
        model.addAttribute("action", String.format("/gifs/%s", gifId));

        return "gif/form";
    }

    // Update an existing GIF
    @RequestMapping(value = "/gifs/{gifId}", method = RequestMethod.POST)
    public String updateGif(@Valid Gif gif, BindingResult result, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.gif", result);
            redirectAttributes.addFlashAttribute("gif", gif);
            return String.format("redirect:/gifs/%s/edit", gif.getId());
        }

        gifService.save(gif, file);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("GIF successfully updated", FlashMessage.Status.SUCCESS));
        return String.format("redirect:/gifs/%s", gif.getId());
    }

    // Delete an existing GIF
    @RequestMapping(value = "/gifs/{gifId}/delete", method = RequestMethod.POST)
    public String deleteGif(@PathVariable Long gifId, RedirectAttributes redirectAttributes) {
        Gif gif = gifService.findById(gifId);
        gifService.delete(gif);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("GIF successfully deleted", FlashMessage.Status.SUCCESS));

        return String.format("redirect:/");
    }

    // Mark/unmark an existing GIF as a favorite
    @RequestMapping(value = "/gifs/{gifId}/favorite", method = RequestMethod.POST)
    public String toggleFavorite(@PathVariable Long gifId, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        // TODO: With GIF whose id is gifId, toggle the favorite field
        Gif gif = gifService.findById(gifId);
        gifService.toggleFavorite(gif);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("GIF successfully marked as favorite", FlashMessage.Status.SUCCESS));
        return String.format("redirect:%s", request.getHeader("referer");
    }

    // Search results
    @RequestMapping("/search")
    public String searchResults(@RequestParam String q, Model model) {
        // TODO: Get list of GIFs whose description contains value specified by q
        List<Gif> gifs = new ArrayList<>();

        model.addAttribute("gifs",gifs);
        return "gif/index";
    }
}