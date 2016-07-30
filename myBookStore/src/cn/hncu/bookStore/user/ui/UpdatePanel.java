/*
 * UpdatePanel.java
 *
 * Created on __DATE__, __TIME__
 */

package cn.hncu.bookStore.user.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cn.hncu.bookStore.common.UserTypeEnum;
import cn.hncu.bookStore.user.business.ebi.UserEbi;
import cn.hncu.bookStore.user.business.factory.UserEbiFactory;
import cn.hncu.bookStore.user.vo.UserModel;

/**
 * 
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class UpdatePanel extends javax.swing.JPanel {
	private JFrame mainFrame = null;
	private String uuid = null;

	/**
	 * Creates new form UpdatePanel
	 * 
	 * @param uuid
	 * @param mainFrame
	 */
	public UpdatePanel(JFrame mainFrame, String uuid) {
		this.mainFrame = mainFrame;
		this.uuid = uuid;
		initComponents();
		myInitData();
	}

	private void myInitData() {
		UserEbi ebi = UserEbiFactory.getUserEbi();
		UserModel user = ebi.getSingle(uuid);
		tfdUuid.setText(user.getUuid());
		tfdUuid.setEditable(false);

		tfdName.setText(user.getName());
		tfdPwd.setText(user.getPwd());
		tfdPwd2.setText(user.getPwd());

		combType.removeAllItems();
		String usert = UserTypeEnum.getNameByType(user.getType());
		combType.addItem(usert);
		for (UserTypeEnum userType : UserTypeEnum.values()) {
			if (!userType.getName().equals(usert)) {
				combType.addItem(userType.getName());
			}
		}

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		tfdName = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		tfdUuid = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		tfdPwd2 = new javax.swing.JPasswordField();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		tfdPwd = new javax.swing.JPasswordField();
		combType = new javax.swing.JComboBox();
		btnBack = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();

		setMinimumSize(new java.awt.Dimension(800, 600));
		setLayout(null);

		jLabel1.setFont(new java.awt.Font("微软雅黑", 1, 48));
		jLabel1.setForeground(new java.awt.Color(204, 0, 0));
		jLabel1.setText("\u4fee\u6539\u7528\u6237");
		add(jLabel1);
		jLabel1.setBounds(250, 30, 230, 80);

		jLabel2.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel2.setText("\u7528\u6237\u7c7b\u578b:");
		add(jLabel2);
		jLabel2.setBounds(90, 310, 90, 30);

		tfdName.setFont(new java.awt.Font("Dialog", 1, 18));
		tfdName.setAutoscrolls(false);
		add(tfdName);
		tfdName.setBounds(470, 160, 120, 30);

		jLabel3.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel3.setText("uuid:");
		add(jLabel3);
		jLabel3.setBounds(120, 160, 50, 30);

		tfdUuid.setFont(new java.awt.Font("Dialog", 1, 12));
		add(tfdUuid);
		tfdUuid.setBounds(190, 160, 110, 30);

		jLabel4.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel4.setText("\u59d3\u540d:");
		add(jLabel4);
		jLabel4.setBounds(410, 160, 50, 30);

		tfdPwd2.setFont(new java.awt.Font("Dialog", 1, 18));
		add(tfdPwd2);
		tfdPwd2.setBounds(470, 240, 170, 30);

		jLabel5.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel5.setText("\u5bc6\u7801:");
		add(jLabel5);
		jLabel5.setBounds(120, 240, 50, 30);

		jLabel6.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel6.setText("\u786e\u8ba4\u5bc6\u7801:");
		add(jLabel6);
		jLabel6.setBounds(380, 240, 90, 30);

		tfdPwd.setFont(new java.awt.Font("宋体", 1, 18));
		add(tfdPwd);
		tfdPwd.setBounds(190, 240, 160, 30);

		combType.setFont(new java.awt.Font("Dialog", 1, 12));
		combType.setForeground(new java.awt.Color(51, 0, 255));
		combType.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "" }));
		add(combType);
		combType.setBounds(190, 310, 170, 30);

		btnBack.setFont(new java.awt.Font("Dialog", 1, 24));
		btnBack.setForeground(new java.awt.Color(0, 204, 204));
		btnBack.setText("\u8fd4\u56de");
		btnBack.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBackActionPerformed(evt);
			}
		});
		add(btnBack);
		btnBack.setBounds(500, 430, 120, 60);

		btnUpdate.setFont(new java.awt.Font("Dialog", 1, 24));
		btnUpdate.setForeground(new java.awt.Color(0, 204, 204));
		btnUpdate.setText("\u4fee\u6539");
		btnUpdate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnUpdateActionPerformed(evt);
			}
		});
		add(btnUpdate);
		btnUpdate.setBounds(170, 430, 120, 60);
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
		// 1收集参数
		String uuid = tfdUuid.getText();
		String name = tfdName.getText();
		String pwd = new String(tfdPwd.getPassword());
		String pwd2 = new String(tfdPwd2.getPassword());

		if (uuid.equals("") || uuid.equals(null)) {
			JOptionPane.showMessageDialog(mainFrame, "用户ID为空，请重新输入！");
			return;
		}

		if (name.equals("") || name.equals(null)) {
			JOptionPane.showMessageDialog(mainFrame, "用户名为空，请重新输入！");
			return;
		}

		if (!pwd.equals(pwd2) || pwd.equals("") || pwd.equals(null)
				|| pwd2.equals("") || pwd2.equals(null)) {
			JOptionPane.showMessageDialog(mainFrame, "两次密码输入不一致或密码为空，请重新输入！");
			return;
		}
		int type = 0;

		try {
			type = UserTypeEnum.getTypeByName(combType.getSelectedItem()
					.toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(mainFrame, "请指定用户类型!");
			return;
		}

		// 2组织参数
		UserModel user = new UserModel();
		user.setName(name);
		user.setPwd(pwd);
		user.setType(type);
		user.setUuid(uuid);

		// 3调用逻辑层
		UserEbi ebi = UserEbiFactory.getUserEbi();

		// 4根据调用返回结果导向不同页面
		if (ebi.update(user)) {
			back();
		} else {
			JOptionPane.showMessageDialog(null, "该用户已经不存在!");
		}

	}

	private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
		back();
	}

	private void back() {
		mainFrame.setContentPane(new ListPanel(mainFrame));
		mainFrame.validate();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnBack;
	private javax.swing.JButton btnUpdate;
	private javax.swing.JComboBox combType;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JTextField tfdName;
	private javax.swing.JPasswordField tfdPwd;
	private javax.swing.JPasswordField tfdPwd2;
	private javax.swing.JTextField tfdUuid;
	// End of variables declaration//GEN-END:variables

}