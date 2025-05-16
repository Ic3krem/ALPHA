package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class userWorkout extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel Title, equipmentLabel, setsLabel, trackingLabel, repLabel, date;
	private JComboBox<String> equipment, sets, reps, tracking;
	private JButton addW, deleteW;
	private JTable workoutT;
	private DefaultTableModel tableModel;
	private int trackingIdCounter = 6; // Start after the sample rows

	private final String[][] EQUIPMENT_AND_TYPES = {
		{"Treadmill", "Cardio"},
		{"Exercise Bike", "Cardio"},
		{"Elliptical", "Cardio"},
		{"Bench Press", "Strength Training"},
		{"Squat Rack", "Strength Training"},
		{"Leg Press", "Strength Training"},
		{"Cable Machine", "Strength Training"},
		{"Free Weights", "Strength Training"},
		{"Battle Ropes", "HIIT"},
		{"Resistance Bands", "Flexibility"},
		{"Yoga Mat", "Flexibility"},
		{"Jump Rope", "HIIT"}
	};

	private final String[] CARDIO_TYPES = {"Cardio"};
	private final String[] STRENGTH_TYPES = {"Strength Training"};
	private final String[] HIIT_TYPES = {"HIIT", "Cardio"};
	private final String[] FLEXIBILITY_TYPES = {"Flexibility", "HIIT"};

	private final String[] SETS_OPTIONS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	private final String[] REPS_OPTIONS = {"5", "8", "10", "12", "15", "20", "25", "30"};
	private final String[] DURATION_OPTIONS = {"5", "10", "15", "20", "25", "30", "45", "60"};

	public userWorkout() {
		setSize(800, 800);
		setLayout(null);
		setBackground(Color.WHITE);

		Title = new JLabel("Workout Tracker");
		Title.setForeground(new Color(0, 64, 128));
		Title.setFont(new Font("Arial Unicode MS", Font.BOLD, 40));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(228, 50, 334, 64);

		equipmentLabel = new JLabel("Equipment");
		equipmentLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 24));
		equipmentLabel.setBounds(67, 155, 131, 38);

		setsLabel = new JLabel("Sets");
		setsLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 24));
		setsLabel.setBounds(444, 155, 50, 38);

		trackingLabel = new JLabel("Tracking Type");
		trackingLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 24));
		trackingLabel.setBounds(67, 264, 166, 38);

		repLabel = new JLabel("Repetition");
		repLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 24));
		repLabel.setBounds(444, 264, 126, 38);

		equipment = new JComboBox<>();
		equipment.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
		equipment.setBounds(67, 190, 330, 53);
		equipment.setLightWeightPopupEnabled(false);

		sets = new JComboBox<>(SETS_OPTIONS);
		sets.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
		sets.setBounds(444, 190, 286, 53);
		sets.setLightWeightPopupEnabled(false);

		reps = new JComboBox<>(REPS_OPTIONS);
		reps.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
		reps.setBounds(444, 300, 286, 53);
		reps.setLightWeightPopupEnabled(false);

		tracking = new JComboBox<>();
		tracking.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
		tracking.setBounds(67, 300, 330, 53);
		tracking.setLightWeightPopupEnabled(false);

		addW = new JButton("Add Workout");
		addW.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
		addW.setForeground(Color.WHITE);
		addW.setBackground(new Color(42, 158, 222));
		addW.setBounds(67, 364, 150, 53);

		deleteW = new JButton("Delete Workout");
		deleteW.setForeground(Color.WHITE);
		deleteW.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
		deleteW.setBackground(new Color(233, 0, 32));
		deleteW.setBounds(247, 364, 150, 53);

		date = new JLabel("Today's Date: " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		date.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
		date.setHorizontalAlignment(SwingConstants.CENTER);
		date.setBounds(257, 712, 281, 36);

		String[] equipmentOptions = new String[EQUIPMENT_AND_TYPES.length];
		for (int i = 0; i < EQUIPMENT_AND_TYPES.length; i++) {
			equipmentOptions[i] = EQUIPMENT_AND_TYPES[i][0];
		}
		equipment.setModel(new DefaultComboBoxModel<>(equipmentOptions));

		equipment.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String selected = (String) equipment.getSelectedItem();
				String[] types = getAppropriateTypes(selected);
				tracking.setModel(new DefaultComboBoxModel<>(types));
				updateSetsAndReps(sets, reps, setsLabel, repLabel, types[0]);
			}
		});

		tracking.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				updateSetsAndReps(sets, reps, setsLabel, repLabel, (String) tracking.getSelectedItem());
			}
		});

		// Table setup
		String[] columnNames = {"Tracking ID", "Date", "Equipment", "Workout Info"};
		tableModel = new DefaultTableModel(columnNames, 0);

		// Correct sample data
		tableModel.addRow(new Object[]{"001", "2025-05-10", "Treadmill", "30 minutes"});
		tableModel.addRow(new Object[]{"002", "2025-05-11", "Bench Press", "3 sets × 10 reps"});
		tableModel.addRow(new Object[]{"003", "2025-05-12", "Yoga Mat", "5 sets × 12 reps"});
		tableModel.addRow(new Object[]{"004", "2025-05-13", "Jump Rope", "15 minutes"});
		tableModel.addRow(new Object[]{"005", "2025-05-14", "Cable Machine", "4 sets × 8 reps"});

		workoutT = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(workoutT);
		scrollPane.setBounds(67, 428, 663, 285);
		scrollPane.setBorder(null);
		scrollPane.getViewport().setBackground(Color.WHITE);

		addW.addActionListener(e -> {
			Object[] row = {
				String.format("%03d", trackingIdCounter++),
				LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				equipment.getSelectedItem(),
				((String) tracking.getSelectedItem()).equals("Cardio") ? 
					sets.getSelectedItem() + " minutes" : 
					sets.getSelectedItem() + " sets × " + 
					(reps.isEnabled() ? reps.getSelectedItem() : "N/A") + " reps"
			};
			tableModel.addRow(row);
		});

		deleteW.addActionListener(e -> {
			int selectedRow = workoutT.getSelectedRow();
			if (selectedRow != -1) {
				tableModel.removeRow(selectedRow);
			} else {
				JOptionPane.showMessageDialog(this, "Please select a row to delete", "No Selection", JOptionPane.WARNING_MESSAGE);
			}
		});

		// Add all components
		add(Title);
		add(equipmentLabel);
		add(setsLabel);
		add(trackingLabel);
		add(repLabel);
		add(equipment);
		add(sets);
		add(reps);
		add(tracking);
		add(addW);
		add(deleteW);
		add(scrollPane);
		add(date);
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	private String[] getAppropriateTypes(String equipment) {
		switch (equipment) {
			case "Treadmill":
			case "Exercise Bike":
			case "Elliptical":
				return CARDIO_TYPES;
			case "Bench Press":
			case "Squat Rack":
			case "Leg Press":
			case "Cable Machine":
			case "Free Weights":
				return STRENGTH_TYPES;
			case "Battle Ropes":
			case "Jump Rope":
				return HIIT_TYPES;
			default:
				return FLEXIBILITY_TYPES;
		}
	}

	private void updateSetsAndReps(JComboBox<String> sets, JComboBox<String> reps,
		JLabel setsLabel, JLabel repLabel, String trackingType) {
		if ("Cardio".equals(trackingType)) {
			sets.setModel(new DefaultComboBoxModel<>(DURATION_OPTIONS));
			setsLabel.setText("Duration (min)");
			reps.setEnabled(false);
			repLabel.setEnabled(false);
		} else {
			sets.setModel(new DefaultComboBoxModel<>(SETS_OPTIONS));
			setsLabel.setText("Sets");
			reps.setEnabled(true);
			repLabel.setEnabled(true);
		}
	}
}
