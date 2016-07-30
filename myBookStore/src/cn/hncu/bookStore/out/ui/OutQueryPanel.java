/*
 * OutQueryPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package cn.hncu.bookStore.out.ui;

import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cn.hncu.bookStore.book.business.factory.BookEbiFactory;
import cn.hncu.bookStore.book.vo.BookModel;
import cn.hncu.bookStore.out.business.factory.OutMainEbiFactory;
import cn.hncu.bookStore.out.vo.OutDetailModel;
import cn.hncu.bookStore.out.vo.OutDetailQueryModel;
import cn.hncu.bookStore.out.vo.OutMainModel;
import cn.hncu.bookStore.out.vo.OutMainQueryModel;
import cn.hncu.bookStore.user.business.factory.UserEbiFactory;
import cn.hncu.bookStore.user.vo.UserModel;
import cn.hncu.bookStore.util.DateUtil;

/**
 * 
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class OutQueryPanel extends javax.swing.JPanel {
	private JFrame mainFrame = null;

	/** Creates new form OutQueryPanel 
	 * @param mainFrame */
	public OutQueryPanel(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		initComponents();
		myInitData();
	}

	/**
	 * 初始化combo Box的数据
	 */
	private void myInitData() {
		//销售人组合框内数据的初始化
		List<UserModel> listUsers = UserEbiFactory.getUserEbi().getAllOut();
		for (UserModel user : listUsers) {
			combOutUser.addItem(user.getName());
		}

		//图书组合框内数据的初始化
		List<BookModel> listBooks = BookEbiFactory.getBookEbi().getAll();
		for (BookModel book : listBooks) {
			combBook.addItem(book.getName());
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		combBook = new javax.swing.JComboBox();
		jLabel7 = new javax.swing.JLabel();
		tfdOutUuid = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		combOutUser = new javax.swing.JComboBox();
		jLabel8 = new javax.swing.JLabel();
		tfdOutNum = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		tfdOutDate = new javax.swing.JTextField();
		jLabel10 = new javax.swing.JLabel();
		tfdOutDate2 = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		tfdOutNum2 = new javax.swing.JTextField();
		jLabel12 = new javax.swing.JLabel();
		tfdOutDetailUuid = new javax.swing.JTextField();
		jLabel13 = new javax.swing.JLabel();
		tfdOutSumMoney = new javax.swing.JTextField();
		jLabel14 = new javax.swing.JLabel();
		tfdOutSumMoney2 = new javax.swing.JTextField();
		jLabel15 = new javax.swing.JLabel();
		btnQuery = new javax.swing.JButton();
		btnBack = new javax.swing.JButton();

		setMinimumSize(new java.awt.Dimension(800, 600));
		setLayout(null);

		jLabel1.setFont(new java.awt.Font("微软雅黑", 1, 36));
		jLabel1.setForeground(new java.awt.Color(204, 0, 0));
		jLabel1.setText("\u9500\u552e\u67e5\u8be2");
		add(jLabel1);
		jLabel1.setBounds(290, 10, 170, 70);

		jLabel5.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel5.setText("\u56fe\u4e66:");
		add(jLabel5);
		jLabel5.setBounds(460, 240, 50, 30);

		combBook.setFont(new java.awt.Font("Dialog", 1, 18));
		combBook.setForeground(new java.awt.Color(0, 0, 255));
		combBook.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "查询所有" }));
		add(combBook);
		combBook.setBounds(510, 240, 200, 30);

		jLabel7.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel7.setText("\u9500\u552e\u5355\u7f16\u53f7:");
		add(jLabel7);
		jLabel7.setBounds(100, 90, 100, 30);
		add(tfdOutUuid);
		tfdOutUuid.setBounds(210, 90, 150, 30);

		jLabel4.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel4.setText("\u9500\u552e\u4eba:");
		add(jLabel4);
		jLabel4.setBounds(440, 90, 80, 30);

		combOutUser.setFont(new java.awt.Font("Dialog", 1, 18));
		combOutUser.setForeground(new java.awt.Color(204, 204, 0));
		combOutUser.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "查询所有" }));
		add(combOutUser);
		combOutUser.setBounds(510, 90, 200, 30);

		jLabel8.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel8.setText("\u9500\u552e\u6700\u5c0f\u6570\u91cf:");
		add(jLabel8);
		jLabel8.setBounds(80, 320, 120, 30);
		add(tfdOutNum);
		tfdOutNum.setBounds(210, 320, 150, 30);

		jLabel9.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel9.setText("\u683c\u5f0f\u5982: 2016-04-13");
		add(jLabel9);
		jLabel9.setBounds(190, 190, 180, 30);
		add(tfdOutDate);
		tfdOutDate.setBounds(210, 160, 150, 30);

		jLabel10.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel10.setText("\u9500\u552e\u622a\u6b62\u65f6\u95f4:");
		add(jLabel10);
		jLabel10.setBounds(390, 160, 120, 30);
		add(tfdOutDate2);
		tfdOutDate2.setBounds(510, 160, 200, 30);

		jLabel11.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel11.setText("\u9500\u552e\u6700\u5927\u6570\u91cf:");
		add(jLabel11);
		jLabel11.setBounds(390, 320, 120, 30);
		add(tfdOutNum2);
		tfdOutNum2.setBounds(510, 320, 200, 30);

		jLabel12.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel12.setText("\u9500\u552e\u660e\u7ec6\u7f16\u53f7:");
		add(jLabel12);
		jLabel12.setBounds(80, 240, 120, 30);
		add(tfdOutDetailUuid);
		tfdOutDetailUuid.setBounds(210, 240, 150, 30);

		jLabel13.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel13.setText("\u9500\u552e\u603b\u4ef7\u6700\u5c0f\u503c:");
		add(jLabel13);
		jLabel13.setBounds(60, 400, 140, 30);
		add(tfdOutSumMoney);
		tfdOutSumMoney.setBounds(210, 400, 150, 30);

		jLabel14.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel14.setText("\u9500\u552e\u603b\u4ef7\u6700\u5927\u503c:");
		add(jLabel14);
		jLabel14.setBounds(370, 400, 140, 30);
		add(tfdOutSumMoney2);
		tfdOutSumMoney2.setBounds(510, 400, 200, 30);

		jLabel15.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel15.setText("\u9500\u552e\u8d77\u59cb\u65f6\u95f4:");
		add(jLabel15);
		jLabel15.setBounds(80, 160, 120, 30);

		btnQuery.setFont(new java.awt.Font("Dialog", 1, 36));
		btnQuery.setForeground(new java.awt.Color(255, 0, 0));
		btnQuery.setText("\u67e5\u8be2");
		btnQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnQueryActionPerformed(evt);
			}
		});
		add(btnQuery);
		btnQuery.setBounds(160, 470, 140, 60);

		btnBack.setFont(new java.awt.Font("Dialog", 1, 36));
		btnBack.setForeground(new java.awt.Color(255, 0, 0));
		btnBack.setText("\u8fd4\u56de");
		btnBack.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBackActionPerformed(evt);
			}
		});
		add(btnBack);
		btnBack.setBounds(490, 470, 140, 60);
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnQueryActionPerformed(java.awt.event.ActionEvent evt) {
		//1收集参数(且验证输入有效性)
		//销售单编号
		String outUuid = tfdOutUuid.getText();
		//用户姓名
		String outUserName = combOutUser.getSelectedItem().toString();
		//用户ID
		String outUserUuid = null;
		if (combOutUser.getSelectedIndex() > 0) {
			outUserUuid = UserEbiFactory.getUserEbi()
					.getUserByName(outUserName).getUuid();
		}

		//销售起始时间
		String txtOutDate = tfdOutDate.getText();
		long outDate = 0;
		if (txtOutDate != null & txtOutDate.trim().length() > 0) {
			outDate = DateUtil.string2Long(txtOutDate + " 00:00:00",
					"销售起始时间格式错误!");
			if (outDate == -1) {
				return;
			}
		}

		//销售截止时间
		String txtOutDate2 = tfdOutDate2.getText();
		long outDate2 = 0;
		if (txtOutDate2 != null & txtOutDate2.trim().length() > 0) {
			outDate2 = DateUtil.string2Long(txtOutDate2 + " 23:59:59",
					"销售截止时间格式错误!");
			if (outDate2 == -1) {
				return;
			}
		}

		//销售明细单编号
		String inDetailUuid = tfdOutDetailUuid.getText();

		//书的Uuid
		String bookUuid = null;
		if (combBook.getSelectedIndex() > 0) {
			String bookName = combBook.getSelectedItem().toString();
			bookUuid = BookEbiFactory.getBookEbi().getBookByName(bookName)
					.getUuid();

		}

		//销售最小数量
		int sumNum = 0;
		if (tfdOutNum.getText() != null
				&& tfdOutNum.getText().trim().length() > 0) {
			try {
				sumNum = Integer.parseInt(tfdOutNum.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(mainFrame, "销售数量最小值格式错误");
				return;
			}
		}

		//销售最大数量
		int sumNum2 = 0;
		if (tfdOutNum2.getText() != null
				&& tfdOutNum2.getText().trim().length() > 0) {
			try {
				sumNum2 = Integer.parseInt(tfdOutNum2.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(mainFrame, "销售数量最大值格式错误");
				return;
			}
		}

		//销售最小总价
		double sumMoney = 0;
		if (tfdOutSumMoney.getText() != null
				&& tfdOutSumMoney.getText().trim().length() > 0) {
			try {
				sumMoney = Double.parseDouble(tfdOutSumMoney.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(mainFrame, "销售总价最小值格式错误");
				return;
			}
		}

		//销售最大总价
		double sumMoney2 = 0;
		if (tfdOutSumMoney2.getText() != null
				&& tfdOutSumMoney2.getText().trim().length() > 0) {
			try {
				sumMoney2 = Double.parseDouble(tfdOutSumMoney2.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(mainFrame, "销售总价最小值格式错误");
				return;
			}
		}

		//2组织参数----分别组织OutMainQueryModel和OutDetailQueryModel

		//组织OutMainQueryModel
		OutMainQueryModel omqm = new OutMainQueryModel();
		omqm.setUuid(outUuid);
		omqm.setOutUserId(outUserUuid);
		omqm.setOutDate(outDate);
		omqm.setOutDate2(outDate2);
		//组织OutDetailQueryModel
		OutDetailQueryModel odqm = new OutDetailQueryModel();
		odqm.setUuid(inDetailUuid);
		odqm.setBookId(bookUuid);
		odqm.setSumMoney(sumMoney);
		odqm.setSumMoney2(sumMoney2);
		odqm.setSumNum(sumNum);
		odqm.setSumNum2(sumNum2);

		//3调用逻辑层
		Map<OutMainModel, List<OutDetailModel>> map = OutMainEbiFactory
				.getOutMainEbi().getByCondition(omqm, odqm);

		//4返回到结果页面
		mainFrame.setContentPane(new OutListPanel(mainFrame, map));
		mainFrame.validate();
	}

	private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
		back();
	}

	private void back() {
		mainFrame.setContentPane(new OutListPanel(mainFrame));
		mainFrame.validate();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnBack;
	private javax.swing.JButton btnQuery;
	private javax.swing.JComboBox combBook;
	private javax.swing.JComboBox combOutUser;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JTextField tfdOutDate;
	private javax.swing.JTextField tfdOutDate2;
	private javax.swing.JTextField tfdOutDetailUuid;
	private javax.swing.JTextField tfdOutNum;
	private javax.swing.JTextField tfdOutNum2;
	private javax.swing.JTextField tfdOutSumMoney;
	private javax.swing.JTextField tfdOutSumMoney2;
	private javax.swing.JTextField tfdOutUuid;
	// End of variables declaration//GEN-END:variables

}