 // departureDate
        departureDateLabel = new JLabel("Date et heure de départ : ");
        departureDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureDateLabel);

        date = new Date();
        spinnerDateModel = new SpinnerDateModel(date, null, null, Calendar.DATE);
        spinner = new JSpinner(spinnerDateModel);
        JSpinner.DateEditor departureDaySpinner = new JSpinner.DateEditor(spinner, "dd/MM/YYYY");
        spinner.setEditor(departureDaySpinner);
        this.add(departureDaySpinner);

        spinnerDateModel = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        spinner = new JSpinner(spinnerDateModel);
        JSpinner.DateEditor departureTimeSpinner = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(departureTimeSpinner);
        this.add(departureTimeSpinner);
// NEW LINE
        // arrivalDate
        arrivalDateLabel = new JLabel("Date et heure d'arrivée : ");
        arrivalDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalDateLabel);

        date = new Date();

        spinnerDateModel = new SpinnerDateModel(date, null, null, Calendar.DATE);
        spinner = new JSpinner(spinnerDateModel);
        JSpinner.DateEditor arrivalDaySpinner = new JSpinner.DateEditor(spinner, "dd/MM/YYYY");
        spinner.setEditor(arrivalDaySpinner);
        this.add(arrivalDaySpinner);

        spinnerDateModel = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        spinner = new JSpinner(spinnerDateModel);
        JSpinner.DateEditor arrivalTimeSpinner = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(arrivalTimeSpinner);
        this.add(arrivalTimeSpinner);