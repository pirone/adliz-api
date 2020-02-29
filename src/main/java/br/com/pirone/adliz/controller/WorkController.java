package br.com.pirone.adliz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.pirone.adliz.exception.ResourceNotFoundException;
import br.com.pirone.adliz.model.Work;
import br.com.pirone.adliz.repository.WorkRepository;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class WorkController {

    @Autowired
    WorkRepository workRepository;

    @GetMapping("/work")
    public List<Work> getAllWorks() {
        return workRepository.findAll();
    }

    @PostMapping("/work")
    public Work createWork(@Valid @RequestBody Work work) {
        return workRepository.save(work);
    }

    @GetMapping("/work/{id}")
    public Work getWorkById(@PathVariable(value = "id") Long workId) {
        return workRepository.findById(workId)
                .orElseThrow(() -> new ResourceNotFoundException("Work", "id", workId));
    }

    @PutMapping("/work/{id}")
    public Work updateWork(@PathVariable(value = "id") Long workId,
                                           @Valid @RequestBody Work workDetails) {

        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new ResourceNotFoundException("Work", "id", workId));

//        note.setTitle(noteDetails.getTitle());
//        note.setContent(noteDetails.getContent());

        Work updatedWork = workRepository.save(work);
        return updatedWork;
    }

    @DeleteMapping("/work/{id}")
    public ResponseEntity<?> deleteWork(@PathVariable(value = "id") Long workId) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new ResourceNotFoundException("Work", "id", workId));

        workRepository.delete(work);

        return ResponseEntity.ok().build();
    }
}
