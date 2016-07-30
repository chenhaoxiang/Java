/*
 * InListPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package cn.hncu.bookStore.in.ui;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;

import cn.hncu.bookStore.in.business.factory.InMainEbiFactory;
import cn.hncu.bookStore.in.vo.InDetailModel;
import cn.hncu.bookStore.in.vo.InMainModel;

/**
 * 
 * @author ³ÂºÆÏè
 *
 * @version 1.0
 */
public class InListPanel extends javax.swing.JPanel {

	private JFrame mainFrame = null;
	private Map<InMainModel, List<InDetailModel>> map;

	/** Creates new form InListPanel */
	public InListPanel(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		initComponents();
		map = InMainEbiFactory.getInMainEbi().getAll();
		myInitData();
	}

	public InListPanel(JFrame mainFrame,
			Map<InMainModel, List<InDetailModel>> map) {
		this.mainFrame = mainFrame;
		initComponents();
		this.map = map;
		myInitData();
	}

	private void myInitData() {
		jListInMain.setListData(map.keySet().toArray());
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		btnToAdd = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jListInMain = new javax.swing.JList();
		jScrollPane2 = new javax.swing.JScrollPane();
		jListInDetail = new javax.swing.JList();
		jLabel2 = new javax.swing.JLabel();
		btnToQuery = new javax.swing.JButton();

		setMinimumSize(new java.awt.Dimension(800, 600));
		setLayout(null);

		jLabel1.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 1, 48));
		jLabel1.setForeground(new java.awt.Color(204, 0, 0));
		jLabel1.setText("\u8fdb\u8d27\u5217\u8868");
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

		jListInMain.setFont(new java.awt.Font("ËÎÌå", 1, 18));
		jListInMain.setForeground(new java.awt.Color(102, 102, 0));
		jListInMain.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jListInMain
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						jListInMainValueChanged(evt);
					}
				});
		jScrollPane1.setViewportView(jListInMain);

		add(jScrollPane1);
		jScrollPane1.setBounds(20, 140, 350, 300);

		jListInDetail.setFont(new java.awt.Font("ËÎÌå", 1, 18));
		jListInDetail.setForeground(new java.awt.Color(102, 102, 0));
		jListInDetail.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane2.setViewportView(jListInDetail);

		add(jScrollPane2);
		jScrollPane2.setBounds(400, 110, 380, 330);

		jLabel2.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabel2.setForeground(new java.awt.Color(51, 0, 204));
		jLabel2.setText("\u8fdb\u8d27\u660e\u7ec6\u5217\u8868\uff1a");
		add(jLabel2);
		jLabel2.setBounds(480, 80, 110, 30);

		btnToQuery.setFont(new java.awt.Font("Dialog", 1, 24));
		btnToQuery.setForeground(new java.awt.Color(0, 204, 204));
		btnToQuery.setText("\u8fdb\u8d27\u67e5\u8be2");
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
		mainFrame.setContentPane(new InQueryPanel(mainFrame));
		mainFrame.validate();
	}

	protected void jListInMainValueChanged(ListSelectionEvent evt) {
		InMainModel inMain = (InMainModel) jListInMain.getSelectedValue();
		List<InDetailModel> details = map.get(inMain);
		//System.out.println(map);
		//System.out.println(inMain);
		jListInDetail.setListData(details.toArray());
	}

	protected void btnToAddActionPerformed(ActionEvent evt) {
		mainFrame.setContentPane(new InAddPanel(mainFrame));
		mainFrame.validate();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnToAdd;
	private javax.swing.JButton btnToQuery;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JList jListInDetail;
	private javax.swing.JList jListInMain;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	// End of variables declaration//GEN-END:variables

}