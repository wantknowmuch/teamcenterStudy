package com.kangle.study.property;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFOperation;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemType;
import com.teamcenter.rac.kernel.TCPreferenceService;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class CreateItemOperation extends AbstractAIFOperation{

	
	private String itemName;
	private TCSession session;
	private String itemDes;
	private String lab_propertyName;
	//��ѡ��
	private TCPreferenceService preferenceService;
	
	private String myPerference = "TC_Custom_Item_Type";
	
	private AbstractAIFApplication app;
	
	public CreateItemOperation(String text ,String itemDes,String text2, TCSession session) {
		// TODO Auto-generated constructor stub
		itemName =text;
		this.itemDes = itemDes;
		this.session = session;
		this.lab_propertyName = text2;
		app = AIFUtility.getCurrentApplication();
		preferenceService = session.getPreferenceService();
	}
	
	@Override
	public void executeOperation() throws Exception {
		// TODO Auto-generated method stub
		if(null == itemName || "".equals(itemName)|| itemName.length() <=0){
			MessageBox.post("������һ��itemName","����",MessageBox.ERROR);
			return;
		}else{
			TCComponentItemType itemType = (TCComponentItemType) session.getTypeComponent("Item");
			String itemId = itemType.getNewID();
			String itemRevision = itemType.getNewRev(null);
			
			//��ȡ��ѡ���ֵ
			String[] types = preferenceService.getStringValues(myPerference);
			String type = types[0];
//			String type = "Item";
			String desc = "";
			TCComponentItem item = itemType.create(itemId, itemRevision, type, itemName,desc , null);
			TCComponent component = (TCComponent) app.getTargetComponent();
			if(component instanceof TCComponentFolder){
				component.add("contents", item);
				item.setProperty("object_desc", lab_propertyName);
				MessageBox.post("�ɹ�����item"+itemName,"�ɹ�",MessageBox.INFORMATION);
			}else{
				MessageBox.post("��ѡ���ļ���","����",MessageBox.WARNING);
			}
					
		}
		
	}

}
