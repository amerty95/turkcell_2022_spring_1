package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.services.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/note")
public class NoteRestController {

    final NoteService noteService;
    public NoteRestController(NoteService noteService) {
        this.noteService = noteService;
    }


    // tek tek data alımı-> Form Data
    // @RequestParam
    /*
    @PostMapping("/save")
    public Map save(@RequestParam String title, @RequestParam(defaultValue = "") String detail) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("title", title);
        hm.put("detail", detail);
        return hm;
    }
     */

    // QueryString
    /*
    @GetMapping("/save")
    public Map save(@RequestParam String title, @RequestParam(defaultValue = "") String detail) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("title", title);
        hm.put("detail", detail);
        return hm;
    }
     */


    // PathVariable
    /*
    @GetMapping("/save/{title}/{detail}")
    public Map save(@PathVariable String title, @PathVariable String detail ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("title", title);
        hm.put("detail", detail);
        return hm;
    }
     */


    @PostMapping("/save")
    public Map save( @RequestBody Note note ) {
        return noteService.save(note);
    }

    @GetMapping("/list")
    public Map list() {
        return noteService.list();
    }

}
