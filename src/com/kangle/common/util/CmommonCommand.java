package com.kangle.common.util;
import java.awt.Frame;
import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.util.MessageBox;
public class CmommonCommand extends AbstractAIFCommand {
	private Object targetArray;
	private String type;
	/**
	 * ͨ�õ�AbstractAIFCommand���࣬���ݵ���˳�������һ��
	 * 
	 * @param frame
	 * @param app
	 * @param type
	 *            �����Ķ�����Ҫ����Ҫ���Ǵ�����Ҫ���õ���һ���AbstractAIFDialog���࣬��ҵ�����õ�Dialog
	 */
	public CmommonCommand(Frame frame, AbstractAIFApplication app, String type) {
		try {
			this.type = type;
			targetArray = app.getTargetComponent();
			if (targetArray != null) {
				if (this.type != null || "".equals(this.type)) {
					// ����ʵ�����������Ҫ�¼����õ�AbstractAIFDialog���࣬��������Ǿ���ҵ������ˣ�������ͨ����
					//dialog������������ģ��������Ҫ��������Բ�����dialog��
					AbstractAIFDialog typeClass = (AbstractAIFDialog) Class.forName(type).newInstance();
					if (typeClass != null) {
						setRunnable(typeClass);
					}
				}
			} else {
				MessageBox.post("��ѡ�����", "��ʾ ", MessageBox.WARNING);
			}
		} catch (Exception exception) {
			MessageBox.post(frame, exception);
		}
	}

}
