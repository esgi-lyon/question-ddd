package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.AwardedPoint;
import org.contextmapper.generated.evaluationcontext.repository.AwardedPointRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardedPointDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AwardedPointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AwardedPoint}.
 */
@Service
@Transactional
public class AwardedPointService {

    private final Logger log = LoggerFactory.getLogger(AwardedPointService.class);

    private final AwardedPointRepository awardedPointRepository;

    private final AwardedPointMapper awardedPointMapper;

    public AwardedPointService(AwardedPointRepository awardedPointRepository, AwardedPointMapper awardedPointMapper) {
        this.awardedPointRepository = awardedPointRepository;
        this.awardedPointMapper = awardedPointMapper;
    }

    /**
     * Save a awardedPoint.
     *
     * @param awardedPointDTO the entity to save.
     * @return the persisted entity.
     */
    public AwardedPointDTO save(AwardedPointDTO awardedPointDTO) {
        log.debug("Request to save AwardedPoint : {}", awardedPointDTO);
        AwardedPoint awardedPoint = awardedPointMapper.toEntity(awardedPointDTO);
        awardedPoint = awardedPointRepository.save(awardedPoint);
        return awardedPointMapper.toDto(awardedPoint);
    }

    /**
     * Update a awardedPoint.
     *
     * @param awardedPointDTO the entity to save.
     * @return the persisted entity.
     */
    public AwardedPointDTO update(AwardedPointDTO awardedPointDTO) {
        log.debug("Request to update AwardedPoint : {}", awardedPointDTO);
        AwardedPoint awardedPoint = awardedPointMapper.toEntity(awardedPointDTO);
        awardedPoint = awardedPointRepository.save(awardedPoint);
        return awardedPointMapper.toDto(awardedPoint);
    }

    /**
     * Partially update a awardedPoint.
     *
     * @param awardedPointDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AwardedPointDTO> partialUpdate(AwardedPointDTO awardedPointDTO) {
        log.debug("Request to partially update AwardedPoint : {}", awardedPointDTO);

        return awardedPointRepository
            .findById(awardedPointDTO.getId())
            .map(existingAwardedPoint -> {
                awardedPointMapper.partialUpdate(existingAwardedPoint, awardedPointDTO);

                return existingAwardedPoint;
            })
            .map(awardedPointRepository::save)
            .map(awardedPointMapper::toDto);
    }

    /**
     * Get all the awardedPoints.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AwardedPointDTO> findAll() {
        log.debug("Request to get all AwardedPoints");
        return awardedPointRepository.findAll().stream().map(awardedPointMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one awardedPoint by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AwardedPointDTO> findOne(Long id) {
        log.debug("Request to get AwardedPoint : {}", id);
        return awardedPointRepository.findById(id).map(awardedPointMapper::toDto);
    }

    /**
     * Delete the awardedPoint by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AwardedPoint : {}", id);
        awardedPointRepository.deleteById(id);
    }
}
