package com.kangle.study.bom;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.PropertyLayout;


public class CreateBomDialog extends AbstractAIFDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private TCSession session;
	
	private AbstractAIFApplication app;
	
	/*
	 * ȷ�ϰ�ť
	 */
	private JButton onButton;
	
	/*
	 * ȡ����ť
	 */
	private JButton cancelButton;
	
	
	JLabel labelItemId= new JLabel("�����������id");
	
	JTextField itemId = new JTextField(20);
	
	JLabel revesionId = new JLabel("�汾id");
	
	JTextField revesionIdArea = new JTextField(20);
	
	
	public CreateBomDialog() {
		this.app = AIFUtility.getCurrentApplication();
		this.session = (TCSession) app.getSession();
	}
	/**
	 * ʵ�ּ���
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if(btn.equals(onButton)){
			if(itemId.getText()!= null && !"".equals(itemId.getText()) ){
				CreateBomOperation dataSetOpration = new CreateBomOperation(
						app.getTargetComponent(),itemId.getText(), revesionIdArea.getText(),session);
				session.queueOperation(dataSetOpration,true);
				this.disposeDialog();
				this.dispose();
			}else{
				MessageBox.post("���id����Ϊ��","��ʾ",MessageBox.ERROR);
			}
		}else{
			this.disposeDialog();
			this.dispose();
		}
	}
	
	@Override
	public void run(){
		super.run();
		initUI();
	}
	
	
	/**
	 * ��ʼ��UI����
	 */
	public void initUI(){
		
		this.setSize(new Dimension(300, 200));
		this.setTitle("׷�ӵ�"+app.getTargetComponent().toString()+"bom��");
		
		

		this.onButton = new JButton("ȷ��");
		this.cancelButton = new JButton("ȡ��");

		this.onButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
		

		JPanel centerPanel = new JPanel(new PropertyLayout());
		centerPanel.add("1.1.left.top", labelItemId);
		centerPanel.add("1.2.left.top",itemId);
		centerPanel.add("2.1.left.top", revesionId);
		centerPanel.add("2.2.left.top", revesionIdArea);

		

		JPanel rootJPanel = new JPanel(new FlowLayout());
		rootJPanel.add(onButton);
		rootJPanel.add(cancelButton);

		this.setLayout(new BorderLayout());
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(rootJPanel, BorderLayout.SOUTH);
		this.setVisible(true);
		this.centerToScreen();
		//�Զ���������
		this.pack();
		this.showDialog();
		
		
		
	}
	
		
}
