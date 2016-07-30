/*
 * OutListPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package cn.hncu.bookStore.out.ui;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;

import cn.hncu.bookStore.out.business.factory.OutMainEbiFactory;
import cn.hncu.bookStore.out.vo.OutDetailModel;
import cn.hncu.bookStore.out.vo.OutMainModel;

/**
 * 
 * @author ³ÂºÆÏè
 *
 * @version 1.0
 */
public class OutListPanel extends javax.swing.JPanel {

	private JFrame mainFrame = null;
	private Map<OutMainModel, List<OutDetailModel>> map;

	/** Creates new form OutListPanel */
	public OutListPanel(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		initComponents();
		map = OutMainEbiFactory.getOutMainEbi().getAll();
		myInitData();
	}

	public OutListPanel(JFrame mainFrame,
			Map<OutMainModel, List<OutDetailModel>> map) {
		this.mainFrame = mainFrame;
		initComponents();
		this.map = map;
		myInitData();
	}

	private void myInitData() {
		jListOutMain.setListData(map.keySet().toArray());
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		btnToAdd = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jListOutMain = new javax.swing.JList();
		jScrollPane2 = new javax.swing.JScrollPane();
		jListOutDetail = new javax.swing.JList();
		jLabel2 = new javax.swing.JLabel();
		btnToQuery = new javax.swing.JButton();

		setMinimumSize(new java.awt.Dimension(800, 600));
		setLayout(null);

		jLabel1.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 1, 48));
		jLabel1.setForeground(new java.awt.Color(204, 0, 0));
		jLabel1.setText("\u9500\u552e\u5217\u8868");
		add(jLabel1);
		jLabel1.setBounds(260, 0, 230, 80);

		btnToAdd.setFont(new java.awt.Font("Dialog", 1, 24));
		btnToAdd.setForeground(new java.awt.Color(0, 204, 204));
		btnToAdd.setText("\u8f6c\u5230\u6dfb\u52a0");
		btnToAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnToAddActionPerformed(evt);
			}
		});
		add(btnToAdd);
		btnToAdd.setBounds(130, 460, 160, 70);

		jListOutMain.setFont(new java.awt.Font("ËÎÌå", 1, 18));
		jListOutMain.setForeground(new java.awt.Color(102, 102, 0));
		jListOutMain.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jListOutMain
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						jListOutMainValueChanged(evt);
					}
				});
		jScrollPane1.setViewportView(jListOutMain);

		add(jScrollPane1);
		jScrollPane1.setBounds(20, 140, 350, 300);

		jListOutDetail.setFont(new java.awt.Font("ËÎÌå", 1, 18));
		jListOutDetail.setForeground(new java.awt.Color(102, 102, 0));
		jListOutDetail.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane2.setViewportView(jListOutDetail);

		add(jScrollPane2);
		jScrollPane2.setBounds(400, 110, 380, 330);

		jLabel2.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabel2.setForeground(new java.awt.Color(51, 0, 204));
		jLabel2.setText("\u9500\u552e\u660e\u7ec6\u5217\u8868\uff1a");
		add(jLabel2);
		jLabel2.setBounds(480, 80, 110, 30);

		btnToQuery.setFont(new java.awt.Font("Dialog", 1, 24));
		btnToQuery.setForeground(new java.awt.Color(0, 204, 204));
		btnToQuery.setText("\u9500\u552e\u67e5\u8be2");
		btnToQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnToQueryActionPerformed(evt);
			}
		});
		add(btnToQuery);
		btnToQuery.setBounds(480, 460, 160, 70);
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnToQueryActionPerformed(java.awt.event.ActionEvent evt) {
		mainFrame.setContentPane(new OutQueryPanel(mainFrame));
		mainFrame.validate();
	}

	protected void jListOutMainValueChanged(ListSelectionEvent evt) {
		OutMainModel inMain = (OutMainModel) jListOutMain.getSelectedValue();
		List<OutDetailModel> details = map.get(inMain);
		//System.out.println(map);
		//System.out.println(inMain);
		jListOutDetail.setListData(details.toArray());
	}

	protected void btnToAddActionPerformed(ActionEvent evt) {
		mainFrame.setContentPane(new OutAddPanel(mainFrame));
		mainFrame.validate();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnToAdd;
	private javax.swing.JButton btnToQuery;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JList jListOutDetail;
	private javax.swing.JList jListOutMain;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	// End of variables declaration//GEN-END:variables

}