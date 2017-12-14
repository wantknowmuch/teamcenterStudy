package com.kangle.common.util;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.common.actions.AbstractAIFAction;
import com.teamcenter.rac.util.MessageBox;

public class CommonAction extends AbstractAIFAction {
	private AbstractAIFApplication app;
	private String type;

	/**
	 * һ��ͨ�õ�AbstractAIFAction���࣬���ݵ���˳����Ե�����һ��
	 * 
	 * @param app
	 *            ���ݼ̳�AbstractAIFActionҪ����Ҫ����AbstractAIFApplication����Ȼûʲô�ã�
	 *            ����Ҫ��ʵ�ָ��๹�캯��
	 * @param type
	 *            ���ݵ���˳����Ҫ���þ����AbstractAIFDialog������
	 */
	public CommonAction(AbstractAIFApplication app, String type) {
		super(app, type);
		this.app = app;
		this.type = type;
	}

	@Override
	public void run() {
		try {
			AbstractAIFCommand abstractaifcommand = new CmommonCommand(parent, app, type);
			abstractaifcommand.executeModal();
		} catch (Exception exception) {
			MessageBox.post(parent, exception);
		}

	}

}
