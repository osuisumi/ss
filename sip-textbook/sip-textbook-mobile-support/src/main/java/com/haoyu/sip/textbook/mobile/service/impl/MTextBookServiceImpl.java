package com.haoyu.sip.textbook.mobile.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.textbook.entity.TextBookEntry;
import com.haoyu.sip.textbook.mobile.entity.MTextBookEntry;
import com.haoyu.sip.textbook.mobile.service.IMTextBookService;
import com.haoyu.sip.textbook.utils.TextBookParam;
import com.haoyu.sip.textbook.utils.TextBookUtils;
import com.haoyu.sip.utils.Collections3;

@Repository
public class MTextBookServiceImpl implements IMTextBookService{

	@Override
	public Response listTextBook(TextBookParam textBookParam) {
		List<MTextBookEntry> mTextBookEntries = Lists.newArrayList();
		
		List<TextBookEntry> textBookEntries = TextBookUtils.getEntryList(textBookParam);
		
		if (Collections3.isNotEmpty(textBookEntries)) {
			mTextBookEntries = BeanUtils.getCopyList(textBookEntries, MTextBookEntry.class);
		}
		
		return Response.successInstance().responseData(mTextBookEntries);
	}

}
