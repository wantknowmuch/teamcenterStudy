package com.kangle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCPropertyDescriptor;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.PropertyLayout;

public class GenerateProductCodeDialog implements ActionListener {
	private JFrame frame;
	/** ��Ʒ���ŶԻ��� */
	private JDialog dialog;
	/** ����panel */
	private JPanel topPane;
	/************ �����-start ***************/
	/** ��������� */
	private JPanel codeApplyPanel;
	/** �Ƿ��޸ĺſ� */
	private JPanel modifyCodePanel;
	/** �������ſ� */
	private JPanel paramCodePanel;
	/** �����ſ� */
	private JPanel featureCodePanel;
	/** ���洦��� */
	private JPanel surfacePanel;
	/** ��ſ� */
	private JPanel serialPanel;
	/** �ȼ��� */
	private JPanel levelPanel;
	/** ���ı���� */
	private JPanel codePanel;
	/** ��ťpanel */
	private JPanel btn_Panel;

	/** ��������������ſ򣭣��������� */
	/** ������ */
	private JPanel hingePanel;
	/** ��ϼ��� */
	private JPanel groupPanel;
	/** ��ȡ��ˮ���� */
	private JPanel serialNumPanel;

	/** -------------�������������-------------- */
	/** ���ϴ��� */
	private JComboBox<String> CB_materialCode;
	/** ���൥Ԫ */
	private JComboBox<String> CB_varietyCode;

	/** -------------�Ƿ��޸ĺſ�------------------ */
	/** �޸ĺ�ѡ��ť */
	private JCheckBox CK_modifyCode;

	/** -------------�������ſ�-------------------- */
	/** ������������� */
	private JTextField Field_paramCode;

	/** -------------�����ſ� --------------------- */
	/** �������ŵ�ѡ��ť */
	private ButtonGroup BtnG_featureCode;
	private JRadioButton RB_featureCode_left;
	private JRadioButton RB_featureCode_right;
	private JRadioButton RB_featureCode_nofeature;
	private Map<JRadioButton, String> Map_RB_featureCode;
	/** -------------��ťpanel--------------------- */
	/** ���밴ť */
	private JButton Btn_apply;
	/** ��ȡ */
	private JButton Btn_get;
	/** �˳���ť */
	private JButton Btn_exit;

	/** -------------���洦��� -------------------- */
	/** ���洦��С�� */
	private JComboBox<String> CB_surfaceCode;
	private DefaultComboBoxModel<String> surfaceCode_model;
	/** ���洦����� */
	private JComboBox<String> CB_surfaceCode_Type;

	/** -------------��ſ� ------------------------- */

	/** ============ ������ ============ */
	/** ���������� */
	private ButtonGroup BtnG_teeath;
	private JRadioButton Btn_hingebtn_doubleteeth;
	private JRadioButton Btn_hingebtn_signalteeth;
	private Map<JRadioButton, String> Map_Btn_teeth;
	/** ============��ϼ��� ============ */
	/** ��ϼ������� */
	private JComboBox<String> CB_groupType;
	private JCheckBox confirmIsGroup;
	private DefaultComboBoxModel<String> groupType_model;
	/** �ֹ��������� */
	private JTextField Field_menualSerialNum;

	/** ============��ȡ��ˮ���� ============ */
	/** ��ȡ�����ˮ����� */
	private JTextField Field_serialNum;
	/** ��ȡ�����ˮ��ť */
	private JButton Btn_getSerialNum;

	/** -------------�ȼ��� ---------------------- */
	/** ��Ʒ�ȼ���ť�� */
	private ButtonGroup BtnG_product_level;
	/** �ŵ�Ʒ */
	private JRadioButton RB_level_good;
	/** ע�ܼ� */
	private JRadioButton RB_level_moulding;
	/** һ��Ʒ */
	private JRadioButton RB_level_1;
	/** �ϸ�Ʒ */
	private JRadioButton RB_level_eligibility;

	private Map<JRadioButton, String> Map_RB_level;
	/** -------------���ı���� --------------- */
	/** �����ʾ�Ĵ�������� */
	private JTextField Field_result;

	/** ���յĲ�Ʒ���� */
	private StringBuilder code;

	/** ���ϴ��Ŷ�Ӧ��ϵ */
	private Map<String, String> MapMaterialCode;
	/** ���൥Ԫ��Ӧ��ϵ */
	private Map<String, String[]> MapVarietyCode;
	/** ģ����ѡ����� */
	private final String[] StrMaterialCode = { "��Ǧ��ͭ:B", "��Ǧп��ͭ:X", "���˶�:M", "��ͭ:H", "��ͭ:P", "����ͭ:Q", "����:T", "����:T",
			"�����:C", "���Ͻ�:L" };
	/** ģ����ѡ�� ������ */
	private final String[] StrVarietyCode = { "����-Բͭо:T-", "����-��о:H-HP", "����-����:L-LP", "����-���ɾ���:Y-YP", "��ͨ����-�ܽ�:A-AP",
			"��ͨ����-�Կڽ�:B-BP", "��ͨ����-ׯͷ:C-CP", "��ͨ����-��Ȧ��:E-", "���ɽ���-�̶���:G-", "���ɽ���-о��:R-", "���ɽ���-����о��(������о��):V-VP",
			"���ɽ���-����ǰ��:D-DP", "���ɽ���-���ɺ�:F-FP", "���ɽ���-���ɽ�����ϼ�:-PP", "�ǻ�:J-JP", "����:Z-ZP", "�μ�:S-SP", "����:K-KP", "˿ͨ:Q-",
			"�����:X-XP" };
	/** ���洦���õĶ�Ӧ��ϵ */
	private Map<String, Map<String, String>> MapSurfaceCode;

	/** ���洦���ʼ��ʱ���� */
	private boolean isInitSurfaceCode = true;

	/** ͳһ���Ƶ����� */
	private Font commonFont = new Font("����", Font.PLAIN, 15);
	/** ��ť���Ϸ�panel */
	private JPanel north;
	/** �·���ť���panel */
	private JPanel south;
	/** ��ɫ���� */
	private Color commonColor = new Color(200, 200, 200);
	private String featureCode = "";
	/** �ȼ����� */
	private String levelCode = "";

	/** һ��TC������ */
	private TCComponentForm form;

	/** ��Ҫ���µ���������Ҳ�д�������Ƕ�����Ŀ���ٺٺ٣����׼Ӳ����أ����ӿ��Զ���Ҫ�ۣ��� */
	private JTextField field;

	/** ��ȡ�ⲿ����ı����� */
	public void getTarget(TCComponent form, JTextField field) {
		this.form = (TCComponentForm) form;
		this.field = field;
	}

	public void run() {
		/* ��ʼ��ʼ����� */
		init();
		/* ��ʼ��UI��û����ȫִ�� */
		drawUI();
		/* ��Ӽ��� */
		addListener();
		/* ִ�����һ�� */
		executeLastStep();

	}

	private void executeLastStep() {
		north.add(topPane);
		dialog.setLayout(new BorderLayout());
		dialog.add(north, BorderLayout.NORTH);
		dialog.add(south, BorderLayout.SOUTH);
		dialog.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - dialog.getWidth()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - dialog.getHeight()) / 2);

		///////////////////////////////////// �����ʾ�Ĳ���,�����һ��////////////////////////////////////
		dialog.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * ��UI
	 */
	private void drawUI() {
		// ����dialog�����С�Ͳ���
		dialog.setSize(840, 550);

		north.setSize(840, 450);
		north.setLocation(0, 0);

		south.setSize(840, 50);
		south.setLocation(0, 500);

		north.setLayout(new FlowLayout());
		south.setLayout(new FlowLayout());

		topPane.setPreferredSize(new Dimension(840, 450));
		topPane.setLocation(0, 0);
		topPane.setLayout(null);
		// �����ſ�
		addCodeApplyPanel();
		// �޸ĺſ�
		addModifyCodePanel();
		// �ȼ������

		// // �������ſ�
		addParamCodePanel();

		// �����ſ�
		addFeatureCodePanel();

		// ���洦���
		addSurfacePanel();

		// ��ſ�
		addSerialPanel();

		// ��Ʒ�ȼ���
		addLevelPanel();

		// ����������ʾ
		addCodePanel();

		// ��ť��
		btn_Panel.setLayout(new FlowLayout());
		btn_Panel.add(Btn_get);
		btn_Panel.add(Btn_apply);
		btn_Panel.add(Btn_exit);
		// ��ť��
		/* �ڶ���panel��� */
		north.add(topPane);
		south.add(btn_Panel);
	}

	/** �����ʾ���Ľ���� */
	private void addCodePanel() {
		JPanel top8 = new JPanel();
		top8.setLayout(new FlowLayout());
		top8.setSize(410, 80);
		top8.setLocation(400, 360);

		codePanel.setLayout(new GridLayout(2, 1));
		codePanel.setPreferredSize(new Dimension(400, 70));

		JLabel lab_code = new JLabel("����");
		Field_result.setEditable(false);
		codePanel.add(lab_code);
		codePanel.add(Field_result);
		top8.add(codePanel);
		topPane.add(top8);
	}

	/** ��ӵȼ��� */
	private void addLevelPanel() {
		JPanel top7 = new JPanel();
		top7.setLayout(new FlowLayout());
		top7.setSize(410, 70);
		top7.setLocation(400, 285);

		levelPanel.setPreferredSize(new Dimension(400, 60));
		levelPanel.setLayout(new FlowLayout());
		levelPanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "��Ʒ�ȼ�"));

		int local = 20;
		for (JRadioButton btn_level : Map_RB_level.keySet()) {
			btn_level.setLocation(local += 10, 20);
			btn_level.setBorder(new EmptyBorder(5, 20, 0, 0));
			btn_level.setFont(commonFont);
			BtnG_product_level.add(btn_level);
			levelPanel.add(btn_level);
		}
		top7.add(levelPanel);
		topPane.add(top7);
	}

	/** �����ſ� */
	private void addSerialPanel() {
		JPanel top6 = new JPanel();
		top6.setLayout(new FlowLayout());
		top6.setSize(410, 265);
		top6.setLocation(400, 10);

		serialPanel.setLayout(new FlowLayout());
		serialPanel.setPreferredSize(new Dimension(410, 260));
		serialPanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "�����"));

		// hingePanel.setPreferredSize(new Dimension(10, 100));

		hingePanel.setLayout(new GridLayout(1, 2));
		hingePanel.setPreferredSize(new Dimension(380, 50));
		hingePanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "����"));
		Btn_hingebtn_signalteeth.setBorder(new EmptyBorder(0, 20, 0, 0));
		Btn_hingebtn_signalteeth.setFont(commonFont);
		Btn_hingebtn_doubleteeth.setFont(commonFont);

		hingePanel.add(Btn_hingebtn_signalteeth);
		hingePanel.add(Btn_hingebtn_doubleteeth);

		JPanel groupPanel_paren = new JPanel();
		groupPanel_paren.setPreferredSize(new Dimension(380, 120));
		groupPanel_paren.setLayout(new PropertyLayout());
		groupPanel_paren.add(confirmIsGroup, "1.1.left.top");
		groupPanel_paren.add(groupPanel, "2.1.left.top");
		confirmIsGroup.setBounds(-5, -5, 20, 20);

		groupPanel.setLayout(new PropertyLayout());
		groupPanel.setPreferredSize(new Dimension(380, 90));
		groupPanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "��ϼ�"));

		JLabel lab_type = new JLabel("��ϼ�����");
		lab_type.setBounds(10, 10, 100, 100);
		lab_type.setBorder(new EmptyBorder(0, 20, 0, 0));
		lab_type.setFont(commonFont);
		JLabel lab_num = new JLabel("�ֹ����");
		lab_num.setBounds(10, 40, 100, 100);
		lab_num.setBorder(new EmptyBorder(0, 20, 0, 0));
		lab_num.setFont(commonFont);
		Field_menualSerialNum.setPreferredSize(new Dimension(130, 26));
		initGT();
		CB_groupType.setPreferredSize(new Dimension(220, 24));
		CB_groupType.setFont(commonFont);
		Field_menualSerialNum.setPreferredSize(new Dimension(220, 24));
		groupPanel.add(lab_type, "1.1.left.top");
		groupPanel.add(CB_groupType, "1.2.left.top");
		groupPanel.add(lab_num, "2.1.left.top");
		groupPanel.add(Field_menualSerialNum, "2.2.left.top");

		serialNumPanel.setLayout(new FlowLayout());
		serialNumPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		serialNumPanel.add(Field_serialNum);
		serialNumPanel.add(Btn_getSerialNum);

		Field_serialNum.setPreferredSize(new Dimension(160, 26));
		Btn_getSerialNum.setPreferredSize(new Dimension(130, 26));

		serialPanel.add(hingePanel);
		serialPanel.add(groupPanel_paren);
		serialPanel.add(serialNumPanel);
		top6.add(serialPanel);
		topPane.add(top6);

	}

	/** ��ӱ��洦��� */
	private void addSurfacePanel() {
		JPanel top5 = new JPanel();
		top5.setLayout(new FlowLayout());
		top5.setSize(340, 80);
		top5.setLocation(20, 360);

		surfacePanel.setPreferredSize(new Dimension(340, 70));
		surfacePanel.setLayout(new PropertyLayout());
		surfacePanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "���洦��"));

		JLabel lab_empty = new JLabel("");
		lab_empty.setPreferredSize(new Dimension(20, 0));
		lab_empty.setBorder(new EmptyBorder(5, 40, 0, 0));
		CB_surfaceCode_Type.setPreferredSize(new Dimension(100, 30));
		// CB_surfaceCode_Type.setBorder(new EmptyBorder(5, 40, 0, 0));
		CB_surfaceCode_Type.setFont(commonFont);

		CB_surfaceCode.setPreferredSize(new Dimension(150, 30));
		// CB_surfaceCode.setBorder(new EmptyBorder(5, 40, 0, 0));
		CB_surfaceCode.setFont(commonFont);
		// ���������
		CB_surfaceCode_Type.setSelectedIndex(0);
		// ��panel��ӵ�ѡ��ť
		surfacePanel.add(lab_empty, "1.1.left.top");
		surfacePanel.add(CB_surfaceCode_Type, "1.2.left.top");
		surfacePanel.add(CB_surfaceCode, "1.3.left.top");
		top5.add(surfacePanel);
		topPane.add(top5);
	}

	/** ��������� */
	private void addFeatureCodePanel() {
		JPanel top4 = new JPanel();
		top4.setSize(340, 70);
		top4.setLocation(20, 285);
		top4.setLayout(new FlowLayout());
		featureCodePanel.setPreferredSize(new Dimension(340, 60));
		featureCodePanel.setLayout(new GridLayout(1, 1));
		featureCodePanel
				.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
						BorderFactory.createLineBorder(commonColor)), "��������"));
		for (JRadioButton btn_feature : Map_RB_featureCode.keySet()) {
			btn_feature.setBorder(new EmptyBorder(0, 20, 0, 0));
			btn_feature.setPreferredSize(new Dimension(70, 20));
			btn_feature.setFont(commonFont);
			BtnG_featureCode.add(btn_feature);
			featureCodePanel.add(btn_feature);
		}
		top4.add(featureCodePanel);
		topPane.add(top4);
	}

	/** ��Ӳ�������� */
	private void addParamCodePanel() {
		JPanel top3 = new JPanel();
		top3.setSize(340, 70);
		top3.setLocation(20, 210);
		top3.setLayout(new FlowLayout());

		paramCodePanel.setPreferredSize(new Dimension(340, 60));
		paramCodePanel.setLayout(new PropertyLayout());
		paramCodePanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "��������"));

		JLabel lab_paramCode = new JLabel("��������");
		lab_paramCode.setFont(commonFont);
		// ���ô�С�ͼ��
		lab_paramCode.setPreferredSize(new Dimension(80, 20));
		lab_paramCode.setBorder(new EmptyBorder(3, 20, 0, 0));

		Field_paramCode.setPreferredSize(new Dimension(130, 26));
		paramCodePanel.add(lab_paramCode, "1.1.left.top");
		paramCodePanel.add(Field_paramCode, "1.2.left.top");
		top3.add(paramCodePanel);
		topPane.add(top3);
	}

	/** ����Ƿ��޸Ŀ� */
	private void addModifyCodePanel() {
		JPanel top2 = new JPanel();
		top2.setSize(340, 70);
		top2.setLocation(20, 125);
		top2.setLayout(new FlowLayout());

		modifyCodePanel.setLayout(new GridLayout(1, 1));
		modifyCodePanel
				.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
						BorderFactory.createLineBorder(commonColor)), "�޸ĺ�"));
		// ���ʹ���˲��ֹ�����������ֱ��setSize��Ӧʹ��setPreferredSize���������С
		modifyCodePanel.setPreferredSize(new Dimension(340, 60));
		CK_modifyCode.setPreferredSize(new Dimension(40, 10));
		CK_modifyCode.setBorder(new EmptyBorder(5, 20, 5, 0));
		CK_modifyCode.setFont(commonFont);
		modifyCodePanel.add(CK_modifyCode);
		top2.add(modifyCodePanel);
		topPane.add(top2);

	}

	/** ��ӱ�������� */
	private void addCodeApplyPanel() {
		JPanel top = new JPanel();
		top.setSize(340, 110);
		top.setLocation(20, 10);
		top.setLayout(new FlowLayout());
		// ������ʽ�Ͳ���
		codeApplyPanel.setPreferredSize(new Dimension(340, 100));
		codeApplyPanel.setLocation(0, 0);
		codeApplyPanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "��������"));
		codeApplyPanel.setLayout(new PropertyLayout());
		// ��ӱ�ǩԪ��
		JLabel lab_MaterialCode = new JLabel("���ϴ���");
		JLabel lab_VarietyCode = new JLabel("���൥Ԫ");
		lab_MaterialCode.setFont(commonFont);
		lab_VarietyCode.setFont(commonFont);
		lab_MaterialCode.setPreferredSize(new Dimension(80, 20));
		lab_VarietyCode.setPreferredSize(new Dimension(80, 20));
		lab_MaterialCode.setBorder(new EmptyBorder(6, 20, 0, 0));
		lab_VarietyCode.setBorder(new EmptyBorder(6, 20, 0, 0));

		CB_materialCode.setFont(commonFont);
		CB_varietyCode.setFont(commonFont);
		// // ������
		codeApplyPanel.add(lab_MaterialCode, "1.1.left.top");
		codeApplyPanel.add(CB_materialCode, "1.2.left.top");
		codeApplyPanel.add(lab_VarietyCode, "2.1.left.top");
		codeApplyPanel.add(CB_varietyCode, "2.2.left.top");
		// // ���ô�С
		CB_varietyCode.setPreferredSize(new Dimension(210, 30));
		CB_materialCode.setPreferredSize(new Dimension(210, 30));
		top.add(codeApplyPanel);
		topPane.add(top);
	}

	/**
	 * ��ʼ��һЩ��Ҫ�Ķ���
	 */
	private void init() {
		// frame��ʼ��
		frame = new JFrame("���ɲ�Ʒ����");
		// ģʽ�Ի���ǿռ����
		dialog = new JDialog(frame, "���ɲ�Ʒ����", true);
		/* ��ʼ������panel */
		south = new JPanel();
		north = new JPanel();
		topPane = new JPanel();
		/*------------------ ����panel��ʼ�� ----------------*/
		codeApplyPanel = new JPanel();
		// ���Ϻ��������ʼ��
		CB_materialCode = new JComboBox<String>();
		// ���൥Ԫ�������ʼ��
		CB_varietyCode = new JComboBox<String>();
		// ��Ӧ��ϵ
		MapMaterialCode = new HashMap<String, String>();
		//
		MapVarietyCode = new HashMap<String, String[]>();
		/*------------------ ����panel��ʼ�� ----------------*/

		/*------------------ ��ʼ���޸ĺ�ѡ���----------------*/
		modifyCodePanel = new JPanel();
		CK_modifyCode = new JCheckBox("�Ƿ��޸�");
		/*------------------ ��ʼ���޸ĺ�ѡ���----------------*/

		/*------------------ ��ʼ���������������----------------*/
		paramCodePanel = new JPanel();
		Field_paramCode = new JTextField(17);
		/*------------------ ��ʼ���������������----------------*/

		/*------------------ ��ʼ���������ŵ�ѡ��----------------*/
		featureCodePanel = new JPanel();
		RB_featureCode_left = new JRadioButton("��");
		RB_featureCode_right = new JRadioButton("��");
		RB_featureCode_nofeature = new JRadioButton("������");
		BtnG_featureCode = new ButtonGroup();
		Map_RB_featureCode = new LinkedHashMap<JRadioButton, String>();
		Map_RB_featureCode.put(RB_featureCode_left, "L");
		Map_RB_featureCode.put(RB_featureCode_right, "R");
		Map_RB_featureCode.put(RB_featureCode_nofeature, "");
		/*------------------ ��ʼ���������ŵ�ѡ��----------------*/

		/*------------------ ���洦���----------------*/
		surfacePanel = new JPanel();
		CB_surfaceCode = new JComboBox<>();
		CB_surfaceCode_Type = new JComboBox<>();
		surfaceCode_model = new DefaultComboBoxModel<>();
		/*------------------ ���洦���----------------*/

		/*------------------ ��ſ�----------------*/
		serialPanel = new JPanel();
		hingePanel = new JPanel();
		groupPanel = new JPanel();
		serialNumPanel = new JPanel();
		BtnG_teeath = new ButtonGroup();
		Btn_hingebtn_doubleteeth = new JRadioButton("˫��");
		Btn_hingebtn_signalteeth = new JRadioButton("����");
		BtnG_teeath.add(Btn_hingebtn_doubleteeth);
		BtnG_teeath.add(Btn_hingebtn_signalteeth);
		Map_Btn_teeth = new HashMap<JRadioButton, String>();
		Map_Btn_teeth.put(Btn_hingebtn_doubleteeth, "2");
		Map_Btn_teeth.put(Btn_hingebtn_signalteeth, "1");

		groupType_model = new DefaultComboBoxModel<>();
		CB_groupType = new JComboBox<>();
		Field_menualSerialNum = new JTextField();
		confirmIsGroup = new JCheckBox("�Ƿ���ϼ�");

		Field_serialNum = new JTextField(16);
		Btn_getSerialNum = new JButton("��ȡ��ˮ���");
		/*------------------ ��ſ�----------------*/

		/*------------------ �ȼ���----------------*/
		levelPanel = new JPanel();

		BtnG_product_level = new ButtonGroup();
		RB_level_good = new JRadioButton("�ŵ�Ʒ");
		RB_level_moulding = new JRadioButton("ע�ܼ�");
		RB_level_1 = new JRadioButton("һ��Ʒ");
		RB_level_eligibility = new JRadioButton("�ϸ�Ʒ");
		Map_RB_level = new LinkedHashMap<JRadioButton, String>();
		Map_RB_level.put(RB_level_good, "1");
		Map_RB_level.put(RB_level_moulding, "2");
		Map_RB_level.put(RB_level_1, "9");
		Map_RB_level.put(RB_level_eligibility, "");
		/*------------------ �ȼ���----------------*/

		/*------------------ ���ı����----------------*/
		codePanel = new JPanel();
		Field_result = new JTextField();
		/*------------------ ���ı����----------------*/

		/*------------------ ��ť���ʼ��----------------*/
		btn_Panel = new JPanel();
		// ���밴ť��ʼ��
		Btn_apply = new JButton("����");
		// ��ȡ����
		Btn_get = new JButton("��ȡ����");
		// �˳���ť��ʼ��
		Btn_exit = new JButton("�˳�");
		/*------------------ ��ť���ʼ��----------------*/
		// ���ս����ʼ��
		code = new StringBuilder();
		// װ��ֵ��ʼ����װ���������ʼֵ
		initSurfaceCode();
		initCB();
	}

	/** ��ʼ�����洦�� */
	private void initSurfaceCode() {
		// ��ȡ��ѡ��////
		// ����
		Map<String, String> barrelPlating = new HashMap<String, String>();
		// �Ҷ�
		Map<String, String> rackPlating = new HashMap<String, String>();
		// ����
		Map<String, String> sprayPlating = new HashMap<String, String>();

		/*** װ�� ***/
		barrelPlating.put("�ƽ�", "G10");
		barrelPlating.put("�ձ���", "G20");
		barrelPlating.put("��õ���", "G30");
		barrelPlating.put("����", "G01");
		barrelPlating.put("����", "G02");
		barrelPlating.put("����", "G03");
		barrelPlating.put("��ͭ", "G04");
		barrelPlating.put("��п", "G05");
		barrelPlating.put("�Ƹ�", "G06");
		barrelPlating.put("����", "G07");
		barrelPlating.put("��ͭ��", "G08");

		rackPlating.put("�ƽ�", "H10");
		rackPlating.put("�ձ���", "H20");
		rackPlating.put("��õ���", "H30");
		rackPlating.put("����", "H01");
		rackPlating.put("����", "H02");
		rackPlating.put("����", "H03");
		rackPlating.put("��ͭ", "H04");
		rackPlating.put("��п", "H05");
		rackPlating.put("�Ƹ�", "H06");
		rackPlating.put("����", "H07");
		rackPlating.put("��ͭ��", "H08");

		sprayPlating.put("��ɫ", "P10");
		sprayPlating.put("��ɫ", "P20");
		sprayPlating.put("��ɫ", "P30");
		sprayPlating.put("ǹɫ", "P40");
		sprayPlating.put("��ɫ", "P50");
		sprayPlating.put("��ɫ", "P60");

		MapSurfaceCode = new HashMap<String, Map<String, String>>();
		MapSurfaceCode.put("����", barrelPlating);
		MapSurfaceCode.put("�Ҷ�", rackPlating);
		MapSurfaceCode.put("����", sprayPlating);
		CB_surfaceCode.setModel(surfaceCode_model);
		for (String key : MapSurfaceCode.keySet()) {
			CB_surfaceCode_Type.addItem(key);
			if (isInitSurfaceCode) {
				for (String codes : MapSurfaceCode.get(key).keySet()) {
					surfaceCode_model.addElement(codes);
				}
				isInitSurfaceCode = false;
			}
		}
	}

	/**
	 * ��ʼ�����ϴ��ź�����
	 */
	private void initCB() {
		// ��ȡ��ѡ��
		for (String item : StrMaterialCode) {
			String[] result = item.split(":");
			String Mater = result[0];
			String code = result[1];
			MapMaterialCode.put(Mater, code);
			CB_materialCode.addItem(Mater);
		}
		for (String item : StrVarietyCode) {
			String[] result = item.split(":");
			String type = result[0];
			String[] code = result[1].split("-");
			MapVarietyCode.put(type, code);
			CB_varietyCode.addItem(type);
		}
	}

	/**
	 * ��ʼ����ϼ�����������
	 */
	private void initGT() {
		// Ҳ�п����ǻ�ȡ��ѡ���
		Map<String, String> groupType = new HashMap<String, String>();
		groupType.put("���ɺ���ϼ�", "");
		groupType.put("����о����ϼ�", "");
		groupType.put("���ɽ�����ϼ�", "");
		groupType.put("����(��״����)", "");
		groupType.put("����(��״������)", "");
		groupType.put("�ǻ�", "");
		groupType.put("����", "");
		groupType.put("�μ�", "");
		groupType.put("˿ͨ", "");
		groupType.put("��������", "");
		CB_groupType.setModel(groupType_model);
		for (String key : groupType.keySet()) {
			groupType_model.addElement(key);
		}
		CB_groupType.setEnabled(false);
		// confirmIsGroup.setEnabled(false);
		Field_menualSerialNum.setEnabled(false);
	}

	/**
	 * ��Ӽ���
	 */
	private void addListener() {
		/* ���밴ť */
		Btn_apply.addActionListener(this);
		/* �˳���ť */
		Btn_exit.addActionListener(this);
		/* ��Ʒ�ȼ� */
		for (JRadioButton btn_level : Map_RB_level.keySet()) {
			btn_level.addActionListener(this);
		}
		/* ���� */
		for (JRadioButton btn_feature : Map_RB_featureCode.keySet()) {
			btn_feature.addActionListener(this);
		}
		/* ���洦�� */
		CB_surfaceCode_Type.addActionListener(this);
		confirmIsGroup.addActionListener(this);
		Btn_get.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* ����֮��ƴ���ַ��������������ϣ�ƴ�ӵ�˳������Ҫ��� */
		if (e.getSource().equals(Btn_get)) {
			String code = execute();
			Field_result.setText(code);
			System.out.println(code);
		}

		else if (e.getSource().equals(Btn_apply)) {
			if (this.code == null || "".equals(this.code) || code.length() <= 0)
				execute();
			try {
				// List<TCPropertyDescriptor> des =
				// form.getDisplayablePropertyDescriptors();
				form.lock();
				form.setProperty("kh3_cpdh", code.toString());
				form.save();
				form.unlock();
				form.refresh();
				field.setText(code.toString());
			} catch (TCException e1) {
				e1.printStackTrace();
			}
			dialog.dispose();
			MessageBox.post("���Զ����ɲ�Ʒ����:" + code, "�ɹ�", MessageBox.INFORMATION);
		}
		/* �˳��Ի��� */
		else if (e.getSource().equals(Btn_exit)) {
			dialog.dispose();
		} else if (Map_RB_level.containsKey(e.getSource())) {
			levelCode = Map_RB_level.get(e.getSource());
		} else if (e.getSource().equals(CB_surfaceCode_Type)) {
			surfaceCode_model.removeAllElements();
			for (String string : MapSurfaceCode.get(CB_surfaceCode_Type.getSelectedItem()).keySet()) {
				surfaceCode_model.addElement(string);
			}
		} else if (Map_RB_featureCode.containsKey(e.getSource())) {
			featureCode = Map_RB_featureCode.get(e.getSource());
		} else if (e.getSource().equals(confirmIsGroup)) {
			if (confirmIsGroup.isSelected()) {
				CB_groupType.setEnabled(true);
				Field_menualSerialNum.setEnabled(true);
			} else {
				CB_groupType.setEnabled(false);
				Field_menualSerialNum.setEnabled(false);
			}
		}
	}

	private String execute() {
		code = new StringBuilder();
		String key = (String) CB_materialCode.getSelectedItem();
		code.append(MapMaterialCode.get(key));
		key = (String) CB_varietyCode.getSelectedItem();
		if (confirmIsGroup.isSelected()) {
			code.append(MapVarietyCode.get(key)[1]);
		} else {
			code.append(MapVarietyCode.get(key)[0]);
		}

		code.append(Field_serialNum.getText());
		if (CK_modifyCode.isSelected())
			code.append("-A");
		key = Field_paramCode.getText();
		code.append(key);
		code.append(levelCode);
		code.append(featureCode);
		code.append("/");
		key = (String) CB_surfaceCode_Type.getSelectedItem();
		Map<String, String> v = MapSurfaceCode.get(key);
		key = v.get(CB_surfaceCode.getSelectedItem());
		code.append(key);
		return code.toString();
	}
	public static void main(String[] args) {
		GenerateProductCodeDialog dialog  = new GenerateProductCodeDialog();
		dialog.run();
	}
}
