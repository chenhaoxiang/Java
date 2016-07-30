/*
 * BookListPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package cn.hncu.bookStore.book.ui;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cn.hncu.bookStore.book.business.factory.BookEbiFactory;
import cn.hncu.bookStore.book.vo.BookModel;

/**
 *
 * @author  __USER__
 */
public class BookListPanel extends javax.swing.JPanel {
	private JFrame mainFrame = null;

	/** Creates new form BookListPanel */
	public BookListPanel(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		initComponents();
		myInitData();
	}

	public BookListPanel(JFrame mainFrame, List<BookModel> results) {
		this.mainFrame=mainFrame;
		initComponents();
		bookLists.setListData(results.toArray());
	}

	private void myInitData() {
		List<BookModel> lists = BookEbiFactory.getBookEbi().getAll();
		bookLists.setListData(lists.toArray());
		
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		bookLists = new javax.swing.JList();
		jLabel1 = new javax.swing.JLabel();
		btnToAdd = new javax.swing.JButton();
		btnToDelete = new javax.swing.JButton();
		btnToUpdate = new javax.swing.JButton();
		btnToQuery = new javax.swing.JButton();

		setMinimumSize(new java.awt.Dimension(800, 600));
		setPreferredSize(new java.awt.Dimension(800, 600));
		setLayout(null);

		bookLists.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane1.setViewportView(bookLists);

		add(jScrollPane1);
		jScrollPane1.setBounds(170, 80, 480, 230);

		jLabel1.setFont(new java.awt.Font("Dialog", 1, 48));
		jLabel1.setForeground(new java.awt.Color(204, 0, 51));
		jLabel1.setText("\u56fe\u4e66\u5217\u8868");
		add(jLabel1);
		jLabel1.setBounds(300, 0, 260, 80);

		btnToAdd.setFont(new java.awt.Font("Dialog", 1, 24));
		btnToAdd.setForeground(new java.awt.Color(0, 102, 102));
		btnToAdd.setText("\u6dfb\u52a0\u56fe\u4e66");
		btnToAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnToAddActionPerformed(evt);
			}
		});
		add(btnToAdd);
		btnToAdd.setBounds(160, 350, 150, 50);

		btnToDelete.setFont(new java.awt.Font("Dialog", 1, 24));
		btnToDelete.setForeground(new java.awt.Color(0, 102, 102));
		btnToDelete.setText("\u5220\u9664\u56fe\u4e66");
		btnToDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnToDeleteActionPerformed(evt);
			}
		});
		add(btnToDelete);
		btnToDelete.setBounds(510, 350, 150, 50);

		btnToUpdate.setFont(new java.awt.Font("Dialog", 1, 24));
		btnToUpdate.setForeground(new java.awt.Color(0, 102, 102));
		btnToUpdate.setText("\u4fee\u6539\u56fe\u4e66");
		btnToUpdate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnToUpdateActionPerformed(evt);
			}
		});
		add(btnToUpdate);
		btnToUpdate.setBounds(160, 450, 150, 50);

		btnToQuery.setFont(new java.awt.Font("Dialog", 1, 24));
		btnToQuery.setForeground(new java.awt.Color(0, 102, 102));
		btnToQuery.setText("\u67e5\u627e\u56fe\u4e66");
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
		mainFrame.setContentPane(new BookQueryPanel(mainFrame));
		mainFrame.validate();
	}

	private void btnToUpdateActionPerformed(java.awt.event.ActionEvent evt) {
		BookModel book = (BookModel) bookLists.getSelectedValue();
		if(book==null){
			JOptionPane.showMessageDialog(mainFrame, "请选择要修改的图书！");
			return ;
		}
		String uuid  = book.getUuid();
		
		mainFrame.setContentPane(new BookUpdatePanel(mainFrame,uuid));
		mainFrame.validate();
		
		
	}

	private void btnToDeleteActionPerformed(java.awt.event.ActionEvent evt) {
		BookModel book = (BookModel) bookLists.getSelectedValue();
		//System.out.println(book);
		if(book==null){
			JOptionPane.showMessageDialog(mainFrame, "请选择要删除的图书！");
			return ;
		}
		String uuid  = book.getUuid();
		
		mainFrame.setContentPane(new BookDeletePanel(mainFrame,uuid));
		mainFrame.validate();
	}

	private void btnToAddActionPerformed(java.awt.event.ActionEvent evt) {
		mainFrame.setContentPane(new BookAddPanel(mainFrame));
		mainFrame.validate();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JList bookLists;
	private javax.swing.JButton btnToAdd;
	private javax.swing.JButton btnToDelete;
	private javax.swing.JButton btnToQuery;
	private javax.swing.JButton btnToUpdate;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	// End of variables declaration//GEN-END:variables

}