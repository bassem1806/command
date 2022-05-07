package com.sip.ams.controllers;


import com.google.gson.Gson;
import com.sip.ams.entities.*;
import com.sip.ams.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/command/")
public class CommandController {

    private final CommandRepository commandRepository;
    private final SousdirectionRepository sousdirectionRepository;
    private final DirectionRepository directionRepository;
    private final DirectiongRepository directiongRepository;
    private final StockRepository stockRepository;
    private final ProviderRepository providerRepository;
    private final ArticleRepository articleRepository;


    @Autowired
    public CommandController(      CommandRepository commandRepository,
                                   SousdirectionRepository sousdirectionRepository,
                                   DirectionRepository directionRepository,
                                   DirectiongRepository directiongRepository,
                                   StockRepository stockRepository,
                                   ProviderRepository providerRepository,
                                   ArticleRepository articleRepository) {

        this.commandRepository =commandRepository;
        this.sousdirectionRepository = sousdirectionRepository;
        this.directionRepository = directionRepository ;
        this.directiongRepository = directiongRepository;
        this.stockRepository = stockRepository;
        this.providerRepository = providerRepository;
        this.articleRepository =articleRepository;


    }

    @GetMapping("list")
    //@ResponseBody
    public String listCommands(Model model) {

        List<Command> lp = (List<Command>)commandRepository.findAll();

        if(lp.size()==0)
            lp = null;
        model.addAttribute("commands", lp);

        return "command/listcommands";

    }

    @GetMapping("add")
    public String showAddCommandForm(Command command, Model model) {



        model.addAttribute("directiongs", directiongRepository.findAll());
        model.addAttribute("directions", directionRepository.findAll());
        model.addAttribute("sousdirections", sousdirectionRepository.findAll());
        model.addAttribute("articles", articleRepository.findAll());
        model.addAttribute("providers", providerRepository.findAll());
        model.addAttribute("stocks", stockRepository.findAll());
        model.addAttribute("command", new Command());

        return "command/addCommand";

    }

    @PostMapping("addSave")

    public String addSousdirection(@Valid Command command, BindingResult result,

                                   @RequestParam(name = "directionId", required = true) Long d,
                                   @RequestParam(name = "directiongId", required = true) Long a,
                                   @RequestParam(name = "sousdirectionId", required = true) Long b,
                                   @RequestParam(name = "providerId", required = true) Long p,
                                   @RequestParam(name = "articleId", required = true) Long z,
                                   @RequestParam(name = "stockId", required = true) Long s)


    {

        Direction direction= directionRepository.findById(d).orElseThrow(()-> new IllegalArgumentException
                ("Invalid direction Id:" +d));
        command.setDirection(direction);

        Directiong directiong = directiongRepository.findById(a).orElseThrow(()-> new IllegalArgumentException
                ("Invalid directiong Id:" +a));
        // System.out.println("libille artile" +article.getLabel());
        command.setDirectiong(directiong);

        Sousdirection sousdirection= sousdirectionRepository.findById(b).orElseThrow(()-> new IllegalArgumentException
                ("Invalid sous direction Id:" +b));
        command.setSousdirection(sousdirection);

        Provider provider= providerRepository.findById(p).orElseThrow(()-> new IllegalArgumentException
                ("Invalid provider Id:" +p));
        command.setProvider(provider);

        Article article= articleRepository.findById(z).orElseThrow(()-> new IllegalArgumentException
                ("Invalid stock Id:" +s));
        command.setArticle(article);


        Stock stock= stockRepository.findById(s).orElseThrow(()-> new IllegalArgumentException
                ("Invalid stock Id:" +s));
        command.setStock(stock);


        commandRepository.save(command);

        return "redirect:list";

    }





    @ResponseBody
    @RequestMapping(value = "loadDirectionByDirectiong/{id}", method = RequestMethod.GET)
    public String loadStatesByCountry(@PathVariable("id") long id) {

        System.out.println("init loadStatesByCountry");
        System.out.println("l id de la direction générale st ="+id);

        //	System.out.println("la taille de la liste est egale ="+directionRepository.findByDirectiong(directiongRepository.findById(id).get()));

        List <Direction> directionByDg =directionRepository.findByDirectiong(directiongRepository.findById(id).get());
        System.out.println("la taille de la liste est egale ="+directionByDg.size());

        Gson gson = new Gson();
        //  GsonBuilder gsonBuilder = new GsonBuilder();
        //    Gson gson = gsonBuilder.registerTypeAdapter(Direction.class, new MessageAdapter()).create();
        return gson.toJson(directionRepository.findByDirectiong(directiongRepository.findById(id).get()));

    }


}
