 Date currentDate = new Date();
        JSpinner departureDate = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        departureDate.setEditor(new JSpinner.DateEditor(departureDate, "dd-MM-yyyy"));

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        this.add(dateLabel);
        this.add(departureDate);

        //...
        JSpinner departureHour = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        departureHour.setEditor(new JSpinner.DateEditor(departureHour, "HH:mm"));

        JLabel departureHourLabel = new JLabel("Heure :");
        departureHourLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        this.add(departureHourLabel);
        this.add(departureHour);

        