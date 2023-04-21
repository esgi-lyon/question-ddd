package org.contextmapper.generated.statcontext.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.LeaderBoard;
import org.contextmapper.generated.statcontext.repository.LeaderBoardRepository;
import org.contextmapper.generated.statcontext.service.LeaderBoardService;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardDTO;
import org.contextmapper.generated.statcontext.service.mapper.LeaderBoardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link LeaderBoard}.
 */
@Service
@Transactional
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private final Logger log = LoggerFactory.getLogger(LeaderBoardServiceImpl.class);

    private final LeaderBoardRepository leaderBoardRepository;

    private final LeaderBoardMapper leaderBoardMapper;

    public LeaderBoardServiceImpl(LeaderBoardRepository leaderBoardRepository, LeaderBoardMapper leaderBoardMapper) {
        this.leaderBoardRepository = leaderBoardRepository;
        this.leaderBoardMapper = leaderBoardMapper;
    }

    @Override
    public LeaderBoardDTO save(LeaderBoardDTO leaderBoardDTO) {
        log.debug("Request to save LeaderBoard : {}", leaderBoardDTO);
        LeaderBoard leaderBoard = leaderBoardMapper.toEntity(leaderBoardDTO);
        leaderBoard = leaderBoardRepository.save(leaderBoard);
        return leaderBoardMapper.toDto(leaderBoard);
    }

    @Override
    public LeaderBoardDTO update(LeaderBoardDTO leaderBoardDTO) {
        log.debug("Request to update LeaderBoard : {}", leaderBoardDTO);
        LeaderBoard leaderBoard = leaderBoardMapper.toEntity(leaderBoardDTO);
        // no save call needed as we have no fields that can be updated
        return leaderBoardMapper.toDto(leaderBoard);
    }

    @Override
    public Optional<LeaderBoardDTO> partialUpdate(LeaderBoardDTO leaderBoardDTO) {
        log.debug("Request to partially update LeaderBoard : {}", leaderBoardDTO);

        return leaderBoardRepository
            .findById(leaderBoardDTO.getId())
            .map(existingLeaderBoard -> {
                leaderBoardMapper.partialUpdate(existingLeaderBoard, leaderBoardDTO);

                return existingLeaderBoard;
            })
            // .map(leaderBoardRepository::save)
            .map(leaderBoardMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LeaderBoardDTO> findAll() {
        log.debug("Request to get all LeaderBoards");
        return leaderBoardRepository.findAll().stream().map(leaderBoardMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LeaderBoardDTO> findOne(Long id) {
        log.debug("Request to get LeaderBoard : {}", id);
        return leaderBoardRepository.findById(id).map(leaderBoardMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LeaderBoard : {}", id);
        leaderBoardRepository.deleteById(id);
    }
}
