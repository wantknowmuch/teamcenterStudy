package com.kangle.study.dataSet;


import com.teamcenter.rac.aif.AbstractAIFOperation;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentDataset;
import com.teamcenter.rac.kernel.TCComponentDatasetType;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class CreateDataSetOperation extends AbstractAIFOperation{

	private InterfaceAIFComponent component;
	
	private TCSession session;
	
	private String path;
	
	private String folderName;
	
	private String folderDescArea;
	
	
	public CreateDataSetOperation(InterfaceAIFComponent targetComponent, String text, TCSession session, String text2,
			String path) {
		// TODO Auto-generated constructor stub
		
		this.component = targetComponent;
		this.folderName = text;
		this.session = session;
		this.folderDescArea = text2;
		this.path = path;
	}

	

	@Override
	public void executeOperation() throws Exception {
		if(!path.endsWith(".txt")){
			MessageBox.post("ѡ�еĲ���txt�ļ�����ѡ��txt�ļ�","��ʾ",MessageBox.WARNING);
			return;
		}

		String as1[] = { this.path };// �ļ�������·��
		String as2[] = { "Text" };
		TCComponentDatasetType t = (TCComponentDatasetType) session.getTypeComponent("Dataset");
		TCComponentDataset f = t.create(folderName, folderDescArea, "Text");
		f.setFiles(as1, as2);
		TCComponent component = (TCComponent) this.component;
		if (component instanceof TCComponentFolder) {
			component.add("contents", f);
		} else if (component instanceof TCComponentItem) {
			component.add("IMAN_reference", f);
		} else if (component instanceof TCComponentItemRevision) {
			component.add("IMAN_specification", f);
		}
		MessageBox.post("���ݼ�" + folderName + "�ɹ�", "�ɹ�", MessageBox.INFORMATION);
		
		
	}

}
