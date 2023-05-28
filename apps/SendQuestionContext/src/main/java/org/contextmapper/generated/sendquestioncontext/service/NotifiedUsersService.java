package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.NotifiedUsers;
import org.contextmapper.generated.sendquestioncontext.repository.NotifiedUsersRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedUsersDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.NotifiedUsersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NotifiedUsers}.
 */
@Service
@Transactional
public class NotifiedUsersService {

    private final Logger log = LoggerFactory.getLogger(NotifiedUsersService.class);

    private final NotifiedUsersRepository notifiedUsersRepository;

    private final NotifiedUsersMapper notifiedUsersMapper;

    public NotifiedUsersService(NotifiedUsersRepository notifiedUsersRepository, NotifiedUsersMapper notifiedUsersMapper) {
        this.notifiedUsersRepository = notifiedUsersRepository;
        this.notifiedUsersMapper = notifiedUsersMapper;
    }

    /**
     * Save a notifiedUsers.
     *
     * @param notifiedUsersDTO the entity to save.
     * @return the persisted entity.
     */
    public NotifiedUsersDTO save(NotifiedUsersDTO notifiedUsersDTO) {
        log.debug("Request to save NotifiedUsers : {}", notifiedUsersDTO);
        NotifiedUsers notifiedUsers = notifiedUsersMapper.toEntity(notifiedUsersDTO);
        notifiedUsers = notifiedUsersRepository.save(notifiedUsers);
        return notifiedUsersMapper.toDto(notifiedUsers);
    }

    /**
     * Update a notifiedUsers.
     *
     * @param notifiedUsersDTO the entity to save.
     * @return the persisted entity.
     */
    public NotifiedUsersDTO update(NotifiedUsersDTO notifiedUsersDTO) {
        log.debug("Request to update NotifiedUsers : {}", notifiedUsersDTO);
        NotifiedUsers notifiedUsers = notifiedUsersMapper.toEntity(notifiedUsersDTO);
        notifiedUsers = notifiedUsersRepository.save(notifiedUsers);
        return notifiedUsersMapper.toDto(notifiedUsers);
    }

    /**
     * Partially update a notifiedUsers.
     *
     * @param notifiedUsersDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NotifiedUsersDTO> partialUpdate(NotifiedUsersDTO notifiedUsersDTO) {
        log.debug("Request to partially update NotifiedUsers : {}", notifiedUsersDTO);

        return notifiedUsersRepository
            .findById(notifiedUsersDTO.getId())
            .map(existingNotifiedUsers -> {
                notifiedUsersMapper.partialUpdate(existingNotifiedUsers, notifiedUsersDTO);

                return existingNotifiedUsers;
            })
            .map(notifiedUsersRepository::save)
            .map(notifiedUsersMapper::toDto);
    }

    /**
     * Get all the notifiedUsers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NotifiedUsersDTO> findAll() {
        log.debug("Request to get all NotifiedUsers");
        return notifiedUsersRepository.findAll().stream().map(notifiedUsersMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one notifiedUsers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NotifiedUsersDTO> findOne(Long id) {
        log.debug("Request to get NotifiedUsers : {}", id);
        return notifiedUsersRepository.findById(id).map(notifiedUsersMapper::toDto);
    }

    /**
     * Delete the notifiedUsers by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NotifiedUsers : {}", id);
        notifiedUsersRepository.deleteById(id);
    }
}
