package com.connor.soa.learning.folder;

import java.util.ArrayList;
import java.util.List;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFOperation;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.common.create.BOCreateDefinitionFactory;
import com.teamcenter.rac.common.create.CreateInstanceInput;
import com.teamcenter.rac.common.create.IBOCreateDefinition;
import com.teamcenter.rac.common.create.ICreateInstanceInput;
import com.teamcenter.rac.common.create.SOAGenericCreateHelper;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class CreateFolderOpration extends AbstractAIFOperation{

	private TCSession session;
	private AbstractAIFApplication app;
	private String folderName;
	private TCComponent component;
	
	
	public CreateFolderOpration(String name) {
		
		this.folderName=name;
	}


	/**
	 * �����ļ���
	 */
	@Override
	public void executeOperation() throws Exception {
		//�鿴Ҫ�������ļ����Ƿ���ڣ�Ȼ���ڽ����ļ��еĴ���
		app = AIFUtility.getCurrentApplication();
		component = (TCComponent) app.getTargetComponent();
		if(null == component){
			System.out.println("��ѡ��һ���ļ���");
			return;
		}
		//���������һ���ݹ��ѯ
		else{
			execute();
		}
		
	}
	
	/**
	 * ��SOA�����ļ��еĹ���
	 * @throws TCException 
	 */
	public void execute() throws TCException{
		BOCreateDefinitionFactory factory = BOCreateDefinitionFactory.getInstance();
		this.session = (TCSession) app.getSession();
		IBOCreateDefinition definition = factory.getCreateDefinition(session, "Item");
		CreateInstanceInput instanceInput = new CreateInstanceInput(definition);
		List<ICreateInstanceInput> list = new ArrayList<>();
		list.add(instanceInput);
		List<TCComponent> tcComponentList = SOAGenericCreateHelper.create(session, definition, list);
		if(tcComponentList.isEmpty()){
			System.out.println("�ļ��д���ʧ��");
			return;
		}
		// ����ļ��ж���-��ʵ����������TCComponentFolder
		TCComponent folder = tcComponentList.get(0);
		folder.setStringProperty("object_name", folderName);
		// ���ļ��йҵ�Ŀ������
		if (!(component instanceof TCComponent)) {
			session.getUser().getNewStuffFolder().add("contents", folder);
			MessageBox.post("�ļ��д����ɹ���\n��ǰ����:" + component + " ����TCComponent����Ŀ�ļ���" + folderName + "���浽NewStuff�ļ�����", "��ʾ",
					MessageBox.WARNING);
			return;
		}
		TCComponent targetComp = (TCComponent) component;
		targetComp.add(targetComp.getDefaultPasteRelation(), folder);
		MessageBox.post("�ļ��д����ɹ���\n��Ŀ�ļ���" + folderName + "���浽" + targetComp + "��", "��ʾ", MessageBox.INFORMATION);
	
	}
}
