$(document).ready(function() {
	$('#subjectDetailsForm').on('submit', function(e) {
		e.preventDefault();


		// Collecting subject data from here...
		const subjectData = {
			subjectName: $('#subjectName').val(), 		// for getting the input value
		};


		// Sending the subject data to the backend using AJAX
		$.ajax({
			url: '/addSubject',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(subjectData),
			success: function(response) {
				alert(response); 						// Displaying a success message
				window.location.href = '/school-details';
			},
			error: function(_xhr, _status, error) {
				console.error('Error:', error);
				alert('Failed to add subject. Please try again.');
			},
		});
	});
});


//Handling School details from here...
function loadSchoolTable() {
	$.ajax({
		url: '/api/schools',  // Endpoint to get all schools
		method: 'GET',
		success: function(data) {
			// Assuming the table has a tbody where rows will be populated
			const tableBody = $('table tbody');
			tableBody.empty();  // Clear the existing table rows

			// Iterate through the data and append new rows to the table
			data.forEach(function(school, index) {
				const row = `
                    <tr>
                        <td>${index + 1}</td>
                        <td>${school.schoolName}</td>
                        <td>${school.state}</td>
                        <td>${school.city}</td>
                        <td>${school.address}</td>
                        <td>${school.landmark}</td>
                        <td>${school.pincode}</td>
                        <td>${school.studentCount}</td>
                    </tr>
                `;
				tableBody.append(row);  // Append each row to the table
			});
		},
		error: function() {
			alert('Failed to load school data.');
		}
	});
}

$(document).ready(function() {
	$('#schoolDetailsForm').on('submit', function(e) {
		e.preventDefault(); // Prevent the default form submission (page refresh)

		const schoolData = {
			schoolName: $('#schoolName').val(),
			state: $('#state').val(),
			city: $('#city').val(),
			address: $('#address').val(),
			landmark: $('#landmark').val(),
			pincode: $('#pincode').val(),
		};

		$.ajax({
			url: '/api/schools/addSchool', // Correct API endpoint
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(schoolData),
			success: function() {
				alert('School details saved successfully!');
				$('#schoolDetailsForm')[0].reset(); // Reset the form after submission
				loadSchoolTable(); // Reload the school table with the updated data
			},
			error: function(xhr, status, error) {
				console.error('AJAX Error:', status, error);
				console.error('Response:', xhr.responseText);
				alert('Failed to save school details: ' + xhr.responseText);
			},
		});
	});
});









$(document).ready(function() {
	// Function to load subjects into the dropdown
	function loadSubjects() {
		$.ajax({
			url: '/getSubjects',
			method: 'GET',
			success: function(subjects) {
				console.log('Subjects:', subjects); // Debugging: Check subject data
				const subjectsDropdown = $('#subjects');
				subjectsDropdown.empty(); // Clear previous options
				subjectsDropdown.append('<option value="" disabled selected>Select a Subject</option>');
				subjects.forEach((subject) => {
					subjectsDropdown.append(
						`<option value="${subject.id}">${subject.subjectName}</option>` // Use subject.id for value
					);
				});
			},
			error: function(_xhr, _status, error) {
				console.error('Error loading subjects:', error);
				alert('Failed to load subjects. Please try again.');
			},
		});
	}

	// Function to handle student form submission
	$('#DetailsForm').on('submit', function(e) {
		e.preventDefault();

		// Collect student data from the form
		const studentData = {
			firstName: $('#firstName').val(),
			lastName: $('#lastName').val(),
			dateOfBirth: $('#dob').val(),
			schoolName: $('#school option:selected').html(),  // Use 'school' ID to capture the selected school (make sure this is the ID, not the name)
			subject: $('#subjects').val() ? $('#subjects').val().map((id) => ({ id: id })) : [],  // Ensure subjects are an array
		};

		// Debugging: Check if schoolId and subjects are correctly captured
		//console.log('School ID:', studentData.schoolName);
		//('Subjects:', studentData.subjects);

		console.log(studentData);

		$.ajax({
			url: '/addStudent',  // Backend endpoint to add student
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(studentData),  // Convert data to JSON
			success: function() {
				alert('Student added successfully!');  // Success message
				loadStudents();  // Reload the student table
				$('#DetailsForm')[0].reset();  // Clear the form
			},
			error: function(xhr, _status, error) {
				console.error('Error adding student:', xhr.responseText || error);
				alert('Failed to add student. Please check the server logs.');
				if (xhr.responseText) {
					try {
						const errorResponse = JSON.parse(xhr.responseText);
						alert('Error: ' + errorResponse.message);
					} catch (err) {
						console.error('Failed to parse error response:', err);
					}
				}
			},
		});
	});

	// Function to load students data
	function loadStudents() {
		$.ajax({
			url: '/getStudents',  // Backend endpoint for fetching students
			method: 'GET',
			success: function(students) {
				console.log('Students:', students);  // Debugging: Check student data
				const studentTableBody = $('table tbody');
				studentTableBody.empty();  // Clear the table body

				students.forEach((student, index) => {
					const profileLink = `/students/${student.id}/profile.pdf`; // Construct the PDF profile link
					const subjectNames = student.subjects && Array.isArray(student.subjects)
						? student.subjects.map((subject) => subject.subjectName).join(', ')
						: 'No subjects assigned';  // Handle null or undefined subjects

					studentTableBody.append(`
                        <tr>
                            <td>${index + 1}</td>
                            <td>${student.firstName}</td>
                            <td>${student.lastName}</td>
                            <td>${student.dateOfBirth}</td>
                            <td>${student.schoolName}</td>  <!-- Display school name -->
                            <td><a href="${profileLink}" target="_blank" class="btn btn-primary">View</a></td>
                        </tr>
                    `);
				});
			},
			error: function(_xhr, _status, error) {
				console.error('Error loading students:', error);
				alert('Failed to load students. Please try again.');
			},
		});
	}

	// Initial setup: load subjects and students
	loadSubjects();
	loadStudents();
});
