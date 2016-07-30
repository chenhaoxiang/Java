/*
 * BookStore.java
 *
 * Created on __DATE__, __TIME__
 */

package cn.hncu.bookStore;

import java.awt.Dimension;
import java.awt.Toolkit;

import cn.hncu.bookStore.book.ui.BookListPanel;
import cn.hncu.bookStore.in.ui.InListPanel;
import cn.hncu.bookStore.out.ui.OutListPanel;
import cn.hncu.bookStore.user.ui.ListPanel;

/**
 *
 * @author  陈浩翔
 */
public class BookStore extends javax.swing.JFrame {

	/** Creates new form BookStore */
	public BookStore() {
		super("书店管理系统---CHX---联系QQ——619699629");
		initComponents();
		//this.setContentPane(new ListPanel(this));
		this.setResizable(false);//不能缩放
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(scr.width / 5, scr.height / 5);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		openMenuItem = new javax.swing.JMenuItem();
		saveMenuItem = new javax.swing.JMenuItem();
		saveAsMenuItem = new javax.swing.JMenuItem();
		exitMenuItem = new javax.swing.JMenuItem();
		editMenu = new javax.swing.JMenu();
		cutMenuItem = new javax.swing.JMenuItem();
		copyMenuItem = new javax.swing.JMenuItem();
		pasteMenuItem = new javax.swing.JMenuItem();
		deleteMenuItem = new javax.swing.JMenuItem();
		helpMenu = new javax.swing.JMenu();
		contentsMenuItem = new javax.swing.JMenuItem();
		aboutMenuItem = new javax.swing.JMenuItem();
		jMenu1 = new javax.swing.JMenu();
		jMenuItemUser = new javax.swing.JMenuItem();
		jMenuItemBook = new javax.swing.JMenuItem();
		jMenuItem2 = new javax.swing.JMenuItem();
		jMenuItem3 = new javax.swing.JMenuItem();
		jMenuItem1 = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(800, 600));

		jLabel2.setFont(new java.awt.Font("Dialog", 1, 48));
		jLabel2.setForeground(new java.awt.Color(204, 0, 0));
		jLabel2.setText("\u4e66\u5e97\u7ba1\u7406\u7cfb\u7edf");

		fileMenu.setText("File");

		openMenuItem.setText("Open");
		fileMenu.add(openMenuItem);

		saveMenuItem.setText("Save");
		fileMenu.add(saveMenuItem);

		saveAsMenuItem.setText("Save As ...");
		fileMenu.add(saveAsMenuItem);

		exitMenuItem.setText("Exit");
		exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		editMenu.setText("Edit");

		cutMenuItem.setText("Cut");
		editMenu.add(cutMenuItem);

		copyMenuItem.setText("Copy");
		editMenu.add(copyMenuItem);

		pasteMenuItem.setText("Paste");
		editMenu.add(pasteMenuItem);

		deleteMenuItem.setText("Delete");
		editMenu.add(deleteMenuItem);

		menuBar.add(editMenu);

		helpMenu.setText("Help");

		contentsMenuItem.setText("Contents");
		helpMenu.add(contentsMenuItem);

		aboutMenuItem.setText("About");
		helpMenu.add(aboutMenuItem);

		menuBar.add(helpMenu);

		jMenu1.setForeground(new java.awt.Color(204, 0, 0));
		jMenu1.setText("\u6a21\u5757");

		jMenuItemUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_U,
				java.awt.event.InputEvent.CTRL_MASK));
		jMenuItemUser.setFont(new java.awt.Font("Dialog", 1, 14));
		jMenuItemUser.setForeground(new java.awt.Color(0, 204, 0));
		jMenuItemUser.setText("\u7528\u6237");
		jMenuItemUser.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemUserActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItemUser);

		jMenuItemBook.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_B,
				java.awt.event.InputEvent.CTRL_MASK));
		jMenuItemBook.setFont(new java.awt.Font("Dialog", 1, 14));
		jMenuItemBook.setForeground(new java.awt.Color(0, 204, 51));
		jMenuItemBook.setText("\u56fe\u4e66");
		jMenuItemBook.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemBookActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItemBook);

		jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_I,
				java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem2.setFont(new java.awt.Font("Dialog", 1, 14));
		jMenuItem2.setForeground(new java.awt.Color(0, 204, 0));
		jMenuItem2.setText("\u8fdb\u8d27");
		jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem2ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem2);

		jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_O,
				java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem3.setFont(new java.awt.Font("Dialog", 1, 14));
		jMenuItem3.setForeground(new java.awt.Color(0, 204, 0));
		jMenuItem3.setText("\u9500\u552e");
		jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem3ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem3);

		jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_DELETE,
				java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem1.setFont(new java.awt.Font("Dialog", 1, 14));
		jMenuItem1.setForeground(new java.awt.Color(0, 204, 0));
		jMenuItem1.setText("\u6ce8\u9500");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem1);

		menuBar.add(jMenu1);

		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(214,
																		214,
																		214)
																.addComponent(
																		jLabel2,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		320,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(34, 34,
																		34)
																.addComponent(
																		jLabel1)))
								.addContainerGap(307, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										119,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										358, Short.MAX_VALUE)
								.addComponent(jLabel1).addGap(12, 12, 12)));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents
	
	/**
	 * 监听进入销售模块
	 * @param evt
	 */
	private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
		this.setContentPane(new OutListPanel(this));
		this.validate();
	}

	/**
	 * 监听进入进货列表
	 * @param evt
	 */
	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
		this.setContentPane(new InListPanel(this));
		this.validate();
	}

	/**
	 * 注销菜单项
	 * @param evt
	 */
	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
		initComponents();
		this.validate();
	}

	/**
	 * 监听进入图书模块
	 * @param evt
	 */
	private void jMenuItemBookActionPerformed(java.awt.event.ActionEvent evt) {
		this.setContentPane(new BookListPanel(this));
		this.validate();
	}

	/**
	 * 监听进入用户模块
	 * @param evt
	 */
	private void jMenuItemUserActionPerformed(java.awt.event.ActionEvent evt) {
		this.setContentPane(new ListPanel(this));
		this.validate();

	}

	private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
		System.exit(0);
	}//GEN-LAST:event_exitMenuItemActionPerformed

	/**
	 * @param 主函数 args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BookStore().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JMenuItem aboutMenuItem;
	private javax.swing.JMenuItem contentsMenuItem;
	private javax.swing.JMenuItem copyMenuItem;
	private javax.swing.JMenuItem cutMenuItem;
	private javax.swing.JMenuItem deleteMenuItem;
	private javax.swing.JMenu editMenu;
	private javax.swing.JMenuItem exitMenuItem;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JMenu helpMenu;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JMenuItem jMenuItem3;
	private javax.swing.JMenuItem jMenuItemBook;
	private javax.swing.JMenuItem jMenuItemUser;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenuItem openMenuItem;
	private javax.swing.JMenuItem pasteMenuItem;
	private javax.swing.JMenuItem saveAsMenuItem;
	private javax.swing.JMenuItem saveMenuItem;
	// End of variables declaration//GEN-END:variables

}