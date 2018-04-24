package com.paier.word.user.util;

import com.paier.word.base.CoreBase;
import com.paier.word.util.StringUtil;
import com.paier.word.util.entity.ErrorMsg;

public class USERErrorMsg extends ErrorMsg{
	
	private Integer errCode;
	
	@Override
	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
		String fileName = "message/message_ZH_CN" ;
		if(errCode.toString().substring(4).equals("88")){
			super.setErrMsg(CoreBase.SUCCESS_STRING);
		}else{
			String errMsg = GlobalUser.getProperties(fileName, errCode.toString());
			super.setErrMsg(StringUtil.isBlank(errMsg) ? "系统异常" : errMsg);
		}
	}	
	
	public Integer getErrCode(){
		return this.errCode;
	}

}
