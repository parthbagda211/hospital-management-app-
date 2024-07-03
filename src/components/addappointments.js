import React, { useState } from 'react';
import axios from 'axios';
import { Container, Form, Button, Row, Col, Alert } from 'react-bootstrap';

const AddAppointmentPage = () => {
  const [appointmentDate, setAppointmentDate] = useState('');
  const [appointmentTime, setAppointmentTime] = useState('');
  const [doctorFirstName, setDoctorFirstName] = useState('');
  const [doctorLastName, setDoctorLastName] = useState('');
  const [doctorSpecialization, setDoctorSpecialization] = useState('');
  const [patientId, setPatientId] = useState('');
  const [showSuccessAlert, setShowSuccessAlert] = useState(false);
  const [showErrorAlert, setShowErrorAlert] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Fetch doctor ID based on name or specialization
      const doctorResponse = await axios.get('http://localhost:8080/doctors/getallbyname', {
        params: {
          firstName: doctorFirstName,
          lastName: doctorLastName,
          specialization: doctorSpecialization
        }
      });

      if (doctorResponse.data.length === 0) {
        setErrorMessage('Doctor not found.');
        setShowErrorAlert(true);
        return;
      }

      const doctorId = doctorResponse.data[0].doctor_id;
      console.log(doctorId);

     

      // Check if the doctor is available at the specified date and time
      const appointmentsResponse = await axios.get('http://localhost:8080/appointments/getallapp');

      const doctorAppointments = appointmentsResponse.data.filter(
        appointment => appointment.doctor.doctorId === doctorId
      );
  
      console.log("Appointments for doctor with ID", doctorId, ":", doctorAppointments);

      const isAvailable = !appointmentsResponse.data.some(
        appointment =>
          appointment.doctorId === doctorId &&
          appointment.appointmentDate === appointmentDate &&
          appointment.appointmentTime === appointmentTime
      );

      if (!isAvailable) {
        setErrorMessage('Doctor is not available at the selected time.');
        setShowErrorAlert(true);
        return;
      }

      const newAppointment = {
        appointmentDate,
        appointmentTime,
        doctorId,
        patientId
      };

      await axios.post('http://localhost:8080/appointments/addappointment', newAppointment);
      setShowSuccessAlert(true);
      setShowErrorAlert(false);
      // Reset form fields
      setAppointmentDate('');
      setAppointmentTime('');
      setDoctorFirstName('');
      setDoctorLastName('');
      setDoctorSpecialization('');
      setPatientId('');
    } catch (error) {
      console.error('Error adding appointment:', error);
      setErrorMessage('Error adding appointment.');
      setShowErrorAlert(true);
    }
  };

  return (
    <Container className="my-5">
      <h2 className="mb-4">Add New Appointment</h2>
      {showSuccessAlert && (
        <Alert variant="success" onClose={() => setShowSuccessAlert(false)} dismissible>
          Appointment added successfully!
        </Alert>
      )}
      {showErrorAlert && (
        <Alert variant="danger" onClose={() => setShowErrorAlert(false)} dismissible>
          {errorMessage}
        </Alert>
      )}
      <Form onSubmit={handleSubmit}>
        <Row>
          <Col md={6}>
            <Form.Group controlId="appointmentDate">
              <Form.Label>Appointment Date</Form.Label>
              <Form.Control
                type="date"
                value={appointmentDate}
                onChange={(e) => setAppointmentDate(e.target.value)}
                required
              />
            </Form.Group>
          </Col>
          <Col md={6}>
            <Form.Group controlId="appointmentTime">
              <Form.Label>Appointment Time</Form.Label>
              <Form.Control
                type="time"
                value={appointmentTime}
                onChange={(e) => setAppointmentTime(e.target.value)}
                required
              />
            </Form.Group>
          </Col>
        </Row>
        <Row>
          <Col md={6}>
            <Form.Group controlId="doctorFirstName">
              <Form.Label>Doctor First Name</Form.Label>
              <Form.Control
                type="text"
                value={doctorFirstName}
                onChange={(e) => setDoctorFirstName(e.target.value)}
              />
            </Form.Group>
          </Col>
          <Col md={6}>
            <Form.Group controlId="doctorLastName">
              <Form.Label>Doctor Last Name</Form.Label>
              <Form.Control
                type="text"
                value={doctorLastName}
                onChange={(e) => setDoctorLastName(e.target.value)}
              />
            </Form.Group>
          </Col>
        </Row>
        <Row>
          <Col md={6}>
            <Form.Group controlId="doctorSpecialization">
              <Form.Label>Doctor Specialization</Form.Label>
              <Form.Control
                type="text"
                value={doctorSpecialization}
                onChange={(e) => setDoctorSpecialization(e.target.value)}
              />
            </Form.Group>
          </Col>
        </Row>
        <Row>
          <Col md={6}>
            <Form.Group controlId="patientId">
              <Form.Label>Patient ID</Form.Label>
              <Form.Control
                type="number"
                value={patientId}
                onChange={(e) => setPatientId(e.target.value)}
                required
              />
            </Form.Group>
          </Col>
        </Row>
        <Button variant="primary" type="submit" className="mt-3">
          Add Appointment
        </Button>
      </Form>
    </Container>
  );
};

export default AddAppointmentPage;
