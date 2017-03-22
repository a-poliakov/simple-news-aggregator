package newsaggregator.controller;

import newsaggregator.dto.SiteDTO;
import newsaggregator.entity.Site;
import newsaggregator.service.ItemService;
import newsaggregator.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


@Controller
public class NewsController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private SiteService siteService;

    @ModelAttribute("site")
    public SiteDTO constructSite() {
        return new SiteDTO();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("sites", siteService.findAll());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String doAddSite(Model model,
                            @ModelAttribute("site") SiteDTO site, BindingResult result) {
        if (result.hasErrors()) {
            return index(model);
        }
        siteService.save(site);
        return "redirect:/";
    }

    @RequestMapping("/remove/{id}")
    public String removeSite(@PathVariable int id) {
        Site site = siteService.findOne(id);
        siteService.delete(site);
        return "redirect:/";
    }

    @RequestMapping(value = "/news-list", method = RequestMethod.GET)
    public String newsList(Model model,
                           @RequestParam(defaultValue="1", value="page", required=false) Integer page,
                           @RequestParam(defaultValue="20", value="size", required=false) Integer size,
                           @RequestParam(defaultValue="", value="search", required=false) String search){
        byte[] bytes = search.getBytes(StandardCharsets.ISO_8859_1);
        search = new String(bytes, Charset.forName("UTF-8"));
        model.addAttribute("search", search);
        if(search == null || search.equals("")) {
            model.addAttribute("items", this.itemService.getItems(page - 1, size));
            model.addAttribute("currentPage", page);
            float nrOfPages = (float) this.itemService.countMembers() / size;
            int maxPages = (int) (((nrOfPages > (int) nrOfPages) || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages);
            model.addAttribute("maxPages", maxPages);
        } else {
            model.addAttribute("items", this.itemService.search(search,  size, page - 1));
            model.addAttribute("currentPage", page);
            float nrOfPages = (float) this.itemService.countBySearchPattern(search) / size;
            int maxPages = (int) (((nrOfPages > (int) nrOfPages) || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages);
            model.addAttribute("maxPages", maxPages);
        }
        return "news-list";
    }

    @RequestMapping(value = "/news-list", method = RequestMethod.POST)
    public String searchNews(Model model,
                           @ModelAttribute String search){
        return "redirect:/news-list?search=" + search;
    }
}
