package com.works.services;

import com.works.entities.Note;
import com.works.repositories.NoteRepository;
import com.works.utils.ERest;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class NoteService {

    final NoteRepository nRepo;
    public NoteService(NoteRepository nRepo) {
        this.nRepo = nRepo;
    }


    public Map save(Note note) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        nRepo.save(note);
        hm.put(ERest.status, true);
        hm.put(ERest.result, note);
        return hm;
    }


    public Map list() {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        hm.put(ERest.result, nRepo.findAll() );
        return hm;
    }



}
