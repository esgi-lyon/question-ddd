package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.PointAwardRule;
import org.contextmapper.generated.evaluationcontext.repository.PointAwardRuleRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.PointAwardRuleDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.PointAwardRuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PointAwardRule}.
 */
@Service
@Transactional
public class PointAwardRuleService {

    private final Logger log = LoggerFactory.getLogger(PointAwardRuleService.class);

    private final PointAwardRuleRepository pointAwardRuleRepository;

    private final PointAwardRuleMapper pointAwardRuleMapper;

    public PointAwardRuleService(PointAwardRuleRepository pointAwardRuleRepository, PointAwardRuleMapper pointAwardRuleMapper) {
        this.pointAwardRuleRepository = pointAwardRuleRepository;
        this.pointAwardRuleMapper = pointAwardRuleMapper;
    }

    /**
     * Save a pointAwardRule.
     *
     * @param pointAwardRuleDTO the entity to save.
     * @return the persisted entity.
     */
    public PointAwardRuleDTO save(PointAwardRuleDTO pointAwardRuleDTO) {
        log.debug("Request to save PointAwardRule : {}", pointAwardRuleDTO);
        PointAwardRule pointAwardRule = pointAwardRuleMapper.toEntity(pointAwardRuleDTO);
        pointAwardRule = pointAwardRuleRepository.save(pointAwardRule);
        return pointAwardRuleMapper.toDto(pointAwardRule);
    }

    /**
     * Update a pointAwardRule.
     *
     * @param pointAwardRuleDTO the entity to save.
     * @return the persisted entity.
     */
    public PointAwardRuleDTO update(PointAwardRuleDTO pointAwardRuleDTO) {
        log.debug("Request to update PointAwardRule : {}", pointAwardRuleDTO);
        PointAwardRule pointAwardRule = pointAwardRuleMapper.toEntity(pointAwardRuleDTO);
        pointAwardRule = pointAwardRuleRepository.save(pointAwardRule);
        return pointAwardRuleMapper.toDto(pointAwardRule);
    }

    /**
     * Partially update a pointAwardRule.
     *
     * @param pointAwardRuleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PointAwardRuleDTO> partialUpdate(PointAwardRuleDTO pointAwardRuleDTO) {
        log.debug("Request to partially update PointAwardRule : {}", pointAwardRuleDTO);

        return pointAwardRuleRepository
            .findById(pointAwardRuleDTO.getId())
            .map(existingPointAwardRule -> {
                pointAwardRuleMapper.partialUpdate(existingPointAwardRule, pointAwardRuleDTO);

                return existingPointAwardRule;
            })
            .map(pointAwardRuleRepository::save)
            .map(pointAwardRuleMapper::toDto);
    }

    /**
     * Get all the pointAwardRules.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PointAwardRuleDTO> findAll() {
        log.debug("Request to get all PointAwardRules");
        return pointAwardRuleRepository
            .findAll()
            .stream()
            .map(pointAwardRuleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pointAwardRule by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PointAwardRuleDTO> findOne(Long id) {
        log.debug("Request to get PointAwardRule : {}", id);
        return pointAwardRuleRepository.findById(id).map(pointAwardRuleMapper::toDto);
    }

    /**
     * Delete the pointAwardRule by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PointAwardRule : {}", id);
        pointAwardRuleRepository.deleteById(id);
    }
}
