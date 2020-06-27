package de.julian.pascalshedreieck.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		/*
		 * addKeyListener(new KeyAdapter() {
		 * 
		 * @Override public void keyTyped(KeyEvent e) { char c = e.getKeyChar(); if
		 * (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c ==
		 * KeyEvent.VK_DELETE) { e.consume(); } } })
		 */;
		setTitle("Pascal's triangle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);

		JButton btnGenerate = new JButton("Generate");

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		JLabel lblAmountOfLines = new JLabel("Amount of lines");

		NumberFormatter formatter = new NumberFormatter(NumberFormat.getIntegerInstance());
		formatter.setValueClass(Integer.class);
		formatter.setAllowsInvalid(false);
		formatter.setMinimum(1);

		JFormattedTextField frmtdtxtfldAmountOfLines = new JFormattedTextField(formatter);

		JLabel lblTheFileWill = new JLabel("The File will be generated in the same folder,");

		JLabel lblYouStartedThis = new JLabel("you started this program from");
		
		JLabel lblDone = new JLabel("Done.");
		lblDone.setVisible(false);
		lblDone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(frmtdtxtfldAmountOfLines, Alignment.LEADING)
							.addComponent(lblAmountOfLines, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblDone))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblYouStartedThis)
								.addComponent(lblTheFileWill)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
							.addComponent(btnGenerate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAmountOfLines)
						.addComponent(lblTheFileWill))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(frmtdtxtfldAmountOfLines, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYouStartedThis))
					.addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnGenerate)
						.addComponent(lblDone)))
		);

		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (frmtdtxtfldAmountOfLines.getText() != null) {
						int num = Integer.parseInt(frmtdtxtfldAmountOfLines.getText());
						Generator.generate(num);
						lblDone.setVisible(true);
						(new Timer()).schedule(new TimerTask() {
							
							@Override
							public void run() {
								System.exit(0);
							}
						}, 1500);
					}
				} catch (NumberFormatException ex) {
				}

			}
		});

		contentPane.setLayout(gl_contentPane);
	}
}
