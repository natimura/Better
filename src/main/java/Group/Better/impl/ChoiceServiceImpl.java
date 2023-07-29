package Group.Better.impl;

import Group.Better.entity.Choice;
import Group.Better.repository.ChoiceRepository;
import Group.Better.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ChoiceServiceImpl implements ChoiceService {

    private ChoiceRepository choiceRepository;

    @Autowired
    public ChoiceServiceImpl(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
    }

    @Override
    public void save(Choice choice){
       choiceRepository.save(choice);
    }

    @Override
    public Choice getById(@PathVariable("id") Long id) {
        return choiceRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Long vote(Long choiceId) {
        Choice choice = choiceRepository.findById(choiceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid choice Id:" + choiceId));
        choice.setVoteCount(choice.getVoteCount() + 1);
        choiceRepository.save(choice);
        return choice.getPost().getId();
    }

    @Override
    public void delete(Choice choice){
        choiceRepository.delete(choice);
    }
}
