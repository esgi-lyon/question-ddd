package org.contextmapper.generated.usermanagementcontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.usermanagementcontext.domain.RegisterCommand;
import org.contextmapper.generated.usermanagementcontext.repository.RegisterCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RegisterCommand}.
 */
@Service
@Transactional
public class RegisterCommandService {

    private final Logger log = LoggerFactory.getLogger(RegisterCommandService.class);

    private final RegisterCommandRepository registerCommandRepository;

    public RegisterCommandService(RegisterCommandRepository registerCommandRepository) {
        this.registerCommandRepository = registerCommandRepository;
    }

    /**
     * Save a registerCommand.
     *
     * @param registerCommand the entity to save.
     * @return the persisted entity.
     */
    public RegisterCommand save(RegisterCommand registerCommand) {
        log.debug("Request to save RegisterCommand : {}", registerCommand);
        return registerCommandRepository.save(registerCommand);
    }

    /**
     * Update a registerCommand.
     *
     * @param registerCommand the entity to save.
     * @return the persisted entity.
     */
    public RegisterCommand update(RegisterCommand registerCommand) {
        log.debug("Request to update RegisterCommand : {}", registerCommand);
        return registerCommandRepository.save(registerCommand);
    }

    /**
     * Partially update a registerCommand.
     *
     * @param registerCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RegisterCommand> partialUpdate(RegisterCommand registerCommand) {
        log.debug("Request to partially update RegisterCommand : {}", registerCommand);

        return registerCommandRepository
            .findById(registerCommand.getId())
            .map(existingRegisterCommand -> {
                if (registerCommand.getFirstname() != null) {
                    existingRegisterCommand.setFirstname(registerCommand.getFirstname());
                }
                if (registerCommand.getLastname() != null) {
                    existingRegisterCommand.setLastname(registerCommand.getLastname());
                }
                if (registerCommand.getMail() != null) {
                    existingRegisterCommand.setMail(registerCommand.getMail());
                }
                if (registerCommand.getPassword() != null) {
                    existingRegisterCommand.setPassword(registerCommand.getPassword());
                }
                if (registerCommand.getRole() != null) {
                    existingRegisterCommand.setRole(registerCommand.getRole());
                }

                return existingRegisterCommand;
            })
            .map(registerCommandRepository::save);
    }

    /**
     * Get all the registerCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RegisterCommand> findAll() {
        log.debug("Request to get all RegisterCommands");
        return registerCommandRepository.findAll();
    }

    /**
     * Get one registerCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RegisterCommand> findOne(Long id) {
        log.debug("Request to get RegisterCommand : {}", id);
        return registerCommandRepository.findById(id);
    }

    /**
     * Delete the registerCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RegisterCommand : {}", id);
        registerCommandRepository.deleteById(id);
    }
}
