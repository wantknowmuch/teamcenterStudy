package com.kangle.study.excel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;

import com.connor.study.handlers.ExportExcel.ExportExcelDialog;
import com.connor.study.handlers.ExportExcel.ExportExcelOperation;
import com.kangle.common.handler.ReportBean;
import com.kangle.common.util.ExcelUtil07;
import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.commands.subscribe.DateTimeButton;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCComponentType;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCProperty;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.PropertyLayout;

/**
 * 
 * @author haozt
 * 2017��12��14��
 */
public class ExcelImportDialog extends AbstractAIFDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * session����
	 */
	private TCSession session;
	
	/**
	 * app����
	 */
	private AbstractAIFApplication app;
	
	/**
	 * ���Ʊ�ǩ
	 */
	private JLabel nameLabel;
	
	/**
	 * ��ʼ��ǩ
	 */
	private JLabel startLabel;
	
	/**
	 * ���� ��ǩ
	 */
	private JLabel endLabel;
	
	/**
	 * �ļ�λ�ñ�ǩ
	 */
	private JLabel fileLocationLabel;
	
	/**
	 * ȷ�ϰ�ť
	 */
	private JButton onButton;
	
	/**
	 * ȡ����ť
	 */
	private JButton cancelButton;
	
	/**
	 * ѡ��ť
	 */
	private JButton chooseButton;
	
	/**
	 * ��ʼ���ڰ�ť
	 */
	private DateTimeButton startTimeButton;
	
	/**
	 * �������ڰ�ť
	 */
	private DateTimeButton endTimeButton;
	
	/**
	 * ѡ��Excel�ļ�����Ŀ��λ��
	 */
	private JTextField textField;
	
	/**
	 * �ļ�ѡ����
	 */
	private JFileChooser fileChooser;
	
	/**
	 * �����ļ�����
	 */
	private List<ReportBean> beanList;
	
	private int number = 0;

	public ExcelImportDialog() {
		super();
		app = AIFUtility.getCurrentApplication();
		session = (TCSession) app.getSession();
		beanList = new ArrayList<>();
	}

	
	
	@Override
	public void run(){
		super.run();
		initUI();
	}
	
	/**
	 * ��ʼ���������
	 */
	public void initUI(){
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		//����·��
		String deskPath = fileSystemView.getDefaultDirectory().getPath();
		
		this.setSize(new Dimension(600, 800));
		this.setTitle("ͼֽ�·�ͳ��");
		
		nameLabel = new JLabel("����ʱ��");
		startLabel = new JLabel("��ʼ����");
		startTimeButton = new DateTimeButton();
		endLabel = new JLabel("��������");
		endTimeButton = new DateTimeButton();
		fileLocationLabel = new JLabel("�ļ�����λ��");
		textField = new JTextField(deskPath);
		//��ʼĿ¼�������û�������
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(deskPath));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		onButton = new JButton("ȷ��");
		cancelButton = new JButton("ȡ��");
		chooseButton = new JButton("...");
		
		//��Ӱ�ť����¼�
		onButton.addActionListener(this);
		cancelButton.addActionListener(this);
		chooseButton.addActionListener(this);
		
		//���� ���Դ���
		JPanel jPanel = new JPanel(new PropertyLayout());
		//�߿�
		jPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.WHITE),"��ѡ������"));
		//��Ԫ���ڴ����е�λ��
		jPanel.add("1.1.left.top",nameLabel);
		jPanel.add("1.2.left.top",startLabel);
		jPanel.add("1.3.left.top",startTimeButton);
		jPanel.add("1.4.left.top",endLabel);
		jPanel.add("1.5.left.top",endTimeButton);
		
		jPanel.add("2.3.left.top",chooseButton);
		jPanel.add("2.2.left.top",textField);
		jPanel.add("2.1.left.top",fileLocationLabel);
		
		//������ť�Ĵ���
		JPanel buttonJPanel = new JPanel(new FlowLayout());
		buttonJPanel.add(onButton);
		buttonJPanel.add(cancelButton);
		
		JPanel topJPanel = new JPanel(new BorderLayout());
		
		topJPanel.add(buttonJPanel,BorderLayout.SOUTH);
		topJPanel.add(jPanel, BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.add(topJPanel,BorderLayout.CENTER);
		this.setVisible(true);
		this.centerToScreen();
		this.pack();
		this.showDialog();

	}
	
	/**
	 * �ļ�ѡ��ť����¼�
	 */
	public void selectFileButtonEvent(){
		fileChooser.setFileSelectionMode(1);// �趨ֻ��ѡ���ļ���
		int state = fileChooser.showOpenDialog(null);// �˾��Ǵ��ļ�ѡ��������Ĵ������
		if (state == 1) {
			return;
		} else {
			File f = fileChooser.getSelectedFile();// fΪѡ�񵽵�Ŀ¼
			textField.setText(f.getAbsolutePath());
		}
	}
	
	/**
	 * ȷ�ϰ�ť����¼�
	 */
	public void onButtonEvent(){
		InterfaceAIFComponent resultCompS = app.getTargetComponent();
		if (resultCompS instanceof TCComponentBOMLine) {
			List<TCComponent> revList = new ArrayList<TCComponent>();
				revList.add((TCComponent) resultCompS);
					try {
						getProperty(revList);
					} catch (TCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					InputStream is = ExcelImportDialog.class.getResourceAsStream("��������.xlsx");
					try {
						ExcelUtil07.writeExcel(textField.getText()+"\\"+"��������.xlsx", is, beanList);
						MessageBox.post("��������ɹ�", "INFO", MessageBox.INFORMATION);

					} catch (IOException e1) {
						e1.printStackTrace();
						MessageBox.post("��������ʧ��", "ERROR", MessageBox.ERROR);

					}
			this.disposeDialog();
			this.dispose();
		}
		 else{
			MessageBox.post("��������ʧ�ܣ���ѡ��һ��bomLine", "error", MessageBox.ERROR);
			return;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object sourceObj = e.getSource();
		if (sourceObj.equals(this.chooseButton)) {
			selectFileButtonEvent();
		} else if (sourceObj.equals(this.onButton)) {
			ExcelImportOperation operation = new ExcelImportOperation(this);
			this.session.queueOperation(operation);
			
			
		} else if (sourceObj.equals(this.cancelButton)) {
			this.disposeDialog();
			this.dispose();
		}
	}
	
	/**
	 * ��ȡ����
	 * @throws TCException 
	 */
	public void getProperty(List<TCComponent> revList) throws TCException{
		if (revList == null) {
			System.out.println("revlistΪ��");
			return;
		}
		
		TCProperty[][] propssRev;
		if (revList.size() > 0 && revList.get(0) instanceof TCComponentBOMLine) {
			// ��ȡһ�����Ķ�����ԣ�����1�Ƕ������ļ��ϣ�����2����Ҫ��õ����Լ�����Щ�����ڶ����б����У�����᷵��null��
			propssRev = TCComponentType.getTCPropertiesSet(revList, new String[] { "bl_child_id", "bl_line_name" });
			for (int i = 0; i < revList.size(); i++) {
				String ownuser = propssRev[i][0].toString();
				ReportBean bean = new ReportBean();
				TCComponentBOMLine ffsj = null;
				TCComponent component = (TCComponent) revList.get(i);
				if (component instanceof TCComponentBOMLine) {
					ffsj = (TCComponentBOMLine) component;
					bean.setIndex("" + (number + 1));
					number++;
					String ffsjRev = ffsj.getTCProperty("bl_rev_item_revision_id").getStringValue();
					String ffsjItemId = ffsj.getProperty("bl_item_item_id");
					String ffsjName = ffsj.getItem().getProperty("object_name");
					bean.setItem_revision_id(ffsjRev);
					bean.setItem_id(ffsjItemId);
					bean.setObject_name(ffsjName);
					if (ownuser != null) {
						bean.setOwning_user(ownuser);
					} else {
						bean.setOwning_user("");
					}
				} else{
					continue;
				}
				this.beanList.add(bean);

				boolean hasChildren = ffsj.hasChildren();//getTCProperty("bl_has_children").getBoolValue();
				if (hasChildren) {
					List<TCComponent> bl_children_list = new ArrayList<TCComponent>();
					AIFComponentContext[] bl_children_List = ffsj.getChildren();
					for (AIFComponentContext child : bl_children_List) {
						bl_children_list.add((TCComponent) child.getComponent());
					}
					getProperty(bl_children_list);
				}
				
				
			}
		
		
		}
			
		
		
	}
	
}
