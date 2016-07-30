/*
 * InQueryPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package cn.hncu.bookStore.in.ui;

import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cn.hncu.bookStore.book.business.factory.BookEbiFactory;
import cn.hncu.bookStore.book.vo.BookModel;
import cn.hncu.bookStore.in.business.factory.InMainEbiFactory;
import cn.hncu.bookStore.in.vo.InDetailModel;
import cn.hncu.bookStore.in.vo.InDetailQueryModel;
import cn.hncu.bookStore.in.vo.InMainModel;
import cn.hncu.bookStore.in.vo.InMainQueryModel;
import cn.hncu.bookStore.user.business.factory.UserEbiFactory;
import cn.hncu.bookStore.user.vo.UserModel;
import cn.hncu.bookStore.util.DateUtil;

/**
 * 
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class InQueryPanel extends javax.swing.JPanel {
	private JFrame mainFrame = null;

	/** Creates new form InQueryPanel 
	 * @param mainFrame */
	public InQueryPanel(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		initComponents();
		myInitData();
	}

	/**
	 * 初始化combo Box的数据
	 */
	private void myInitData() {
		//进货人组合框内数据的初始化
		List<UserModel> listUsers = UserEbiFactory.getUserEbi().getAllIn();
		for (UserModel user : listUsers) {
			combInUser.addItem(user.getName());
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
		tfdInUuid = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		combInUser = new javax.swing.JComboBox();
		jLabel8 = new javax.swing.JLabel();
		tfdInNum = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		tfdInDate = new javax.swing.JTextField();
		jLabel10 = new javax.swing.JLabel();
		tfdInDate2 = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		tfdInNum2 = new javax.swing.JTextField();
		jLabel12 = new javax.swing.JLabel();
		tfdInDetailUuid = new javax.swing.JTextField();
		jLabel13 = new javax.swing.JLabel();
		tfdInSumMoney = new javax.swing.JTextField();
		jLabel14 = new javax.swing.JLabel();
		tfdInSumMoney2 = new javax.swing.JTextField();
		jLabel15 = new javax.swing.JLabel();
		btnQuery = new javax.swing.JButton();
		btnBack = new javax.swing.JButton();

		setMinimumSize(new java.awt.Dimension(800, 600));
		setLayout(null);

		jLabel1.setFont(new java.awt.Font("微软雅黑", 1, 36));
		jLabel1.setForeground(new java.awt.Color(204, 0, 0));
		jLabel1.setText("\u8fdb\u8d27\u67e5\u8be2");
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
		jLabel7.setText("\u8fdb\u8d27\u5355\u7f16\u53f7:");
		add(jLabel7);
		jLabel7.setBounds(100, 90, 100, 30);
		add(tfdInUuid);
		tfdInUuid.setBounds(210, 90, 150, 30);

		jLabel4.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel4.setText("\u8fdb\u8d27\u4eba:");
		add(jLabel4);
		jLabel4.setBounds(440, 90, 80, 30);

		combInUser.setFont(new java.awt.Font("Dialog", 1, 18));
		combInUser.setForeground(new java.awt.Color(204, 204, 0));
		combInUser.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "查询所有" }));
		add(combInUser);
		combInUser.setBounds(510, 90, 200, 30);

		jLabel8.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel8.setText("\u8fdb\u8d27\u6700\u5c0f\u6570\u91cf:");
		add(jLabel8);
		jLabel8.setBounds(80, 320, 120, 30);
		add(tfdInNum);
		tfdInNum.setBounds(210, 320, 150, 30);

		jLabel9.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel9.setText("\u683c\u5f0f\u5982: 2016-04-13");
		add(jLabel9);
		jLabel9.setBounds(190, 190, 180, 30);
		add(tfdInDate);
		tfdInDate.setBounds(210, 160, 150, 30);

		jLabel10.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel10.setText("\u8fdb\u8d27\u622a\u6b62\u65f6\u95f4:");
		add(jLabel10);
		jLabel10.setBounds(390, 160, 120, 30);
		add(tfdInDate2);
		tfdInDate2.setBounds(510, 160, 200, 30);

		jLabel11.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel11.setText("\u8fdb\u8d27\u6700\u5927\u6570\u91cf:");
		add(jLabel11);
		jLabel11.setBounds(390, 320, 120, 30);
		add(tfdInNum2);
		tfdInNum2.setBounds(510, 320, 200, 30);

		jLabel12.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel12.setText("\u8fdb\u8d27\u660e\u7ec6\u7f16\u53f7:");
		add(jLabel12);
		jLabel12.setBounds(80, 240, 120, 30);
		add(tfdInDetailUuid);
		tfdInDetailUuid.setBounds(210, 240, 150, 30);

		jLabel13.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel13.setText("\u8fdb\u8d27\u603b\u4ef7\u6700\u5c0f\u503c:");
		add(jLabel13);
		jLabel13.setBounds(60, 400, 140, 30);
		add(tfdInSumMoney);
		tfdInSumMoney.setBounds(210, 400, 150, 30);

		jLabel14.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel14.setText("\u8fdb\u8d27\u603b\u4ef7\u6700\u5927\u503c:");
		add(jLabel14);
		jLabel14.setBounds(370, 400, 140, 30);
		add(tfdInSumMoney2);
		tfdInSumMoney2.setBounds(510, 400, 200, 30);

		jLabel15.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jLabel15.setText("\u8fdb\u8d27\u8d77\u59cb\u65f6\u95f4:");
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
		btnQuery.setBounds(160, 460, 140, 60);

		btnBack.setFont(new java.awt.Font("Dialog", 1, 36));
		btnBack.setForeground(new java.awt.Color(255, 0, 0));
		btnBack.setText("\u8fd4\u56de");
		btnBack.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBackActionPerformed(evt);
			}
		});
		add(btnBack);
		btnBack.setBounds(480, 460, 140, 60);
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnQueryActionPerformed(java.awt.event.ActionEvent evt) {
		//1收集参数(且验证输入有效性)
		//进货单编号
		String inUuid = tfdInUuid.getText();
		//用户姓名
		String inUserName = combInUser.getSelectedItem().toString();
		//用户ID
		String inUserUuid = null;
		if (combInUser.getSelectedIndex() > 0) {
			inUserUuid = UserEbiFactory.getUserEbi().getUserByName(inUserName)
					.getUuid();
		}

		//进货起始时间
		String txtInDate = tfdInDate.getText();
		long inDate = 0;
		if (txtInDate != null & txtInDate.trim().length() > 0) {
			inDate = DateUtil.string2Long(txtInDate + " 00:00:00",
					"进货起始时间格式错误!");
			if (inDate == -1) {
				return;
			}
		}

		//进货截止时间
		String txtInDate2 = tfdInDate2.getText();
		long inDate2 = 0;
		if (txtInDate2 != null & txtInDate2.trim().length() > 0) {
			inDate2 = DateUtil.string2Long(txtInDate2 + " 23:59:59",
					"进货截止时间格式错误!");
			if (inDate2 == -1) {
				return;
			}
		}

		//进货明细单编号
		String inDetailUuid = tfdInDetailUuid.getText();

		//书的Uuid
		String bookUuid = null;
		if (combBook.getSelectedIndex() > 0) {
			String bookName = combBook.getSelectedItem().toString();
			bookUuid = BookEbiFactory.getBookEbi().getBookByName(bookName)
					.getUuid();

		}

		//进货最小数量
		int sumNum = 0;
		if (tfdInNum.getText() != null
				&& tfdInNum.getText().trim().length() > 0) {
			try {
				sumNum = Integer.parseInt(tfdInNum.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(mainFrame, "进货数量最小值格式错误");
				return;
			}
		}

		//进货最大数量
		int sumNum2 = 0;
		if (tfdInNum2.getText() != null
				&& tfdInNum2.getText().trim().length() > 0) {
			try {
				sumNum2 = Integer.parseInt(tfdInNum2.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(mainFrame, "进货数量最大值格式错误");
				return;
			}
		}

		//进货最小总价
		double sumMoney = 0;
		if (tfdInSumMoney.getText() != null
				&& tfdInSumMoney.getText().trim().length() > 0) {
			try {
				sumMoney = Double.parseDouble(tfdInSumMoney.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(mainFrame, "进货总价最小值格式错误");
				return;
			}
		}

		//进货最大总价
		double sumMoney2 = 0;
		if (tfdInSumMoney2.getText() != null
				&& tfdInSumMoney2.getText().trim().length() > 0) {
			try {
				sumMoney2 = Double.parseDouble(tfdInSumMoney2.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(mainFrame, "进货总价最小值格式错误");
				return;
			}
		}

		//2组织参数----分别组织InMainQueryModel和InDetailQueryModel

		//组织InMainQueryModel
		InMainQueryModel imqm = new InMainQueryModel();
		imqm.setUuid(inUuid);
		imqm.setInUserId(inUserUuid);
		imqm.setInDate(inDate);
		imqm.setInDate2(inDate2);
		//组织InDetailQueryModel
		InDetailQueryModel idqm = new InDetailQueryModel();
		idqm.setUuid(inDetailUuid);
		idqm.setBookId(bookUuid);
		idqm.setSumMoney(sumMoney);
		idqm.setSumMoney2(sumMoney2);
		idqm.setSumNum(sumNum);
		idqm.setSumNum2(sumNum2);

		//3调用逻辑层
		Map<InMainModel, List<InDetailModel>> map = InMainEbiFactory
				.getInMainEbi().getByCondition(imqm, idqm);

		//4返回到结果页面
		mainFrame.setContentPane(new InListPanel(mainFrame, map));
		mainFrame.validate();
	}

	private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
		back();
	}

	private void back() {
		mainFrame.setContentPane(new InListPanel(mainFrame));
		mainFrame.validate();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnBack;
	private javax.swing.JButton btnQuery;
	private javax.swing.JComboBox combBook;
	private javax.swing.JComboBox combInUser;
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
	private javax.swing.JTextField tfdInDate;
	private javax.swing.JTextField tfdInDate2;
	private javax.swing.JTextField tfdInDetailUuid;
	private javax.swing.JTextField tfdInNum;
	private javax.swing.JTextField tfdInNum2;
	private javax.swing.JTextField tfdInSumMoney;
	private javax.swing.JTextField tfdInSumMoney2;
	private javax.swing.JTextField tfdInUuid;
	// End of variables declaration//GEN-END:variables

}