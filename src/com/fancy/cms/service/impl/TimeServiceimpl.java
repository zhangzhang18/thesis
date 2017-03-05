package com.fancy.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fancy.cms.dao.TimeMapper;
import com.fancy.cms.model.Time;
import com.fancy.cms.service.TimeService;

@Service
@Transactional
public class TimeServiceimpl implements TimeService {

	
	@Autowired
	private TimeMapper timeMapper;
	
	
	@Override
	public Time selectTime() {
		return timeMapper.selectTime();
	}


	@Override
	public void update(Time time) {
		timeMapper. update(time);		
	}

}
