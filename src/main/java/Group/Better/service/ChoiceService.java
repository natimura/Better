package Group.Better.service;

import Group.Better.entity.Choice;

public interface ChoiceService {

    void save(Choice choice);

    Choice getById(Long id);

    Long vote(Long choiceId);

    void delete(Choice choice);
}
