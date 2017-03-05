package com.fancy.cms.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fancy.cms.service.FcReceiveMsgService;

@Service
@Transactional
public class FcReceiveMsgServcieImpl implements FcReceiveMsgService {

	protected static final Log log = LogFactory.getLog(FcReceiveMsgServcieImpl.class);

}
