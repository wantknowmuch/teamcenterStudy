package com.kangle.study.bom;


import com.teamcenter.rac.aif.AbstractAIFOperation;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCComponentBOMWindow;
import com.teamcenter.rac.kernel.TCComponentBOMWindowType;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentItemRevisionType;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.kernel.TCTypeService;
import com.teamcenter.rac.util.MessageBox;

public class CreateBomOperation extends AbstractAIFOperation{

	private InterfaceAIFComponent component;
	
	private TCSession session;
	
	/*
	 * �����id
	 */
	private String itemId;
	
	/*
	 * ������汾id
	 */
	private String revesionId;
	

	public CreateBomOperation(InterfaceAIFComponent targetComponent, String text, String text2, TCSession session2) {
		this.component = targetComponent;
		this.itemId = text;
		this.session = session2;
		this.revesionId = text2;
	
	
	}



	@Override
	public void executeOperation() throws Exception {
		if(!(component instanceof TCComponentItemRevision)){
			MessageBox.post("��ѡ����������������","����",MessageBox.ERROR);
			return;
		}
		if(null == itemId || "".equals(itemId)){
			MessageBox.post("�����������id","����",MessageBox.ERROR);
			return;
		}
		if(null == revesionId || "".equals(revesionId)){
			MessageBox.post("������������汾��","����",MessageBox.ERROR);
			return;
		}
		
		TCComponentItemRevisionType itemType = (TCComponentItemRevisionType)session.getTypeComponent("ItemRevision");
		TCComponentItemRevision[] itemRevisions = itemType.findRevisions(itemId, revesionId);
		TCComponentItemRevision componentRevisoin = (TCComponentItemRevision) this.component;
		
		TCTypeService service = session.getTypeService();
		TCComponentBOMWindowType type = (TCComponentBOMWindowType) service.getTypeComponent("BOMWindow");
		//����bomWidow
		TCComponentBOMWindow view  = type.create(null);
		
		
		//����bomLine
		TCComponentBOMLine bomLine = view.setWindowTopLine(componentRevisoin.getItem(),componentRevisoin,null,null);
		bomLine.add("view",itemRevisions[0]);
		
		
		MessageBox.post("��" + itemId + "/" + revesionId + "������" + component + "��BOM�³ɹ�", "�ɹ�", MessageBox.INFORMATION);
		
		
	}

}
