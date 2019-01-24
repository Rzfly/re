package com.shu.re.Utils;

import org.springframework.stereotype.Service;

@Service
public class NetResult {
	public int status;
	public Object result;

	public NetResult() {
		status = 0;
	}

	public NetResult(int status) {
		this.status = status;
	}
}
