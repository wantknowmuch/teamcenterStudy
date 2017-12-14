package com.kangle.common.util;

import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aifrcp.AIFUtility;

public class SuperHandler extends AbstractAIFCommand {
	public String operationName;

	public SuperHandler() {
		super();
	}

	public SuperHandler(String operationName) {
		super();
		this.operationName = operationName;
	}

	// �����ƹ�action command��dialogֱ�ӽ���operation
	public void CallOperation() {
		if (this.operationName != null || !"".equals(this.operationName)) {
			AbstractAIFDialog typeClass;
			try {
				typeClass = (AbstractAIFDialog) Class.forName(operationName).newInstance();
				if (typeClass != null) {
					setRunnable(typeClass);
				}
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		} else {
			try {
				throw new Exception("Dialog������Ϊ��");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
