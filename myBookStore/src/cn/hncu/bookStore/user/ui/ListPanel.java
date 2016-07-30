/*
 * ListPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package cn.hncu.bookStore.user.ui;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cn.hncu.bookStore.user.business.ebi.UserEbi;
import cn.hncu.bookStore.user.business.factory.UserEbiFactory;
import cn.hncu.bookStore.user.vo.UserModel;

/**
 * 表现层-用户列表面板
 * 
 * @author 陈浩翔
 * @version 1.0
 */
public class ListPanel extends javax.swing.JPanel {
	private JFrame mainFrame = null;

	/** Creates new form ListPanel */
	public ListPanel(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		initComponents();
		myInitData();
	}

	public ListPanel(JFrame mainFrame, List<UserModel> results) {
		this.mainFrame = mainFrame;
		initComponents();

		userLists.setListData(results.toArray());
	}

	/**
	 * 读取所有用户并添加进列表
	 */
	private void myInitData() {
		UserEbi user = UserEbiFactory.getUserEbi();
		List<UserModel> list = user.getAll();
		userLists.setListData(list.toArray());
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		userLists = new javax.swing.JList();
		jLabel1 = new javax.swing.JLabel();
		btnToAdd = new javax.swing.JButton();
		btnToDelete = new javax.swing.JButton();
		btnToUpdate = new javax.swing.JButton();
		btnToQuery = new javax.swing.JButton();

		setMinimumSize(new java.awt.Dimension(800, 600));
		setLayout(null);

		userLists.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane1.setViewportView(userLists);

		add(jScrollPane1);
		jScrollPane1.setBounds(170, 80, 480, 230);

		jLabel1.setFont(new java.awt.Font("Dialog", 1, 48));
		jLabel1.setForeground(new java.awt.Color(204, 0, 51));
		jLabel1.setText("\u7528\u6237\u5217\u8868");
		add(jLabel1);
		jLabel1.setBounds(300, 0, 260, 80);

		btnToAdd.setFont(new java.awt.Font("Dialog", 1, 24));
		btnToAdd.setForeground(new java.awt.Color(0, 102, 102));
		btnToAdd.setText("\u6dfb\u52a0\u7528\u6237");
		btnToAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnToAddActionPerformed(evt);
			}
		});
		add(btnToAdd);
		btnToAdd.setBounds(160, 350, 150, 50);

		btnToDelete.setFont(new java.awt.Font("Dialog", 1, 24));
		btnToDelete.setForeground(new java.awt.Color(0, 102, 102));
		btnToDelete.setText("\u5220\u9664\u7528\u6237");
		btnToDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnToDeleteActionPerformed(evt);
			}
		});
		add(btnToDelete);
		btnToDelete.setBounds(510, 350, 150, 50);

		btnToUpdate.setFont(new java.awt.Font("Dialog", 1, 24));
		btnToUpdate.setForeground(new java.awt.Color(0, 102, 102));
		btnToUpdate.setText("\u4fee\u6539\u7528\u6237");
		btnToUpdate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnToUpdateActionPerformed(evt);
			}
		});
		add(btnToUpdate);
		btnToUpdate.setBounds(160, 450, 150, 50);

		btnToQuery.setFont(new java.awt.Font("Dialog", 1, 24));
		btnToQuery.setForeground(new java.awt.Color(0, 102, 102));
		btnToQuery.setText("\u67e5\u627e\u7528\u6237");
		btnToQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnToQueryActionPerformed(evt);
			}
		});
		add(btnToQuery);
		btnToQuery.setBounds(510, 450, 150, 50);
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnToQueryActionPerformed(java.awt.event.ActionEvent evt) {
		mainFrame.setContentPane(new QueryPanel(mainFrame));
		mainFrame.validate();

	}

	private void btnToUpdateActionPerformed(java.awt.event.ActionEvent evt) {
		UserModel user = (UserModel) userLists.getSelectedValue();

		if (user == null) {
			JOptionPane.showMessageDialog(mainFrame, "请选择要修改的用户!");
			return;
		}

		String uuid = user.getUuid();

		mainFrame.setContentPane(new UpdatePanel(mainFrame, uuid));
		mainFrame.validate();
	}

	private void btnToDeleteActionPerformed(java.awt.event.ActionEvent evt) {
		UserModel user = (UserModel) userLists.getSelectedValue();

		if (user == null) {
			JOptionPane.showMessageDialog(mainFrame, "请选择要删除的用户!");
			return;
		}

		String uuid = user.getUuid();

		mainFrame.setContentPane(new DeletePanel(mainFrame, uuid));
		mainFrame.validate();
	}

	private void btnToAddActionPerformed(java.awt.event.ActionEvent evt) {
		mainFrame.setContentPane(new AddPanel(mainFrame));
		mainFrame.validate();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnToAdd;
	private javax.swing.JButton btnToDelete;
	private javax.swing.JButton btnToQuery;
	private javax.swing.JButton btnToUpdate;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JList userLists;
	// End of variables declaration//GEN-END:variables

}