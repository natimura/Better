package Group.Better.service;

import Group.Better.entity.Choice;
import Group.Better.repository.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ChoiceService {

    @Autowired
    private ChoiceRepository choiceRepository;

    public void save(Choice choice){
       choiceRepository.save(choice);
    }

    public Choice getById(@PathVariable("id") String id) {
        return choiceRepository.findById(id).orElseThrow();
    }

    @Transactional
    public String vote(String choiceId) {
        Choice choice = choiceRepository.findById(choiceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid choice Id:" + choiceId));
        choice.setVoteCount(choice.getVoteCount() + 1);
        choiceRepository.save(choice);
        return choice.getPost().getId();
    }
}
