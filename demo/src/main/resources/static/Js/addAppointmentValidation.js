function validateForm() {
        // Check if any field is empty
        var dateValue = document.getElementById("date").value;
        var startHrValue = document.getElementById("startHr").value;
        var endHrValue = document.getElementById("endHr").value;

        if (dateValue.trim() === "" || startHrValue.trim() === "" || endHrValue.trim() === "") {
            showError("date-error", "All fields are required");
            return false;
        } else {
            clearError("date-error");
        }

        // Continue with other validations

        // Validate if selected date is before today's date
        var selectedDate = new Date(dateValue);
        var today = new Date();

        // Set time to 00:00:00 for both dates to compare only dates
        selectedDate.setHours(0, 0, 0, 0);
        today.setHours(0, 0, 0, 0);

        if (selectedDate < today) {
            showError("date-error", "Preferred date cannot be before today's date");
            return false;
        } else {
            clearError("date-error");
        }

        // Validate start and end time
        if (startHrValue >= endHrValue) {
            showError("endHr-error", "End time must be after start time");
            return false;
        } else {
            clearError("endHr-error");
        }

        // Additional validations can be added here
        
        return true; // Submit the form if all validations pass
    }

    function showError(elementId, errorMessage) {
        var errorElement = document.getElementById(elementId);
        errorElement.textContent = errorMessage;
        errorElement.style.display = "block"; // Display the error message
    }

    function clearError(elementId) {
        var errorElement = document.getElementById(elementId);
        errorElement.textContent = ""; // Clear the error message
        errorElement.style.display = "none"; // Hide the error message
    }