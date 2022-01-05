package org.conan.service;

import org.conan.domain.Criteria;
import org.conan.domain.ReplyPageDTO;
import org.conan.domain.ReplyVO;
import org.conan.mapper.ReplyMapper;
import org.conan.persistence.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	
	private ReplyMapper mapper;
	private BoardMapper boardMapper;
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		log.info("register......."+vo);
		boardMapper.updateReplyCnt(vo.getBno(),1);
		return mapper.insert(vo);
	}
	@Override
	public ReplyVO get(Long rno) {
		// TODO Auto-generated method stub
		log.info("get........"+rno);
		return mapper.read(rno);
		
	}
	@Override
	public int modify(ReplyVO vo) {
		// TODO Auto-generated method stub
		log.info("modify........"+vo);
		return mapper.update(vo);
	}
	@Transactional
	@Override
	public int remove(Long rno) {
		// TODO Auto-generated method stub
		log.info("remove........"+rno);
		ReplyVO vo = mapper.read(rno);
		boardMapper.updateReplyCnt(vo.getBno(),-1);
		return mapper.delete(rno);
	}
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		log.info("get reply list of a board........"+bno);
		log.info(bno+"의 댓글 수 :" +mapper.getCountByBno(bno));
		return new ReplyPageDTO (mapper.getCountByBno(bno) ,mapper.getListWithPaging(cri, bno));
	}
	
	
}
